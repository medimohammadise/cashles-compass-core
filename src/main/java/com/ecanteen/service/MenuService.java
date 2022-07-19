package com.ecanteen.service;

import com.ecanteen.service.dto.MenuDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


/**
 * Service Interface for managing {@link com.ecanteen.domain.Menu}.
 */
public interface MenuService {

    /**
     * Save a menu.
     *
     * @param menuDTO the entity to save.
     * @return the persisted entity.
     */
    MenuDTO save(MenuDTO menuDTO);

    /**
     * Updates a menu.
     *
     * @param menuDTO the entity to update.
     * @return the persisted entity.
     */
    MenuDTO update(MenuDTO menuDTO);

    /**
     * Partially updates a Product.
     *
     * @param menuDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<MenuDTO> partialUpdate(MenuDTO menuDTO);


    /**
     * Get the "id" menu.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MenuDTO> findOne(Long id);

    /**
     * Get all the menu.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MenuDTO> findAll(Pageable pageable);


    /**
     * Delete the "id" menu.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

