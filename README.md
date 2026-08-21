# SoleStore

SoleStore is a full-stack shoe shop with a React storefront and a Spring Boot/PostgreSQL API. Customers can browse products, manage a database-backed cart, check out with COD or mock online payment, and track orders. Administrators can manage the catalogue, inventory, users, and order statuses.

## Stack and architecture

- Frontend: React, Vite, React Router, Axios, Bootstrap, React Icons, React Toastify
- Backend: Java 17, Spring Boot 3.4, Spring Web, Spring Data JPA, Spring Security, JWT, Bean Validation
- Database: PostgreSQL 16
- Layers: controllers -> DTOs -> services -> repositories -> PostgreSQL

The repository is split into `frontend/`, `backend/`, and `database/`. Entities model users/roles, catalogue categories/products/variants, carts, and orders. Passwords are BCrypt hashes and API responses never include them.

## Local setup

1. Start PostgreSQL: copy `backend/.env.example` values into a local environment or `.env`, then run `docker compose up -d postgres`.
2. Start the API from `backend/`:
	`./mvnw spring-boot:run` (Windows: `mvnw.cmd spring-boot:run`)
3. Start the frontend from `frontend/`: `npm install` then `npm run dev`.

Frontend defaults to `http://localhost:8081/api`; set `VITE_API_BASE_URL` when the API uses another URL. Backend settings are environment variables: `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`, `JWT_SECRET`, `JWT_EXPIRATION_MS`, and `FRONTEND_URL`.

## Docker

Build the backend first with `backend\mvnw.cmd clean package`, then set the required variables from `.env.example` and run `docker compose up --build`. Compose provides PostgreSQL persistence and health checks plus backend and Nginx frontend services. The SoleStore service/container names are isolated from unrelated containers.

## API overview

- `POST /api/auth/register`, `POST /api/auth/login`
- Public `GET /api/products`, `/api/products/{id}`, `/api/categories`
- Admin catalogue APIs under `/api/products`, `/api/categories`, `/api/variants`, `/api/users`, `/api/admin`
- Authenticated customer APIs: `/api/profile`, `/api/cart`, `/api/orders`, and `POST /api/orders`

JWTs are sent as `Authorization: Bearer <token>`. Customer cart and order ownership is derived from the token subject, never a client-provided user ID.

## Development accounts

The SQL seed uses the development-only password `password` for `admin@solestore.local` and `customer@solestore.local`. Do not use these credentials outside local development; change or remove seed data for deployment.

## Testing and CI

- Backend: `backend\mvnw.cmd clean package` and `backend\mvnw.cmd test`
- Frontend: `cd frontend`, `npm install`, `npm run build`
- GitHub Actions runs the Maven Wrapper build/tests and frontend npm build on relevant changes.

## Troubleshooting

- Missing datasource/JWT values: export the variables in `backend/.env.example` before starting Spring Boot.
- Port conflicts: set `PORT` for Spring Boot, `BACKEND_PORT`/`FRONTEND_PORT` for Compose, or run the API on another port and update `VITE_API_BASE_URL`.
- Database reset: stop Compose and remove only the `solestore-full-folder_postgres_data` volume when a clean local database is required.
