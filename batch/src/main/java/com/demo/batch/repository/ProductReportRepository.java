package com.demo.batch.repository;

import com.demo.batch.entity.ProductReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductReportRepository extends JpaRepository<ProductReport, Long> {
    @Query("SELECT p FROM ProductReport p WHERE p.productId = :productId")
    Optional<ProductReport> findByProjectId(@Param("productId") long productId);
}
