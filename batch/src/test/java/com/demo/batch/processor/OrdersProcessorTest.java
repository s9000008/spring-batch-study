package com.demo.batch.processor;

import com.demo.batch.entity.FinalOrder;
import com.demo.batch.entity.OrderItem;
import com.demo.batch.entity.Orders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrdersProcessorTest {

    @InjectMocks
    private OrdersProcessor ordersProcessor;

    @Mock
    private DataSource dataSource;
    @Mock
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test() throws Exception {
        Orders orders = new Orders(1000L,1L, LocalDate.now(),"paid");
        /*OrderItem orderItem1 = new OrderItem(1L,1000L, 1L, 10,new BigDecimal("1000"));
        OrderItem orderItem2 = new OrderItem(2L,1000L, 10L, 10,new BigDecimal("50"));
        List<OrderItem> orderItemList = Arrays.asList(orderItem1, orderItem2);
        when(ordersProcessor.process(orders)).thenReturn(
                new FinalOrder(
                        0L,
                        orders.getId(),
                        orders.getUserId(),
                        orders.getOrderDate(),
                        20, new BigDecimal("1050"),
                        orders.getStatus()));*/
        FinalOrder finalOrder = ordersProcessor.process(orders);
        assertNotNull(finalOrder);
        /*assertEquals(20, finalOrder.getQuantity());
        assertEquals(new BigDecimal("1050"), finalOrder.getTotalPrice());
*/
    }


}
