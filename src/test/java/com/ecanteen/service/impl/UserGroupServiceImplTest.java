package com.ecanteen.service.impl;

 import com.ecanteen.domain.UserGroup;
 import com.ecanteen.repository.UserGroupRepository;
 import com.ecanteen.service.UserGroupServiceImpl;
 import com.ecanteen.service.dto.UserGroupDTO;

import com.ecanteen.service.mapper.UserGroupMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;

public class UserGroupServiceImplTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";

    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";

    private static final String UPDATED_NAME = "BBBBBBBBBB";



    private static final Random random = new Random();

    private static final AtomicLong count = new AtomicLong(random.nextInt() + (2L * Integer.MAX_VALUE));



    private UserGroupServiceImpl userGroupServiceImpl;

    private UserGroupRepository userGroupRepository;

    private EntityManager em;


    private MockMvc mockMvc;


    private UserGroupMapper userGroupMapper;

    private UserGroup userGroup;




    @BeforeEach
    void setUp() {
        userGroup = createEntity(em);
        userGroupRepository = Mockito.mock(UserGroupRepository.class);
        userGroupMapper = Mockito.mock(UserGroupMapper.class);
        userGroupServiceImpl = new UserGroupServiceImpl(userGroupRepository, userGroupMapper);
    }


    //create data for test

    public static UserGroup createEntity(EntityManager em) {
        UserGroup userGroup = new UserGroup();
        userGroup.setName(DEFAULT_NAME);
        userGroup.setCode(DEFAULT_CODE);


        return userGroup;
    }


    @Test
    void shouldReturnAllUserGroups() {

        //we have three sections for tets


        //arrange
        //in this section we prepare required things
        List<UserGroup> userGroupList = new ArrayList<>();
        userGroupList.add(userGroup);
        userGroupList.add(userGroup);
        Pageable pageable = PageRequest.of(0, 5);
        Page<UserGroup> userGroupListPage = new PageImpl<>(userGroupList, pageable, userGroupList.size());
        UserGroupDTO userGroupDTO1 = new UserGroupDTO();
        userGroupDTO1.setId(1L);
        UserGroupDTO userGroupDTO2 = new UserGroupDTO();
        userGroupDTO2.setId(2L);


        //act
        //in this section we invoke method of services for test
        List<UserGroupDTO> userGroupDTOList = new ArrayList<>();
        userGroupDTOList.add(userGroupDTO1);
        userGroupDTOList.add(userGroupDTO2);

        //asser
        //and finally we write this section To get the result we expect

        Mockito.when(userGroupMapper.toDto(userGroup)).thenReturn(userGroupDTO1);
        Mockito.when(userGroupMapper.toDto(userGroup)).thenReturn(userGroupDTO2);
        Mockito.when(userGroupRepository.findAll(pageable)).thenReturn(userGroupListPage);

        assertThat(userGroupServiceImpl.findAll(pageable)).hasSize(2);
    }

    @Test
    void shouldCreateUserGroups() {
        UserGroupDTO userGroupDTO = org.mapstruct.factory.Mappers.getMapper(UserGroupMapper.class).toDto(userGroup);
        Mockito.when(userGroupMapper.toDto(userGroup)).thenReturn(userGroupDTO);
        Mockito.when(userGroupRepository.save(Mockito.any(UserGroup.class)))
            .thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void shouldUpdateUserGroup() {

        userGroupRepository.saveAndFlush(userGroup);
        int databaseSizeBeforeUpdate = userGroupRepository.findAll().size();

        UserGroup partialUpdatedUserGroup = new UserGroup();
        partialUpdatedUserGroup.setId(userGroup.getId());

        partialUpdatedUserGroup.setName(UPDATED_NAME);
        partialUpdatedUserGroup.setCode(UPDATED_CODE);




        UserGroupDTO userGroupDTO = Mappers.getMapper(UserGroupMapper.class).toDto(partialUpdatedUserGroup);
        Mockito.when(userGroupMapper.toDto(partialUpdatedUserGroup)).thenReturn(userGroupDTO);
        Mockito.when(userGroupRepository.save(Mockito.any(UserGroup.class)))
            .thenAnswer(i -> i.getArguments()[0]);

        assertThat(userGroupRepository.findAll().size()).isEqualTo(databaseSizeBeforeUpdate);
    }
}
