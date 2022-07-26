package com.ecanteen.service.mapper;


 import com.ecanteen.domain.Order;
 import com.ecanteen.domain.Parent;
 import com.ecanteen.service.dto.OrderDTO;
 import com.ecanteen.service.dto.ParentDTO;
import org.mapstruct.Mapper;

 import java.util.List;


@Mapper(componentModel = "spring",imports = { Parent.class, ParentDTO.class })
public interface ParentMapper extends EntityMapper<ParentDTO, Parent> {

}
