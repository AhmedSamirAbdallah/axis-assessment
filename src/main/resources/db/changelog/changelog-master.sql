-- Drop Sequences if they exist
DROP SEQUENCE IF EXISTS transaction_seq;
DROP SEQUENCE IF EXISTS account_seq;
DROP SEQUENCE IF EXISTS user_seq;

-- Drop Tables if they exist
DROP TABLE IF EXISTS transactions CASCADE;
DROP TABLE IF EXISTS accounts CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- Create Sequences
CREATE SEQUENCE user_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE account_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE transaction_seq
    START WITH 1
    INCREMENT BY 1;

-- Create Users Table
CREATE TABLE users (
    id BIGINT DEFAULT nextval('user_seq') PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    ssn VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(20),
    birth_date DATE,
    address VARCHAR(255),
    user_account_status VARCHAR(255),
    security_answer VARCHAR(255) NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by VARCHAR(255),
    modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_by VARCHAR(255),
    deleted BOOLEAN DEFAULT FALSE
);

-- Create Accounts Table
CREATE TABLE accounts (
    id BIGINT DEFAULT nextval('account_seq') PRIMARY KEY,
    user_id BIGINT NOT NULL,
    account_number VARCHAR(255) UNIQUE NOT NULL,
    balance DECIMAL(15, 2) NOT NULL CHECK (balance >= 0),
    currency VARCHAR(10) NOT NULL,
    account_status VARCHAR(255) NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by VARCHAR(255),
    modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_by VARCHAR(255),
    deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

-- Create Transactions Table
CREATE TABLE transactions (
    id BIGINT DEFAULT nextval('transaction_seq') PRIMARY KEY,
    account_id BIGINT NOT NULL,
    transaction_type VARCHAR(255) NOT NULL,
    amount DECIMAL(15, 2) NOT NULL CHECK (amount > 0),
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    currency VARCHAR(10) NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by VARCHAR(255),
    modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_by VARCHAR(255),
    deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (account_id) REFERENCES accounts (id) ON DELETE CASCADE
);
