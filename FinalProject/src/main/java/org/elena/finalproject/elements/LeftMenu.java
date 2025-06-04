package org.elena.finalproject.elements;

import io.qameta.allure.Step;
import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LeftMenu {

    private static final By ADMIN_MENU_LOCATOR = By.id("adminmenu");
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

    @Step("Hovering over {leftMenuEnum} menu item in the Left menu")
    public void hoverOverItem(LeftMenuEnum leftMenuEnum) {
        WebElement menuItemElement = Browser.waitForElementToBeVisible(By.id(leftMenuEnum.getId()));
        Browser.moveToElement(menuItemElement).perform();
        Browser.waitExpanding(ITEM_HOVER_LOCATOR);
    }

    @Step("Clicking {leftMenuEnum} menu item in the Left menu")
    public void clickItem(LeftMenuEnum leftMenuEnum) {
        WebElement menuItemElement = Browser.waitForElementToBeVisible(By.id(leftMenuEnum.getId()));
        Browser.moveToElement(menuItemElement).perform();
        Browser.waitExpanding(ITEM_HOVER_LOCATOR);
        WebElement menuItemHoverElement = Browser.waitForElementToBeClickable(ITEM_HOVER_LOCATOR);
        menuItemHoverElement.click();
    }
    @Step("")
    public WebElement adminMenu() {
       WebElement adminMenuElement = Browser.waitForElementToBeVisible(ADMIN_MENU_LOCATOR);
       return adminMenuElement;
    }

//    public void findAdminMenuItem(String id) {
//        WebElement adminMenuElement = Browser.waitForElementToBeVisible(ADMIN_MENU_LOCATOR);
//        WebElement adminMenuItemElement = adminMenuElement.
//
//
//
//
//        try {
//            Assert.assertTrue(searchTopMenu.findElement(By.cssSelector(cssSelector)).isDisplayed());
//        } catch (NoSuchElementException ex) {
//            throw new RuntimeException("\"" + topMenuItem + "\" is not found");
//        }
//    }





}
