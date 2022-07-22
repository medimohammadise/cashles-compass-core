package com.ecanteen.web.rest;


  import com.ecanteen.repository.NotificationHistoryRepository;
 import com.ecanteen.service.NotificationHistoryService;
 import com.ecanteen.service.dto.NotificationHistoryDTO;
import com.ecanteen.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class NotificationHistoryResource {

    private static final String ENTITY_NAME = "notificationHistory";
    private final Logger log = LoggerFactory.getLogger(NotificationHistoryResource.class);

    private final NotificationHistoryService notificationHistoryService;

    private final NotificationHistoryRepository notificationHistoryRepository;
    @Autowired
    public NotificationHistoryResource(NotificationHistoryService notificationHistoryService, NotificationHistoryRepository notificationHistoryRepository) {
        this.notificationHistoryService = notificationHistoryService;
        this.notificationHistoryRepository = notificationHistoryRepository;
    }


    @PostMapping("/notificationHistories")
    public ResponseEntity<NotificationHistoryDTO> createNotificationHistories(@RequestBody NotificationHistoryDTO notificationHistoryDTO) throws URISyntaxException {
        log.debug("REST request to save  NotificationHistories : {}", notificationHistoryDTO);
        if (notificationHistoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new NotificationHistories cannot already have an ID", ENTITY_NAME, "id-exists");
        }
        NotificationHistoryDTO result = notificationHistoryService.save(notificationHistoryDTO);
        return ResponseEntity
            .created(new URI("/api/notificationHistories/" + result.getId()))
            .body(result);
    }


    @GetMapping("/notificationHistories")
    public ResponseEntity<List<NotificationHistoryDTO>> getAllNotificationHistories(Pageable pageable) {
        Page<NotificationHistoryDTO> page = notificationHistoryService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }


    @PutMapping("/notificationHistories/{id}")
    public ResponseEntity<NotificationHistoryDTO> updateNotificationHistories(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NotificationHistoryDTO notificationHistoryDTO
    ) throws URISyntaxException {
        log.debug("REST request to update notificationHistoryDTO : {}, {}", id, notificationHistoryDTO);
        if (notificationHistoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, notificationHistoryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!notificationHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        NotificationHistoryDTO result = notificationHistoryService.update(notificationHistoryDTO);
        return ResponseEntity
            .ok()
            .body(result);
    }


    @DeleteMapping("/notificationHistories/{id}")
    public ResponseEntity<Void> deleteNotificationHistories(@PathVariable Long id) {
        log.debug("REST request to delete notificationHistories : {}", id);
        notificationHistoryService.delete(id);
        return ResponseEntity
            .noContent()
            .build();
    }
}
