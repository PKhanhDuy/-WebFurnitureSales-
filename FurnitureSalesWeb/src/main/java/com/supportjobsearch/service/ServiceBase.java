package com.supportjobsearch.service;

import com.example.ecommerce.Common.LogObj;

public abstract class ServiceBase {
    protected final LogObj log = new LogObj();
    String logName = getClass().getName();

    public ServiceBase() {
        log.setName(logName);
    }

    public abstract void init();

}
