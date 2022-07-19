package com.ecanteen.web.rest;

import com.ecanteen.repository.StudentRepository;
import com.ecanteen.repository.WorkerRepository;
import com.ecanteen.service.StudentService;
import com.ecanteen.service.WorkerService;
import com.ecanteen.service.dto.StudentDTO;
import com.ecanteen.service.dto.WorkerDTO;
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

public class WorkerResource {

    private static final String ENTITY_NAME = "worker";
    private final Logger log = LoggerFactory.getLogger(WorkerResource.class);


    private final WorkerService workerService;

    private final WorkerRepository workerRepository;
    @Autowired
    public WorkerResource(WorkerService workerService, WorkerRepository workerRepository) {
        this.workerService = workerService;
        this.workerRepository = workerRepository;
    }


    @PostMapping("/workers")
    public ResponseEntity<WorkerDTO> createWorker(@RequestBody WorkerDTO workerDTO) throws URISyntaxException {
        log.debug("REST request to save  worker : {}", workerDTO);
        if (workerDTO.getId() != null) {
            throw new BadRequestAlertException("A new student cannot already have an ID", ENTITY_NAME, "id-exists");
        }
        WorkerDTO result = workerService.save(workerDTO);
        return ResponseEntity
            .created(new URI("/api/workers/" + result.getId()))
            .body(result);
    }



    @GetMapping("/workers")
    public ResponseEntity<List<WorkerDTO>> getAllWorkers(Pageable pageable) {
        Page<WorkerDTO> page = workerService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }



    @PutMapping("/workers/{id}")
    public ResponseEntity<WorkerDTO> updateWorker(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody WorkerDTO workerDTO
    ) throws URISyntaxException {
        log.debug("REST request to update worker : {}, {}", id, workerDTO);
        if (workerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        WorkerDTO result = workerService.update(workerDTO);
        return ResponseEntity
            .ok()
            .body(result);
    }


    @DeleteMapping("/workers/{id}")
    public ResponseEntity<Void> deleteWorker(@PathVariable Long id) {
        log.debug("REST request to delete worker : {}", id);
        workerService.delete(id);
        return ResponseEntity
            .noContent()
            .build();
    }

    }

