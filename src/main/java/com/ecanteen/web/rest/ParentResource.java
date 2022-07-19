package com.ecanteen.web.rest;


import com.ecanteen.repository.ParentRepository;
 import com.ecanteen.service.ParentService;

import com.ecanteen.service.dto.ParentDTO;

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

public class ParentResource {

    private static final String ENTITY_NAME = "parent";
    private final Logger log = LoggerFactory.getLogger(ParentResource.class);

    private final ParentService parentService;

    private final ParentRepository parentRepository;
    @Autowired
    public ParentResource(ParentService parentService, ParentRepository parentRepository) {
        this.parentService = parentService;
        this.parentRepository = parentRepository;
    }


    @PostMapping("/parents")
    public ResponseEntity<ParentDTO> createParent(@RequestBody ParentDTO parentDTO) throws URISyntaxException {
        log.debug("REST request to save  parent : {}", parentDTO);
        if (parentDTO.getId() != null) {
            throw new BadRequestAlertException("A new student cannot already have an ID", ENTITY_NAME, "id-exists");
        }
        ParentDTO result = parentService.save(parentDTO);
        return ResponseEntity
            .created(new URI("/api/parents/" + result.getId()))
            .body(result);
    }



    @GetMapping("/parents")
    public ResponseEntity<List<ParentDTO>> getAllParents(Pageable pageable) {
        Page<ParentDTO> page = parentService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }



    @PutMapping("/parents/{id}")
    public ResponseEntity<ParentDTO> updateParent(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ParentDTO parentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update parent : {}, {}", id, parentDTO);
        if (parentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, parentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!parentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ParentDTO result = parentService.update(parentDTO);
        return ResponseEntity
            .ok()
            .body(result);
    }

    @DeleteMapping("/parents/{id}")
    public ResponseEntity<Void> deleteParent(@PathVariable Long id) {
        log.debug("REST request to delete parent : {}", id);
        parentService.delete(id);
        return ResponseEntity
            .noContent()
            .build();
    }

}
