package com.ecanteen.service.impl;


import com.ecanteen.domain.Account;

import com.ecanteen.repository.AccountRepository;
import com.ecanteen.service.dto.AccountDTO;
import com.ecanteen.service.mapper.AccountMapper;
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
public class AccountServiceIntTest {

    private static final String DEFAULT_ACCOUNTNAME = "AAAAAAAAAA";

    private static final String UPDATED_ACCOUNTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNTNUMBER = "AAAAAAAAAA";

    private static final String UPDATED_ACCOUNTNUMBER = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);


    private static final Random random = new Random();

    private static final AtomicLong count = new AtomicLong(random.nextInt() + (2L * Integer.MAX_VALUE));


    private AccountMapper accountMapper;

    private Account account;
    private AccountRepository accountRepository;


    private EntityManager em;
    @Autowired
    private MockMvc mockMvc;


    public static Account createEntity(EntityManager em) {
        Account account = new Account();

        account.setAccountName(DEFAULT_ACCOUNTNAME);
        account.setAccountNumber(DEFAULT_ACCOUNTNUMBER);
        account.setCreatedDate(DEFAULT_CREATED_DATE);
        account.setModifiedDate(DEFAULT_MODIFIED_DATE);

        return account;
    }


    @BeforeEach
    void setUp() {
        account = createEntity(em);
    }


    @Test
    void shouldReturnAllAccounts() throws Exception {
        mockMvc.perform(get("/api/accounts"))
            .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateAccount() throws Exception {
        accountRepository.saveAndFlush(account);
        Account updatedAccount = accountRepository.findById(account.getId()).get();
        em.detach(updatedAccount);
        updatedAccount.accountName(UPDATED_ACCOUNTNAME).accountNumber(UPDATED_ACCOUNTNUMBER)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        AccountDTO accountDTO = accountMapper.toDto(updatedAccount);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/accounts/{id}", 14)
                .content(TestUtil.convertObjectToJsonBytes(updatedAccount))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
