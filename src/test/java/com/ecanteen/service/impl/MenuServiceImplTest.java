package com.ecanteen.service.impl;

import com.ecanteen.domain.Menu;

import com.ecanteen.repository.MenuRepository;

import com.ecanteen.service.dto.MenuDTO;

import com.ecanteen.service.mapper.MenuMapper;

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

public class MenuServiceImplTest {


    /***
     * Unit Test for Menu Api
     */


    //create some data for test instead of talk to database using repository.

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



    private MenuServiceImpl menuServiceImpl;

    private MenuRepository menuRepository;

    private EntityManager em;


    private MockMvc mockMvc;


    private MenuMapper menuMapper;

    private Menu menu;




    @BeforeEach
    void setUp() {
        menu = createEntity(em);
        menuRepository = Mockito.mock(MenuRepository.class);
        menuMapper = Mockito.mock(MenuMapper.class);
        menuServiceImpl = new MenuServiceImpl(menuRepository, menuMapper);
    }


    //create data for test

    public static Menu createEntity(EntityManager em) {
        Menu menu = new Menu();

        menu.setName(DEFAULT_NAME);
        menu.setCode(DEFAULT_CODE);
        menu.setCreatedDate(DEFAULT_CREATED_DATE);
        menu.setModifiedDate(DEFAULT_MODIFIED_DATE);

        return menu;
    }


    @Test
    void shouldReturnAllMenus() {

        //we have three sections for tets


        //arrange
        //in this section we prepare required things
        List<Menu> menuList = new ArrayList<>();
        menuList.add(menu);
        menuList.add(menu);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Menu> menuPage = new PageImpl<>(menuList, pageable, menuList.size());
        MenuDTO menuDTO1 = new MenuDTO();
        menuDTO1.setId(1L);
        MenuDTO menuDTO2 = new MenuDTO();
        menuDTO2.setId(2L);


        //act
        //in this section we invoke method of services for test
        List<MenuDTO> menuDTOList = new ArrayList<>();
        menuDTOList.add(menuDTO1);
        menuDTOList.add(menuDTO2);

        //asser
        //and finally we write this section To get the result we expect

        Mockito.when(menuMapper.toDto(menu)).thenReturn(menuDTO1);
        Mockito.when(menuMapper.toDto(menu)).thenReturn(menuDTO2);
        Mockito.when(menuRepository.findAll(pageable)).thenReturn(menuPage);

        assertThat(menuServiceImpl.findAll(pageable)).hasSize(2);
    }

    @Test
    void shouldCreateMenus() {
        MenuDTO menuDTO = org.mapstruct.factory.Mappers.getMapper(MenuMapper.class).toDto(menu);
        Mockito.when(menuMapper.toDto(menu)).thenReturn(menuDTO);
        Mockito.when(menuRepository.save(Mockito.any(Menu.class)))
            .thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void shouldUpdateMenu() {

        menuRepository.saveAndFlush(menu);
        int databaseSizeBeforeUpdate = menuRepository.findAll().size();

        Menu partialUpdatedMenu = new Menu();
        partialUpdatedMenu.setId(menu.getId());

        partialUpdatedMenu.setName(UPDATED_NAME);
        partialUpdatedMenu.setCode(UPDATED_CODE);

        partialUpdatedMenu.setCreatedDate(UPDATED_CREATED_DATE);
        partialUpdatedMenu.setModifiedDate(UPDATED_MODIFIED_DATE);


         MenuDTO menuDTO = org.mapstruct.factory.Mappers.getMapper(MenuMapper.class).toDto(partialUpdatedMenu);
        Mockito.when(menuMapper.toDto(partialUpdatedMenu)).thenReturn(menuDTO);
        Mockito.when(menuRepository.save(Mockito.any(Menu.class)))
            .thenAnswer(i -> i.getArguments()[0]);

        assertThat(menuRepository.findAll().size()).isEqualTo(databaseSizeBeforeUpdate);
    }
}
