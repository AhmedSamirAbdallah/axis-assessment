# **Axis-FinTech-Assessment**

## **FinTech API**

The FinTech API is designed to streamline the management of financial transactions and user accounts, providing a comprehensive solution for modern banking needs.


## **Introduction**

FinTech is a comprehensive Fintech API designed to manage financial transactions and user accounts. It provides functionalities for user registration, account management, and transaction processing, including deposits and withdrawals.

## **Installation**

To install Project Title, follow these steps:

1. Clone the repository: **`git clone https://github.com/AhmedSamirAbdallah/fintech-assessment.git`**
2. Navigate to the project directory: **`cd fintech-assessment`**
3. Run Docker Compose: In the project root, start the services using **`docker-compose up -d`**
4. Ensure you have Java and Maven installed.
5. Install dependencies: **`Install dependencies: mvn install`**


## **API Documentation with Swagger**

The FinTech API uses Swagger for interactive API documentation, making it easy to explore the available endpoints.

Accessing Swagger UI
Once the application is running, you can access the Swagger UI at the following URL:http://localhost:8050/fintech/swagger-ui/index.html

- Features of Swagger UI
View all available endpoints: Explore each API endpoint along with its request and response formats.

- Try it out: Test API calls directly from the Swagger interface.
Automatic documentation updates: As you modify your API, Swagger UI automatically reflects those changes.


## **API Documentation**

The FinTech API provides endpoints for managing user accounts, transactions, and balances. Below is an overview of the main API endpoints along with examples.

1. User Management

- Create User
    - Endpoint: POST http://localhost:8050/fintech/api/users
    - Description: Register a new user.

- Get user
    - Endpoint: GET http://localhost:8050/fintech/api/users/{id}
    - Description: Retrieve the details of a user by their ID.

2. Account Management
- Create Account
    - Endpoint: POST http://localhost:8050/fintech/api/accounts
    - Description: Create a new account for a user.

3. Transaction Management

- Deposit Funds
    - Endpoint: POST http://localhost:8050/fintech/api/accounts/{id}/deposit
    - Description: Deposit funds into an account.

- Withdraw Funds
    - Endpoint: POST http://localhost:8050/fintech/api/accounts/{id}/withdraw
    - Description: Withdraw funds from an account.

- Balance Inquiry
    - Endpoint: POST http://localhost:8050/fintech/api/accounts/{id}/balance
    - Description: Retrieve the balance of an account.






