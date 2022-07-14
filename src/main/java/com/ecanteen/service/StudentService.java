package com.ecanteen.service;


import com.ecanteen.service.dto.StudentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;


/**
 * Service Interface for managing {@link com.ecanteen.domain.Student}.
 */

public interface StudentService {
    /**
     * Save a Student.
     *
     * @param studentDTO the entity to save.
     * @return the persisted entity.
     */
    StudentDTO save(StudentDTO studentDTO);

    /**
     * Updates a Student.
     *
     * @param studentDTO the entity to update.
     * @return the persisted entity.
     */
    StudentDTO update(StudentDTO studentDTO);

    /**
     * Partially updates a Student.
     *
     * @param studentDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<StudentDTO> partialUpdate(StudentDTO studentDTO);


    /**
     * Get the "id" Student.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StudentDTO> findOne(Long id);

    /**
     * Get all the Students.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<StudentDTO> findAll(Pageable pageable);


    /**
     * Delete the "id" Student.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}



