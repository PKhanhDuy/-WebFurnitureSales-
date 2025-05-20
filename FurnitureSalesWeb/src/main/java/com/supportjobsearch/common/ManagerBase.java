package com.supportjobsearch.common;

public abstract class ManagerBase implements IInitializable{
    protected final LogObj log = new LogObj();
    protected String logName = getClass().getName();
    protected boolean initialized = false;

    @Override
    public void Initialize() {
        if (initialized) return;
        log.setName(logName);
        log.info("Initialize commerce...");

        startInitializeBehavior();
    }

    protected void endInitialize(boolean status) {
        log.info("Initialize completed with status:" + status);
        initialized = status;
        endInitializeBehavior();
    }

    protected abstract void startInitializeBehavior();
    protected abstract void endInitializeBehavior();
}