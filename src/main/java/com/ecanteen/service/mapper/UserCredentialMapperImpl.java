package com.ecanteen.service.mapper;


 import com.ecanteen.domain.UserCredential;
 import com.ecanteen.service.dto.UserCredentialDTO;
 import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserCredentialMapperImpl implements UserCredentialMapper {

    @Override
    public UserCredential toEntity(UserCredentialDTO dto) {
        if (dto == null) {
            return null;
        }
        UserCredential userCredential = new UserCredential();

        userCredential.setId(dto.getId());
        userCredential.setUserName(dto.getUserName());
        userCredential.setCreatedDate(dto.getCreatedDate());

        return userCredential;
    }

    @Override
    public UserCredentialDTO toDto(UserCredential entity) {
        if (entity == null) {
            return null;
        }

        UserCredentialDTO userCredentialDTO = new UserCredentialDTO();

        userCredentialDTO.setId(entity.getId());
        userCredentialDTO.setUserName(entity.getUserName());
        userCredentialDTO.setCreatedDate(entity.getCreatedDate());


        return userCredentialDTO;
    }


    @Override
    public List<UserCredential> toEntity(List<UserCredentialDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }

        List<UserCredential> list = new ArrayList<UserCredential>(dtoList.size());
        for (UserCredentialDTO userCredentialDTO : dtoList) {
            list.add(toEntity(userCredentialDTO));
        }

        return list;
    }

    @Override
    public List<UserCredentialDTO> toDto(List<UserCredential> entityList) {
        if (entityList == null) {
            return null;
        }

        List<UserCredentialDTO> list = new ArrayList<UserCredentialDTO>(entityList.size());
        for (UserCredential userCredential: entityList) {
            list.add(toDto(userCredential));
        }

        return list;
    }

    @Override
    public void partialUpdate(UserCredential entity, UserCredentialDTO dto) {
        if (dto == null) {
            return;
        }

        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        if (dto.getCreatedDate() != null) {
            entity.createdDate(dto.getCreatedDate());
        }
        if (dto.getUserName() != null) {
            entity.userName(dto.getUserName());
        }


    }
}
