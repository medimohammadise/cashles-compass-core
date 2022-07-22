package com.ecanteen.web.rest;


import com.ecanteen.repository.UserGroupRepository;
import com.ecanteen.service.UserGroupService;

import com.ecanteen.service.dto.UserGroupDTO;
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
public class UserGroupResource {

    private static final String ENTITY_NAME = "userGroup";
    private final Logger log = LoggerFactory.getLogger(UserGroupResource.class);

    private final UserGroupService userGroupService;

    private final UserGroupRepository userGroupRepository;

    @Autowired
    public UserGroupResource(UserGroupService userGroupService, UserGroupRepository userGroupRepository) {
        this.userGroupService = userGroupService;
        this.userGroupRepository = userGroupRepository;
    }

    @PostMapping("/userGroups")
    public ResponseEntity<UserGroupDTO> createUserGroups(@RequestBody UserGroupDTO userGroupDTO) throws URISyntaxException {
        log.debug("REST request to save  userGroups : {}", userGroupDTO);
        if (userGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new userGroups cannot already have an ID", ENTITY_NAME, "id-exists");
        }
        UserGroupDTO result = userGroupService.save(userGroupDTO);
        return ResponseEntity
            .created(new URI("/api/userGroups/" + result.getId()))
            .body(result);
    }


    @GetMapping("/userGroups")
    public ResponseEntity<List<UserGroupDTO>> getAllUserGroups(Pageable pageable) {
        Page<UserGroupDTO> page = userGroupService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }


    @PutMapping("/userGroups/{id}")
    public ResponseEntity<UserGroupDTO> updateUserGroups(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UserGroupDTO userGroupDTO
    ) throws URISyntaxException {
        log.debug("REST request to update userGroupDTO : {}, {}", id, userGroupDTO);
        if (userGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userGroupDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userGroupRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        UserGroupDTO result = userGroupService.update(userGroupDTO);
        return ResponseEntity
            .ok()
            .body(result);
    }


    @DeleteMapping("/userGroups/{id}")
    public ResponseEntity<Void> deleteUserGroups(@PathVariable Long id) {
        log.debug("REST request to delete userGroups : {}", id);
        userGroupService.delete(id);
        return ResponseEntity
            .noContent()
            .build();
    }
}
