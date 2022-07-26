package com.ecanteen.service.impl;


import com.ecanteen.domain.NotificationHistory;
import com.ecanteen.domain.enumeration.NotificationMethod;
import com.ecanteen.domain.enumeration.NotificationStatus;

import com.ecanteen.repository.NotificationHistoryRepository;

import com.ecanteen.service.dto.NotificationHistoryDTO;
 import com.ecanteen.service.mapper.NotificationHistoryMapper;

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

public class NotificationHistoryServiceImplTest {

    private static final ZonedDateTime DEFAULT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);

    private static final ZonedDateTime UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final NotificationStatus DEFAULT_STATUS = NotificationStatus.DELIVERED;

    private static final NotificationStatus UPDATED_STATUS = NotificationStatus.DELIVERED;

    private static final NotificationMethod DEFAULT_METHOD = NotificationMethod.EMAIL;

    private static final NotificationMethod UPDATED_METHOD = NotificationMethod.EMAIL;


    private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);


    private static final Random random = new Random();

    private static final AtomicLong count = new AtomicLong(random.nextInt() + (2L * Integer.MAX_VALUE));



    private NotificationHistoryServiceImpl notificationHistoryServiceImpl;

    private NotificationHistoryRepository notificationHistoryRepository;

    private EntityManager em;


    private MockMvc mockMvc;


    private NotificationHistoryMapper notificationHistoryMapper;

    private com.ecanteen.domain.NotificationHistory NotificationHistory;




    @BeforeEach
    void setUp() {
        NotificationHistory = createEntity(em);
        notificationHistoryRepository = Mockito.mock(NotificationHistoryRepository.class);
        notificationHistoryMapper = Mockito.mock(NotificationHistoryMapper.class);
        notificationHistoryServiceImpl = new NotificationHistoryServiceImpl(notificationHistoryRepository, notificationHistoryMapper);
    }


    //create data for test

    public static NotificationHistory createEntity(EntityManager em) {
        NotificationHistory notificationHistory = new NotificationHistory();

        notificationHistory.setStatus(DEFAULT_STATUS);
        notificationHistory.setDate(DEFAULT_DATE);
        notificationHistory.setMethod(DEFAULT_METHOD);

        return notificationHistory;
    }


    @Test
    void shouldReturnAllNotificationHistories() {

        //we have three sections for tets


        //arrange
        //in this section we prepare required things
        List<NotificationHistory> notificationHistoryList = new ArrayList<>();
        notificationHistoryList.add(NotificationHistory);
        notificationHistoryList.add(NotificationHistory);
        Pageable pageable = PageRequest.of(0, 5);
        Page<NotificationHistory> notificationHistoryPage = new PageImpl<>(notificationHistoryList, pageable, notificationHistoryList.size());
        NotificationHistoryDTO notificationHistoryDTO1 = new NotificationHistoryDTO();
        notificationHistoryDTO1.setId(1L);
        NotificationHistoryDTO notificationHistoryDTO2 = new NotificationHistoryDTO();
        notificationHistoryDTO2.setId(2L);


        //act
        //in this section we invoke method of services for test
        List<NotificationHistoryDTO> notificationHistoryDTOList = new ArrayList<>();
        notificationHistoryDTOList.add(notificationHistoryDTO1);
        notificationHistoryDTOList.add(notificationHistoryDTO2);

        //asser
        //and finally we write this section To get the result we expect

        Mockito.when(notificationHistoryMapper.toDto(NotificationHistory)).thenReturn(notificationHistoryDTO1);
        Mockito.when(notificationHistoryMapper.toDto(NotificationHistory)).thenReturn(notificationHistoryDTO2);
        Mockito.when(notificationHistoryRepository.findAll(pageable)).thenReturn(notificationHistoryPage);

        assertThat(notificationHistoryServiceImpl.findAll(pageable)).hasSize(2);
    }

    @Test
    void shouldCreateNotificationHistories() {
        NotificationHistoryDTO notificationHistoryDTO = org.mapstruct.factory.Mappers.getMapper(NotificationHistoryMapper.class).toDto(NotificationHistory);
        Mockito.when(notificationHistoryMapper.toDto(NotificationHistory)).thenReturn(notificationHistoryDTO);
        Mockito.when(notificationHistoryRepository.save(Mockito.any(NotificationHistory.class)))
            .thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void shouldUpdateNotificationHistory() {

        notificationHistoryRepository.saveAndFlush(NotificationHistory);
        int databaseSizeBeforeUpdate = notificationHistoryRepository.findAll().size();

        NotificationHistory partialUpdatedNotificationHistory = new NotificationHistory();
        partialUpdatedNotificationHistory.setId(NotificationHistory.getId());

        partialUpdatedNotificationHistory.setMethod(UPDATED_METHOD);
        partialUpdatedNotificationHistory.setStatus(UPDATED_STATUS);

        partialUpdatedNotificationHistory.setDate(UPDATED_DATE);


        NotificationHistoryDTO notificationHistoryDTO = org.mapstruct.factory.Mappers.getMapper(NotificationHistoryMapper.class).toDto(partialUpdatedNotificationHistory);
        Mockito.when(notificationHistoryMapper.toDto(partialUpdatedNotificationHistory)).thenReturn(notificationHistoryDTO);
        Mockito.when(notificationHistoryRepository.save(Mockito.any(NotificationHistory.class)))
            .thenAnswer(i -> i.getArguments()[0]);

        assertThat(notificationHistoryRepository.findAll().size()).isEqualTo(databaseSizeBeforeUpdate);
    }
}
