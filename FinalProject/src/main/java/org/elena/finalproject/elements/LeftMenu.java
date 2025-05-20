package org.elena.finalproject.elements;

import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LeftMenu {

    private static final By ITEM_HOVER_LOCATOR = By.xpath("//li[contains(@class,'opensub')]");

    private final DashboardMenu dashboardMenu = new DashboardMenu();
    private final PostsMenu postsMenu = new PostsMenu();
    private final MediaMenu mediaMenu = new MediaMenu();
    private final PagesMenu pagesMenu = new PagesMenu();

    public DashboardMenu getDashboardMenu() {
        return dashboardMenu;
    }

    public PostsMenu getPostsMenu() {
        return postsMenu;
    }

    public MediaMenu getMediaMenu() {
        return mediaMenu;
    }

    public PagesMenu getPagesMenu() {
        return pagesMenu;
    }

    public void hoverOverItem(LeftMenuEnum leftMenuEnum) {
        WebElement menuItemElement = Browser.waitForElementToBeVisible(By.id(leftMenuEnum.getId()));
        Browser.moveToElement(menuItemElement).perform();
        Browser.waitExpanding(ITEM_HOVER_LOCATOR);
    }

    public void clickItem(LeftMenuEnum leftMenuEnum) {
        WebElement menuItemElement = Browser.waitForElementToBeVisible(By.id(leftMenuEnum.getId()));
        Browser.moveToElement(menuItemElement).perform();
        Browser.waitExpanding(ITEM_HOVER_LOCATOR);
        WebElement menuItemHoverElement = Browser.waitForElementToBeClickable(ITEM_HOVER_LOCATOR);
        menuItemHoverElement.click();
    }
}
