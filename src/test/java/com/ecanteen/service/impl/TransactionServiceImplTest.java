package com.ecanteen.service.impl;

import com.ecanteen.domain.Transaction;
import com.ecanteen.domain.enumeration.PaymentMethod;
import com.ecanteen.domain.enumeration.TransactionStatus;
import com.ecanteen.repository.TransactionRepository;
import com.ecanteen.service.TransactionServiceImpl;
import com.ecanteen.service.dto.TransactionDTO;
import com.ecanteen.service.mapper.TransactionMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionServiceImplTest {

    private static final TransactionStatus DEFAULT_TRANSACTION_STATUS = TransactionStatus.FAILED;

    private static final TransactionStatus UPDATED_TRANSACTION_STATUS = TransactionStatus.FAILED;

    private static final PaymentMethod DEFAULT_PAYMENT_METHOD = PaymentMethod.CARD;

    private static final PaymentMethod UPDATED_PAYMENT_METHOD = PaymentMethod.CARD;
    private static final String DEFAULT_ORDER_NUMBER = "AAAAAAAAAA";

    private static final String UPDATED_ORDER_NUMBER = "BBBBBBBBBB";
    private static final String DEFAULT_TRANSACTION_ID = "AAAAAAAAAA";

    private static final String UPDATED_TRANSACTION_ID = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);


    private static final Random random = new Random();

    private static final AtomicLong count = new AtomicLong(random.nextInt() + (2L * Integer.MAX_VALUE));


    private TransactionServiceImpl transactionServiceImpl;

    private TransactionRepository transactionRepository;

    private EntityManager em;


    private MockMvc mockMvc;


    private TransactionMapper transactionMapper;

    private Transaction transaction;


    @BeforeEach
    void setUp() {
        transaction = createEntity(em);
        transactionRepository = Mockito.mock(TransactionRepository.class);
        transactionMapper = Mockito.mock(TransactionMapper.class);
        transactionServiceImpl = new TransactionServiceImpl(transactionRepository, transactionMapper);
    }


    //create data for test

    public static Transaction createEntity(EntityManager em) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(DEFAULT_TRANSACTION_ID);
        transaction.setOrderNumber(DEFAULT_ORDER_NUMBER);
        transaction.setPaymentMethod(DEFAULT_PAYMENT_METHOD);
        transaction.setTransactionStatus(DEFAULT_TRANSACTION_STATUS);
        transaction.setCreatedDate(DEFAULT_CREATED_DATE);
        transaction.setModifiedDate(DEFAULT_MODIFIED_DATE);

        return transaction;
    }


    @Test
    void shouldReturnAllTransactions() {

        //we have three sections for tets


        //arrange
        //in this section we prepare required things
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        transactionList.add(transaction);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Transaction> transactionPage = new PageImpl<>(transactionList, pageable, transactionList.size());
        TransactionDTO transactionListDTO1 = new TransactionDTO();
        transactionListDTO1.setId(1L);
        TransactionDTO transactionListDTO2 = new TransactionDTO();
        transactionListDTO2.setId(2L);


        //act
        //in this section we invoke method of services for test
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        transactionDTOList.add(transactionListDTO1);
        transactionDTOList.add(transactionListDTO2);

        //asser
        //and finally we write this section To get the result we expect

        Mockito.when(transactionMapper.toDto(transaction)).thenReturn(transactionListDTO1);
        Mockito.when(transactionMapper.toDto(transaction)).thenReturn(transactionListDTO2);
        Mockito.when(transactionRepository.findAll(pageable)).thenReturn(transactionPage);

        assertThat(transactionServiceImpl.findAll(pageable)).hasSize(2);
    }

    @Test
    void shouldCreateTransactions() {
        TransactionDTO transactionDTO = org.mapstruct.factory.Mappers.getMapper(TransactionMapper.class).toDto(transaction);
        Mockito.when(transactionMapper.toDto(transaction)).thenReturn(transactionDTO);
        Mockito.when(transactionRepository.save(Mockito.any(Transaction.class)))
            .thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void shouldUpdateTransaction() {

        transactionRepository.saveAndFlush(transaction);
        int databaseSizeBeforeUpdate = transactionRepository.findAll().size();

        Transaction partialUpdatedTransaction = new Transaction();
        partialUpdatedTransaction.setId(transaction.getId());

        partialUpdatedTransaction.setTransactionId(UPDATED_TRANSACTION_ID);
        partialUpdatedTransaction.setTransactionStatus(UPDATED_TRANSACTION_STATUS);
        partialUpdatedTransaction.setPaymentMethod(UPDATED_PAYMENT_METHOD);
        partialUpdatedTransaction.setOrderNumber(UPDATED_ORDER_NUMBER);
        partialUpdatedTransaction.setCreatedDate(UPDATED_CREATED_DATE);
        partialUpdatedTransaction.setModifiedDate(UPDATED_MODIFIED_DATE);


        TransactionDTO transactionDTO = org.mapstruct.factory.Mappers.getMapper(TransactionMapper.class).toDto(partialUpdatedTransaction);
        Mockito.when(transactionMapper.toDto(partialUpdatedTransaction)).thenReturn(transactionDTO);
        Mockito.when(transactionRepository.save(Mockito.any(Transaction.class)))
            .thenAnswer(i -> i.getArguments()[0]);

        assertThat(transactionRepository.findAll().size()).isEqualTo(databaseSizeBeforeUpdate);
    }
}
