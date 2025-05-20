package com.supportjobsearch.database;

import com.supportjobsearch.common.ManagerBase;

import java.io.IOException;
import java.util.Properties;

public class DBProperties extends ManagerBase {
    private static final Properties prop = new Properties();

    public String host;
    public int port;
    public String username;
    public String password;
    public String dbname;
    public String option;

    public static DBProperties getInstance(){
        return new DBProperties();
    }

    @Override
    protected void startInitializeBehavior() {

        log.info("Loading properties from file");
        try {
            prop.load(DBProperties.class.getClassLoader().getResourceAsStream("db.properties"));

        } catch (IOException e) {
            log.error("Error loading properties");
            log.error(e);
            endInitialize(false);
        }

        host = prop.getProperty("db.host");
        username = prop.getProperty("db.username");
        password = prop.getProperty("db.password");
        dbname = prop.getProperty("db.dbname");
        option = prop.getProperty("db.option");

        log.info("Loaded properties from file");

        try{
            port = Integer.parseInt(prop.getProperty("db.port"));
        }catch (NumberFormatException e){
            port = 3306;
            log.warn("Error parsing port, using 3306");
        }

        endInitialize(true);
    }

    @Override
    protected void endInitializeBehavior() {}
}
