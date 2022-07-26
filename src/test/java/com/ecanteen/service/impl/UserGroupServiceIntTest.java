package com.ecanteen.service.impl;


import com.ecanteen.domain.UserGroup;
import com.ecanteen.repository.UserGroupRepository;
import com.ecanteen.service.dto.UserGroupDTO;
import com.ecanteen.service.mapper.UserGroupMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityManager;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserGroupServiceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";

    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";

    private static final String UPDATED_NAME = "BBBBBBBBBB";


    private static final Random random = new Random();

    private static final AtomicLong count = new AtomicLong(random.nextInt() + (2L * Integer.MAX_VALUE));


    private UserGroupServiceImpl userGroupServiceImpl;

    private UserGroupRepository userGroupRepository;

    private UserGroupMapper userGroupMapper;

    private UserGroup userGroup;

    private EntityManager em;
    @Autowired
    private MockMvc mockMvc;


    public static UserGroup createEntity(EntityManager em) {
        UserGroup userGroup = new UserGroup();
        userGroup.setName(DEFAULT_NAME);
        userGroup.setCode(DEFAULT_CODE);


        return userGroup;
    }

    @BeforeEach
    void setUp() {
        userGroup = createEntity(em);
    }


    @Test
    void shouldReturnAllUserGroups() throws Exception {
        mockMvc.perform(get("/api/userGroups"))
            .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateUserGroup() throws Exception {
        userGroupRepository.saveAndFlush(userGroup);
        UserGroup updatedUserGroup = userGroupRepository.findById(userGroup.getId()).get();
        em.detach(updatedUserGroup);
        updatedUserGroup
            .name(UPDATED_NAME)
            .code(UPDATED_CODE);


        UserGroupDTO userGroupDTO = userGroupMapper.toDto(updatedUserGroup);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/userGroups/{id}", 14)
                .content(TestUtil.convertObjectToJsonBytes(updatedUserGroup))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
