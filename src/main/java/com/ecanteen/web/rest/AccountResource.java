package com.ecanteen.web.rest;


import com.ecanteen.repository.AccountRepository;
 import com.ecanteen.service.AccountService;
 import com.ecanteen.service.dto.AccountDTO;
 import com.ecanteen.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class AccountResource {

    private static final String ENTITY_NAME = "account";
    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    private final AccountService accountService;

    private final AccountRepository accountRepository;
    @Autowired
    public AccountResource(AccountService accountService, AccountRepository accountRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }


    @PostMapping("/accounts")
    public ResponseEntity<AccountDTO> createAccounts(@RequestBody AccountDTO accountDTO) throws URISyntaxException {
        log.debug("REST request to save  accounts : {}", accountDTO);
        if (accountDTO.getId() != null) {
            throw new BadRequestAlertException("A new accounts cannot already have an ID", ENTITY_NAME, "id-exists");
        }
        AccountDTO result = accountService.save(accountDTO);
        return ResponseEntity
            .created(new URI("/api/accounts/" + result.getId()))
            .body(result);
    }



    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDTO>> getAllAccounts(Pageable pageable) {
        Page<AccountDTO> page = accountService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }



    @PutMapping("/accounts/{id}")
    public ResponseEntity<AccountDTO> updateAccounts(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AccountDTO accountDTO
    ) throws URISyntaxException {
        log.debug("REST request to update accountDTO : {}, {}", id, accountDTO);
        if (accountDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, accountDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!accountRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AccountDTO result = accountService.update(accountDTO);
        return ResponseEntity
            .ok()
            .body(result);
    }


    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<Void> deleteAccounts(@PathVariable Long id) {
        log.debug("REST request to delete account : {}", id);
        accountService.delete(id);
        return ResponseEntity
            .noContent()
            .build();
    }
}
