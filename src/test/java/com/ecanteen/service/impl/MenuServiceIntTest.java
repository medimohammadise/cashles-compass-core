package com.ecanteen.service.impl;

import com.ecanteen.domain.Menu;

import com.ecanteen.repository.MenuRepository;

import com.ecanteen.service.dto.MenuDTO;

import com.ecanteen.service.mapper.MenuMapper;

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

public class MenuServiceIntTest {



    /***
     * Integration Test for Menu Api
     */



    private static final String DEFAULT_NAME = "AAAAAAAAAA";

    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";

    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);


    private static final Random random = new Random();

    private static final AtomicLong count = new AtomicLong(random.nextInt() + (2L * Integer.MAX_VALUE));



    private Menu menu;

    private MenuRepository menuRepository;

    private MenuMapper menuMapper;
    private EntityManager em;
    @Autowired
    private MockMvc mockMvc;


    public static Menu createEntity(EntityManager em) {
        Menu menu = new Menu();

        menu.setName(DEFAULT_NAME);
        menu.setCode(DEFAULT_CODE);
        menu.setCreatedDate(DEFAULT_CREATED_DATE);
        menu.setModifiedDate(DEFAULT_MODIFIED_DATE);

        return menu;
    }

    @BeforeEach
    void setUp() {
        menu = createEntity(em);
    }


    @Test
    void shouldReturnAllMenus() throws Exception {
        mockMvc.perform(get("/api/menus"))
            .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateStudent() throws Exception {
        menuRepository.saveAndFlush(menu);
        Menu updatedMenu = menuRepository.findById(menu.getId()).get();
        em.detach(updatedMenu);
        updatedMenu
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        MenuDTO menuDTO = menuMapper.toDto(updatedMenu);

        mockMvc.perform( MockMvcRequestBuilders
                .put("/api/menus/{id}", 14)
                .content(TestUtil.convertObjectToJsonBytes(updatedMenu))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

}
