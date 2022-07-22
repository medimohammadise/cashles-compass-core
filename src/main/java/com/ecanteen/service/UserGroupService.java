package com.ecanteen.service;

import com.ecanteen.service.dto.UserGroupDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserGroupService {

    UserGroupDTO save(UserGroupDTO userGroupDTO);


    UserGroupDTO update(UserGroupDTO userGroupDTO);


    Optional<UserGroupDTO> partialUpdate(UserGroupDTO userGroupDTO);


    Optional<UserGroupDTO> findOne(Long id);


    Page<UserGroupDTO> findAll(Pageable pageable);


    void delete(Long id);
}
