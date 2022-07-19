package com.ecanteen.service;

import com.ecanteen.service.dto.ProductCategoryDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


/**
 * Service Interface for managing {@link com.ecanteen.domain.ProductCategory}.
 */
public interface ProductCategoryService {


    /**
     * Save a ProductCategory.
     *
     * @param productCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    ProductCategoryDTO save(ProductCategoryDTO productCategoryDTO);

    /**
     * Updates a ProductCategory.
     *
     * @param productCategoryDTO the entity to update.
     * @return the persisted entity.
     */
    ProductCategoryDTO update(ProductCategoryDTO productCategoryDTO);

    /**
     * Partially updates a ProductCategory.
     *
     * @param productCategoryDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ProductCategoryDTO> partialUpdate(ProductCategoryDTO productCategoryDTO);


    /**
     * Get the "id" ProductCategory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProductCategoryDTO> findOne(Long id);

    /**
     * Get all the Product.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProductCategoryDTO> findAll(Pageable pageable);


    /**
     * Delete the "id" ProductCategory.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

