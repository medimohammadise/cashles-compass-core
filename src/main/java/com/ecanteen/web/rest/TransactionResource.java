package com.ecanteen.web.rest;


 import com.ecanteen.repository.TransactionRepository;
 import com.ecanteen.service.TransactionService;
 import com.ecanteen.service.dto.TransactionDTO;
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
public class TransactionResource {

    private static final String ENTITY_NAME = "transaction";
    private final Logger log = LoggerFactory.getLogger(TransactionResource.class);

    private final TransactionService transactionService;

    private final TransactionRepository transactionRepository;
    @Autowired
    public TransactionResource(TransactionService transactionService, TransactionRepository transactionRepository) {
        this.transactionService = transactionService;
        this.transactionRepository = transactionRepository;
    }


    @PostMapping("/transactions")
    public ResponseEntity<TransactionDTO> createTransactions(@RequestBody TransactionDTO transactionDTo) throws URISyntaxException {
        log.debug("REST request to save  transactions : {}", transactionDTo);
        if (transactionDTo.getId() != null) {
            throw new BadRequestAlertException("A new transactions cannot already have an ID", ENTITY_NAME, "id-exists");
        }
        TransactionDTO result = transactionService.save(transactionDTo);
        return ResponseEntity
            .created(new URI("/api/transactions/" + result.getId()))
            .body(result);
    }


    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions(Pageable pageable) {
        Page<TransactionDTO> page = transactionService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @PutMapping("/transactions/{id}")
    public ResponseEntity<TransactionDTO> updateTransactions(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TransactionDTO transactionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update transactionDTO : {}, {}", id, transactionDTO);
        if (transactionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, transactionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!transactionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TransactionDTO result = transactionService.update(transactionDTO);
        return ResponseEntity
            .ok()
            .body(result);
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<Void> deleteTransactions(@PathVariable Long id) {
        log.debug("REST request to delete transactions : {}", id);
        transactionService.delete(id);
        return ResponseEntity
            .noContent()
            .build();
    }
}
