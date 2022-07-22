package com.ecanteen.service;

import com.ecanteen.service.dto.AccountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AccountService {


    AccountDTO save(AccountDTO accountDTO);


    AccountDTO update(AccountDTO accountDTO);


    Optional<AccountDTO> partialUpdate(AccountDTO accountDTO);


    Optional<AccountDTO> findOne(Long id);


    Page<AccountDTO> findAll(Pageable pageable);


    void delete(Long id);
}
