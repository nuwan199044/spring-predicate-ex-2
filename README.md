# Student Controller API

This API provides various endpoints to interact with student data. Below are the details and usage examples for each endpoint.

## Endpoints

### 1. Get Student By Name

**URL:** `GET /v1/student/{name}`

**Description:** Retrieves a student by their name.

**Example Request:**
```sh
curl -X GET "http://localhost:8080/v1/student/Henry%20Taylor"
```

### 2. Get Student By Filter

**URL:** `GET /v1/student/filter`

**Description:** Retrieves a student by column filter.

**Example Request:**
```sh
curl -X GET "http://localhost:8080/v1/student/filter?column=id&value=2""
```

### 3. Get Student By Filters

**URL:** `GET /v1/student/filters`

**Description:** Retrieves a student by multiple column filters.

**Example Request:**
```sh
curl -X POST "curl --location 'http://localhost:8080/v1/student/filters' \
--header 'Content-Type: application/json' \
--data '
    [
        {
            "column" : "id",
            "value" : "2"
        },
        {
            "column" : "name",
            "value" : "Charlie Davis"
        }
    ]
'"
```

### 4. Get Student By City

**URL:** `GET /v1/student/city/{city}`

**Description:** Retrieves a student by city.

**Example Request:**
```sh
curl -X GET "http://localhost:8080/v1/student/city/Phoenix"
```

### 5. Get Student By Subject

**URL:** `GET /v1/student/subject/{subjectName}`

**Description:** Retrieves a student by subject.

**Example Request:**
```sh
curl -X GET "http://localhost:8080/v1/student/subject/English"
```

### 6. Get Student By Filters With Condition

**URL:** `GET /v1/student/filtersWithCondition`

**Description:** Retrieves a student by multiple filters with condition.

**Example Request:**
```sh
curl -X POST "curl --location 'http://localhost:8080/v1/student/filtersWithCondition' \
--header 'Content-Type: application/json' \
--data '{
    "globalOperator" : "OR",
    "filters" : [
        {
            "column" : "id",
            "value" : "2"
        },
        {
            "column" : "name",
            "value" : "Charlie Davis"
        }
    ]
}
'"
```

### 7. Get Student By Filters With Operation

**URL:** `GET /v1/student/filtersWithOperation`

**Description:** Retrieves a student by multiple filters with operations.

**Example Request:**
```sh
curl -X POST "curl --location 'http://localhost:8080/v1/student/filtersWithOperation' \
--header 'Content-Type: application/json' \
--data '{
    "globalOperator" : "OR",
    "filters" : [
        {
            "column" : "id",
            "value" : "2,4,6",
            "operation" : "IN"
        },
        {
            "column" : "id",
            "value" : "5",
            "operation" : "EQUAL"
        },
        {
            "column" : "name",
            "value" : "Da",
            "operation" : "LIKE"
        }
    ]
}'

curl --location 'http://localhost:8080/v1/student/filtersWithOperation' \
--header 'Content-Type: application/json' \
--data '{
    "globalOperator" : "OR",
    "filters" : [
        {
            "column" : "id",
            "value" : "6",
            "operation" : "GREATER_THAN"
        }
    ]
}'

curl --location 'http://localhost:8080/v1/student/filtersWithOperation' \
--header 'Content-Type: application/json' \
--data '{
    "globalOperator" : "OR",
    "filters" : [
        {
            "column" : "id",
            "value" : "6",
            "operation" : "LESS_THAN"
        }
    ]
}'

curl --location 'http://localhost:8080/v1/student/filtersWithOperation' \
--header 'Content-Type: application/json' \
--data '{
    "globalOperator" : "OR",
    "filters" : [
        {
            "column" : "id",
            "value" : "2,5",
            "operation" : "BETWEEN"
        }
    ]
}'

curl --location 'http://localhost:8080/v1/student/filtersWithOperation' \
--header 'Content-Type: application/json' \
--data '{
    "globalOperator" : "OR",
    "filters" : [
        {
            "column" : "city",
            "value" : "Chicago",
            "joinTable" : "address",
            "operation" : "JOIN"
        }
    ]
}'"
```

### 8. Get Student By Filters With Pagination

**URL:** `GET /v1/student/filtersWithOperation/pagination`

**Description:** Retrieves a student by multiple filters with pagination.

**Example Request:**
```sh
curl --location 'http://localhost:8080/v1/student/filtersWithOperation/pagination' \
--header 'Content-Type: application/json' \
--data '{
    "globalOperator" : "OR",
    "page": {
        "pageNo": 1,
        "pageSize": 2,
        "sort": "ASC",
        "sortColumn": "studentId"
    },
    "filters" : [
        {
            "column" : "name",
            "value" : "a",
            "operation" : "LIKE"
        }
    ]
}'"
```

