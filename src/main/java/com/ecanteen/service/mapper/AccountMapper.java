package com.ecanteen.service.mapper;

import com.ecanteen.domain.Account;
import com.ecanteen.domain.Product;
import com.ecanteen.service.dto.AccountDTO;
import com.ecanteen.service.dto.ProductDTO;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring" ,imports = { Account.class, AccountDTO.class })

public interface AccountMapper  extends EntityMapper<AccountDTO, Account>{


}

