package com.ecanteen.service.impl;

import com.ecanteen.domain.Account;
 import com.ecanteen.repository.AccountRepository;
import com.ecanteen.service.dto.AccountDTO;
 import com.ecanteen.service.mapper.AccountMapper;
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

public class AccountServiceImplTest {


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



    private AccountServiceImpl accountServiceImpl;

    private AccountRepository accountRepository;

    private EntityManager em;


    private MockMvc mockMvc;


    private AccountMapper accountMapper;

    private Account account;




    @BeforeEach
    void setUp() {
        account = createEntity(em);
        accountRepository = Mockito.mock(AccountRepository.class);
        accountMapper = Mockito.mock(AccountMapper.class);
        accountServiceImpl = new AccountServiceImpl(accountRepository, accountMapper);
    }


    //create data for test

    public static Account createEntity(EntityManager em) {
        Account account = new Account();

        account.setAccountName(DEFAULT_ACCOUNTNAME);
        account.setAccountNumber(DEFAULT_ACCOUNTNUMBER);
        account.setCreatedDate(DEFAULT_CREATED_DATE);
        account.setModifiedDate(DEFAULT_MODIFIED_DATE);

        return account;
    }


    @Test
    void shouldReturnAllAccounts() {

        //we have three sections for tets


        //arrange
        //in this section we prepare required things
        List<Account> accountList = new ArrayList<>();
        accountList.add(account);
        accountList.add(account);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Account> accountPage = new PageImpl<>(accountList, pageable, accountList.size());
        AccountDTO accountDTO1 = new AccountDTO();
        accountDTO1.setId(1L);
        AccountDTO accountDTO2 = new AccountDTO();
        accountDTO2.setId(2L);


        //act
        //in this section we invoke method of services for test
        List<AccountDTO> accountDTOList = new ArrayList<>();
        accountDTOList.add(accountDTO1);
        accountDTOList.add(accountDTO2);

        //asser
        //and finally we write this section To get the result we expect

        Mockito.when(accountMapper.toDto(account)).thenReturn(accountDTO1);
        Mockito.when(accountMapper.toDto(account)).thenReturn(accountDTO2);
        Mockito.when(accountRepository.findAll(pageable)).thenReturn(accountPage);

        assertThat(accountServiceImpl.findAll(pageable)).hasSize(2);
    }

    @Test
    void shouldCreateAccounts() {
        AccountDTO accountDTO = org.mapstruct.factory.Mappers.getMapper(AccountMapper.class).toDto(account);
        Mockito.when(accountMapper.toDto(account)).thenReturn(accountDTO);
        Mockito.when(accountRepository.save(Mockito.any(Account.class)))
            .thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void shouldUpdateAccount() {

        accountRepository.saveAndFlush(account);
        int databaseSizeBeforeUpdate = accountRepository.findAll().size();

        Account partialUpdatedAccount = new Account();
        partialUpdatedAccount.setId(account.getId());

        partialUpdatedAccount.setAccountNumber(UPDATED_ACCOUNTNAME);
        partialUpdatedAccount.setAccountName(UPDATED_ACCOUNTNAME);
        partialUpdatedAccount.setCreatedDate(UPDATED_CREATED_DATE);
        partialUpdatedAccount.setModifiedDate(UPDATED_MODIFIED_DATE);


        AccountDTO accountDTO = org.mapstruct.factory.Mappers.getMapper(AccountMapper.class).toDto(partialUpdatedAccount);
        Mockito.when(accountMapper.toDto(partialUpdatedAccount)).thenReturn(accountDTO);
        Mockito.when(accountRepository.save(Mockito.any(Account.class)))
            .thenAnswer(i -> i.getArguments()[0]);

        assertThat(accountRepository.findAll().size()).isEqualTo(databaseSizeBeforeUpdate);
    }
}
