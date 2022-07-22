package com.ecanteen.service.mapper;


 import com.ecanteen.domain.UserGroup;
 import com.ecanteen.service.dto.UserGroupDTO;
 import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserGroupMapperImpl implements UserGroupMapper {

    @Override
    public UserGroup toEntity(UserGroupDTO dto) {
        if (dto == null) {
            return null;
        }
        UserGroup userGroup = new UserGroup();

        userGroup.setId(dto.getId());
        userGroup.setName(dto.getName());
        userGroup.setCode(dto.getCode());


        return userGroup;
    }

    @Override
    public UserGroupDTO toDto(UserGroup entity) {
        if (entity == null) {
            return null;
        }

        UserGroupDTO userGroupDTO = new UserGroupDTO();

        userGroupDTO.setId(entity.getId());
        userGroupDTO.setName(entity.getName());
        userGroupDTO.setCode(entity.getCode());


        return userGroupDTO;
    }


    @Override
    public List<UserGroup> toEntity(List<UserGroupDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }

        List<UserGroup> list = new ArrayList<UserGroup>(dtoList.size());
        for (UserGroupDTO userGroupDTO : dtoList) {
            list.add(toEntity(userGroupDTO));
        }

        return list;
    }

    @Override
    public List<UserGroupDTO> toDto(List<UserGroup> entityList) {
        if (entityList == null) {
            return null;
        }

        List<UserGroupDTO> list = new ArrayList<UserGroupDTO>(entityList.size());
        for (UserGroup userGroup: entityList) {
            list.add(toDto(userGroup));
        }

        return list;
    }

    @Override
    public void partialUpdate(UserGroup entity, UserGroupDTO dto) {
        if (dto == null) {
            return;
        }

        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        if (dto.getName() != null) {
            entity.name(dto.getName());
        }
        if (dto.getCode() != null) {
            entity.code(dto.getCode());
        }

    }
}
