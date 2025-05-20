package org.elena.finalproject.pages.media;

import org.elena.finalproject.pages.BasePage;
import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MediaPage extends BasePage {

    private static final String PATH = AddNewMediaFilePage.getPath();
    private static final By PAGE_LOCATOR = By.xpath("//div[@class='wrap']/h1[contains(text(),'Media Library')]");
    private static final By TABLE_VIEW_LOCATOR = By.id("view-switch-list");
    private static final By UPLOADED_FILE_LOCATOR = By.xpath(String.format("//p[@class='filename'][text()[contains(.,'%s')]]", PATH));
    private static final By DELETE_PERMANENTLY_LOCATOR = By.xpath(String.format("//p[@class='filename'][text()[contains(.,'%s')]]/..//span[@class='delete']/a[contains(text(),'Delete Permanently')]", PATH));

    public MediaPage() {
        super();
    }

    @Override
    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }

    public void chooseTableView() {
        WebElement tableViewElement = Browser.waitForElementToBeVisible(TABLE_VIEW_LOCATOR);
        Browser.moveToElement(tableViewElement).click().perform();
    }

    public boolean isFileUploaded() {
        WebElement uploadedFileElement = Browser.waitForElementToBeVisible(UPLOADED_FILE_LOCATOR);
        return uploadedFileElement != null && uploadedFileElement.isDisplayed();
    }

    public void deleteMediaFile() {
        WebElement deletePermanentlyElement = Browser.waitForElementToBePresent(DELETE_PERMANENTLY_LOCATOR);
        Browser.click(deletePermanentlyElement);
        Browser.acceptAlert();
    }

    public boolean isFileDeleted() {
        WebElement deletedFileElement = Browser.waitForElementToBeVisible(UPLOADED_FILE_LOCATOR);
        return deletedFileElement == null;
    }
}
