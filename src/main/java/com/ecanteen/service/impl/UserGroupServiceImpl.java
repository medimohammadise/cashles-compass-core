package com.ecanteen.service.impl;


import com.ecanteen.domain.UserGroup;
import com.ecanteen.repository.UserGroupRepository;


import com.ecanteen.service.UserGroupService;
import com.ecanteen.service.dto.UserGroupDTO;
import com.ecanteen.service.mapper.UserGroupMapper;
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
public class UserGroupServiceImpl implements UserGroupService {

    private final Logger log = LoggerFactory.getLogger(UserGroupServiceImpl.class);


    private final UserGroupMapper userGroupMapper;
    private final UserGroupRepository userGroupRepository;


    @Autowired
    public UserGroupServiceImpl(UserGroupRepository userGroupRepository, UserGroupMapper userGroupMapper) {
        this.userGroupRepository = userGroupRepository;
        this.userGroupMapper = userGroupMapper;
    }

    @Override
    public UserGroupDTO save(UserGroupDTO userGroupDTO) {
        log.debug("Request to save userGroups : {}", userGroupMapper);
        UserGroup userGroup = userGroupMapper.toEntity(userGroupDTO);
        userGroup = userGroupRepository.save(userGroup);
        return userGroupMapper.toDto(userGroup);
    }

    @Override
    public UserGroupDTO update(UserGroupDTO userGroupDTO) {
        log.debug("Request to save userGroups : {}", userGroupDTO);
        UserGroup userGroup = userGroupMapper.toEntity(userGroupDTO);
        userGroup = userGroupRepository.save(userGroup);
        return userGroupMapper.toDto(userGroup);
    }

    @Override
    public Optional<UserGroupDTO> partialUpdate(UserGroupDTO userGroupDTO) {
        log.debug("Request to partially update userGroups : {}", userGroupDTO);

        return userGroupRepository
            .findById(userGroupDTO.getId())
            .map(existingUserGroup -> {
                userGroupMapper.partialUpdate(existingUserGroup, userGroupDTO);

                return existingUserGroup;
            })
            .map(userGroupRepository::save)
            .map(userGroupMapper::toDto);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Page<UserGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all userGroups");
        return userGroupRepository.findAll(pageable).map(userGroupMapper::toDto);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Optional<UserGroupDTO> findOne(Long id) {
        log.debug("Request to get userGroups : {}", id);
        return userGroupRepository.findById(id).map(userGroupMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete userGroups : {}", id);
        userGroupRepository.deleteById(id);
    }
}
