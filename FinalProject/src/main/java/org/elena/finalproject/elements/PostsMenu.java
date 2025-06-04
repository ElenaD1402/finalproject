package org.elena.finalproject.elements;

import io.qameta.allure.Step;
import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PostsMenu {

    private static final By ALL_POSTS_LOCATOR = By.xpath("//a[contains(text(),'All Posts')]");
    private static final By ADD_NEW_POST_LOCATOR = By.xpath("//li/a[contains(text(),'Add New Post')]");
    private static final By CATEGORIES_LOCATOR = By.xpath("//a[contains(text(),'Categories')]");
    private static final By TAGS_LOCATOR = By.xpath("//a[contains(text(),'Tags')]");

    @Step("Clicking 'All Posts' in the Posts menu. Opening 'Posts' page")
    public void goToPostsPage() {
        WebElement allPostsElement = Browser.waitForElementToBeVisible(ALL_POSTS_LOCATOR);
        Browser.moveToElement(allPostsElement).click().perform();
    }

    @Step("Clicking 'Add New Post' in the Posts menu. Opening 'Add New Post' page")
    public void addNewPost() {
        WebElement addNewPostElement = Browser.waitForElementToBeVisible(ADD_NEW_POST_LOCATOR);
        Browser.moveToElement(addNewPostElement).click().perform();
    }

    @Step("Clicking 'Categories' in the Posts menu. Opening 'Categories' page")
    public void goToCategoriesPage() {
        WebElement categoriesElement = Browser.waitForElementToBeVisible(CATEGORIES_LOCATOR);
        Browser.moveToElement(categoriesElement).click().perform();
    }

    @Step("Clicking 'Tags' in the Posts menu. Opening 'Tags' page")
    public void goToTagsPage() {
        WebElement tagsElement = Browser.waitForElementToBeVisible(TAGS_LOCATOR);
        Browser.moveToElement(tagsElement).click().perform();
    }
}
