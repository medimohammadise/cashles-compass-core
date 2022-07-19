package com.ecanteen.service.mapper;


import com.ecanteen.domain.Menu;
import com.ecanteen.service.dto.MenuDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link com.ecanteen.domain.Menu} and its DTO {@link com.ecanteen.service.dto.MenuDTO}.
 */

@Mapper(componentModel = "spring")
public interface MenuMapper extends EntityMapper<MenuDTO, Menu>{}


