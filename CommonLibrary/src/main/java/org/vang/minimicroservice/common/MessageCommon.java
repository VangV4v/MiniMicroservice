package org.vang.minimicroservice.common;

public class MessageCommon {
    public static String ERROR_001 = "Username is already exist";
    public static String ERROR_002 = "Email is already exist";
    public static String ERROR_003 = "Phone is already exist";
    public static String ERROR_004 = "Please send request with hdnOldEmail";
    public static String ERROR_005 = "Please send request with hdnOldPhone";

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
}