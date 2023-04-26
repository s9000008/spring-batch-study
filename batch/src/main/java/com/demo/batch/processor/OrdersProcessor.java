package com.demo.batch.processor;

import com.demo.batch.entity.FinalOrder;
import com.demo.batch.entity.OrderItem;
import com.demo.batch.entity.Orders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Transactional(isolation = Isolation.SERIALIZABLE)
@Component
public class OrdersProcessor implements ItemProcessor<Orders, FinalOrder> {
    private static final Logger log = LoggerFactory.getLogger(OrdersProcessor.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private DataSource dataSource;

    @Override
    public FinalOrder process(Orders item) throws Exception {
		log.info("PROCESS");
		FinalOrder finalOrder = new FinalOrder();
		finalOrder.setId(0L);
		String sql = "SELECT id, order_id, product_id, quantity, price FROM order_items WHERE order_id = ?";
		List<OrderItem> orderItemList = jdbcTemplate.query(sql, new Object[]{item.getId()},
				BeanPropertyRowMapper.newInstance(OrderItem.class));
		AtomicReference<BigDecimal> total = new AtomicReference<>(BigDecimal.ZERO);
		orderItemList.stream().forEach((orderItem) ->{
			total.updateAndGet(t -> t.add(orderItem.getPrice()));
		});
		int quantityCount = orderItemList.stream().mapToInt(OrderItem::getQuantity).sum();


    	finalOrder.setOrderId(item.getId());
    	//finalOrder.setProductName("TBD");
    	finalOrder.setQuantity(quantityCount);
    	finalOrder.setStatus(item.getStatus());
    	finalOrder.setTotal_price(total.get());
    	finalOrder.setOrderDate(item.getOrderDate());
    	finalOrder.setUserId(item.getUserId());
        return finalOrder;
    }
}
