package com.ecanteen.service.impl;

import com.ecanteen.domain.ActivationCode;
import com.ecanteen.domain.Menu;
import com.ecanteen.repository.ActivationCodeRepository;
import com.ecanteen.repository.MenuRepository;
import com.ecanteen.service.ActivationCodeServiceImpl;
import com.ecanteen.service.MenuServiceImpl;
import com.ecanteen.service.dto.ActivationCodeDTO;
import com.ecanteen.service.dto.MenuDTO;
import com.ecanteen.service.mapper.ActivationCodeMapper;
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

public class ActivationCodeServiceImplTest {


    private static final String DEFAULT_CODE = "AAAAAAAAAA";

    private static final String UPDATED_CODE = "BBBBBBBBBB";
    private static final String DEFAULT_EXPIRYTIME = "AAAAAAAAAA";

    private static final String UPDATED_EXPIRYTIME = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";

    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);



    private static final Random random = new Random();

    private static final AtomicLong count = new AtomicLong(random.nextInt() + (2L * Integer.MAX_VALUE));



    private ActivationCodeServiceImpl activationCodeServiceImpl;

    private ActivationCodeRepository activationCodeRepository;

    private EntityManager em;


    private MockMvc mockMvc;


    private ActivationCodeMapper activationCodeMapper;

    private ActivationCode activationCode;




    @BeforeEach
    void setUp() {
        activationCode = createEntity(em);
        activationCodeRepository = Mockito.mock(ActivationCodeRepository.class);
        activationCodeMapper = Mockito.mock(ActivationCodeMapper.class);
        activationCodeServiceImpl = new ActivationCodeServiceImpl(activationCodeRepository, activationCodeMapper);
    }


    //create data for test

    public static ActivationCode createEntity(EntityManager em) {
        ActivationCode activationCode = new ActivationCode();

        activationCode.setCode(DEFAULT_CODE);
        activationCode.setCreatedBy(DEFAULT_CREATED_BY);
        activationCode.setCreatedDate(DEFAULT_CREATED_DATE);
        activationCode.setExpiryTime(DEFAULT_EXPIRYTIME);

        return activationCode;
    }


    @Test
    void shouldReturnAllActivationCodes() {

        //we have three sections for tets


        //arrange
        //in this section we prepare required things
        List<ActivationCode> activationCodeList = new ArrayList<>();
        activationCodeList.add(activationCode);
        activationCodeList.add(activationCode);
        Pageable pageable = PageRequest.of(0, 5);
        Page<ActivationCode> menuPage = new PageImpl<>(activationCodeList, pageable, activationCodeList.size());
        ActivationCodeDTO activationCodeDTO1 = new ActivationCodeDTO();
        activationCodeDTO1.setId(1L);
        ActivationCodeDTO activationCodeDTO2 = new ActivationCodeDTO();
        activationCodeDTO2.setId(2L);


        //act
        //in this section we invoke method of services for test
        List<ActivationCodeDTO> activationCodeDTOList = new ArrayList<>();
        activationCodeDTOList.add(activationCodeDTO1);
        activationCodeDTOList.add(activationCodeDTO2);

        //asser
        //and finally we write this section To get the result we expect

        Mockito.when(activationCodeMapper.toDto(activationCode)).thenReturn(activationCodeDTO1);
        Mockito.when(activationCodeMapper.toDto(activationCode)).thenReturn(activationCodeDTO2);
        Mockito.when(activationCodeRepository.findAll(pageable)).thenReturn(menuPage);

        assertThat(activationCodeServiceImpl.findAll(pageable)).hasSize(2);
    }

    @Test
    void shouldCreateActivationCodes() {
        ActivationCodeDTO activationCodeDTO = org.mapstruct.factory.Mappers.getMapper(ActivationCodeMapper.class).toDto(activationCode);
        Mockito.when(activationCodeMapper.toDto(activationCode)).thenReturn(activationCodeDTO);
        Mockito.when(activationCodeRepository.save(Mockito.any(ActivationCode.class)))
            .thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void shouldUpdateActivationCode() {

        activationCodeRepository.saveAndFlush(activationCode);
        int databaseSizeBeforeUpdate = activationCodeRepository.findAll().size();

        ActivationCode partialUpdatedActivationCode = new ActivationCode();
        partialUpdatedActivationCode.setId(activationCode.getId());

        partialUpdatedActivationCode.setExpiryTime(UPDATED_EXPIRYTIME);
        partialUpdatedActivationCode.setCode(UPDATED_CODE);

        partialUpdatedActivationCode.setCreatedDate(UPDATED_CREATED_DATE);
        partialUpdatedActivationCode.setCreatedBy(UPDATED_CREATED_BY);


        MenuDTO menuDTO = org.mapstruct.factory.Mappers.getMapper(MenuMapper.class).toDto(partialUpdatedActivationCode);
        Mockito.when(activationCodeMapper.toDto(partialUpdatedActivationCode)).thenReturn(menuDTO);
        Mockito.when(activationCodeRepository.save(Mockito.any(ActivationCode.class)))
            .thenAnswer(i -> i.getArguments()[0]);

        assertThat(activationCodeRepository.findAll().size()).isEqualTo(databaseSizeBeforeUpdate);
    }
}
