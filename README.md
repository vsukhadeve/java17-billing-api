# Java 17 Spring Boot Billing API

This is a small demo project showing off Java 17 features (records, sealed types, switch expressions)
inside a Spring Boot 3 REST API, with Swagger / OpenAPI documentation.

## Tech Stack

- Java 17
- Spring Boot 3
- Spring Web
- Bean Validation (Jakarta)
- springdoc-openapi (Swagger UI)

## Running Locally

```bash
mvn clean spring-boot:run
```

The API will be available at:

- Base URL: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`

## Example Endpoints

### Generate Invoice

`POST /api/invoices`

Request body:

```json
{
  "customerId": "C001",
  "customerName": "Acme NGO",
  "planType": "STANDARD",
  "usageHours": 18,
  "nonProfit": true
}
```

### List Plans

`GET /api/invoices/plans`

## Running in GitHub Codespaces

1. Push this project to GitHub.
2. Open the repository.
3. Click **Code → Codespaces → Create codespace on main**.
4. In the integrated terminal, run:

   ```bash
   mvn clean spring-boot:run
   ```

5. When port 8080 is detected, make it **public** and open in browser.
6. Navigate to `/swagger-ui.html` to explore the API.

## Postman Collection

A Postman collection is available under `postman/BillingAPI.postman_collection.json`.
