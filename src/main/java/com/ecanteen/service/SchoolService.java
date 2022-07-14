package com.ecanteen.service;

import com.ecanteen.service.dto.SchoolDTO;

/**
 * Service Interface for managing {@link com.ecanteen.domain.School}.
 */
public interface SchoolService {
    /**
     * Save a school.
     *
     * @param schoolDTO the entity to save.
     * @return the persisted entity.
     */
    SchoolDTO save(SchoolDTO schoolDTO);

}
