package com.supportjobsearch.enums;

public enum RolePermission {
    CLIENT(1),
    MANAGER(2),
    EMPLOYEE(3),
    ADMINISTRATOR(4);

    private final int value;

    RolePermission(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static String SUPREME = "Supreme";
    public static String USER_MANAGEMENT= "User Management";
    public static String ORDER_MANAGEMENT= "Order Management";
    public static String PRODUCT_MANAGEMENT= "Product Management";
    public static String REPORTS_MANAGEMENT= "Reporting";
}
