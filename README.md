# Book Catalog API

A Spring Boot REST API for searching books using the **Open Library API**, with caching support via **Redis**. This project demonstrates how to integrate an external API, implement caching, and containerize a Java application using **Docker**.

---

## Features

- Search books by title using the Open Library API
- Retrieve book details including:
  - Title
  - Author
  - Publication year
- Cache API responses using Redis to improve performance
- Fully containerized with Docker

---

## Technologies

- Java
- Spring Boot
- Maven
- Redis (for caching)
- Docker & Docker Compose

---

## Prerequisites

- Docker
- Docker Compose(If needed)

---

## Running the Application with Docker

1. Clone the repository:

```bash
git clone https://github.com/JonasCandido/book-catalog-api
cd book-catalog-api
```

2. Build images and start the container
```bash
docker-compose up --build
```

3. Get book by title<br>
Example:<br>
GET http://localhost:8080/books?title=harry+potter
