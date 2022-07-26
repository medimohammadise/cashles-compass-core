package com.ecanteen.service.impl;

import com.ecanteen.domain.Parent;
import com.ecanteen.repository.ParentRepository;
import com.ecanteen.service.ParentService;
import com.ecanteen.service.dto.ParentDTO;
import com.ecanteen.service.mapper.ParentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional
public class ParentServiceImpl implements ParentService {


    private final Logger log = LoggerFactory.getLogger(ParentServiceImpl.class);


    private final ParentMapper parentMapper;
    private final ParentRepository parentRepository;


    @Autowired
    public ParentServiceImpl(ParentRepository parentRepository, ParentMapper parentMapper) {
        this.parentRepository = parentRepository;
        this.parentMapper = parentMapper;
    }

    @Override
    public ParentDTO save(ParentDTO parentDTO) {
        log.debug("Request to save students : {}", parentMapper);
        Parent parent = parentMapper.toEntity(parentDTO);
        parent = parentRepository.save(parent);
        return parentMapper.toDto(parent);
    }

    @Override
    public ParentDTO update(ParentDTO parentDTO) {
        log.debug("Request to save students : {}", parentDTO);
        Parent parent = parentMapper.toEntity(parentDTO);
        parent = parentRepository.save(parent);
        return parentMapper.toDto(parent);
    }

    @Override
    public Optional<ParentDTO> partialUpdate(ParentDTO parentDTO) {
        log.debug("Request to partially update parents : {}", parentDTO);

        return parentRepository
            .findById(parentDTO.getId())
            .map(existingSchool -> {
                parentMapper.partialUpdate(existingSchool, parentDTO);

                return existingSchool;
            })
            .map(parentRepository::save)
            .map(parentMapper::toDto);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Page<ParentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all parents");
        return parentRepository.findAll(pageable).map(parentMapper::toDto);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Optional<ParentDTO> findOne(Long id) {
        log.debug("Request to get parent : {}", id);
        return parentRepository.findById(id).map(parentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete parent : {}", id);
        parentRepository.deleteById(id);
    }
}
