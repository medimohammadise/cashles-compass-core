package com.ecanteen.service.mapper;



import com.ecanteen.domain.Transaction;
import com.ecanteen.service.dto.TransactionDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public Transaction toEntity(TransactionDTO dto) {
        if (dto == null) {
            return null;
        }
        Transaction transaction = new Transaction();

        transaction.setId(dto.getId());
        transaction.setTransactionId(dto.getTransactionId());
        transaction.setTransactionStatus(dto.getTransactionStatus());
        transaction.setCreatedDate(dto.getCreatedDate());
        transaction.setOrderNumber(dto.getOrderNumber());
        transaction.setPaymentMethod(dto.getPaymentMethod());
        transaction.setModifiedDate(dto.getModifiedDate());



        return transaction;
    }

    @Override
    public TransactionDTO toDto(Transaction entity) {
        if (entity == null) {
            return null;
        }

        TransactionDTO transactionDTO = new TransactionDTO();

        transactionDTO.setId(entity.getId());
        transactionDTO.setTransactionId(entity.getTransactionId());
        transactionDTO.setTransactionStatus(entity.getTransactionStatus());
        transactionDTO.setOrderNumber(entity.getOrderNumber());
        transactionDTO.setCreatedDate(entity.getCreatedDate());
        transactionDTO.setModifiedDate(entity.getModifiedDate());
        transactionDTO.setPaymentMethod(entity.getPaymentMethod());


        return transactionDTO;
    }


    @Override
    public List<Transaction> toEntity(List<TransactionDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }

        List<Transaction> list = new ArrayList<Transaction>(dtoList.size());
        for (TransactionDTO transactionDTO : dtoList) {
            list.add(toEntity(transactionDTO));
        }

        return list;
    }

    @Override
    public List<TransactionDTO> toDto(List<Transaction> entityList) {
        if (entityList == null) {
            return null;
        }

        List<TransactionDTO> list = new ArrayList<TransactionDTO>(entityList.size());
        for (Transaction transaction: entityList) {
            list.add(toDto(transaction));
        }

        return list;
    }

    @Override
    public void partialUpdate(Transaction entity, TransactionDTO dto) {
        if (dto == null) {
            return;
        }

        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        if (dto.getPaymentMethod() != null) {
            entity.paymentMethod(dto.getPaymentMethod());
        }
        if (dto.getTransactionStatus() != null) {
            entity.transactionStatus(dto.getTransactionStatus());
        }
        if (dto.getTransactionId() != null) {
            entity.transactionId(dto.getTransactionId());
        }
        if (dto.getOrderNumber() != null) {
            entity.orderNumber(dto.getOrderNumber());
        }
        if (dto.getCreatedDate() != null) {
            entity.createdDate(dto.getCreatedDate());
        }
        if (dto.getModifiedDate() != null) {
            entity.modifiedDate(dto.getModifiedDate());
        }

    }
}
