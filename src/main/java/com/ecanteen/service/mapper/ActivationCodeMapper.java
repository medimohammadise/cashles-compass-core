package com.ecanteen.service.mapper;

import com.ecanteen.domain.ActivationCode;
 import com.ecanteen.service.dto.ActivationCodeDTO;
 import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")

public interface ActivationCodeMapper  extends EntityMapper<ActivationCodeDTO, ActivationCode>{}
