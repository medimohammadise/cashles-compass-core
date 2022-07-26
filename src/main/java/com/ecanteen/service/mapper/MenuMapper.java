package com.ecanteen.service.mapper;


import com.ecanteen.domain.ActivationCode;
import com.ecanteen.domain.Menu;
import com.ecanteen.domain.Worker;
import com.ecanteen.service.dto.ActivationCodeDTO;
import com.ecanteen.service.dto.MenuDTO;
import com.ecanteen.service.dto.WorkerDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper for the entity {@link com.ecanteen.domain.Menu} and its DTO {@link com.ecanteen.service.dto.MenuDTO}.
 */

@Mapper(componentModel = "spring" ,imports = { Menu.class, MenuDTO.class })
public interface MenuMapper extends EntityMapper<MenuDTO, Menu> {

}


