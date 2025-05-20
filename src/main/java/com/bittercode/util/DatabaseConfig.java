package com.bittercode.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class DatabaseConfig {

    static Properties prop = new Properties();

    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("application.properties");

        try {
            if(input != null) {
                prop.load(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getEnvOrProp(String envKey, String propKey) {
        String val = System.getenv(envKey);
        if (val != null && !val.isEmpty()) {
            return val;
        }
        return prop.getProperty(propKey);
    }

    public final static String DRIVER_NAME = getEnvOrProp("DB_DRIVER", "db.driver");
    public final static String DB_HOST = getEnvOrProp("DB_HOST", "db.host");
    public final static String DB_PORT = getEnvOrProp("DB_PORT", "db.port");
    public final static String DB_NAME = getEnvOrProp("DB_NAME", "db.name");
    public final static String DB_USER_NAME = getEnvOrProp("DB_USER", "db.username");
    public final static String DB_PASSWORD = getEnvOrProp("DB_PASS", "db.password");

    public final static String CONNECTION_STRING = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?useSSL=false&allowPublicKeyRetrieval=true";
}
