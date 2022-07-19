package com.ecanteen.service;

import com.ecanteen.service.dto.OrderDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


/**
 * Service Interface for managing {@link com.ecanteen.domain.Order}.
 */
public interface OrderService {


    /**
     * Save a Order.
     *
     * @param orderDTO the entity to save.
     * @return the persisted entity.
     */
    OrderDTO save(OrderDTO orderDTO);

    /**
     * Updates a Order.
     *
     * @param orderDTO the entity to update.
     * @return the persisted entity.
     */
    OrderDTO update(OrderDTO orderDTO);

    /**
     * Partially updates a Order.
     *
     * @param orderDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<OrderDTO> partialUpdate(OrderDTO orderDTO);


    /**
     * Get the "id" Order.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OrderDTO> findOne(Long id);

    /**
     * Get all the Order.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OrderDTO> findAll(Pageable pageable);


    /**
     * Delete the "id" Order.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

