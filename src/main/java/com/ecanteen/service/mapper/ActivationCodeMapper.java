package com.ecanteen.service.mapper;

import com.ecanteen.domain.Account;
import com.ecanteen.domain.ActivationCode;
import com.ecanteen.service.dto.AccountDTO;
import com.ecanteen.service.dto.ActivationCodeDTO;
 import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring" ,imports = { ActivationCode.class, ActivationCodeDTO.class })
public interface ActivationCodeMapper extends EntityMapper<ActivationCodeDTO, ActivationCode>{


}
