package com.ecanteen.service.mapper;


import com.ecanteen.domain.Student;
import com.ecanteen.service.dto.StudentDTO;
import org.mapstruct.Mapper;


/**
 * Mapper for the entity {@link Student} and its DTO {@link StudentDTO}.
 */
@Mapper(componentModel = "spring")
public interface StudentMapper extends EntityMapper<StudentDTO, Student> {}


