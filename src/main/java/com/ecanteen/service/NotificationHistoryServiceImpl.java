package com.ecanteen.service;


import com.ecanteen.domain.NotificationHistory;
import com.ecanteen.repository.NotificationHistoryRepository;
import com.ecanteen.service.dto.NotificationHistoryDTO;
import com.ecanteen.service.mapper.NotificationHistoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class NotificationHistoryServiceImpl implements NotificationHistoryService {

    private final Logger log = LoggerFactory.getLogger(NotificationHistoryServiceImpl.class);


    private final NotificationHistoryMapper notificationHistoryMapper;
    private final NotificationHistoryRepository notificationHistoryRepository;


    @Autowired
    public NotificationHistoryServiceImpl(NotificationHistoryRepository notificationHistoryRepository, NotificationHistoryMapper notificationHistoryMapper) {
        this.notificationHistoryRepository = notificationHistoryRepository;
        this.notificationHistoryMapper = notificationHistoryMapper;
    }

    @Override
    public NotificationHistoryDTO save(NotificationHistoryDTO notificationHistoryDTO) {
        log.debug("Request to save NotificationHistories : {}", notificationHistoryMapper);
        NotificationHistory notificationHistory = notificationHistoryMapper.toEntity(notificationHistoryDTO);
        notificationHistory = notificationHistoryRepository.save(notificationHistory);
        return notificationHistoryMapper.toDto(notificationHistory);
    }

    @Override
    public NotificationHistoryDTO update(NotificationHistoryDTO notificationHistoryDTO) {
        log.debug("Request to save NotificationHistories : {}", notificationHistoryDTO);
        NotificationHistory notificationHistory = notificationHistoryMapper.toEntity(notificationHistoryDTO);
        notificationHistory = notificationHistoryRepository.save(notificationHistory);
        return notificationHistoryMapper.toDto(notificationHistory);
    }

    @Override
    public Optional<NotificationHistoryDTO> partialUpdate(NotificationHistoryDTO notificationHistoryDTO) {
        log.debug("Request to partially update NotificationHistories : {}", notificationHistoryDTO);

        return notificationHistoryRepository
            .findById(notificationHistoryDTO.getId())
            .map(existingNotificationHistory -> {
                notificationHistoryMapper.partialUpdate(existingNotificationHistory, notificationHistoryDTO);

                return existingNotificationHistory;
            })
            .map(notificationHistoryRepository::save)
            .map(notificationHistoryMapper::toDto);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Page<NotificationHistoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NotificationHistories");
        return notificationHistoryRepository.findAll(pageable).map(notificationHistoryMapper::toDto);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Optional<NotificationHistoryDTO> findOne(Long id) {
        log.debug("Request to get NotificationHistories : {}", id);
        return notificationHistoryRepository.findById(id).map(notificationHistoryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete NotificationHistories : {}", id);
        notificationHistoryRepository.deleteById(id);
    }
}
