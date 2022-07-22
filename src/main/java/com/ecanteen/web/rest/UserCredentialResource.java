package com.ecanteen.web.rest;


 import com.ecanteen.repository.UserCredentialRepository;
 import com.ecanteen.service.UserCredentialService;
 import com.ecanteen.service.dto.UserCredentialDTO;
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
public class UserCredentialResource {

    private static final String ENTITY_NAME = "userCredential";
    private final Logger log = LoggerFactory.getLogger(UserCredentialResource.class);

    private final UserCredentialService userCredentialService;

    private final UserCredentialRepository userCredentialRepository;
    @Autowired
    public UserCredentialResource(UserCredentialService userCredentialService, UserCredentialRepository userCredentialRepository) {
        this.userCredentialService = userCredentialService;
        this.userCredentialRepository = userCredentialRepository;
    }


    @PostMapping("/userCredentials")
    public ResponseEntity<UserCredentialDTO> createUserCredentials(@RequestBody UserCredentialDTO userCredentialDTO) throws URISyntaxException {
        log.debug("REST request to save  userCredentials : {}", userCredentialDTO);
        if (userCredentialDTO.getId() != null) {
            throw new BadRequestAlertException("A new userCredentials cannot already have an ID", ENTITY_NAME, "id-exists");
        }
        UserCredentialDTO result = userCredentialService.save(userCredentialDTO);
        return ResponseEntity
            .created(new URI("/api/userCredentials/" + result.getId()))
            .body(result);
    }



    @GetMapping("/userCredentials")
    public ResponseEntity<List<UserCredentialDTO>> getAllUserCredentials(Pageable pageable) {
        Page<UserCredentialDTO> page = userCredentialService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }


    @PutMapping("/userCredentials/{id}")
    public ResponseEntity<UserCredentialDTO> updateUserCredentials(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UserCredentialDTO userCredentialDTO
    ) throws URISyntaxException {
        log.debug("REST request to update userCredentialDTO : {}, {}", id, userCredentialDTO);
        if (userCredentialDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userCredentialDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userCredentialRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        UserCredentialDTO result = userCredentialService.update(userCredentialDTO);
        return ResponseEntity
            .ok()
            .body(result);
    }


    @DeleteMapping("/userCredentials/{id}")
    public ResponseEntity<Void> deleteUserCredentials(@PathVariable Long id) {
        log.debug("REST request to delete userCredentials : {}", id);
        userCredentialService.delete(id);
        return ResponseEntity
            .noContent()
            .build();
    }
}
