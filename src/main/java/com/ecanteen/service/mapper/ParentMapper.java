package com.ecanteen.service.mapper;


 import com.ecanteen.domain.Parent;
 import com.ecanteen.service.dto.ParentDTO;
import org.mapstruct.Mapper;




@Mapper(componentModel = "spring")
public interface ParentMapper extends EntityMapper<ParentDTO, Parent> {
}
