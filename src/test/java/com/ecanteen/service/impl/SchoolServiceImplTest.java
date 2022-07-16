package com.ecanteen.service.impl;

import com.ecanteen.domain.School;
import com.ecanteen.repository.SchoolRepository;
import com.ecanteen.service.dto.SchoolDTO;
import com.ecanteen.service.mapper.SchoolMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import static org.assertj.core.api.Assertions.assertThat;

public class SchoolServiceImplTest {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";
    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";
    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";
    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";
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
    private SchoolServiceImpl schoolServiceImpl;
    private SchoolRepository schoolRepository;
    private EntityManager em;
    private MockMvc mockMvc;
    private SchoolMapper schoolMapper;
    private School school;

    /**
     * Create an entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static School createEntity(EntityManager em) {
        School school = new School();
        school.setName(DEFAULT_NAME);
        school.setEmail(DEFAULT_EMAIL);
        school.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        school.setAddress(DEFAULT_ADDRESS);
        school.setCreatedDate(DEFAULT_CREATED_DATE);
        school.setKkUseId(DEFAULT_KK_USE_ID);
        school.setModifiedDate(DEFAULT_MODIFIED_DATE);
        school.setCreatedBy(DEFAULT_CREATED_BY);
        school.setModifiedBy(DEFAULT_MODIFIED_BY);
        return school;
    }


    // with this annotation each test would be a SchoolServiceImpl instance
    @BeforeEach
    void setUp() {
        school = createEntity(em);
        schoolRepository = Mockito.mock(SchoolRepository.class);
        schoolMapper = Mockito.mock(SchoolMapper.class);
        schoolServiceImpl = new SchoolServiceImpl(schoolRepository, schoolMapper);
    }

    //   Unit test for getting all schools using Mockito
    @Test
    void shouldReturnAllSchools() {

//        Arrange:setup the required prerequisites

        List<School> schoolList = new ArrayList<>();
        schoolList.add(school);
        schoolList.add(school);
        Pageable pageable = PageRequest.of(0, 5);
        Page<School> schoolPage = new PageImpl<>(schoolList, pageable, schoolList.size());
        SchoolDTO schoolDto1 = new SchoolDTO();
        schoolDto1.setId(1L);
        SchoolDTO schoolDto2 = new SchoolDTO();
        schoolDto2.setId(2L);

        //      Act:invoke business logic we want to test

        List<SchoolDTO> schoolDTOList = new ArrayList<>();
        schoolDTOList.add(schoolDto1);
        schoolDTOList.add(schoolDto2);
//        Page<SchoolDTO> schoolDTOPage = new PageImpl<>(schoolDTOList, pageable, schoolDTOList.size());

        Mockito.when(schoolMapper.toDto(school)).thenReturn(schoolDto1);
        Mockito.when(schoolMapper.toDto(school)).thenReturn(schoolDto2);
        Mockito.when(schoolRepository.findAll(pageable)).thenReturn(schoolPage);

//        Assertion:verify whatever the criteria we are checking for it

        assertThat(schoolServiceImpl.findAll(pageable)).hasSize(2);
    }

    //    Unit test to create a school
    @Test
    void shouldCreateSchool() {
        SchoolDTO schoolDTO = org.mapstruct.factory.Mappers.getMapper(SchoolMapper.class).toDto(school);
        Mockito.when(schoolMapper.toDto(school)).thenReturn(schoolDTO);
        Mockito.when(schoolRepository.save(Mockito.any(School.class)))
            .thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void shouldUpdateSchool() {
        // Initialize the database
        schoolRepository.saveAndFlush(school);
        int databaseSizeBeforeUpdate = schoolRepository.findAll().size();
        // Update the school using partial update
        School partialUpdatedSchool = new School();
        partialUpdatedSchool.setId(school.getId());

        partialUpdatedSchool.setName(UPDATED_NAME);
        partialUpdatedSchool.setEmail(UPDATED_EMAIL);
        partialUpdatedSchool.setPhoneNumber(UPDATED_PHONE_NUMBER);
        partialUpdatedSchool.setAddress(UPDATED_ADDRESS);
        partialUpdatedSchool.setKkUseId(UPDATED_KK_USE_ID);
        partialUpdatedSchool.setCreatedDate(UPDATED_CREATED_DATE);
        partialUpdatedSchool.setModifiedDate(UPDATED_MODIFIED_DATE);
        partialUpdatedSchool.setCreatedBy(UPDATED_CREATED_BY);
        partialUpdatedSchool.setModifiedBy(UPDATED_MODIFIED_BY);

        SchoolDTO schoolDTO = org.mapstruct.factory.Mappers.getMapper(SchoolMapper.class).toDto(partialUpdatedSchool);
        Mockito.when(schoolMapper.toDto(partialUpdatedSchool)).thenReturn(schoolDTO);
        Mockito.when(schoolRepository.save(Mockito.any(School.class)))
            .thenAnswer(i -> i.getArguments()[0]);

        assertThat(schoolRepository.findAll().size()).isEqualTo(databaseSizeBeforeUpdate);
    }
}
