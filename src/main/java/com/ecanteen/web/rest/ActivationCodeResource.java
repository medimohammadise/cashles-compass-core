package com.ecanteen.web.rest;


import com.ecanteen.repository.ActivationCodeRepository;
import com.ecanteen.service.ActivationCodeService;
import com.ecanteen.service.dto.ActivationCodeDTO;
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
public class ActivationCodeResource {
    private static final String ENTITY_NAME = "activationCode";
    private final Logger log = LoggerFactory.getLogger(ActivationCodeResource.class);

    private final ActivationCodeService activationCodeService;

    private final ActivationCodeRepository activationCodeRepository;

    @Autowired
    public ActivationCodeResource(ActivationCodeService activationCodeService, ActivationCodeRepository activationCodeRepository) {
        this.activationCodeService = activationCodeService;
        this.activationCodeRepository = activationCodeRepository;
    }


    @PostMapping("/activationCodes")
    public ResponseEntity<ActivationCodeDTO> createActivationCodes(@RequestBody ActivationCodeDTO activationCodeDTO) throws URISyntaxException {
        log.debug("REST request to save  ActivationCodes : {}", activationCodeDTO);
        if (activationCodeDTO.getId() != null) {
            throw new BadRequestAlertException("A new activationCodes cannot already have an ID", ENTITY_NAME, "id-exists");
        }
        ActivationCodeDTO result = activationCodeService.save(activationCodeDTO);
        return ResponseEntity
            .created(new URI("/api/activationCodes/" + result.getId()))
            .body(result);
    }


    @GetMapping("/activationCodes")
    public ResponseEntity<List<ActivationCodeDTO>> getAllActivationCodes(Pageable pageable) {
        Page<ActivationCodeDTO> page = activationCodeService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }


    @PutMapping("/activationCodes/{id}")
    public ResponseEntity<ActivationCodeDTO> updateActivationCodes(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ActivationCodeDTO activationCodeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ActivationCodeDTO : {}, {}", id, activationCodeDTO);
        if (activationCodeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, activationCodeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!activationCodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ActivationCodeDTO result = activationCodeService.update(activationCodeDTO);
        return ResponseEntity
            .ok()
            .body(result);
    }


    @DeleteMapping("/activationCodes/{id}")
    public ResponseEntity<Void> deleteActivationCodes(@PathVariable Long id) {
        log.debug("REST request to delete activationCodes : {}", id);
        activationCodeService.delete(id);
        return ResponseEntity
            .noContent()
            .build();
    }


}
