package com.demo.batch.writer;

import com.demo.batch.entity.ProductReport;
import com.demo.batch.repository.ProductReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductReportWriter implements ItemWriter<ProductReport> {

    private static final Logger log = LoggerFactory.getLogger(ProductReportWriter.class);

    @Autowired
    private ProductReportRepository repository;

    @Override
    public void write(List<? extends ProductReport> list) throws Exception {
        list.stream().forEach( item -> {
            ProductReport row = repository.findByProjectId(item.getProductId().longValue()).orElse(null);
            if(row != null){
                row.setPrice(row.getPrice().add(item.getPrice()));
                row.setQuantity(row.getQuantity() + item.getQuantity());
                repository.save(row);
            }else{
                repository.save(item);
            }

        });
    }
}
