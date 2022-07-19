package com.ecanteen.service;


import com.ecanteen.service.dto.ProductDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.ecanteen.domain.Product}.
 */
public interface ProductService {

    /**
     * Save a Product.
     *
     * @param productDTO the entity to save.
     * @return the persisted entity.
     */
    ProductDTO save(ProductDTO productDTO);

    /**
     * Updates a ProductDTO.
     *
     * @param productDTO the entity to update.
     * @return the persisted entity.
     */
    ProductDTO update(ProductDTO productDTO);

    /**
     * Partially updates a Product.
     *
     * @param productDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ProductDTO> partialUpdate(ProductDTO productDTO);


    /**
     * Get the "id" Product.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProductDTO> findOne(Long id);

    /**
     * Get all the Product.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProductDTO> findAll(Pageable pageable);


    /**
     * Delete the "id" Product.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

