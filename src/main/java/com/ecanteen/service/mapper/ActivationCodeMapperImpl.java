package com.ecanteen.service.mapper;

import com.ecanteen.domain.ActivationCode;
import com.ecanteen.service.dto.ActivationCodeDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ActivationCodeMapperImpl implements ActivationCodeMapper {

    @Override
    public ActivationCode toEntity(ActivationCodeDTO dto) {
        if (dto == null) {
            return null;
        }
        ActivationCode activationCode = new ActivationCode();

        activationCode.setId(dto.getId());
        activationCode.setCode(dto.getCode());
        activationCode.setExpiryTime(dto.getExpiryTime());
        activationCode.setCreatedDate(dto.getCreatedDate());
        activationCode.setCreatedBy(dto.getCreatedBy());

        return activationCode;
    }

    @Override
    public ActivationCodeDTO toDto(ActivationCode entity) {
        if (entity == null) {
            return null;
        }

        ActivationCodeDTO activationCodeDTO = new ActivationCodeDTO();

        activationCodeDTO.setId(entity.getId());
        activationCodeDTO.setCode(entity.getCode());
        activationCodeDTO.setExpiryTime(entity.getExpiryTime());
        activationCodeDTO.setCreatedDate(entity.getCreatedDate());
        activationCodeDTO.setCreatedBy(entity.getCreatedBy());

        return activationCodeDTO;
    }


    @Override
    public List<ActivationCode> toEntity(List<ActivationCodeDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }

        List<ActivationCode> list = new ArrayList<ActivationCode>(dtoList.size());
        for (ActivationCodeDTO activationCodeDTO : dtoList) {
            list.add(toEntity(activationCodeDTO));
        }

        return list;
    }

    @Override
    public List<ActivationCodeDTO> toDto(List<ActivationCode> entityList) {
        if (entityList == null) {
            return null;
        }

        List<ActivationCodeDTO> list = new ArrayList<ActivationCodeDTO>(entityList.size());
        for (ActivationCode activationCode: entityList) {
            list.add(toDto(activationCode));
        }

        return list;
    }

    @Override
    public void partialUpdate(ActivationCode entity, ActivationCodeDTO dto) {
        if (dto == null) {
            return;
        }

        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        if (dto.getCode() != null) {
            entity.code(dto.getCode());
        }
        if (dto.getExpiryTime() != null) {
            entity.expiryTime(dto.getExpiryTime());
        }
        if (dto.getCreatedDate() != null) {
            entity.createdDate(dto.getCreatedDate());
        }
        if (dto.getCreatedBy() != null) {
            entity.createdBy(dto.getCreatedBy());
        }

    }
}
