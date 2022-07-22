package com.ecanteen.service.mapper;

import com.ecanteen.domain.Account;
 import com.ecanteen.service.dto.AccountDTO;
 import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")

public interface AccountMapper  extends EntityMapper<AccountDTO, Account>{}

