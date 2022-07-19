package com.ecanteen.service.impl;

import com.ecanteen.domain.School;
import com.ecanteen.repository.SchoolRepository;
import com.ecanteen.service.SchoolService;
import com.ecanteen.service.dto.SchoolDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link School}.
 */
@Service
@Transactional
public class SchoolServiceImpl implements SchoolService {

    private final Logger log = LoggerFactory.getLogger(SchoolServiceImpl.class);

    private final SchoolRepository schoolRepository;


    public SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public School save(School school) {
        log.debug("Request to save School : {}", school);
        return schoolRepository.save(school);
    }

    @Override
    public SchoolDTO save(SchoolDTO schoolDTO) {
        return null;
    }
}
