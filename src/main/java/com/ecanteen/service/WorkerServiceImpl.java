package com.ecanteen.service;

import com.ecanteen.domain.Worker;
import com.ecanteen.repository.WorkerRepository;
import com.ecanteen.service.dto.WorkerDTO;
import com.ecanteen.service.mapper.WorkerMapper;
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
public class WorkerServiceImpl implements WorkerService {


    private final Logger log = LoggerFactory.getLogger(WorkerServiceImpl.class);


    private final WorkerMapper workerMapper;
    private final WorkerRepository workerRepository;


    @Autowired
    public WorkerServiceImpl(WorkerRepository workerRepository, WorkerMapper workerMapper) {
        this.workerRepository = workerRepository;
        this.workerMapper = workerMapper;
    }

    @Override
    public WorkerDTO save(WorkerDTO workerDTO) {
        log.debug("Request to save workers : {}", workerMapper);
        Worker worker = workerMapper.toEntity(workerDTO);
        worker = workerRepository.save(worker);
        return workerMapper.toDto(worker);
    }

    @Override
    public WorkerDTO update(WorkerDTO workerDTO) {
        log.debug("Request to save workers : {}", workerDTO);
        Worker worker = workerMapper.toEntity(workerDTO);
        worker = workerRepository.save(worker);
        return workerMapper.toDto(worker);
    }

    @Override
    public Optional<WorkerDTO> partialUpdate(WorkerDTO workerDTO) {
        log.debug("Request to partially update Workers : {}", workerDTO);

        return workerRepository
            .findById(workerDTO.getId())
            .map(existingWorkers -> {
                workerMapper.partialUpdate(existingWorkers, workerDTO);

                return existingWorkers;
            })
            .map(workerRepository::save)
            .map(workerMapper::toDto);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Page<WorkerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Workers");
        return workerRepository.findAll(pageable).map(workerMapper::toDto);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Optional<WorkerDTO> findOne(Long id) {
        log.debug("Request to get Worker: {}", id);
        return workerRepository.findById(id).map(workerMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Worker : {}", id);
        workerRepository.deleteById(id);
    }
}
