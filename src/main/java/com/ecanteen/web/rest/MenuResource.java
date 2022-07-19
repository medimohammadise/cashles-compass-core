package com.ecanteen.web.rest;

import com.ecanteen.repository.MenuRepository;

import com.ecanteen.service.MenuService;

import com.ecanteen.service.dto.MenuDTO;

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
public class MenuResource {
    private static final String ENTITY_NAME = "menu";
    private final Logger log = LoggerFactory.getLogger(MenuResource.class);

    private final MenuService menuService;

    private final MenuRepository menuRepository;
    @Autowired
    public MenuResource(MenuService menuService, MenuRepository menuRepository) {
        this.menuService = menuService;
        this.menuRepository = menuRepository;
    }

    /**
     * {@code POST  /menus} : Create a new menu.
     * @param menuDTO the menuDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new menuDTO, or with status {@code 400 (Bad Request)} if the menus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/menus")
    public ResponseEntity<MenuDTO> createMenus(@RequestBody MenuDTO menuDTO) throws URISyntaxException {
        log.debug("REST request to save  menus : {}", menuDTO);
        if (menuDTO.getId() != null) {
            throw new BadRequestAlertException("A new menus cannot already have an ID", ENTITY_NAME, "id-exists");
        }
        MenuDTO result = menuService.save(menuDTO);
        return ResponseEntity
            .created(new URI("/api/menus/" + result.getId()))
            .body(result);
    }


    /**
     * {@code GET  /menus} : get all the menus.
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of menus in body.
     */
    @GetMapping("/menus")
    public ResponseEntity<List<MenuDTO>> getAllMenus(Pageable pageable) {
        Page<MenuDTO> page = menuService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }


    /**
     * {@code PUT  /menus/:id} : Updates an existing menus.
     * @param id the id of the MenuDTO to save.
     * @param menuDTO the  MenuDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated menuDTO,
     * or with status {@code 400 (Bad Request)} if the menuDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the menuDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/menus/{id}")
    public ResponseEntity<MenuDTO> updateMenus(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MenuDTO menuDTO
    ) throws URISyntaxException {
        log.debug("REST request to update menuDTO : {}, {}", id, menuDTO);
        if (menuDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, menuDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!menuRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MenuDTO result = menuService.update(menuDTO);
        return ResponseEntity
            .ok()
            .body(result);
    }

    /**
     * {@code DELETE  /menus/:id} : delete the "id" menu.
     * @param id the id of the menuDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/menus/{id}")
    public ResponseEntity<Void> deleteMenus(@PathVariable Long id) {
        log.debug("REST request to delete menus : {}", id);
        menuService.delete(id);
        return ResponseEntity
            .noContent()
            .build();
    }
}

