package com.ecanteen.service.mapper;



import com.ecanteen.domain.Product;
import com.ecanteen.domain.ProductCategory;
import com.ecanteen.domain.Student;

import com.ecanteen.service.dto.ProductCategoryDTO;
import com.ecanteen.service.dto.ProductDTO;
import com.ecanteen.service.dto.StudentDTO;
import org.mapstruct.Mapper;

import java.util.List;


/**
 * Mapper for the entity {@link Student} and its DTO {@link StudentDTO}.
 */
@Mapper(componentModel = "spring" ,imports = { Student.class, StudentDTO.class })
public interface StudentMapper extends EntityMapper<StudentDTO, Student> {


}


