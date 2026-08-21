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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
	@JsonIgnore @OneToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "user_id", nullable = false, unique = true) private User user;
	@Column(nullable = false, updatable = false) private LocalDateTime createdAt;
	@Column(nullable = false) private LocalDateTime updatedAt;
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true) private List<CartItem> items = new ArrayList<>();

	@PrePersist void onCreate() { createdAt = updatedAt = LocalDateTime.now(); }
	@PreUpdate void onUpdate() { updatedAt = LocalDateTime.now(); }
	public Long getId() { return id; } public void setId(Long value) { id = value; }
	public User getUser() { return user; } public void setUser(User value) { user = value; }
	public LocalDateTime getCreatedAt() { return createdAt; } public LocalDateTime getUpdatedAt() { return updatedAt; }
	public List<CartItem> getItems() { return items; }
}
