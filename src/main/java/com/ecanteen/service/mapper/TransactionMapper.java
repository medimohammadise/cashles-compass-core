package com.ecanteen.service.mapper;

 import com.ecanteen.domain.Transaction;
 import com.ecanteen.service.dto.TransactionDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")

public interface TransactionMapper extends EntityMapper<TransactionDTO, Transaction>{}
