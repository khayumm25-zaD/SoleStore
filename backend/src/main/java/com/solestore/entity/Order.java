package com.solestore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
	@JsonIgnore @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "user_id", nullable = false) private User user;
	@NotNull @DecimalMin("0.00") @Column(nullable = false, precision = 12, scale = 2) private BigDecimal totalAmount;
	@NotNull @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20) private OrderStatus status = OrderStatus.PENDING;
	@NotBlank @Column(nullable = false, length = 1000) private String shippingAddress;
	@NotNull @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20) private PaymentStatus paymentStatus = PaymentStatus.PENDING;
	@Column(nullable = false, updatable = false) private LocalDateTime createdAt;
	@Column(nullable = false) private LocalDateTime updatedAt;
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true) private List<OrderItem> items = new ArrayList<>();

	@PrePersist void onCreate() { createdAt = updatedAt = LocalDateTime.now(); }
	@PreUpdate void onUpdate() { updatedAt = LocalDateTime.now(); }
	public Long getId() { return id; } public void setId(Long value) { id = value; }
	public User getUser() { return user; } public void setUser(User value) { user = value; }
	public BigDecimal getTotalAmount() { return totalAmount; } public void setTotalAmount(BigDecimal value) { totalAmount = value; }
	public OrderStatus getStatus() { return status; } public void setStatus(OrderStatus value) { status = value; }
	public String getShippingAddress() { return shippingAddress; } public void setShippingAddress(String value) { shippingAddress = value; }
	public PaymentStatus getPaymentStatus() { return paymentStatus; } public void setPaymentStatus(PaymentStatus value) { paymentStatus = value; }
	public LocalDateTime getCreatedAt() { return createdAt; } public LocalDateTime getUpdatedAt() { return updatedAt; }
	public List<OrderItem> getItems() { return items; }
}
