package org.elena.finalproject.pages.media;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.elena.finalproject.pages.BasePage;
import org.elena.finalproject.webDriver.Browser;
import org.elena.finalproject.webDriver.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MediaPage extends BasePage {

    private static final String PATH = AddNewMediaFilePage.getPath().replace(".png", "");
    private static final By PAGE_LOCATOR = By.xpath("//div[@class='wrap']/h1[contains(text(),'Media Library')]");
    private static final By TABLE_VIEW_LOCATOR = By.id("view-switch-list");
    private static final By UPLOADED_FILE_LOCATOR = By.xpath(String.format("//*[contains(@aria-label,'“%s” (Edit)')]", PATH));
    private static final By DELETE_PERMANENTLY_LOCATOR = By.xpath(String.format("//p[@class='filename'][text()[contains(.,'%s')]]/..//span[@class='delete']/a[contains(text(),'Delete Permanently')]", PATH));
    private static final By SEARCH_MEDIA_FIELD_LOCATOR = By.id("media-search-input");
    private static final By SEARCH_MEDIA_BUTTON_LOCATOR = By.id("search-submit");
    private static final By NO_MEDIA_FOUND_LOCATOR = By.xpath("//*[contains(text(),'No media files found.')]");

    private Logger logger = Logger.getLogger(this.getClass());

    public MediaPage() {
        super();
    }

    @Step("User opens 'Media Library' page")
    public void openMediaPage() {
        Browser.getWebDriver().get(Configuration.getBaseUrl() + "/upload.php");
        logger.info("Opening 'Media Library' page");
    }

    @Step("Checking whether 'Media Library' page is opened")
    @Override
    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }

    @Step("User chooses a table view")
    public void chooseTableView() {
        WebElement tableViewElement = Browser.waitForElementToBeVisible(TABLE_VIEW_LOCATOR);
        Browser.moveToElement(tableViewElement).click().perform();
        logger.info("Choosing a table view");
    }

    @Step("User searches for the media file")
    private void searchMedia() {
        WebElement searchMediaFieldElement = Browser.waitForElementToBeVisible(SEARCH_MEDIA_FIELD_LOCATOR);
        searchMediaFieldElement.clear();
        searchMediaFieldElement.sendKeys(PATH);
        WebElement searchMediaButtonElement = Browser.waitForElementToBeClickable(SEARCH_MEDIA_BUTTON_LOCATOR);
        searchMediaButtonElement.click();
        logger.info("Searching for the the media file");
    }

    @Step("Checking whether the media file is uploaded")
    public boolean isFileUploaded() {
        WebElement uploadedFileElement = Browser.waitForElementToBeClickable(UPLOADED_FILE_LOCATOR);
        return uploadedFileElement != null && uploadedFileElement.isDisplayed();
    }

    @Step("User deletes a media file")
    public void deleteMediaFile() {
        searchMedia();
        WebElement deletePermanentlyElement = Browser.waitForElementToBePresent(DELETE_PERMANENTLY_LOCATOR);
        Browser.click(deletePermanentlyElement);
        Browser.acceptAlert();
        logger.info("Deleting a media file");
    }

    @Step("Checking whether the media file is deleted")
    public boolean isFileDeleted() {
        searchMedia();
        WebElement noMediaFoundElement = Browser.waitForElementToBeVisible(NO_MEDIA_FOUND_LOCATOR);
        return noMediaFoundElement != null && noMediaFoundElement.isDisplayed();
    }
}
