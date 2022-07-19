package com.ecanteen.web.rest;

import com.ecanteen.repository.OrderRepository;

import com.ecanteen.service.OrderService;

import com.ecanteen.service.dto.OrderDTO;

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
public class OrderResource {
    private static final String ENTITY_NAME = "order";
    private final Logger log = LoggerFactory.getLogger(OrderResource.class);

    private final  OrderService OrderService;

    private final  OrderRepository OrderRepository;
    @Autowired
    public OrderResource(OrderService OrderService, OrderRepository OrderRepository) {
        this.OrderService = OrderService;
        this.OrderRepository = OrderRepository;
    }

    /**
     * {@code POST  /orders} : Create a new order.
     * @param orderDTO the orderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderDTO, or with status {@code 400 (Bad Request)} if the order has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/orders")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) throws URISyntaxException {
        log.debug("REST request to save  order : {}", orderDTO);
        if (orderDTO.getId() != null) {
            throw new BadRequestAlertException("A new order cannot already have an ID", ENTITY_NAME, "id-exists");
        }
        OrderDTO result = OrderService.save(orderDTO);
        return ResponseEntity
            .created(new URI("/api/orders/" + result.getId()))
            .body(result);
    }


    /**
     * {@code GET  /orders} : get all the orders.
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orders in body.
     */
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders(Pageable pageable) {
        Page<OrderDTO> page = OrderService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }


    /**
     * {@code PUT  /orders/:id} : Updates an existing order.
     * @param id the id of the orderDTO to save.
     * @param orderDTO the  orderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderDTO,
     * or with status {@code 400 (Bad Request)} if the orderDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> updateOrder(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OrderDTO orderDTO
    ) throws URISyntaxException {
        log.debug("REST request to update order : {}, {}", id, orderDTO);
        if (orderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, orderDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!OrderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OrderDTO result = OrderService.update(orderDTO);
        return ResponseEntity
            .ok()
            .body(result);
    }

    /**
     * {@code DELETE  /orders/:id} : delete the "id" order.
     * @param id the id of the orderDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        log.debug("REST request to delete order : {}", id);
        OrderService.delete(id);
        return ResponseEntity
            .noContent()
            .build();
    }
}

