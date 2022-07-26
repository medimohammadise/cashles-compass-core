package com.ecanteen.service.impl;

import com.ecanteen.domain.Order;

import com.ecanteen.repository.OrderRepository;

import com.ecanteen.service.OrderService;
import com.ecanteen.service.dto.OrderDTO;

import com.ecanteen.service.mapper.OrderMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.ecanteen.domain.Order}.
 */

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);


    private final OrderMapper OrderMapper;
    private final OrderRepository orderRepository;



    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper OrderMapper) {
        this.orderRepository = orderRepository;
        this.OrderMapper = OrderMapper;
    }

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        log.debug("Request to save  : {}", OrderMapper);
        Order order = OrderMapper.toEntity(orderDTO);
        order = orderRepository.save(order);
        return OrderMapper.toDto(order);
    }

    @Override
    public OrderDTO update(OrderDTO orderDTO) {
        log.debug("Request to save Order : {}", orderDTO);
        Order order = OrderMapper.toEntity(orderDTO);
        order = orderRepository.save(order);
        return OrderMapper.toDto(order);
    }

    @Override
    public Optional<OrderDTO> partialUpdate(OrderDTO orderDTO) {
        log.debug("Request to partially update students : {}", orderDTO);

        return orderRepository
            .findById(orderDTO.getId())
            .map(existingOrder -> {
                OrderMapper.partialUpdate(existingOrder, orderDTO);

                return existingOrder;
            })
            .map(orderRepository::save)
            .map(OrderMapper::toDto);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Page<OrderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Order");
        return orderRepository.findAll(pageable).map(OrderMapper::toDto);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Optional<OrderDTO> findOne(Long id) {
        log.debug("Request to get order : {}", id);
        return orderRepository.findById(id).map(OrderMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete order : {}", id);
        orderRepository.deleteById(id);
    }
}
