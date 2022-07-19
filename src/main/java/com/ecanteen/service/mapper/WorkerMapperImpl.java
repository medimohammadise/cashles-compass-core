package com.ecanteen.service.mapper;


import com.ecanteen.domain.Worker;

import com.ecanteen.service.dto.WorkerDTO;

import java.util.ArrayList;
import java.util.List;

public class WorkerMapperImpl implements WorkerMapper {
    @Override
    public Worker toEntity(WorkerDTO dto) {
        if (dto == null) {
            return null;
        }
        Worker worker = new Worker();

        worker.setId(dto.getId());
        worker.setName(dto.getName());
        worker.setImageUrl(dto.getImageUrl());
        worker.setComment(dto.getComment());
        worker.setAddress(dto.getAddress());
        worker.setIsApproved(dto.getApproved());
        worker.setRegNo(dto.getRegNo());
        worker.setCreatedBy(dto.getCreatedBy());
        worker.setCreatedDate(dto.getModifiedDate());
        worker.setModifiedBy(dto.getModifiedBy());

        worker.setModifiedDate(dto.getModifiedDate());


        return worker;
    }

    @Override
    public WorkerDTO toDto(Worker entity) {
        if (entity == null) {
            return null;
        }

        WorkerDTO workerDTO = new WorkerDTO();

        workerDTO.setId(entity.getId());
        workerDTO.setName(entity.getName());
        workerDTO.setImageUrl(entity.getImageUrl());
        workerDTO.setComment(entity.getComment());
        workerDTO.setAddress(entity.getAddress());
        workerDTO.setApproved(entity.getIsApproved());
        workerDTO.setRegNo(entity.getRegNo());
        workerDTO.setCreatedBy(entity.getCreatedBy());
        workerDTO.setCreatedDate(entity.getCreatedDate());
        workerDTO.setModifiedBy(entity.getModifiedBy());
        workerDTO.setModifiedDate(entity.getModifiedDate());

        return workerDTO;
    }


    @Override
    public List<Worker> toEntity(List<WorkerDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }

        List<Worker> list = new ArrayList<Worker>(dtoList.size());
        for (WorkerDTO workerDTO : dtoList) {
            list.add(toEntity(workerDTO));
        }

        return list;
    }

    @Override
    public List<WorkerDTO> toDto(List<Worker> entityList) {
        if (entityList == null) {
            return null;
        }

        List<WorkerDTO> list = new ArrayList<WorkerDTO>(entityList.size());
        for (Worker worker : entityList) {
            list.add(toDto(worker));
        }

        return list;
    }

    @Override
    public void partialUpdate(Worker entity, WorkerDTO dto) {
        if (dto == null) {
            return;
        }

        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        if (dto.getName() != null) {
            entity.name(dto.getName());
        }

        if (dto.getComment() != null) {
            entity.comment(dto.getComment());
        }
        if (dto.getApproved() != null) {
            entity.isApproved(dto.getApproved());
        }
        if (dto.getImageUrl() != null) {
            entity.imageUrl(dto.getImageUrl());
        }
        if (dto.getAddress() != null) {
            entity.address(dto.getAddress());
        }
        if (dto.getRegNo() != null) {
            entity.regNo(dto.getRegNo());
        }

        if (dto.getCreatedDate() != null) {
            entity.createdDate(dto.getCreatedDate());
        }
        if (dto.getModifiedDate() != null) {
            entity.modifiedDate(dto.getModifiedDate());
        }
        if (dto.getCreatedBy() != null) {
            entity.createdBy(dto.getCreatedBy());
        }
        if (dto.getModifiedBy() != null) {
            entity.modifiedBy(dto.getModifiedBy());
        }


    }
}
