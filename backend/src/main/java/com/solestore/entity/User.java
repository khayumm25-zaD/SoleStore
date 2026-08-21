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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank @Size(max = 100)
	@Column(nullable = false, length = 100)
	private String name;

	@NotBlank @Email @Size(max = 150)
	@Column(nullable = false, unique = true, length = 150)
	private String email;

	@JsonIgnore
	@NotBlank @Size(min = 60, max = 100)
	@Column(nullable = false, length = 100)
	private String password;

	@Size(max = 30)
	@Column(length = 30)
	private String mobile;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private LocalDateTime updatedAt;

	@JsonIgnore @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
	private Cart cart;

	@JsonIgnore @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Order> orders = new ArrayList<>();

	@PrePersist void onCreate() { createdAt = updatedAt = LocalDateTime.now(); }
	@PreUpdate void onUpdate() { updatedAt = LocalDateTime.now(); }

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public String getMobile() { return mobile; }
	public void setMobile(String mobile) { this.mobile = mobile; }
	public Role getRole() { return role; }
	public void setRole(Role role) { this.role = role; }
	public LocalDateTime getCreatedAt() { return createdAt; }
	public LocalDateTime getUpdatedAt() { return updatedAt; }
	public Cart getCart() { return cart; }
	public void setCart(Cart cart) { this.cart = cart; }
	public List<Order> getOrders() { return orders; }
}
