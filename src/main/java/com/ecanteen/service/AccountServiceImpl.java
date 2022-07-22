package com.ecanteen.service;


import com.ecanteen.domain.Account;
import com.ecanteen.repository.AccountRepository;
import com.ecanteen.service.dto.AccountDTO;
import com.ecanteen.service.mapper.AccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);


    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;


    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountDTO save(AccountDTO accountDTO) {
        log.debug("Request to save accounts : {}", accountMapper);
        Account account = accountMapper.toEntity(accountDTO);
        account = accountRepository.save(account);
        return accountMapper.toDto(account);
    }

    @Override
    public AccountDTO update(AccountDTO accountDTO) {
        log.debug("Request to save accounts : {}", accountDTO);
        Account account = accountMapper.toEntity(accountDTO);
        account = accountRepository.save(account);
        return accountMapper.toDto(account);
    }

    @Override
    public Optional<AccountDTO> partialUpdate(AccountDTO accountDTO) {
        log.debug("Request to partially update accounts : {}", accountDTO);

        return accountRepository
            .findById(accountDTO.getId())
            .map(existingAccounts -> {
                accountMapper.partialUpdate(existingAccounts, accountDTO);

                return existingAccounts;
            })
            .map(accountRepository::save)
            .map(accountMapper::toDto);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Page<AccountDTO> findAll(Pageable pageable) {
        log.debug("Request to get all accounts");
        return accountRepository.findAll(pageable).map(accountMapper::toDto);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Optional<AccountDTO> findOne(Long id) {
        log.debug("Request to get accounts : {}", id);
        return accountRepository.findById(id).map(accountMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete accounts : {}", id);
        accountRepository.deleteById(id);
    }
}
