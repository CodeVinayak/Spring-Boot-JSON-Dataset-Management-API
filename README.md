# Assignment 1 - Spring Boot Project

This project is a basic Spring Boot application with the following dependencies:
- Spring Web
- Spring Data JPA
- H2 Database
- Lombok

## Requirements
- Java 21
- Maven

## How to Build

```
mvn clean install
```

## How to Run

```
mvn spring-boot:run
```

The application will start on [http://localhost:8080](http://localhost:8080).

## H2 Database Console

Access the H2 console at [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- JDBC URL: `jdbc:h2:mem:testdb`
- User Name: `sa`
- Password: (leave blank) 

## API Documentation

### 1. Insert a Dataset Record

**POST** `/api/dataset/{datasetName}/record`

- **Description:** Insert a new record for a dataset. The request body is any JSON object, which will be stored as-is.
- **Path Variable:**
  - `datasetName` (String): Name of the dataset
- **Request Body Example:**
```json
{
  "id": 1,
  "name": "John Doe",
  "age": 30,
  "department": "Engineering"
}
```
- **Response Example:**
```json
{
  "message": "Record added successfully",
  "dataset": "employee_dataset",
  "recordId": 1
}
```

---

### 2. Query Dataset Records

**GET** `/api/dataset/{datasetName}/query`

- **Description:** Query records for a dataset. Supports optional grouping and sorting.
- **Path Variable:**
  - `datasetName` (String): Name of the dataset
- **Query Parameters:**
  - `groupBy` (optional): Field name to group by
  - `sortBy` (optional): Field name to sort by
  - `order` (optional): `asc` (default) or `desc`

#### a) Group by a field
```
GET /api/dataset/employee_dataset/query?groupBy=department
```
- **Response Example:**
```json
{
  "groupedRecords": {
    "Engineering": [
      { "id": 1, "name": "John Doe", "age": 30, "department": "Engineering" },
      { "id": 2, "name": "Jane Smith", "age": 25, "department": "Engineering" }
    ],
    "Marketing": [
      { "id": 3, "name": "Alice Brown", "age": 28, "department": "Marketing" }
    ]
  }
}
```

#### b) Sort by a field
```
GET /api/dataset/employee_dataset/query?sortBy=age&order=asc
```
- **Response Example:**
```json
{
  "sortedRecords": [
    { "id": 2, "name": "Jane Smith", "age": 25, "department": "Engineering" },
    { "id": 3, "name": "Alice Brown", "age": 28, "department": "Marketing" },
    { "id": 1, "name": "John Doe", "age": 30, "department": "Engineering" }
  ]
}
```

---

## Error Handling

- All errors return a JSON object with `status` and `message` fields.
- **Example:**
```json
{
  "status": "error",
  "message": "Validation failed"
}
```

---

## Testing

- The project is designed to be testable with Postman or similar tools.
- Automated unit/integration tests (JUnit) are not included in this version. To add tests, create test classes in `src/test/java` using JUnit and Spring Boot Test. 