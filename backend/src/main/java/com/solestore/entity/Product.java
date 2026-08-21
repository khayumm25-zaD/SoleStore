package com.solestore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank @Size(max = 150) @Column(nullable = false, length = 150) private String name;
	@Size(max = 2000) @Column(length = 2000) private String description;
	@NotBlank @Size(max = 100) @Column(nullable = false, length = 100) private String brand;
	@NotNull @DecimalMin(value = "0.00") @Column(nullable = false, precision = 12, scale = 2) private BigDecimal price;
	@Size(max = 500) @Column(length = 500) private String imageUrl;
	@Column(nullable = false) private boolean active = true;

	@NotNull @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "category_id", nullable = false) private Category category;

	@Column(nullable = false, updatable = false) private LocalDateTime createdAt;
	@Column(nullable = false) private LocalDateTime updatedAt;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductVariant> variants = new ArrayList<>();
	@JsonIgnore @OneToMany(mappedBy = "product") private List<CartItem> cartItems = new ArrayList<>();
	@JsonIgnore @OneToMany(mappedBy = "product") private List<OrderItem> orderItems = new ArrayList<>();

	@PrePersist void onCreate() { createdAt = updatedAt = LocalDateTime.now(); }
	@PreUpdate void onUpdate() { updatedAt = LocalDateTime.now(); }
	public Long getId() { return id; } public void setId(Long id) { this.id = id; }
	public String getName() { return name; } public void setName(String name) { this.name = name; }
	public String getDescription() { return description; } public void setDescription(String value) { description = value; }
	public String getBrand() { return brand; } public void setBrand(String value) { brand = value; }
	public BigDecimal getPrice() { return price; } public void setPrice(BigDecimal value) { price = value; }
	public String getImageUrl() { return imageUrl; } public void setImageUrl(String value) { imageUrl = value; }
	public boolean isActive() { return active; } public void setActive(boolean value) { active = value; }
	public Category getCategory() { return category; } public void setCategory(Category value) { category = value; }
	public LocalDateTime getCreatedAt() { return createdAt; } public LocalDateTime getUpdatedAt() { return updatedAt; }
	public List<ProductVariant> getVariants() { return variants; }
}
