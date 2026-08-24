 
SoleStore 👟

A full-stack e-commerce platform for managing and selling footwear, built with React, Spring Boot, PostgreSQL, Docker, and JWT-based authentication.



SoleStore supports customer shopping workflows and administrator operations including catalogue management, inventory management, cart management, checkout, order tracking, and role-based administration.



🚀 Features

Customer

User registration and login

JWT-based authentication

Browse products and categories

View product variants and available sizes

Add products to cart

Update cart quantities

Remove cart items

Checkout with Cash on Delivery (COD) or mock online payment

View order history

View individual order details

Secure ownership-based access to carts and orders

Administrator

Secure admin authentication

Role-based access control

Create and manage categories

Create and manage products

Manage product variants and inventory

View registered users

View all customer orders

Update order statuses

Protected administrative REST APIs

Security

JWT authentication

BCrypt password hashing

Role-based authorization

Customer/admin endpoint isolation

Environment-based database credentials

Environment-based JWT secret

Environment-based admin credentials

Authentication failures return 401 Unauthorized

Unauthorized operations return 403 Forbidden

Business-rule violations return appropriate 400 Bad Request responses

Customer cart/order ownership is derived from the authenticated JWT subject

🏗️ Architecture

&#x20;                   ┌─────────────────────┐

&#x20;                   │      React UI       │

&#x20;                   │   Vite + Bootstrap  │

&#x20;                   └──────────┬──────────┘

&#x20;                              │

&#x20;                              │ REST / JSON

&#x20;                              ▼

&#x20;                   ┌─────────────────────┐

&#x20;                   │   Spring Boot API   │

&#x20;                   │                     │

&#x20;                   │ Controllers         │

&#x20;                   │       ↓             │

&#x20;                   │ DTOs                │

&#x20;                   │       ↓             │

&#x20;                   │ Services            │

&#x20;                   │       ↓             │

&#x20;                   │ Repositories        │

&#x20;                   └──────────┬──────────┘

&#x20;                              │

&#x20;                              │ JPA / Hibernate

&#x20;                              ▼

&#x20;                   ┌─────────────────────┐

&#x20;                   │    PostgreSQL 16    │

&#x20;                   └─────────────────────┘



&#x20;             Docker Compose orchestrates all services
# SoleStore 👟

A full-stack e-commerce platform for managing and selling footwear, built with **React, Spring Boot, PostgreSQL, Docker, and JWT-based authentication**.

SoleStore supports customer shopping workflows and administrator operations including catalogue management, inventory management, cart management, checkout, order tracking, and role-based administration.

---

## 🚀 Features

### Customer

- User registration and login
- JWT-based authentication
- Browse products and categories
- View product variants and available sizes
- Add products to cart
- Update cart quantities
- Remove cart items
- Checkout with Cash on Delivery (COD) or mock online payment
- View order history
- View individual order details
- Secure ownership-based access to carts and orders

### Administrator

- Secure admin authentication
- Role-based access control
- Create and manage categories
- Create and manage products
- Manage product variants and inventory
- View registered users
- View all customer orders
- Update order statuses
- Protected administrative REST APIs

### Security

- JWT authentication
- BCrypt password hashing
- Role-based authorization
- Customer/admin endpoint isolation
- Environment-based database credentials
- Environment-based JWT secret
- Environment-based admin credentials
- Authentication failures return `401 Unauthorized`
- Unauthorized operations return `403 Forbidden`
- Business-rule violations return appropriate `400 Bad Request` responses
- Customer cart/order ownership is derived from the authenticated JWT subject

---

## 🏗️ Architecture

```text
                    ┌─────────────────────┐
                    │      React UI       │
                    │   Vite + Bootstrap  │
                    └──────────┬──────────┘
                               │
                               │ REST / JSON
                               ▼
                    ┌─────────────────────┐
                    │   Spring Boot API   │
                    │                     │
                    │ Controllers         │
                    │       ↓             │
                    │ DTOs                │
                    │       ↓             │
                    │ Services            │
                    │       ↓             │
                    │ Repositories        │
                    └──────────┬──────────┘
                               │
                               │ JPA / Hibernate
                               ▼
                    ┌─────────────────────┐
                    │    PostgreSQL 16    │
                    └─────────────────────┘

              Docker Compose orchestrates all services

