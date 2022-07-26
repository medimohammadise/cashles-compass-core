package com.ecanteen.service.mapper;

import com.ecanteen.domain.Transaction;
import com.ecanteen.domain.UserCredential;
import com.ecanteen.service.dto.TransactionDTO;
import com.ecanteen.service.dto.UserCredentialDTO;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring",imports = { UserCredential.class, UserCredentialDTO.class })

public interface UserCredentialMapper extends EntityMapper<UserCredentialDTO, UserCredential> {


}
