package com.demo.batch.processor;

import com.demo.batch.entity.OrderItem;
import com.demo.batch.entity.ProductReport;
import com.demo.batch.repository.ProductReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

//@Transactional(isolation = Isolation.READ_COMMITTED)
@Component
public class ProductReportProcessor implements ItemProcessor<OrderItem, ProductReport> {

    private static final Logger log = LoggerFactory.getLogger(ProductReportProcessor.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Override
    public ProductReport process(OrderItem item) throws Exception {
        ProductReport productReport = new ProductReport(0L, item.getProductId(), item.getQuantity(), 1, item.getPrice());
        return productReport;
    }
}
