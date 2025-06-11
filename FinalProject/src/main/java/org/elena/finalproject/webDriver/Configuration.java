package org.elena.finalproject.webDriver;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    private static Properties properties;

    private static Logger logger = Logger.getLogger(Configuration.class);

    private Configuration() {
    }

    public static Properties getProperties() {
        if (properties == null) {
            initProperties();
        }
        return properties;
    }

    private static void initProperties() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/project.properties"));
        } catch (IOException ex) {
            System.out.println("Cannot read the properties");
            logger.error("Cannot read the properties");
        }
    }

    public static BrowserEnum getBrowserEnum() {
        return BrowserEnum.valueOf(getProperties().get("browser").toString());
    }

    public static String getChromeDriverLocation() {
        return properties.getProperty("chromeDriverLocation");
    }

    public static String getBaseUrl() {
        return getProperties().get("baseUrl").toString();
    }

    public static String getRemoteDriverUrl() {
        return properties.getProperty("remoteWebDriverUrl");
    }
}
