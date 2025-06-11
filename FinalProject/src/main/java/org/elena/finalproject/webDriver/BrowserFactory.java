package org.elena.finalproject.webDriver;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {

    static {
        System.setProperty("webdriver.chrome.driver", Configuration.getChromeDriverLocation());
    }

    private static Logger logger = Logger.getLogger(BrowserFactory.class);

    public static WebDriver createDriver(BrowserEnum browserEnum) {
        WebDriver webDriver;
        switch (browserEnum) {
            case CHROME -> {
                webDriver = new ChromeDriver(getChromeOptions());
            }
            case REMOTE_CHROME -> {
                try {
                    webDriver = new RemoteWebDriver(new URL(Configuration.getRemoteDriverUrl()), getChromeOptions());
                } catch (MalformedURLException ex) {
                    System.out.println("Cannot create a driver with URL = " + Configuration.getRemoteDriverUrl());
                    logger.error("Cannot create a driver with URL = " + Configuration.getRemoteDriverUrl());
                    webDriver = null;
                }
            }
            case FIREFOX -> {
                webDriver = new FirefoxDriver(getFirefoxOptions());
            }
            case REMOTE_FIREFOX -> {
                try {
                    webDriver = new RemoteWebDriver(new URL(Configuration.getRemoteDriverUrl()), getFirefoxOptions());
                } catch (MalformedURLException ex) {
                    System.out.println("Cannot create a driver with URL = " + Configuration.getRemoteDriverUrl());
                    logger.error("Cannot create a driver with URL = " + Configuration.getRemoteDriverUrl());
                    webDriver = null;
                }
            }
            default -> {
                logger.error(browserEnum + " is not supported");
                throw new RuntimeException(browserEnum + " is not supported");
            }
        }
        return webDriver;
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("safebrowsing.enabled", true);
        prefs.put("download.default_directory", "C:\\Users\\Lena\\Downloads\\");
        prefs.put("browser.helperApps.neverAsk.saveToDisk", "application/pdf;text/csv;application/octet-stream;application/x-msdownload");
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        return chromeOptions;
    }

    private static FirefoxOptions getFirefoxOptions() {
        var firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        return firefoxOptions;
    }
}
