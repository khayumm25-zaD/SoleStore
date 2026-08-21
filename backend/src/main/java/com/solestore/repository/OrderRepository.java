package com.solestore.repository;

import com.solestore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserIdOrderByCreatedAtDesc(Long userId);
    @Query("select coalesce(sum(o.totalAmount), 0) from Order o where o.status <> com.solestore.entity.OrderStatus.CANCELLED")
    BigDecimal totalRevenue();
}