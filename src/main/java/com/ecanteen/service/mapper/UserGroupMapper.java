package com.ecanteen.service.mapper;

import com.ecanteen.domain.Menu;
import com.ecanteen.domain.UserGroup;
import com.ecanteen.service.dto.MenuDTO;
import com.ecanteen.service.dto.UserGroupDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")

public interface UserGroupMapper extends EntityMapper<UserGroupDTO, UserGroup>{}
