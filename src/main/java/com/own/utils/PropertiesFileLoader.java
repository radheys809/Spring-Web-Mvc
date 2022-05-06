package com.own.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesFileLoader {
    static Logger logger = LoggerFactory.getLogger(PropertiesFileLoader.class);

    private InputStream is;
    private Properties prop;

    public Properties getInstance(String path) {
        if (prop == null) {
            prop = new Properties();
        }
        try {
            if (path != null) {
                is = new FileInputStream(new File(path));
                prop.load(is);
                logger.debug(path.split("/") + " file loaded successfully");
                return prop;
            } else {
                logger.debug("property file not exists in workspace");
                return null;
            }
        } catch (Exception e) {
            logger.error("Error occred while loading property file  :", e);
            return null;
        }
    }
}
