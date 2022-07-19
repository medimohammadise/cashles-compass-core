package com.ecanteen.service.mapper;


import com.ecanteen.domain.Order;
import com.ecanteen.service.dto.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toEntity(OrderDTO dto) {
        if (dto == null) {
            return null;
        }
        Order order = new Order();

        order.setId(dto.getId());
        order.setOrderNumber(dto.getOrderNumber());
        order.setOrderCode(dto.getOrderCode());
        order.setCreatedDate(dto.getCreatedDate());
        order.setModifiedDate(dto.getModifiedDate());

        return order;
    }

    @Override
    public OrderDTO toDto(Order entity) {
        if (entity == null) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(entity.getId());
        orderDTO.setOrderNumber(entity.getOrderNumber());
        orderDTO.setOrderCode(entity.getOrderCode());
        orderDTO.setCreatedDate(entity.getCreatedDate());
        orderDTO.setModifiedDate(entity.getModifiedDate());

        return orderDTO;
    }


    @Override
    public List<Order> toEntity(List<OrderDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }

        List<Order> list = new ArrayList<Order>(dtoList.size());
        for (OrderDTO orderDTO : dtoList) {
            list.add(toEntity(orderDTO));
        }

        return list;
    }

    @Override
    public List<OrderDTO> toDto(List<Order> entityList) {
        if (entityList == null) {
            return null;
        }

        List<OrderDTO> list = new ArrayList<OrderDTO>(entityList.size());
        for (Order order : entityList) {
            list.add(toDto(order));
        }

        return list;
    }

    @Override
    public void partialUpdate(Order entity, OrderDTO dto) {
        if (dto == null) {
            return;
        }

        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        if (dto.getOrderNumber() != null) {
            entity.setOrderNumber(dto.getOrderNumber());
        }
        if (dto.getOrderCode() != null) {
            entity.setOrderCode(dto.getOrderCode());
        }
        if (dto.getCreatedDate() != null) {
            entity.createdDate(dto.getCreatedDate());
        }
        if (dto.getModifiedDate() != null) {
            entity.modifiedDate(dto.getModifiedDate());
        }

    }
}
