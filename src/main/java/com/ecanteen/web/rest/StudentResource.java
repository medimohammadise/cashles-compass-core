package com.ecanteen.web.rest;


import com.ecanteen.repository.StudentRepository;
import com.ecanteen.service.StudentService;
import com.ecanteen.service.dto.StudentDTO;

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


/**
 * REST controller for managing {@link com.ecanteen.domain.Student}.
 */


@RestController
@RequestMapping("/api")
public class StudentResource {
    private static final String ENTITY_NAME = "student";
    private final Logger log = LoggerFactory.getLogger(StudentResource.class);

    private final StudentService studentService;

    private final StudentRepository studentRepository;
    @Autowired
    public StudentResource(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    /**
     * {@code POST  /students} : Create a new student.
     * @param studentDTO the studentsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new studentDTO, or with status {@code 400 (Bad Request)} if the student has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/students")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) throws URISyntaxException {
        log.debug("REST request to save  student : {}", studentDTO);
        if (studentDTO.getId() != null) {
            throw new BadRequestAlertException("A new student cannot already have an ID", ENTITY_NAME, "id-exists");
        }
        StudentDTO result = studentService.save(studentDTO);
        return ResponseEntity
            .created(new URI("/api/students/" + result.getId()))
            .body(result);
    }


    /**
     * {@code GET  /students} : get all the students.
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of students in body.
     */
    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> getAllStudents(Pageable pageable) {
        Page<StudentDTO> page = studentService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }


    /**
     * {@code PUT  /students/:id} : Updates an existing student.
     * @param id the id of the studentDTO to save.
     * @param studentDTO the  studentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated studentDTO,
     * or with status {@code 400 (Bad Request)} if the studentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the studentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/students/{id}")
    public ResponseEntity<StudentDTO> updateStudent(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody StudentDTO studentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update student : {}, {}", id, studentDTO);
        if (studentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, studentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!studentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        StudentDTO result = studentService.update(studentDTO);
        return ResponseEntity
            .ok()
            .body(result);
    }

    /**
     * {@code DELETE  /students/:id} : delete the "id" student.
     * @param id the id of the studentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        log.debug("REST request to delete student : {}", id);
        studentService.delete(id);
        return ResponseEntity
            .noContent()
            .build();
    }
}

