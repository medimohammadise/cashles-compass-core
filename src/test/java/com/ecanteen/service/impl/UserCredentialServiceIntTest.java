package com.ecanteen.service.impl;


import com.ecanteen.domain.UserCredential;
import com.ecanteen.repository.UserCredentialRepository;
import com.ecanteen.service.UserCredentialServiceImpl;
import com.ecanteen.service.dto.UserCredentialDTO;
import com.ecanteen.service.mapper.UserCredentialMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserCredentialServiceIntTest {

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";

    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Random random = new Random();

    private static final AtomicLong count = new AtomicLong(random.nextInt() + (2L * Integer.MAX_VALUE));


    private UserCredentialServiceImpl userCredentialServiceImpl;

    private UserCredentialRepository userCredentialRepository;

    private UserCredentialMapper userCredentialMapper;

    private UserCredential userCredential;

    private EntityManager em;
    @Autowired
    private MockMvc mockMvc;


    public static UserCredential createEntity(EntityManager em) {
        UserCredential userCredential = new UserCredential();

        userCredential.setUserName(DEFAULT_USERNAME);
        userCredential.setCreatedDate(DEFAULT_CREATED_DATE);

        return userCredential;
    }


    @BeforeEach
    void setUp() {
        userCredential = createEntity(em);
    }


    @Test
    void shouldReturnAllUserCredentials() throws Exception {
        mockMvc.perform(get("/api/userCredential"))
            .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateUserCredential() throws Exception {
        userCredentialRepository.saveAndFlush(userCredential);
        UserCredential updatedUserCredential = userCredentialRepository.findById(userCredential.getId()).get();
        em.detach(updatedUserCredential);
        updatedUserCredential.userName(UPDATED_USERNAME).createdDate(UPDATED_CREATED_DATE);

        UserCredentialDTO userCredentialDTO = userCredentialMapper.toDto(updatedUserCredential);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/userCredentials/{id}", 14)
                .content(TestUtil.convertObjectToJsonBytes(updatedUserCredential))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
