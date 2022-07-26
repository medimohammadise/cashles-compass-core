package com.ecanteen.service.impl;


import com.ecanteen.domain.Student;
import com.ecanteen.domain.enumeration.ROLE;
import com.ecanteen.repository.StudentRepository;

import com.ecanteen.service.dto.StudentDTO;
import com.ecanteen.service.mapper.ParentMapper;
import com.ecanteen.service.mapper.StudentMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;


import static org.assertj.core.api.Assertions.assertThat;

public class StudentServiceImplTest {

    /***
     * Unit Test for Student Api
     */


    //create some data for test instead of talk to database using repository.

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

    private static final Random random = new Random();

    private static final AtomicLong count = new AtomicLong(random.nextInt() + (2L * Integer.MAX_VALUE));



    //Provide dependencies of StudentService
    private ParentMapper.StudentServiceImpl studentServiceImpl;

    private StudentRepository studentRepository;

    private EntityManager em;


    private MockMvc mockMvc;


    private StudentMapper studentMapper;

    private Student student;


    //setUp instance of dependecies once for each of tests method

    @BeforeEach
    void setUp() {
        student = createEntity(em);
        studentRepository = Mockito.mock(StudentRepository.class);
        studentMapper = Mockito.mock(StudentMapper.class);
        studentServiceImpl = new ParentMapper.StudentServiceImpl(studentRepository, studentMapper);
    }


    //create data for test

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




    @Test
    void shouldReturnAllStudents() {

        //we have three sections for tets


        //arrange
        //in this section we prepare required things
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Student> studentPage = new PageImpl<>(studentList, pageable, studentList.size());
        StudentDTO studentDTO1 = new StudentDTO();
        studentDTO1.setId(1L);
        StudentDTO studentDTO2 = new StudentDTO();
        studentDTO2.setId(2L);


        //act
        //in this section we invoke method of services for test
        List<StudentDTO> studentDTOList = new ArrayList<>();
        studentDTOList.add(studentDTO1);
        studentDTOList.add(studentDTO2);

        //asser
        //and finally we write this section To get the result we expect

        Mockito.when(studentMapper.toDto(student)).thenReturn(studentDTO1);
        Mockito.when(studentMapper.toDto(student)).thenReturn(studentDTO2);
        Mockito.when(studentRepository.findAll(pageable)).thenReturn(studentPage);

        assertThat(studentServiceImpl.findAll(pageable)).hasSize(2);
    }

    @Test
    void shouldCreateStudents() {
        StudentDTO studentDTO = org.mapstruct.factory.Mappers.getMapper(StudentMapper.class).toDto(student);
        Mockito.when(studentMapper.toDto(student)).thenReturn(studentDTO);
        Mockito.when(studentRepository.save(Mockito.any(Student.class)))
            .thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void shouldUpdateStudent() {

        studentRepository.saveAndFlush(student);
        int databaseSizeBeforeUpdate = studentRepository.findAll().size();

        Student partialUpdatedStudent = new Student();
        partialUpdatedStudent.setId(student.getId());

        partialUpdatedStudent.setFullName(UPDATED_FULLNAME);
        partialUpdatedStudent.setAddress(UPDATED_ADDRESS);
        partialUpdatedStudent.setEmailVerified(UPDATED_EMAIL_VERIFIED);
        partialUpdatedStudent.setPhoneVerified(UPDATED_PHONE_VERIFIED);
        partialUpdatedStudent.setImageUrl(UPDATED_IMAGEURL);
        partialUpdatedStudent.setEnabled(UPDATED_ISENABLED);
        partialUpdatedStudent.setEmail(UPDATED_EMAIL);
        partialUpdatedStudent.setPhoneNumber(UPDATED_PHONE_NUMBER);
        partialUpdatedStudent.setKkUesId(UPDATED_KK_USE_ID);
        partialUpdatedStudent.setCreatedDate(UPDATED_CREATED_DATE);
        partialUpdatedStudent.setModifiedDate(UPDATED_MODIFIED_DATE);
        partialUpdatedStudent.setCreatedBy(UPDATED_CREATED_BY);
        partialUpdatedStudent.setModifiedBy(UPDATED_MODIFIED_BY);
        partialUpdatedStudent.setRole(UPDATED_ROLE);

        StudentDTO studentDTO = org.mapstruct.factory.Mappers.getMapper(StudentMapper.class).toDto(partialUpdatedStudent);
        Mockito.when(studentMapper.toDto(partialUpdatedStudent)).thenReturn(studentDTO);
        Mockito.when(studentRepository.save(Mockito.any(Student.class)))
            .thenAnswer(i -> i.getArguments()[0]);

        assertThat(studentRepository.findAll().size()).isEqualTo(databaseSizeBeforeUpdate);
    }

}
