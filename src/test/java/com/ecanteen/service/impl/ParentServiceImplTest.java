package com.ecanteen.service.impl;

import com.ecanteen.domain.Parent;
import com.ecanteen.domain.enumeration.ROLE;
import com.ecanteen.repository.ParentRepository;
import com.ecanteen.service.dto.ParentDTO;
import com.ecanteen.service.mapper.ParentMapper;
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

public class ParentServiceImplTest {

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

    private static final Random random = new Random();

    private static final AtomicLong count = new AtomicLong(random.nextInt() + (2L * Integer.MAX_VALUE));


    private ParentServiceImpl parentServiceImpl;

    private ParentRepository parentRepository;

    private EntityManager em;


    private MockMvc mockMvc;


    private ParentMapper parentMapper;

    private Parent parent;


    @BeforeEach
    void setUp() {
        parent = createEntity(em);
        parentRepository = Mockito.mock(ParentRepository.class);
        parentMapper = Mockito.mock(ParentMapper.class);
        parentServiceImpl = new ParentServiceImpl(parentRepository, parentMapper);
    }


    //create data for test

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


    @Test
    void shouldReturnAllParents() {


        List<Parent> parentList = new ArrayList<>();
        parentList.add(parent);
        parentList.add(parent);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Parent> parentPage = new PageImpl<>(parentList, pageable, parentList.size());
        ParentDTO parentDTO1 = new ParentDTO();
        parentDTO1.setId(1L);
        ParentDTO parentDTO2 = new ParentDTO();
        parentDTO2.setId(2L);


        //act
        //in this section we invoke method of services for test
        List<ParentDTO> parentDTODTOList = new ArrayList<>();
        parentDTODTOList.add(parentDTO1);
        parentDTODTOList.add(parentDTO2);

        //asser
        //and finally we write this section To get the result we expect

        Mockito.when(parentMapper.toDto(parent)).thenReturn(parentDTO1);
        Mockito.when(parentMapper.toDto(parent)).thenReturn(parentDTO2);
        Mockito.when(parentRepository.findAll(pageable)).thenReturn(parentPage);

        assertThat(parentServiceImpl.findAll(pageable)).hasSize(2);
    }

    @Test
    void shouldCreateParents() {
        ParentDTO parentDTO = org.mapstruct.factory.Mappers.getMapper(ParentMapper.class).toDto(parent);
        Mockito.when(parentMapper.toDto(parent)).thenReturn(parentDTO);
        Mockito.when(parentRepository.save(Mockito.any(Parent.class)))
            .thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void shouldUpdateParent() {

        parentRepository.saveAndFlush(parent);
        int databaseSizeBeforeUpdate = parentRepository.findAll().size();

        Parent partialUpdatedParent = new Parent();
        partialUpdatedParent.setId(parent.getId());

        partialUpdatedParent.setFullName(UPDATED_FULLNAME);
        partialUpdatedParent.setAddress(UPDATED_ADDRESS);
        partialUpdatedParent.setEmailVerified(UPDATED_EMAIL_VERIFIED);
        partialUpdatedParent.setPhoneVerified(UPDATED_PHONE_VERIFIED);
        partialUpdatedParent.setImageUrl(UPDATED_IMAGEURL);
        partialUpdatedParent.setEnabled(UPDATED_ISENABLED);
        partialUpdatedParent.setEmail(UPDATED_EMAIL);
        partialUpdatedParent.setPhoneNumber(UPDATED_PHONE_NUMBER);
        partialUpdatedParent.setKkUesId(UPDATED_KK_USE_ID);
        partialUpdatedParent.setCreatedDate(UPDATED_CREATED_DATE);
        partialUpdatedParent.setModifiedDate(UPDATED_MODIFIED_DATE);
        partialUpdatedParent.setCreatedBy(UPDATED_CREATED_BY);
        partialUpdatedParent.setModifiedBy(UPDATED_MODIFIED_BY);
        partialUpdatedParent.setRole(UPDATED_ROLE);

        ParentDTO parentDTO = org.mapstruct.factory.Mappers.getMapper(ParentMapper.class).toDto(partialUpdatedParent);
        Mockito.when(parentMapper.toDto(partialUpdatedParent)).thenReturn(parentDTO);
        Mockito.when(parentRepository.save(Mockito.any(Parent.class)))
            .thenAnswer(i -> i.getArguments()[0]);

        assertThat(parentRepository.findAll().size()).isEqualTo(databaseSizeBeforeUpdate);
    }

}
