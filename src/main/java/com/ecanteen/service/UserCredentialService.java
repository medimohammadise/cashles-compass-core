package com.ecanteen.service;

 import com.ecanteen.service.dto.UserCredentialDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserCredentialService {

    UserCredentialDTO save(UserCredentialDTO userCredentialDTO);


    UserCredentialDTO update(UserCredentialDTO userCredentialDTO);


    Optional<UserCredentialDTO> partialUpdate(UserCredentialDTO userCredentialDTO);


    Optional<UserCredentialDTO> findOne(Long id);


    Page<UserCredentialDTO> findAll(Pageable pageable);


    void delete(Long id);
}
