# bfhl-api

Spring Boot REST API for the Acropolis Campus Hiring API Round.

## Endpoint

```http
POST /bfhl
Content-Type: application/json
```

Request:

```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```

## Configure Identity

Update `src/main/resources/application.properties` before submitting:

```properties
bfhl.full-name=your_full_name
bfhl.dob=ddmmyyyy
bfhl.email=your@email.com
bfhl.roll-number=YOURROLLNO
```

These can also be overridden on a host with environment variables:

```text
BFHL_FULL_NAME
BFHL_DOB
BFHL_EMAIL
BFHL_ROLL_NUMBER
```

## Run Locally

```bash
mvn spring-boot:run
```

## Test

```bash
mvn test
```

## Hosting

Deploy the repository to Render, Railway, or another Java host. The public URL must end with:

```text
/bfhl
```
