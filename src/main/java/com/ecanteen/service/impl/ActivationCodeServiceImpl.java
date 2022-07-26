package com.ecanteen.service.impl;


import com.ecanteen.domain.ActivationCode;
import com.ecanteen.repository.ActivationCodeRepository;
import com.ecanteen.service.ActivationCodeService;
import com.ecanteen.service.dto.ActivationCodeDTO;
import com.ecanteen.service.mapper.ActivationCodeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ActivationCodeServiceImpl implements ActivationCodeService {

    private final Logger log = LoggerFactory.getLogger(ActivationCodeServiceImpl.class);


    private final ActivationCodeMapper activationCodeMapper;
    private final ActivationCodeRepository activationCodeRepository;


    @Autowired
    public ActivationCodeServiceImpl(ActivationCodeRepository activationCodeRepository, ActivationCodeMapper activationCodeMapper) {
        this.activationCodeRepository = activationCodeRepository;
        this.activationCodeMapper = activationCodeMapper;
    }

    @Override
    public ActivationCodeDTO save(ActivationCodeDTO activationCodeDTO) {
        log.debug("Request to save ActivationsCodes : {}", activationCodeMapper);
        ActivationCode activationCode = activationCodeMapper.toEntity(activationCodeDTO);
        activationCode = activationCodeRepository.save(activationCode);
        return activationCodeMapper.toDto(activationCode);
    }

    @Override
    public ActivationCodeDTO update(ActivationCodeDTO activationCodeDTO) {
        log.debug("Request to save ActivationsCodes : {}", activationCodeDTO);
        ActivationCode activationCode = activationCodeMapper.toEntity(activationCodeDTO);
        activationCode = activationCodeRepository.save(activationCode);
        return activationCodeMapper.toDto(activationCode);
    }

    @Override
    public Optional<ActivationCodeDTO> partialUpdate(ActivationCodeDTO activationCodeDTO) {
        log.debug("Request to partially update ActivationsCodes : {}", activationCodeDTO);

        return activationCodeRepository
            .findById(activationCodeDTO.getId())
            .map(existingActivationCode -> {
                activationCodeMapper.partialUpdate(existingActivationCode, activationCodeDTO);

                return existingActivationCode;
            })
            .map(activationCodeRepository::save)
            .map(activationCodeMapper::toDto);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Page<ActivationCodeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ActivationsCodes");
        return activationCodeRepository.findAll(pageable).map(activationCodeMapper::toDto);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Optional<ActivationCodeDTO> findOne(Long id) {
        log.debug("Request to get ActivationsCodes : {}", id);
        return activationCodeRepository.findById(id).map(activationCodeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ActivationsCodes : {}", id);
        activationCodeRepository.deleteById(id);
    }
}
