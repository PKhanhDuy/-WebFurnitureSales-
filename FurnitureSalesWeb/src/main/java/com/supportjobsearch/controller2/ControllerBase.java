package com.supportjobsearch.controller2;


import com.supportjobsearch.common.LogObj;

public interface ControllerBase {
    public LogObj log = new LogObj();
    default void initialize(){
        MC.createInstance();
        log.setName(getClass().getSimpleName());
    }
}
