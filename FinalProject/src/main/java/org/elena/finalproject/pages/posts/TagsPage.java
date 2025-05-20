package org.elena.finalproject.pages.posts;

import org.elena.finalproject.pages.BasePage;
import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TagsPage extends BasePage {

    private static final By PAGE_LOCATOR = By.xpath("//div[@class='wrap nosubsub']/h1[contains(text(),'Tags')]");

    public TagsPage() {
        super();
    }

    @Override
    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }
}
