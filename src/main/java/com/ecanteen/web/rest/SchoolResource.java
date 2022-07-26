package com.ecanteen.web.rest;

import com.ecanteen.repository.SchoolRepository;
import com.ecanteen.service.SchoolService;
import com.ecanteen.service.dto.SchoolDTO;
import java.net.URI;
import java.net.URISyntaxException;

import com.ecanteen.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
            throw new BadRequestAlertException("A new school cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SchoolDTO result = schoolService.save(schoolDTO);
        return ResponseEntity
            .created(new URI("/api/schools/" + result.getId()))
            .body(result);
    }
}
