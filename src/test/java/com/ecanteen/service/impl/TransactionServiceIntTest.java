package com.ecanteen.service.impl;


 import com.ecanteen.domain.Transaction;
import com.ecanteen.domain.enumeration.PaymentMethod;
import com.ecanteen.domain.enumeration.TransactionStatus;
 import com.ecanteen.repository.TransactionRepository;
import com.ecanteen.service.TransactionServiceImpl;
 import com.ecanteen.service.dto.TransactionDTO;
 import com.ecanteen.service.mapper.TransactionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TransactionServiceIntTest {

    private static final TransactionStatus DEFAULT_TRANSACTION_STATUS= TransactionStatus.FAILED;

    private static final TransactionStatus UPDATED_TRANSACTION_STATUS = TransactionStatus.FAILED;

    private static final PaymentMethod DEFAULT_PAYMENT_METHOD = PaymentMethod.CARD;

    private static final PaymentMethod UPDATED_PAYMENT_METHOD = PaymentMethod.CARD;
    private static final String DEFAULT_ORDER_NUMBER =  "AAAAAAAAAA";

    private static final String UPDATED_ORDER_NUMBER = "BBBBBBBBBB";
    private static final String DEFAULT_TRANSACTION_ID =  "AAAAAAAAAA";

    private static final String UPDATED_TRANSACTION_ID = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);


    private static final Random random = new Random();

    private static final AtomicLong count = new AtomicLong(random.nextInt() + (2L * Integer.MAX_VALUE));



    private TransactionServiceImpl transactionServiceImpl;

    private TransactionRepository transactionRepository;

    private TransactionMapper transactionMapper;

    private Transaction transaction;


     private EntityManager em;
    @Autowired
    private MockMvc mockMvc;


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

    @BeforeEach
    void setUp() {
        transaction = createEntity(em);
    }


    @Test
    void shouldReturnAllTransactions() throws Exception {
        mockMvc.perform(get("/api/transactions"))
            .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateTransaction() throws Exception {
        transactionRepository.saveAndFlush(transaction);
        Transaction updatedTransaction = transactionRepository.findById(transaction.getId()).get();
        em.detach(updatedTransaction);
        updatedTransaction
            .transactionId(UPDATED_TRANSACTION_ID)
            .transactionStatus(UPDATED_TRANSACTION_STATUS)
            .orderNumber(UPDATED_ORDER_NUMBER)
            .paymentMethod(UPDATED_PAYMENT_METHOD)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        TransactionDTO transactionDTO = transactionMapper.toDto(updatedTransaction);

        mockMvc.perform( MockMvcRequestBuilders
                .put("/api/transactions/{id}", 14)
                .content(TestUtil.convertObjectToJsonBytes(updatedTransaction))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
