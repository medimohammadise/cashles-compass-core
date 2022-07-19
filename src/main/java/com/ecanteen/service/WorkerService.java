package com.ecanteen.service;

 import com.ecanteen.service.dto.WorkerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface WorkerService {


    WorkerDTO save(WorkerDTO workerDTO);


    WorkerDTO update(WorkerDTO workerDTO);


    Optional<WorkerDTO> partialUpdate(WorkerDTO workerDTO);



    Optional<WorkerDTO> findOne(Long id);

    Page<WorkerDTO> findAll(Pageable pageable);


    void delete(Long id);
}
