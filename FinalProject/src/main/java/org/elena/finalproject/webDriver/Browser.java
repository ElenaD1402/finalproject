package org.elena.finalproject.webDriver;

import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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
        } catch (NotFoundException | TimeoutException ex) {
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

    public static List<WebElement> waitForElementsToBeVisible(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(TIME_OUT_IN_SECONDS));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            List<WebElement> listElement = getWebDriver().findElements(locator);
            return listElement;
        } catch (NotFoundException ex) {
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

    public static void waitDeleting(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(TIME_OUT_IN_SECONDS));
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    public static void waitRefreshing(By locator) {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(TIME_OUT_IN_SECONDS));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
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

    public static void switchToFrame(WebElement frame) {
        getWebDriver().switchTo().frame(frame);
    }

    public static void switchToDefaultContent() {
        getWebDriver().switchTo().defaultContent();
    }

    public static void pressKey(Keys key) {
        Actions pressKey = new Actions(getWebDriver());
        pressKey.keyDown(String.valueOf(key)).keyUp(String.valueOf(key)).perform();
    }

    @Attachment
    public static byte[] makeScreenshot() {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static void close() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }
}
