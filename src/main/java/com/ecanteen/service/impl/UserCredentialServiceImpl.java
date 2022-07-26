package com.ecanteen.service.impl;

  import com.ecanteen.domain.UserCredential;
  import com.ecanteen.repository.UserCredentialRepository;
  import com.ecanteen.service.UserCredentialService;
  import com.ecanteen.service.dto.UserCredentialDTO;
  import com.ecanteen.service.mapper.UserCredentialMapper;
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

public class UserCredentialServiceImpl implements UserCredentialService {

    private final Logger log = LoggerFactory.getLogger(UserCredentialServiceImpl.class);


    private final UserCredentialMapper userCredentialMapper;
    private final UserCredentialRepository userCredentialRepository;



    @Autowired
    public UserCredentialServiceImpl(UserCredentialRepository userCredentialRepository, UserCredentialMapper userCredentialMapper) {
        this.userCredentialRepository = userCredentialRepository;
        this.userCredentialMapper = userCredentialMapper;
    }

    @Override
    public UserCredentialDTO save(UserCredentialDTO userCredentialDTO) {
        log.debug("Request to save UserCredential : {}", userCredentialMapper);
        UserCredential userCredential = userCredentialMapper.toEntity(userCredentialDTO);
        userCredential = userCredentialRepository.save(userCredential);
        return userCredentialMapper.toDto(userCredential);
    }

    @Override
    public UserCredentialDTO update(UserCredentialDTO userCredentialDTO) {
        log.debug("Request to save UserCredential : {}", userCredentialDTO);
        UserCredential userCredential = userCredentialMapper.toEntity(userCredentialDTO);
        userCredential = userCredentialRepository.save(userCredential);
        return userCredentialMapper.toDto(userCredential);
    }

    @Override
    public Optional<UserCredentialDTO> partialUpdate(UserCredentialDTO userCredentialDTO) {
        log.debug("Request to partially update UserCredential : {}", userCredentialDTO);

        return userCredentialRepository
            .findById(userCredentialDTO.getId())
            .map(existingUserCredential -> {
                userCredentialMapper.partialUpdate(existingUserCredential, userCredentialDTO);

                return existingUserCredential;
            })
            .map(userCredentialRepository::save)
            .map(userCredentialMapper::toDto);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Page<UserCredentialDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserCredential");
        return userCredentialRepository.findAll(pageable).map(userCredentialMapper::toDto);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Optional<UserCredentialDTO> findOne(Long id) {
        log.debug("Request to get UserCredential : {}", id);
        return userCredentialRepository.findById(id).map(userCredentialMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserCredential : {}", id);
        userCredentialRepository.deleteById(id);
    }
}
