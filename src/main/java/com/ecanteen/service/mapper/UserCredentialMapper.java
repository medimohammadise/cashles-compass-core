package com.ecanteen.service.mapper;

import com.ecanteen.domain.UserCredential;
import com.ecanteen.service.dto.UserCredentialDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")

public interface UserCredentialMapper extends EntityMapper<UserCredentialDTO, UserCredential> {
}
