package com.ecanteen.service.impl;

import com.ecanteen.domain.Order;

import com.ecanteen.repository.OrderRepository;

import com.ecanteen.service.dto.OrderDTO;

import com.ecanteen.service.mapper.OrderMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class OrderServiceIntTest {
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


    private Order order;

    private OrderRepository orderRepository;

    private OrderMapper orderMapper;
    private EntityManager em;
    @Autowired
    private MockMvc mockMvc;

    public static Order createEntity(EntityManager em) {
        Order Order = new Order();

        Order.setOrderNumber(DEFAULT_ORDERNUMBER);
        Order.setOrderCode(DEFAULT_ORDERCODE);
        Order.setCreatedDate(DEFAULT_CREATED_DATE);
        Order.setModifiedDate(DEFAULT_MODIFIED_DATE);

        return Order;
    }


    @BeforeEach
    void setUp() {
        order = createEntity(em);
    }


    @Test
    void shouldReturnAllOrders() throws Exception {
        mockMvc.perform(get("/api/orders"))
            .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateOrder() throws Exception {
        orderRepository.saveAndFlush(order);

        Order updatedOrder = orderRepository.findById(order.getId()).get();
         em.detach(updatedOrder);
        updatedOrder
            .orderNumber(UPDATED_ORDERNUMBER)
            .orderCode(UPDATED_ORDERCODE)

            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        OrderDTO OrderDTO = orderMapper.toDto(updatedOrder);

        mockMvc.perform( MockMvcRequestBuilders
                .put("/api/orders/{id}", 14)
                .content(TestUtil.convertObjectToJsonBytes(updatedOrder))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
