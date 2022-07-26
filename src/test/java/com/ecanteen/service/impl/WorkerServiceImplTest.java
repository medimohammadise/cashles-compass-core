package com.ecanteen.service.impl;

import com.ecanteen.domain.Worker;
import com.ecanteen.repository.WorkerRepository;
import com.ecanteen.service.dto.WorkerDTO;
import com.ecanteen.service.mapper.WorkerMapper;
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

public class WorkerServiceImplTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";

    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_REG_NO = "AAAAAAAAAA";
    private static final String UPDATED_REG_NO = "BBBBBBBBBB";
    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGEURL = "AAAAAAAAAA";

    private static final String UPDATED_IMAGEURL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_APPROVED = true;

    private static final Boolean UPDATED_IS_APPROVED = true;


    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";
    private static final String DEFAULT_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIED_BY = "BBBBBBBBBB";

    private static final Random random = new Random();

    private static final AtomicLong count = new AtomicLong(random.nextInt() + (2L * Integer.MAX_VALUE));


    private WorkerServiceImpl workerServiceImpl;

    private WorkerRepository workerRepository;

    private EntityManager em;


    private MockMvc mockMvc;


    private WorkerMapper workerMapper;

    private Worker worker;


    @BeforeEach
    void setUp() {
        worker = createEntity(em);
        workerRepository = Mockito.mock(WorkerRepository.class);
        workerMapper = Mockito.mock(WorkerMapper.class);
        workerServiceImpl = new WorkerServiceImpl(workerRepository, workerMapper);
    }


    public static Worker createEntity(EntityManager em) {

        Worker worker = new Worker();
        worker.setName(DEFAULT_NAME);
        worker.setComment(DEFAULT_COMMENT);
        worker.setRegNo(DEFAULT_REG_NO);
        worker.setIsApproved(DEFAULT_IS_APPROVED);
        worker.setImageUrl(DEFAULT_IMAGEURL);
        worker.setAddress(DEFAULT_ADDRESS);
        worker.setCreatedDate(DEFAULT_CREATED_DATE);
        worker.setModifiedDate(DEFAULT_MODIFIED_DATE);
        worker.setCreatedBy(DEFAULT_CREATED_BY);
        worker.setModifiedBy(DEFAULT_MODIFIED_BY);

        return worker;
    }


    @Test
    void shouldReturnAllWorkers() {


        List<Worker> workerList = new ArrayList<>();
        workerList.add(worker);
        workerList.add(worker);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Worker> workerPage = new PageImpl<>(workerList, pageable, workerList.size());
        WorkerDTO workerDTO1 = new WorkerDTO();
        workerDTO1.setId(1L);
        WorkerDTO workerDTO2 = new WorkerDTO();
        workerDTO2.setId(2L);


        //act
        //in this section we invoke method of services for test
        List<WorkerDTO> workerDTODTOList = new ArrayList<>();
        workerDTODTOList.add(workerDTO1);
        workerDTODTOList.add(workerDTO2);

        //asser
        //and finally we write this section To get the result we expect

        Mockito.when(workerMapper.toDto(worker)).thenReturn(workerDTO1);
        Mockito.when(workerMapper.toDto(worker)).thenReturn(workerDTO2);
        Mockito.when(workerRepository.findAll(pageable)).thenReturn(workerPage);

        assertThat(workerServiceImpl.findAll(pageable)).hasSize(2);
    }

    @Test
    void shouldCreateWorkers() {
        WorkerDTO workerDTO = org.mapstruct.factory.Mappers.getMapper(WorkerMapper.class).toDto(worker);
        Mockito.when(workerMapper.toDto(worker)).thenReturn(workerDTO);
        Mockito.when(workerRepository.save(Mockito.any(Worker.class)))
            .thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void shouldUpdateWorker() {

        workerRepository.saveAndFlush(worker);
        int databaseSizeBeforeUpdate = workerRepository.findAll().size();

        Worker partialUpdatedWorker = new Worker();
        partialUpdatedWorker.setId(worker.getId());

        partialUpdatedWorker.setName(UPDATED_NAME);
        partialUpdatedWorker.setAddress(UPDATED_ADDRESS);
        partialUpdatedWorker.isApproved(UPDATED_IS_APPROVED);
        partialUpdatedWorker.setComment(UPDATED_COMMENT);
        partialUpdatedWorker.setImageUrl(UPDATED_IMAGEURL);
        partialUpdatedWorker.setRegNo(UPDATED_REG_NO);
        partialUpdatedWorker.setCreatedDate(UPDATED_CREATED_DATE);
        partialUpdatedWorker.setModifiedDate(UPDATED_MODIFIED_DATE);
        partialUpdatedWorker.setCreatedBy(UPDATED_CREATED_BY);
        partialUpdatedWorker.setModifiedBy(UPDATED_MODIFIED_BY);

        WorkerDTO workerDTO = org.mapstruct.factory.Mappers.getMapper(WorkerMapper.class).toDto(partialUpdatedWorker);
        Mockito.when(workerMapper.toDto(partialUpdatedWorker)).thenReturn(workerDTO);
        Mockito.when(workerRepository.save(Mockito.any(Worker.class)))
            .thenAnswer(i -> i.getArguments()[0]);

        assertThat(workerRepository.findAll().size()).isEqualTo(databaseSizeBeforeUpdate);
    }

}
