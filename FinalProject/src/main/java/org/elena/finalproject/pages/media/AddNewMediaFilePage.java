package org.elena.finalproject.pages.media;

import org.elena.finalproject.pages.BasePage;
import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.nio.file.Path;

public class AddNewMediaFilePage extends BasePage {

    private static final By PAGE_LOCATOR = By.xpath("//div[@class='wrap']/h1[contains(text(),'Upload New Media')]");
    private static final By UPLOAD_FILES_FIELD_LOCATOR = By.xpath("//*[@id='plupload-upload-ui']//input[@type='file']");
    private static final By UPLOADED_FILES_LOCATOR = By.id("media-items");

    static Path path = Path.of("test_image1.png");
    String absolutePath = path.toAbsolutePath().toString();

    public AddNewMediaFilePage() {
        super();
    }

    public static String getPath() {
        return path.toString();
    }

    @Override
    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }

    public void uploadMediaFile() {
        WebElement uploadInput = Browser.waitForElementToBePresent(UPLOAD_FILES_FIELD_LOCATOR);
        uploadInput.sendKeys(absolutePath);
    }

    public boolean isFileUploaded() {
        WebElement uploadedFileElement = Browser.waitForElementToBeVisible(UPLOADED_FILES_LOCATOR);
        return uploadedFileElement != null && uploadedFileElement.isDisplayed();
    }
}
