package org.elena.finalproject.elements;

import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PostsMenu {

    private static final By ALL_POSTS_LOCATOR = By.xpath("//a[contains(text(),'All Posts')]");
    private static final By ADD_NEW_POST_LOCATOR = By.xpath("//li/a[contains(text(),'Add New Post')]");
    private static final By CATEGORIES_LOCATOR = By.xpath("//a[contains(text(),'Categories')]");
    private static final By TAGS_LOCATOR = By.xpath("//a[contains(text(),'Tags')]");

    public void goToPostsPage() {
        WebElement allPostsElement = Browser.waitForElementToBeVisible(ALL_POSTS_LOCATOR);
        Browser.moveToElement(allPostsElement).click().perform();
    }

    public void addNewPost() {
        WebElement addNewPostElement = Browser.waitForElementToBeVisible(ADD_NEW_POST_LOCATOR);
        Browser.moveToElement(addNewPostElement).click().perform();
    }

    public void goToCategoriesPage() {
        WebElement categoriesElement = Browser.waitForElementToBeVisible(CATEGORIES_LOCATOR);
        Browser.moveToElement(categoriesElement).click().perform();
    }

    public void goToTagsPage() {
        WebElement tagsElement = Browser.waitForElementToBeVisible(TAGS_LOCATOR);
        Browser.moveToElement(tagsElement).click().perform();
    }
}
