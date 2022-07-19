package com.ecanteen.service.mapper;


import com.ecanteen.domain.Parent;

import com.ecanteen.service.dto.ParentDTO;


import java.util.ArrayList;
import java.util.List;

public class ParentMapperImpl implements ParentMapper {
    @Override
    public Parent toEntity(ParentDTO dto) {
        if (dto == null) {
            return null;
        }
        Parent parent = new Parent();

        parent.setId(dto.getId());

        parent.setFullName(dto.getFullName());
        parent.setEmail(dto.getEmail());
        parent.setPhoneNumber(dto.getPhoneNumber());
        parent.setImageUrl(dto.getImageUrl());
        parent.setAddress(dto.getAddress());
        parent.setEnabled(dto.getEnabled());
        parent.setPhoneVerified(dto.getPhoneVerified());
        parent.setEmailVerified(dto.getEmailVerified());
        parent.setCreatedDate(dto.getCreatedDate());
        parent.setKkUesId(dto.getKkUesId());
        parent.setModifiedDate(dto.getModifiedDate());
        parent.setCreatedBy(dto.getCreatedBy());
        parent.setModifiedBy(dto.getModifiedBy());
        parent.setRole(dto.getRole());
        return parent;
    }

    @Override
    public ParentDTO toDto(Parent entity) {
        if (entity == null) {
            return null;
        }

        ParentDTO parentDTO = new ParentDTO();



        parentDTO.setFullName(entity.getFullName());
        parentDTO.setEmail(entity.getEmail());
        parentDTO.setPhoneNumber(entity.getPhoneNumber());
        parentDTO.setImageUrl(entity.getImageUrl());
        parentDTO.setAddress(entity.getAddress());
        parentDTO.setEnabled(entity.getEnabled());
        parentDTO.setPhoneVerified(entity.getPhoneVerified());
        parentDTO.setEmailVerified(entity.getEmailVerified());
        parentDTO.setCreatedDate(entity.getCreatedDate());
        parentDTO.setKkUesId(entity.getKkUesId());
        parentDTO.setModifiedDate(entity.getModifiedDate());
        parentDTO.setCreatedBy(entity.getCreatedBy());
        parentDTO.setModifiedBy(entity.getModifiedBy());
        parentDTO.setRole(entity.getRole());
        return parentDTO;
    }


    @Override
    public List<Parent> toEntity(List<ParentDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }

        List<Parent> list = new ArrayList<Parent>(dtoList.size());
        for (ParentDTO parentDTO : dtoList) {
            list.add(toEntity(parentDTO ));
        }

        return list;
    }

    @Override
    public List<ParentDTO> toDto(List<Parent> entityList) {
        if (entityList == null) {
            return null;
        }

        List<ParentDTO> list = new ArrayList<ParentDTO>(entityList.size());
        for (Parent parent : entityList) {
            list.add(toDto(parent));
        }

        return list;
    }

    @Override
    public void partialUpdate(Parent entity, ParentDTO dto) {
        if (dto == null) {
            return;
        }

        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        if (dto.getFullName() != null) {
            entity.fullName(dto.getFullName());
        }
        if (dto.getAddress() != null) {
            entity.address(dto.getAddress());
        }
        if (dto.getImageUrl() != null) {
            entity.imageUrl(dto.getImageUrl());
        }
        if (dto.getEmailVerified() != null) {
            entity.emailVerified(dto.getEmailVerified());
        }
        if (dto.getPhoneNumber() != null) {
            entity.phoneNumber(dto.getPhoneNumber());
        }
        if (dto.getPhoneVerified() != null) {
            entity.phoneVerified(dto.getPhoneVerified());
        }
        if (dto.getEmail() != null) {

            entity.email(dto.getEmail());
        }
        if (dto.getEnabled() != null) {

            entity.enabled(dto.getEnabled());
        }
        if (dto.getKkUesId() != null) {

            entity.kkUesId(dto.getKkUesId());
        }
        if (dto.getRole() != null) {

            entity.role(dto.getRole().toString());
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
