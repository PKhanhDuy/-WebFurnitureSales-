package com.supportjobsearch.controller2;


import com.supportjobsearch.common.IInitializable;
import com.supportjobsearch.common.LogObj;
import com.supportjobsearch.database.JDBIConnect;
import com.supportjobsearch.service.*;

import java.util.ArrayList;

public class MC {

    public Integer savedID = null;
    public Integer backupID = null;

    public ProductService productService;
    public UserService userService;
    public OrderService orderService;
    public OrderItemService orderItemService;
    public WarehouseService warehouseService;
//    public PermissionService permissionService;
//    public PromotionService promotionService;
    public CategoryService categoryService;
    public LogObj log = new LogObj();

    private boolean initialized;
    private final IInitializable conn;
    private final ArrayList<ServiceBase> serviceList;

    public static MC instance;

    public static MC createInstance() {
        if (instance == null) {
            instance = new MC();
            instance.init();
        }
        return instance;
    }

    public MC() {
        initialized = false;
        conn = JDBIConnect.getInstance();
        serviceList = new ArrayList<>();
        serviceList.add(productService = ProductService.getInstance());
        serviceList.add(userService = UserService.getInstance());
        serviceList.add(orderService = OrderService.getInstance());
        serviceList.add(warehouseService = WarehouseService.getInstance());
//        serviceList.add(permissionService = PermissionService.getInstance());
//        serviceList.add(promotionService = PromotionService.getInstance());
        serviceList.add(categoryService = CategoryService.getInstance());
        serviceList.add(orderItemService = new OrderItemService());
    }

    private void init() {
        if (initialized) {
            log.info("Manager initialized");
            return;
        }

        log.setName(getClass().getName());
        log.info("Initializing commerce...");

        conn.Initialize();
        serviceList.forEach(ServiceBase::init);

        initialized = true;
        log.info("Initialized with status: " + initialized);
    }

    public static void main(String[] args) {
//        System.out.println(" ee địt mẹ");
    }

}
