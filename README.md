## To Do List Service

This service provides a basic RESTful API to create and retrieve To Do list items for specific users.

### Features

- **Create a To Do item**: Allows users to create a new To Do list item.
- **Retrieve To Do items for a user**: List all To Do items for a specified user.

### Prerequisites

- Java 11 or newer
- Gradle

### Setting Up

1. **Clone the repository**:
   ```bash
    git clone https://github.com/your-repository-link.git
    cd to-do-list-service
    ```
2. **Build the project**:
   ```bash
    ./gradlew clean build
    ```
3. **Run the service**:
    ```bash
    ./gradlew bootRun
    ```
The service should now be running on `http://localhost:8080`.

### API Endpoints

1. **Create a To Do item**
   - **URL**: `/api/todos`
   - **Method**: `POST`
   - **Body**:
     ```json
     {
         "userId": "string",
         "description": "string"
     }
     ```
     ```bash
     curl -X POST http://localhost:8080/api/todos
       -H "Content-Type: application/json"
       -d '{
       "userId": "string",
       "description": "string"
       }'
     ```
2. **Retrieve To Do items for a user**
   - **URL**: `/api/todos/user/{userId}`
   - **Method**: `GET`
       ```bash
     curl http://localhost:8080/api/todos/user/{userId}
     ```

### Logging

The service uses SLF4J with Logback for logging. Logs are only output to the console.

### Testing

The project contains both unit and integration tests. To run them, use:
```bash
./gradle test
```    

### Contributing

If you'd like to contribute, please fork the repository and use a feature branch. Pull requests are warmly welcome.

### Licensing

The code in this project is licensed under MIT license.




