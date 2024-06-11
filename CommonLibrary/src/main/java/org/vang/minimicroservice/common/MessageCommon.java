package org.vang.minimicroservice.common;

public class MessageCommon {
    public static String AUTH_001 = "Login fail! Invalid account";
    public static String ERROR_001 = "Username is already exist";
    public static String ERROR_002 = "Email is already exist";
    public static String ERROR_003 = "Phone is already exist";
    public static String ERROR_004 = "Please send request with hdnOldEmail";
    public static String ERROR_005 = "Please send request with hdnOldPhone";
    public static String ERROR_006 = "Expiration has at least 30 days";
    public static String NO_RESULT = "No Result";

   public static class Admin {
        public static final String CREATE_SUCCESSFUL = "Admin created successfully";
        public static final String UPDATE_SUCCESSFUL = "Admin updated successfully";
       public static final String DELETE_SUCCESSFUL = "Admin deleted successfully";
    }

    public static class Category {
        public static final String CREATE_SUCCESSFUL = "Category created successfully";
        public static final String UPDATE_SUCCESSFUL = "Category updated successfully";
        public static final String DELETE_SUCCESSFUL = "Category deleted successfully";
    }

    public static class Seller {
       public static final String CREATE_SUCCESSFUL = "Seller created successfully";
       public static final String UPDATE_SUCCESSFUL = "Seller updated successfully";
       public static final String DELETE_SUCCESSFUL = "Seller deleted successfully";
    }

    public static class Product {
        public static final String CREATE_SUCCESSFUL = "Product created successfully";
        public static final String UPDATE_SUCCESSFUL = "Product updated successfully";
        public static final String DELETE_SUCCESSFUL = "Product deleted successfully";
    }

    public static class Cart {
        public static final String CREATE_SUCCESSFUL = "Add product to cart successfully";
        public static final String DELETE_SUCCESSFUL = "Remove product to cart successfully";
        public static final String DELETE_ALL_SUCCESSFUL = "Remove all products to cart successfully";
    }

    public static class Address {
        public static final String CREATE_SUCCESSFUL = "Add address successfully";
        public static final String UPDATE_SUCCESSFUL = "Change your address successfully";
        public static final String DELETE_SUCCESSFUL = "Delete address successfully";
    }
}