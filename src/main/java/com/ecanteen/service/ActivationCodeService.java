package com.ecanteen.service;

 import com.ecanteen.service.dto.ActivationCodeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ActivationCodeService {

    ActivationCodeDTO save(ActivationCodeDTO activationCodeDTO);


    ActivationCodeDTO update(ActivationCodeDTO activationCodeDTO);


    Optional<ActivationCodeDTO> partialUpdate(ActivationCodeDTO activationCodeDTO);


    Optional<ActivationCodeDTO> findOne(Long id);


    Page<ActivationCodeDTO> findAll(Pageable pageable);


    void delete(Long id);
}
