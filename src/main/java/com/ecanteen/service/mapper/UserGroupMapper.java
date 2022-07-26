package com.ecanteen.service.mapper;

import com.ecanteen.domain.Menu;
import com.ecanteen.domain.UserCredential;
import com.ecanteen.domain.UserGroup;
import com.ecanteen.service.dto.MenuDTO;
import com.ecanteen.service.dto.UserCredentialDTO;
import com.ecanteen.service.dto.UserGroupDTO;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring" ,imports = { UserGroup.class, UserGroupDTO.class })

public interface UserGroupMapper extends EntityMapper<UserGroupDTO, UserGroup>{


}
