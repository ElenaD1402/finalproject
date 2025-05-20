package org.elena.finalproject.webDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Browser {

    public static final long DEFAULT_TIME_OUT = 10L;
    public static final int TIME_OUT_IN_SECONDS = 20;

    private static WebDriver webDriver;

    private Browser() {
    }

    public static WebDriver getWebDriver() {
        if (webDriver == null) {
            initDriver();
        }
        return webDriver;
    }

    public static JavascriptExecutor getJavascriptExecutor() {
        return (JavascriptExecutor) getWebDriver();
    }

    public static void initDriver() {
        webDriver = BrowserFactory.createDriver(Configuration.getBrowserEnum());
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(DEFAULT_TIME_OUT));
    }

    public static WebElement waitForElementToBeClickable(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(TIME_OUT_IN_SECONDS));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            WebElement element = getWebDriver().findElement(locator);
            return element;
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static WebElement waitForElementToBeVisible(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(TIME_OUT_IN_SECONDS));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            WebElement element = getWebDriver().findElement(locator);
            return element;
        } catch (TimeoutException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static WebElement waitForElementToBePresent(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(TIME_OUT_IN_SECONDS));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            WebElement element = getWebDriver().findElement(locator);
            return element;
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static Actions moveToElement(WebElement webElement) {
        Actions moveTo = new Actions(Browser.getWebDriver());
        return moveTo.moveToElement(webElement);
    }

    public static void waitExpanding(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(TIME_OUT_IN_SECONDS));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            WebElement element = getWebDriver().findElement(locator);
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void waitUploading(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(TIME_OUT_IN_SECONDS));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            WebElement element = getWebDriver().findElement(locator);
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void scroll(WebElement webElement) {
        getJavascriptExecutor().executeScript("arguments[0].scrollIntoView();", webElement);
    }

    public static void click(WebElement webElement) {
        getJavascriptExecutor().executeScript("arguments[0].click();", webElement);
    }

    public static void acceptAlert() {
        getWebDriver().switchTo().alert().accept();
    }

    public static void unhide(WebElement webElement) {
        String script = "arguments[0].style.opacity=1;"
                + "arguments[0].style['transform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['MozTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['WebkitTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['msTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['OTransform']='translate(0px, 0px) scale(1)';"
                + "return true;";
        getJavascriptExecutor().executeScript(script, webElement);
    }

    public static void close() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }
}
