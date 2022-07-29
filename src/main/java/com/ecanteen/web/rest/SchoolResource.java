package com.ecanteen.web.rest;

import com.ecanteen.repository.SchoolRepository;
import com.ecanteen.service.SchoolService;
import com.ecanteen.service.dto.SchoolDTO;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

import com.ecanteen.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * REST controller for managing {@link com.ecanteen.domain.School}.
 */
@RestController
@RequestMapping("/api")
public class SchoolResource {

    private final Logger log = LoggerFactory.getLogger(SchoolResource.class);

    private static final String ENTITY_NAME = "school";

    private String applicationName;

    private final SchoolService schoolService;

    private final SchoolRepository schoolRepository;


    public SchoolResource(SchoolService schoolService, SchoolRepository schoolRepository) {
        this.schoolService = schoolService;
        this.schoolRepository = schoolRepository;
    }

    /**
     * {@code POST  /schools} : Create a new school.
     *
     * @param schoolDTO the schoolDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new schoolDTO, or with status {@code 400 (Bad Request)} if the school has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/schools")
    public ResponseEntity<SchoolDTO> createSchool(@RequestBody SchoolDTO schoolDTO) throws URISyntaxException {
        log.debug("REST request to save School : {}", schoolDTO);
        if (schoolDTO.getId() != null) {
            throw new BadRequestAlertException("A new school cannot already have an ID", ENTITY_NAME, "id-exists");
        }
        SchoolDTO result = schoolService.save(schoolDTO);
        return ResponseEntity
            .created(new URI("/api/schools/" + result.getId()))
            .body(result);
    }
    /**
     * {@code GET  /schools} : get all the schools.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of schools in body.
     */
    @GetMapping("/schools")
    public ResponseEntity<List<SchoolDTO>> getAllSchools(Pageable pageable) {
        Page<SchoolDTO> page = schoolService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    /**
     * {@code DELETE  /schools/:id} : delete the "id" school.
     *
     * @param id the id of the schoolDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */

    /**
     * {@code PUT  /schools/:id} : Updates an existing school.
     *
     * @param id the id of the schoolDTO to save.
     * @param schoolDTO the schoolDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated schoolDTO,
     * or with status {@code 400 (Bad Request)} if the schoolDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the schoolDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/schools/{id}")
    public ResponseEntity<SchoolDTO> updateSchool(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SchoolDTO schoolDTO
    ) throws URISyntaxException {
        log.debug("REST request to update School : {}, {}", id, schoolDTO);
        if (schoolDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, schoolDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!schoolRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SchoolDTO result = schoolService.update(schoolDTO);
        return ResponseEntity
            .ok()
            .body(result);
    }
    @DeleteMapping("/schools/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
        log.debug("REST request to delete School : {}", id);
        schoolService.delete(id);
        return ResponseEntity
            .noContent()
            .build();
    }

}
