package com.ecanteen.service.mapper;


import com.ecanteen.domain.NotificationHistory;
import com.ecanteen.domain.Order;
import com.ecanteen.service.dto.NotificationHistoryDTO;
import com.ecanteen.service.dto.OrderDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper for the entity {@link com.ecanteen.domain.Order} and its DTO {@link com.ecanteen.service.dto.OrderDTO}.
 */

@Mapper(componentModel = "spring"  ,imports = { Order.class, OrderDTO.class })
public interface OrderMapper extends EntityMapper<OrderDTO, Order> {


}
