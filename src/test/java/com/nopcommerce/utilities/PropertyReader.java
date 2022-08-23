package com.nopcommerce.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    //This is not in use anymore as owner API implementation is available.
    Properties prop;
    String propertyFilePath = System.getProperty("user.dir") + "/resources/config.properties";
    FileInputStream configFile;

    protected Logger logger = LogManager.getLogger(this.getClass());

    @Deprecated
    public String getPropertyValue(String key) {
        prop = new Properties();

        try {
            configFile = new FileInputStream(propertyFilePath);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e.getCause());
        }

        try {
            prop.load(configFile);
        } catch (IOException e) {
            logger.error(e.getMessage(), e.getCause());
        }
        return prop.getProperty(key);
    }
}
