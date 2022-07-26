package com.ecanteen.service.impl;

import com.ecanteen.domain.Order;

import com.ecanteen.repository.OrderRepository;

import com.ecanteen.service.dto.OrderDTO;

import com.ecanteen.service.mapper.OrderMapper;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceImplTest {
    private static final String DEFAULT_ORDERNUMBER = "AAAAAAAAAA";

    private static final String UPDATED_ORDERNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERCODE = "AAAAAAAAAA";

    private static final String UPDATED_ORDERCODE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Random random = new Random();

    private static final AtomicLong count = new AtomicLong(random.nextInt() + (2L * Integer.MAX_VALUE));



    //Provide dependencies of StudentService
    private OrderServiceImpl orderServiceimpl;

    private OrderRepository orderRepository;

    private EntityManager em;


    private MockMvc mockMvc;


    private OrderMapper orderMapper;

    private Order order;



    @BeforeEach
    void setUp() {
        order = createEntity(em);
        orderRepository = Mockito.mock(OrderRepository.class);
        orderMapper = Mockito.mock(OrderMapper.class);
        orderServiceimpl = new OrderServiceImpl(orderRepository, orderMapper);
    }


    //create data for test

    public static Order createEntity(EntityManager em) {
        Order Order = new Order();

        Order.setOrderNumber(DEFAULT_ORDERNUMBER);
        Order.setOrderCode(DEFAULT_ORDERCODE);
        Order.setCreatedDate(DEFAULT_CREATED_DATE);
        Order.setModifiedDate(DEFAULT_MODIFIED_DATE);

        return Order;
    }




    @Test
    void shouldReturnAllOrders() {



        //arrange
        //in this section we prepare required things
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        orderList.add(order);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Order> orderPage = new PageImpl<>(orderList, pageable, orderList.size());
        OrderDTO OrderDTO1 = new OrderDTO();
        OrderDTO1.setId(1L);
        OrderDTO OrderDTO2 = new OrderDTO();
        OrderDTO2.setId(2L);


        //act
         List<OrderDTO> orderDTOList = new ArrayList<>();
        orderDTOList.add(OrderDTO1);
        orderDTOList.add(OrderDTO2);

        //asser

        Mockito.when(orderMapper.toDto(order)).thenReturn(OrderDTO1);
        Mockito.when(orderMapper.toDto(order)).thenReturn(OrderDTO2);
        Mockito.when(orderRepository.findAll(pageable)).thenReturn(orderPage);

        assertThat(orderServiceimpl.findAll(pageable)).hasSize(2);
    }

    @Test
    void shouldCreateOrders() {
        OrderDTO orderDTO = org.mapstruct.factory.Mappers.getMapper(OrderMapper.class).toDto(order);
        Mockito.when(orderMapper.toDto(order)).thenReturn(orderDTO);
        Mockito.when(orderRepository.save(Mockito.any(Order.class)))
            .thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void shouldUpdateOrder() {

        orderRepository.saveAndFlush(order);
        int databaseSizeBeforeUpdate = orderRepository.findAll().size();

        Order partialUpdatedOrder = new Order();
        partialUpdatedOrder.setId(order.getId());

        partialUpdatedOrder.setOrderNumber(UPDATED_ORDERNUMBER);
        partialUpdatedOrder.setOrderCode(UPDATED_ORDERCODE);

        partialUpdatedOrder.setCreatedDate(UPDATED_CREATED_DATE);
        partialUpdatedOrder.setModifiedDate(UPDATED_MODIFIED_DATE);


        OrderDTO orderDTO = org.mapstruct.factory.Mappers.getMapper(OrderMapper.class).toDto(partialUpdatedOrder);
        Mockito.when(orderMapper.toDto(partialUpdatedOrder)).thenReturn(orderDTO);
        Mockito.when(orderRepository.save(Mockito.any(Order.class)))
            .thenAnswer(i -> i.getArguments()[0]);

        assertThat(orderRepository.findAll().size()).isEqualTo(databaseSizeBeforeUpdate);
    }
}
