package org.elena.finalproject.pages.media;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.elena.finalproject.pages.BasePage;
import org.elena.finalproject.webDriver.Browser;
import org.elena.finalproject.webDriver.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.nio.file.Path;

public class AddNewMediaFilePage extends BasePage {

    private static final By PAGE_LOCATOR = By.xpath("//div[@class='wrap']/h1[contains(text(),'Upload New Media')]");
    private static final By UPLOAD_FILES_FIELD_LOCATOR = By.xpath("//*[@id='plupload-upload-ui']//input[@type='file']");
    private static final By UPLOADED_FILES_LOCATOR = By.xpath("//*[contains(@data-clipboard-text,'/wp-content/uploads/')]");
    private static final Path PATH = Path.of("test_image1.png");
    private static final String ABSOLUTE_PATH = PATH.toAbsolutePath().toString();

    private Logger logger = Logger.getLogger(this.getClass());

    public AddNewMediaFilePage() {
        super();
    }

    public static String getPath() {
        return PATH.toString();
    }

    @Step("User opens 'Add New Media File' page")
    public void openAddNewMediaFilePage() {
        Browser.getWebDriver().get(Configuration.getBaseUrl() + "/media-new.php");
        logger.info("Opening 'Add New Media File' page");
    }

    @Step("Checking whether 'Add New Media File' page is opened")
    @Override
    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }

    @Step("User uploads a media file")
    public void uploadMediaFile() {
        WebElement uploadInput = Browser.waitForElementToBePresent(UPLOAD_FILES_FIELD_LOCATOR);
        uploadInput.sendKeys(ABSOLUTE_PATH);
        logger.info("Uploading a media file");
    }

    @Step("Checking whether the media file is uploaded")
    public boolean isFileUploaded() {
        WebElement uploadedFileElement = Browser.waitForElementToBeVisible(UPLOADED_FILES_LOCATOR);
        return uploadedFileElement != null && uploadedFileElement.isDisplayed();
    }
}
