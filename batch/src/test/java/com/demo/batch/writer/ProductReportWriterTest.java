package com.demo.batch.writer;

import com.demo.batch.entity.ProductReport;
import com.demo.batch.entity.Products;
import com.demo.batch.repository.ProductReportRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoSettings;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductReportWriterTest {
    @InjectMocks
    private ProductReportWriter productReportWriter;

    @Mock
    private ProductReportRepository repo;

    @Test
    public void test() throws Exception {
        ProductReport item1 = new ProductReport(1L,100L,10,2,new BigDecimal("1000"));
        ProductReport item2 = new ProductReport(2L,10L,10,2,new BigDecimal("1000"));
        List<ProductReport> itemList = Arrays.asList(item1, item2);
        when(repo.findByProjectId(10L)).thenReturn(Optional.of(item2));
        when(repo.findByProjectId(100L)).thenReturn(Optional.of(item1));
        when(repo.findByProjectId(18L)).thenReturn(Optional.empty());
        productReportWriter.write(itemList);
        verify(repo).findByProjectId(100L);
        verify(repo).findByProjectId(10L);
    }
}
