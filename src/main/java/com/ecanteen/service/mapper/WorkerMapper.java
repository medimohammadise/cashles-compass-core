package com.ecanteen.service.mapper;


 import com.ecanteen.domain.UserGroup;
 import com.ecanteen.domain.Worker;
 import com.ecanteen.service.dto.UserGroupDTO;
 import com.ecanteen.service.dto.WorkerDTO;
import org.mapstruct.Mapper;

 import java.util.List;

@Mapper(componentModel = "spring",uses = {}  ,imports = { Worker.class, WorkerDTO.class })
public interface WorkerMapper extends EntityMapper <WorkerDTO, Worker> {




}

