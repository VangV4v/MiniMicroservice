package org.vang.minimicroservice.service;

public class CustomerServiceCommon {

    public static final String CUSTOMER = "Customer";
    public static final String ROLE_CUSTOMER = "ROLE_CUSTOMER";
    public static class Message {
        public static String ADD_CUSTOMER_SUCCESS = "Customer created successfully!";
        public static String UPDATE_CUSTOMER_SUCCESS = "Customer updated successfully!";
        public static String DELETE_CUSTOMER_SUCCESS = "Customer deleted successfully!";
        public static String ERROR_CUSTOMER_001 = "Username is already exist";
        public static String ERROR_CUSTOMER_002 = "Email is already exist";
        public static String ERROR_CUSTOMER_003 = "Phone is already exist";
        public static String ERROR_CUSTOMER_004 = "Please send request with hdnOldEmail";
        public static String ERROR_CUSTOMER_005 = "Please send request with hdnOldPhone";
    }

}