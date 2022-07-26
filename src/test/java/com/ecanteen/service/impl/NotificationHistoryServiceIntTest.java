package com.ecanteen.service.impl;


 import com.ecanteen.domain.NotificationHistory;
import com.ecanteen.domain.enumeration.NotificationMethod;
import com.ecanteen.domain.enumeration.NotificationStatus;
 import com.ecanteen.repository.NotificationHistoryRepository;
 import com.ecanteen.service.dto.NotificationHistoryDTO;

import com.ecanteen.service.mapper.NotificationHistoryMapper;
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
public class NotificationHistoryServiceIntTest {

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



    private NotificationHistoryMapper notificationHistoryMapper;

    private com.ecanteen.domain.NotificationHistory NotificationHistory;




    @Autowired
    private MockMvc mockMvc;


    public static com.ecanteen.domain.NotificationHistory createEntity(EntityManager em) {
        NotificationHistory notificationHistory = new NotificationHistory();

        notificationHistory.setStatus(DEFAULT_STATUS);
        notificationHistory.setDate(DEFAULT_DATE);
        notificationHistory.setMethod(DEFAULT_METHOD);

        return notificationHistory;
    }

    @BeforeEach
    void setUp() {
        NotificationHistory = createEntity(em);
    }


    @Test
    void shouldReturnAllNotificationHistories() throws Exception {
        mockMvc.perform(get("/api/notificationHistories"))
            .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateNotificationHistory() throws Exception {
        notificationHistoryRepository.saveAndFlush(NotificationHistory);
        NotificationHistory updateNotificationHistory = notificationHistoryRepository.findById(NotificationHistory.getId()).get();
        em.detach(updateNotificationHistory);
        updateNotificationHistory.status(UPDATED_STATUS).method(UPDATED_METHOD).date(UPDATED_DATE);


        NotificationHistoryDTO notificationHistoryDTO = notificationHistoryMapper.toDto(updateNotificationHistory);

        mockMvc.perform( MockMvcRequestBuilders
                .put("/api/notificationHistories/{id}", 14)
                .content(TestUtil.convertObjectToJsonBytes(updateNotificationHistory))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
