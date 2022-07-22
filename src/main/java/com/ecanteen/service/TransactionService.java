package com.ecanteen.service;

 import com.ecanteen.service.dto.TransactionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TransactionService {


    TransactionDTO save(TransactionDTO transactionDTO);


    TransactionDTO update(TransactionDTO transactionDTO);


    Optional<TransactionDTO> partialUpdate(TransactionDTO transactionDTO);


    Optional<TransactionDTO> findOne(Long id);


    Page<TransactionDTO> findAll(Pageable pageable);


    void delete(Long id);
}
