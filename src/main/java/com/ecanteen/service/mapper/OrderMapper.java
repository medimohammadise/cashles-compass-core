package com.ecanteen.service.mapper;


import com.ecanteen.domain.Order;
import com.ecanteen.service.dto.OrderDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link com.ecanteen.domain.Order} and its DTO {@link com.ecanteen.service.dto.OrderDTO}.
 */

@Mapper(componentModel = "spring")
public interface OrderMapper extends EntityMapper<OrderDTO, Order> {}
