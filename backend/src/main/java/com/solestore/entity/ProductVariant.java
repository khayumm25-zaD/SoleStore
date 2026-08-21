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
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "product_variants", uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "size"}))
public class ProductVariant {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
	@JsonIgnore @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "product_id", nullable = false) private Product product;
	@NotBlank @Column(nullable = false, length = 20) private String size;
	@Min(0) @Column(nullable = false) private int stockQuantity;

	public Long getId() { return id; } public void setId(Long value) { id = value; }
	public Product getProduct() { return product; } public void setProduct(Product value) { product = value; }
	public String getSize() { return size; } public void setSize(String value) { size = value; }
	public int getStockQuantity() { return stockQuantity; } public void setStockQuantity(int value) { stockQuantity = value; }
}
