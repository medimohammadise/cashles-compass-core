package com.ecanteen.service;

 import com.ecanteen.service.dto.NotificationHistoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NotificationHistoryService {

    NotificationHistoryDTO save(NotificationHistoryDTO notificationHistoryDTO);


    NotificationHistoryDTO update(NotificationHistoryDTO notificationHistoryDTO);


    Optional<NotificationHistoryDTO> partialUpdate(NotificationHistoryDTO notificationHistoryDTO);


    Optional<NotificationHistoryDTO> findOne(Long id);


    Page<NotificationHistoryDTO> findAll(Pageable pageable);


    void delete(Long id);
}
