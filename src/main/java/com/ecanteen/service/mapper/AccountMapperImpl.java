package com.ecanteen.service.mapper;


import com.ecanteen.domain.Account;
import com.ecanteen.service.dto.AccountDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Account toEntity(AccountDTO dto) {
        if (dto == null) {
            return null;
        }
        Account account = new Account();

        account.setId(dto.getId());
        account.setAccountNumber(dto.getAccountNumber());
        account.setAccountName(dto.getAccountName());
        account.setCreatedDate(dto.getCreatedDate());
        account.setModifiedDate(dto.getModifiedDate());

        return account;
    }

    @Override
    public AccountDTO toDto(Account entity) {
        if (entity == null) {
            return null;
        }

        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setId(entity.getId());
        accountDTO.setAccountNumber(entity.getAccountNumber());
        accountDTO.setAccountName(entity.getAccountName());
        accountDTO.setCreatedDate(entity.getCreatedDate());
        accountDTO.setModifiedDate(entity.getModifiedDate());

        return accountDTO;
    }


    @Override
    public List<Account> toEntity(List<AccountDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }

        List<Account> list = new ArrayList<Account>(dtoList.size());
        for (AccountDTO accountDTO : dtoList) {
            list.add(toEntity(accountDTO));
        }

        return list;
    }

    @Override
    public List<AccountDTO> toDto(List<Account> entityList) {
        if (entityList == null) {
            return null;
        }

        List<AccountDTO> list = new ArrayList<AccountDTO>(entityList.size());
        for (Account account : entityList) {
            list.add(toDto(account));
        }

        return list;
    }

    @Override
    public void partialUpdate(Account entity, AccountDTO dto) {
        if (dto == null) {
            return;
        }

        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        if (dto.getAccountNumber() != null) {
            entity.accountNumber(dto.getAccountNumber());
        }
        if (dto.getAccountName() != null) {
            entity.accountName(dto.getAccountName());
        }
        if (dto.getAccountName() != null) {
            entity.accountName(dto.getAccountName());
        }

        if (dto.getCreatedDate() != null) {
            entity.createdDate(dto.getCreatedDate());
        }
        if (dto.getModifiedDate() != null) {
            entity.modifiedDate(dto.getModifiedDate());
        }

    }
}
