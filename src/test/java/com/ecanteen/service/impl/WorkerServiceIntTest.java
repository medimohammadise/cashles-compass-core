package com.ecanteen.service.impl;


import com.ecanteen.domain.Worker;

import com.ecanteen.repository.WorkerRepository;
import com.ecanteen.service.dto.WorkerDTO;

import com.ecanteen.service.mapper.WorkerMapper;
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
public class WorkerServiceIntTest {

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


    private Worker worker;

    private WorkerRepository workerRepository;

    private WorkerMapper workerMapper;
    private EntityManager em;
    @Autowired
    private MockMvc mockMvc;


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

    @BeforeEach
    void setUp() {
        worker = createEntity(em);
    }


    @Test
    void shouldReturnAllWorkers() throws Exception {
        mockMvc.perform(get("/api/workers"))
            .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateWorkers() throws Exception {
        workerRepository.saveAndFlush(worker);
        Worker updatedWorker = workerRepository.findById(worker.getId()).get();
        em.detach(updatedWorker);
        updatedWorker
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .comment(UPDATED_COMMENT)
            .regNo(UPDATED_REG_NO)
            .imageUrl(UPDATED_IMAGEURL)
            .isApproved(UPDATED_IS_APPROVED)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY);

        WorkerDTO workerDTO = workerMapper.toDto(updatedWorker);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/workers/{id}", 14)
                .content(TestUtil.convertObjectToJsonBytes(updatedWorker))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
