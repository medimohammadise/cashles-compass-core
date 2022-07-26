package com.ecanteen.service.impl;


import com.ecanteen.domain.UserCredential;
import com.ecanteen.repository.UserCredentialRepository;

import com.ecanteen.service.dto.UserCredentialDTO;

import com.ecanteen.service.mapper.UserCredentialMapper;
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

public class UserCredentialServiceImplTest {

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";

    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);


    private static final Random random = new Random();

    private static final AtomicLong count = new AtomicLong(random.nextInt() + (2L * Integer.MAX_VALUE));


    private UserCredentialServiceImpl userCredentialServiceImpl;

    private UserCredentialRepository userCredentialRepository;

    private EntityManager em;


    private MockMvc mockMvc;


    private UserCredentialMapper userCredentialMapper;

    private UserCredential userCredential;


    @BeforeEach
    void setUp() {
        userCredential = createEntity(em);
        userCredentialRepository = Mockito.mock(UserCredentialRepository.class);
        userCredentialMapper = Mockito.mock(UserCredentialMapper.class);
        userCredentialServiceImpl = new UserCredentialServiceImpl(userCredentialRepository, userCredentialMapper);
    }


    //create data for test

    public static UserCredential createEntity(EntityManager em) {
        UserCredential userCredential = new UserCredential();

        userCredential.setUserName(DEFAULT_USERNAME);
        userCredential.setCreatedDate(DEFAULT_CREATED_DATE);

        return userCredential;
    }


    @Test
    void shouldReturnAllUserCredential() {

        //we have three sections for tets


        //arrange
        //in this section we prepare required things
        List<UserCredential> userCredentialList = new ArrayList<>();
        userCredentialList.add(userCredential);
        userCredentialList.add(userCredential);
        Pageable pageable = PageRequest.of(0, 5);
        Page<UserCredential> userCredentialPage = new PageImpl<>(userCredentialList, pageable, userCredentialList.size());
        UserCredentialDTO userCredentialDTO1 = new UserCredentialDTO();
        userCredentialDTO1.setId(1L);
        UserCredentialDTO userCredentialDTO2 = new UserCredentialDTO();
        userCredentialDTO2.setId(2L);


        //act
        //in this section we invoke method of services for test
        List<UserCredentialDTO> userCredentialDTOList = new ArrayList<>();
        userCredentialDTOList.add(userCredentialDTO1);
        userCredentialDTOList.add(userCredentialDTO2);

        //asser
        //and finally we write this section To get the result we expect

        Mockito.when(userCredentialMapper.toDto(userCredential)).thenReturn(userCredentialDTO1);
        Mockito.when(userCredentialMapper.toDto(userCredential)).thenReturn(userCredentialDTO2);
        Mockito.when(userCredentialRepository.findAll(pageable)).thenReturn(userCredentialPage);

        assertThat(userCredentialServiceImpl.findAll(pageable)).hasSize(2);
    }

    @Test
    void shouldCreateUserCredentials() {
        UserCredentialDTO UserCredentialDTO = org.mapstruct.factory.Mappers.getMapper(UserCredentialMapper.class).toDto(userCredential);
        Mockito.when(userCredentialMapper.toDto(userCredential)).thenReturn(UserCredentialDTO);
        Mockito.when(userCredentialRepository.save(Mockito.any(UserCredential.class)))
            .thenAnswer(i -> i.getArguments()[0]);
    }


    @Test
    void shouldUpdateUserCredential() {

        userCredentialRepository.saveAndFlush(userCredential);
        int databaseSizeBeforeUpdate = userCredentialRepository.findAll().size();

        UserCredential partialUpdatedUserCredential = new UserCredential();
        partialUpdatedUserCredential.setId(userCredential.getId());

        partialUpdatedUserCredential.setUserName(UPDATED_USERNAME);

        partialUpdatedUserCredential.setCreatedDate(UPDATED_CREATED_DATE);


        UserCredentialDTO userCredentialDTO = org.mapstruct.factory.Mappers.getMapper(UserCredentialMapper.class).toDto(partialUpdatedUserCredential);
        Mockito.when(userCredentialMapper.toDto(partialUpdatedUserCredential)).thenReturn(userCredentialDTO);
        Mockito.when(userCredentialRepository.save(Mockito.any(UserCredential.class)))
            .thenAnswer(i -> i.getArguments()[0]);

        assertThat(userCredentialRepository.findAll().size()).isEqualTo(databaseSizeBeforeUpdate);
    }
}
