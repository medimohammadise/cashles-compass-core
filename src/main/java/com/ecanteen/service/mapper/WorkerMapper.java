package com.ecanteen.service.mapper;


 import com.ecanteen.domain.Worker;
 import com.ecanteen.service.dto.WorkerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkerMapper extends EntityMapper <WorkerDTO, Worker> {}

