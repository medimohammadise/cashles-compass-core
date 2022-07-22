package com.ecanteen.service;

 import com.ecanteen.service.dto.ParentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ParentService {


    ParentDTO save(ParentDTO parentDTO);


    ParentDTO update(ParentDTO parentDTO);


    Optional<ParentDTO> partialUpdate(ParentDTO parentDTO);


    Optional<ParentDTO> findOne(Long id);


    Page<ParentDTO> findAll(Pageable pageable);


    void delete(Long id);
}
