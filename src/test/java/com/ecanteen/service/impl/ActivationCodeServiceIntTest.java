package com.ecanteen.service.impl;


import com.ecanteen.domain.ActivationCode;
 import com.ecanteen.repository.ActivationCodeRepository;
 import com.ecanteen.service.ActivationCodeServiceImpl;
import com.ecanteen.service.dto.ActivationCodeDTO;
 import com.ecanteen.service.mapper.ActivationCodeMapper;
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
public class ActivationCodeServiceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";

    private static final String UPDATED_CODE = "BBBBBBBBBB";
    private static final String DEFAULT_EXPIRYTIME = "AAAAAAAAAA";

    private static final String UPDATED_EXPIRYTIME = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";

    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);


    private static final Random random = new Random();

    private static final AtomicLong count = new AtomicLong(random.nextInt() + (2L * Integer.MAX_VALUE));




    private ActivationCodeServiceImpl activationCodeServiceImpl;

    private ActivationCodeRepository activationCodeRepository;


    private ActivationCodeMapper activationCodeMapper;

    private ActivationCode activationCode;


    private EntityManager em;
    @Autowired
    private MockMvc mockMvc;


    public static ActivationCode createEntity(EntityManager em) {
        ActivationCode activationCode = new ActivationCode();

        activationCode.setCode(DEFAULT_CODE);
        activationCode.setCreatedBy(DEFAULT_CREATED_BY);
        activationCode.setCreatedDate(DEFAULT_CREATED_DATE);
        activationCode.setExpiryTime(DEFAULT_EXPIRYTIME);

        return activationCode;
    }

    @BeforeEach
    void setUp() {
        activationCode = createEntity(em);
    }


    @Test
    void shouldReturnAllActivationCodes() throws Exception {
        mockMvc.perform(get("/api/activationCodes"))
            .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateActivationCode() throws Exception {
        activationCodeRepository.saveAndFlush(activationCode);
        ActivationCode updatedActivationCode = activationCodeRepository.findById(activationCode.getId()).get();
        em.detach(updatedActivationCode);
        updatedActivationCode.expiryTime(UPDATED_EXPIRYTIME)
             .code(UPDATED_CODE)
            .createdDate(UPDATED_CREATED_DATE).createdBy(UPDATED_CREATED_BY);

        ActivationCodeDTO activationCodeDTO = activationCodeMapper.toDto(updatedActivationCode);

        mockMvc.perform( MockMvcRequestBuilders
                .put("/api/activationCodes/{id}", 14)
                .content(TestUtil.convertObjectToJsonBytes(updatedActivationCode))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
