package com.ecanteen.service.impl;


import com.ecanteen.domain.Student;
import com.ecanteen.domain.enumeration.ROLE;

import com.ecanteen.repository.StudentRepository;


import com.ecanteen.service.dto.StudentDTO;
import com.ecanteen.service.mapper.StudentMapper;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StudentServiceIntTest {
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

    private static final ROLE DEFAULT_ROLE = ROLE.ROLE_STUDENT;

    private static final ROLE UPDATED_ROLE = ROLE.ROLE_STUDENT;

    private Student student;

    private StudentRepository studentRepository;

    private StudentMapper studentMapper;
    private EntityManager em;
    @Autowired
    private MockMvc mockMvc;

    /**
     * Create an entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Student createEntity(EntityManager em) {
        Student student = new Student();
        student.setFullName(DEFAULT_FULLNAME);
        student.setEmail(DEFAULT_EMAIL);
        student.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        student.setImageUrl(DEFAULT_IMAGEURL);
        student.setAddress(DEFAULT_ADDRESS);
        student.setEnabled(DEFAULT_ISENABLED);
        student.setPhoneVerified(DEFAULT_PHONE_VERIFIED);
        student.setEmailVerified(DEFAULT_EMAIL_VERIFIED);
        student.setCreatedDate(DEFAULT_CREATED_DATE);
        student.setKkUesId(DEFAULT_KK_USE_ID);
        student.setModifiedDate(DEFAULT_MODIFIED_DATE);
        student.setCreatedBy(DEFAULT_CREATED_BY);
        student.setModifiedBy(DEFAULT_MODIFIED_BY);
        student.setRole(DEFAULT_ROLE);
        return student;
    }

    @BeforeEach
    void setUp() {
        student = createEntity(em);
    }


    @Test
    void shouldReturnAllStudents() throws Exception {
        mockMvc.perform(get("/api/students"))
            .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateStudent() throws Exception {
        studentRepository.saveAndFlush(student);
        // Update the student
        Student updatedStudent = studentRepository.findById(student.getId()).get();
        // Disconnect from session so that the updates on updatedStudent are not directly saved in db
        em.detach(updatedStudent);
        updatedStudent
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
        StudentDTO studentDTO = studentMapper.toDto(updatedStudent);

        mockMvc.perform( MockMvcRequestBuilders
                .put("/api/students/{id}", 14)
                .content(TestUtil.convertObjectToJsonBytes(updatedStudent))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
