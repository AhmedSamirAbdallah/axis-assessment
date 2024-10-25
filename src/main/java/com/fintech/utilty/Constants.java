package com.fintech.utilty;

public class Constants {
    public static class ErrorMessage {
        public static final String FIRST_NAME_REQUIRED = "First name is required";
        public static final String LAST_NAME_REQUIRED = "Last name is required";
        public static final String EMAIL_INVALID = "Email should be valid";
        public static final String SSN_REQUIRED = "SSN is required";
        public static final String PHONE_NUMBER_INVALID = "Phone number must be valid according to international formats";
        public static final String BIRTH_DATE_FUTURE = "Birth date cannot be in the future";
        public static final String USER_NOT_FOUND = "User with ID %d not found";
        public static final String EMAIL_ALREADY_EXISTS = "An account with this email already exists";
        public static final String SSN_ALREADY_EXISTS = "An account with this SSN already exists";

    }

    public static class SuccessMessage {

        public static final String USER_CREATED_SUCCESSFULLY = "User created successfully";
        public static final String USER_RETRIEVED_SUCCESSFULLY = "User retrieved successfully";

    }
}
