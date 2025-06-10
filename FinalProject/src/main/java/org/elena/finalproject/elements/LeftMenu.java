package org.elena.finalproject.elements;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LeftMenu {

    private static final By ITEM_HOVER_LOCATOR = By.xpath("//li[contains(@class,'opensub')]");
    private static final By ALL_MENU_ITEMS_LOCATOR = By.xpath("//*[@id='adminmenu']/li");

    private final DashboardMenu dashboardMenu = new DashboardMenu();
    private final PostsMenu postsMenu = new PostsMenu();
    private final MediaMenu mediaMenu = new MediaMenu();
    private final PagesMenu pagesMenu = new PagesMenu();

    private Logger logger = Logger.getLogger(this.getClass());

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

    @Step("User hovers over {leftMenuEnum} menu item in the Left menu")
    public void hoverOverItem(LeftMenuEnum leftMenuEnum) {
        WebElement menuItemElement = Browser.waitForElementToBeVisible(By.id(leftMenuEnum.getId()));
        Browser.moveToElement(menuItemElement).perform();
        Browser.waitExpanding(ITEM_HOVER_LOCATOR);
        logger.info("Hovering over " + leftMenuEnum.name() + " menu item in the Left menu");
    }

    @Step("User clicks {leftMenuEnum} menu item in the Left menu")
    public void clickItem(LeftMenuEnum leftMenuEnum) {
        hoverOverItem(leftMenuEnum);
        WebElement menuItemHoverElement = Browser.waitForElementToBeClickable(ITEM_HOVER_LOCATOR);
        menuItemHoverElement.click();
        logger.info("Clicking " + leftMenuEnum.name() + " menu item in the Left menu");
    }

    @Step("Checking whether left menu item with id = '{id}' is present")
    public boolean isMenuItemPresent(String id) {
        List<WebElement> menuElements = Browser.waitForElementsToBeVisible(ALL_MENU_ITEMS_LOCATOR);
        return menuElements.stream().anyMatch(webElement -> webElement.findElement(By
                .xpath(String.format("//*[@id='%s']", id))).isDisplayed());
    }
}
