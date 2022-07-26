package com.ecanteen.service.mapper;

 import com.ecanteen.domain.Product;
 import com.ecanteen.domain.Student;
 import com.ecanteen.domain.Transaction;
 import com.ecanteen.service.dto.ProductDTO;
 import com.ecanteen.service.dto.StudentDTO;
 import com.ecanteen.service.dto.TransactionDTO;
import org.mapstruct.Mapper;

 import java.util.List;


@Mapper(componentModel = "spring" ,imports = { Transaction.class, TransactionDTO.class })

public interface TransactionMapper extends EntityMapper<TransactionDTO, Transaction>{


}
