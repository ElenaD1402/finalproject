package org.elena.finalproject.pages;

import io.qameta.allure.Step;
import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CommentsPage extends BasePage {

    private static final By PAGE_LOCATOR = By.xpath("//div[@class='wrap']/h1[contains(text(),'Comments')]");

    public CommentsPage() {
        super();
    }

    @Step("Checking whether 'Comments' page is opened")
    @Override
    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }
}
