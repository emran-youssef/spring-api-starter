# 🛒 Store API — Spring Boot Backend

A fully-featured RESTful backend API for an e-commerce store, built with Spring Boot. Covers user management, product catalog, shopping cart, and authentication with Spring Security.

---

## 🚀 Tech Stack

| Technology | Purpose |
|---|---|
| Java 23 + Spring Boot 3.5 | Core framework |
| Spring Security | Authentication & authorization |
| Spring Data JPA + Hibernate | ORM & database access |
| MySQL | Relational database |
| Flyway | Database migrations |
| MapStruct | DTO mapping |
| Lombok | Boilerplate reduction |
| Bean Validation | Input validation |
| SpringDoc OpenAPI (Swagger) | API documentation |
| Maven | Build tool |

---

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/codewithmosh/store/
│   │   ├── controllers/       # REST controllers (Users, Products, Carts, Auth)
│   │   ├── entities/          # JPA entities (User, Product, Cart, Category...)
│   │   ├── dtos/              # Request/Response DTOs
│   │   ├── services/          # Business logic layer
│   │   ├── repositories/      # Spring Data JPA repositories
│   │   ├── mappers/           # MapStruct mappers
│   │   ├── config/            # Security configuration
│   │   ├── exceptions/        # Custom exceptions
│   │   └── validation/        # Custom validation annotations
│   └── resources/
│       ├── application.yaml   # App configuration
│       └── db/migration/      # Flyway SQL migration scripts
```

---

## ⚙️ Getting Started

### Prerequisites

- Java 23+
- MySQL 8+
- Maven 3.9+

### 1. Clone the repository

```bash
git clone https://github.com/emran-youssef/spring-api-starter.git
cd spring-api-starter
```

### 2. Set up the database

Make sure MySQL is running, then create a `.env` file or set environment variables:

```yaml
# application-local.yaml (not committed to git)
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/store_api?createDatabaseIfNotExist=true
    username: root
    password: YOUR_PASSWORD
```

### 3. Run the application

```bash
./mvnw spring-boot:run
```

Flyway will automatically run the migration scripts and create all tables on first launch.

### 4. Access Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🔐 Authentication

The API uses **Spring Security** with stateless session management.

| Endpoint | Method | Auth Required |
|---|---|---|
| `/auth/login` | POST | ❌ Public |
| `/users` | POST | ❌ Public |
| `/carts/**` | ALL | ❌ Public |
| All other endpoints | ANY | ✅ Required |

### Login

```http
POST /auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "password123"
}
```

---

## 📦 API Endpoints

### 👤 Users

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/users` | Register a new user |
| `GET` | `/users` | Get all users (sortable by `name` or `email`) |
| `GET` | `/users/{id}` | Get user by ID |
| `PUT` | `/users/{id}` | Update user |
| `DELETE` | `/users/{id}` | Delete user |
| `POST` | `/users/{id}/change-password` | Change password |

**Register example:**
```json
{
  "name": "Emran Khaleel",
  "email": "emran@example.com",
  "password": "secret123"
}
```

---

### 📦 Products

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/products/all` | Get all products (filter by `?categoryID=`) |
| `GET` | `/products/{id}` | Get product by ID |
| `POST` | `/products/create` | Create a product |
| `PUT` | `/products/{id}` | Update a product |
| `DELETE` | `/products/{id}` | Delete a product |

---

### 🛒 Carts

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/carts` | Create a new cart |
| `GET` | `/carts/{id}` | Get cart by UUID |
| `POST` | `/carts/{cartId}/items` | Add item to cart |
| `PUT` | `/carts/{cartId}/items/{itemId}` | Update item quantity |
| `DELETE` | `/carts/{cartId}/items/{itemId}` | Remove item from cart |
| `DELETE` | `/carts/{cartId}` | Clear entire cart |

**Add item example:**
```json
{
  "productId": 1,
  "quantity": 2
}
```

---

## 🗄️ Database Schema

```
users ──< addresses
users ──< profiles (1-to-1)
users >──< products (wishlist)
categories ──< products
carts ──< cart_items >── products
```

Migrations are located in `src/main/resources/db/migration/`:
- `V1__initial_migration.sql` — Creates all core tables
- `V2__create_carts_cart_items_tables.sql` — Adds cart tables

---

## ✅ Validation

The API enforces input validation on all requests:

- `@NotBlank`, `@Email`, `@Size` — Standard constraints
- `@Lowercase` — Custom annotation ensuring emails are lowercase
- Validation errors return `400 Bad Request` with field-level error messages:

```json
{
  "email": "Email must be lowercase.",
  "password": "Password must be between 6 and 25 characters."
}
```

---

## 🧠 Key Design Decisions

- **Service Layer** — Business logic is separated from controllers via `CartService` and `UserService`
- **DTO Pattern** — Entities are never exposed directly; MapStruct handles all mapping
- **Stateless Security** — No HTTP sessions; ready for JWT integration
- **UUID Cart IDs** — Carts use `BINARY(16)` UUIDs for security and scalability
- **Cascade Management** — Careful use of `CascadeType` and `orphanRemoval` to manage related entities

---

## 📌 Future Improvements

- [ ] Add JWT token generation and filter
- [ ] Add Role-based access control (ADMIN / USER)
- [ ] Add pagination to product and user listings
- [ ] Write unit and integration tests
- [ ] Move product logic from controller into a dedicated `ProductService`

---

## 👨‍💻 Author

**Emran Youssef Al-Khaleel**
- GitHub: [github.com/emran-youssef](https://github.com/emran-youssef)
- LinkedIn: [Emran Al-Khaleel](https://linkedin.com/in/emran-al-khaleel)
- Email: emrankhaleel03@gmail.com
