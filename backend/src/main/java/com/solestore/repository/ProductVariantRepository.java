package com.solestore.repository;

import com.solestore.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import jakarta.persistence.LockModeType;

import java.util.Optional;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<ProductVariant> findByProductIdAndSize(Long productId, String size);
    long countByStockQuantityLessThanEqual(int threshold);
}