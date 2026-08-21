package com.solestore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "cart_items")
public class CartItem {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
	@JsonIgnore @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "cart_id", nullable = false) private Cart cart;
	@ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "product_id", nullable = false) private Product product;
	@NotBlank @Column(nullable = false, length = 20) private String size;
	@Min(1) @Column(nullable = false) private int quantity;
	@NotNull @DecimalMin("0.00") @Column(nullable = false, precision = 12, scale = 2) private BigDecimal price;

	public Long getId() { return id; } public void setId(Long value) { id = value; }
	public Cart getCart() { return cart; } public void setCart(Cart value) { cart = value; }
	public Product getProduct() { return product; } public void setProduct(Product value) { product = value; }
	public String getSize() { return size; } public void setSize(String value) { size = value; }
	public int getQuantity() { return quantity; } public void setQuantity(int value) { quantity = value; }
	public BigDecimal getPrice() { return price; } public void setPrice(BigDecimal value) { price = value; }
}
