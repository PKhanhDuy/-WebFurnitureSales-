package com.supportjobsearch.DAO.iml;

import org.jdbi.v3.core.ConnectionException;
import org.jdbi.v3.core.Handle;

public abstract class ImplementBase {
    protected final LogObj log = new LogObj();
    protected String logName = getClass().getName();
    protected JDBIConnect db;
    protected Handle handle;

    protected ImplementBase(){
        log.setName(logName);
        db = JDBIConnect.getInstance();
        try {
            handle = db.jdbi.open();
        }
        catch (ConnectionException e){
            log.error(e);
        }
    }

}
