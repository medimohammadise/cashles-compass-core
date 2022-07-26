package com.ecanteen.service.impl;

import com.ecanteen.domain.Parent;
 import com.ecanteen.domain.enumeration.ROLE;
import com.ecanteen.repository.ParentRepository;
 import com.ecanteen.service.dto.ParentDTO;
 import com.ecanteen.service.mapper.ParentMapper;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ParentServiceIntTest {

    private static final String DEFAULT_FULLNAME = "AAAAAAAAAA";

    private static final String UPDATED_FULLNAME = "BBBBBBBBBB";
    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";
    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";
    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGEURL = "AAAAAAAAAA";

    private static final String UPDATED_IMAGEURL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ISENABLED = true;

    private static final Boolean UPDATED_ISENABLED = true;

    private static final Boolean DEFAULT_PHONE_VERIFIED = true;

    private static final Boolean UPDATED_PHONE_VERIFIED = true;

    private static final Boolean DEFAULT_EMAIL_VERIFIED = true;

    private static final Boolean UPDATED_EMAIL_VERIFIED = true;
    private static final String DEFAULT_KK_USE_ID = "AAAAAAAAAA";
    private static final String UPDATED_KK_USE_ID = "BBBBBBBBBB";
    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";
    private static final String DEFAULT_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIED_BY = "BBBBBBBBBB";

    private static final ROLE DEFAULT_ROLE = ROLE.ROLE_PARENT;
    private static final ROLE UPDATED_ROLE = ROLE.ROLE_PARENT;

    private Parent parent;

    private ParentRepository parentRepository;

    private ParentMapper parentMapper;
    private EntityManager em;
    @Autowired
    private MockMvc mockMvc;


    public static Parent createEntity(EntityManager em) {
        Parent parent = new Parent();
        parent.setFullName(DEFAULT_FULLNAME);
        parent.setEmail(DEFAULT_EMAIL);
        parent.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        parent.setImageUrl(DEFAULT_IMAGEURL);
        parent.setAddress(DEFAULT_ADDRESS);
        parent.setEnabled(DEFAULT_ISENABLED);
        parent.setPhoneVerified(DEFAULT_PHONE_VERIFIED);
        parent.setEmailVerified(DEFAULT_EMAIL_VERIFIED);
        parent.setCreatedDate(DEFAULT_CREATED_DATE);
        parent.setKkUesId(DEFAULT_KK_USE_ID);
        parent.setModifiedDate(DEFAULT_MODIFIED_DATE);
        parent.setCreatedBy(DEFAULT_CREATED_BY);
        parent.setModifiedBy(DEFAULT_MODIFIED_BY);
        parent.setRole(DEFAULT_ROLE);
        return parent;
    }

    @BeforeEach
    void setUp() {
        parent = createEntity(em);
    }


    @Test
    void shouldReturnAllParents() throws Exception {
        mockMvc.perform(get("/api/parents"))
            .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateParents() throws Exception {
        parentRepository.saveAndFlush(parent);
         Parent updatedParent = parentRepository.findById(parent.getId()).get();
         em.detach(updatedParent);
        updatedParent
            .fullName(UPDATED_FULLNAME)
            .address(UPDATED_ADDRESS)
            .emailVerified(UPDATED_EMAIL_VERIFIED)
            .phoneVerified(UPDATED_PHONE_VERIFIED)
            .imageUrl(UPDATED_IMAGEURL)
            .enabled(UPDATED_ISENABLED)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .kkUesId(UPDATED_KK_USE_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .role(String.valueOf(UPDATED_ROLE));
        ParentDTO ParentDTO = parentMapper.toDto(updatedParent);

        mockMvc.perform( MockMvcRequestBuilders
                .put("/api/parents/{id}", 14)
                .content(TestUtil.convertObjectToJsonBytes(updatedParent))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
