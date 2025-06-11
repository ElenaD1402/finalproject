package org.elena.finalproject.pages.posts;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.elena.finalproject.models.Category;
import org.elena.finalproject.pages.BasePage;
import org.elena.finalproject.webDriver.Browser;
import org.elena.finalproject.webDriver.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CategoriesPage extends BasePage {

    private static final By PAGE_LOCATOR = By.xpath("//div[@class='wrap nosubsub']/h1[contains(text(),'Categories')]");
    private static final By NEW_CATEGORY_NAME_LOCATOR = By.id("tag-name");
    private static final By NEW_CATEGORY_SLUG_LOCATOR = By.id("tag-slug");
    private static final By PARENT_CATEGORY_LIST_LOCATOR = By.id("parent");
    private static final By NEW_CATEGORY_DESCRIPTION_LOCATOR = By.id("tag-description");
    private static final By ADD_NEW_CATEGORY_BUTTON_LOCATOR = By.id("submit");
    private static final By CATEGORY_IS_ADDED_LOCATOR = By.xpath("//div[@id='ajax-response']//*[contains(text(),'Category added.')]");
    private static final By SEARCH_CATEGORIES_FIELD_LOCATOR = By.id("tag-search-input");
    private static final By SEARCH_CATEGORIES_BUTTON_LOCATOR = By.id("search-submit");
    private static final By NO_CATEGORIES_FOUND_LOCATOR = By.xpath("//*[contains(text(),'No categories found.')]");

    private Logger logger = Logger.getLogger(this.getClass());

    public CategoriesPage() {
        super();
    }

    @Step("User opens 'Categories' page")
    public void openCategoriesPage() {
        Browser.getWebDriver().get(Configuration.getBaseUrl() + "/edit-tags.php?taxonomy=category");
        logger.info("Opening 'Categories' page");
    }

    @Step("Checking whether 'Categories' page is opened")
    @Override
    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }

    @Step("User enters a category name")
    private void enterCategoryName(String name) {
        WebElement nameElement = Browser.waitForElementToBeVisible(NEW_CATEGORY_NAME_LOCATOR);
        nameElement.clear();
        nameElement.sendKeys(name);
        logger.info("Entering a category name");
    }

    @Step("User enters a category slug")
    private void enterCategorySlug(String slug) {
        WebElement slugElement = Browser.waitForElementToBeVisible(NEW_CATEGORY_SLUG_LOCATOR);
        slugElement.clear();
        slugElement.sendKeys(slug);
        logger.info("Entering a category slug");
    }

    @Step("User chooses a parent category")
    private void chooseParentCategory(String parentCategoryName) {
        WebElement parentCategoriesListElement = Browser.waitForElementToBeClickable(PARENT_CATEGORY_LIST_LOCATOR);
        parentCategoriesListElement.click();
        WebElement parentCategoryElement = Browser.waitForElementToBeClickable(By
                .xpath("//*[@id='parent']/option[contains(text(),'" + parentCategoryName + "')]"));
        parentCategoryElement.click();
        logger.info("Choosing a parent category");
    }

    @Step("User enters a category description")
    private void enterCategoryDescription(String description) {
        WebElement descriptionElement = Browser.waitForElementToBeVisible(NEW_CATEGORY_DESCRIPTION_LOCATOR);
        descriptionElement.clear();
        descriptionElement.sendKeys(description);
        logger.info("Entering a category description");
    }

    @Step("User clicks 'Add New Category' button")
    private void clickAddNewCategoryButton() {
        WebElement addNewCategoryButtonElement = Browser.waitForElementToBeClickable(ADD_NEW_CATEGORY_BUTTON_LOCATOR);
        addNewCategoryButtonElement.click();
        logger.info("Clicking 'Add New Category' button");
    }

    @Step("User adds a new category without parent")
    public void addNewCategoryWithoutParent(Category category) {
        enterCategoryName(category.getName());
        enterCategorySlug(category.getSlug());
        enterCategoryDescription(category.getDescription());
        clickAddNewCategoryButton();
        logger.info("Adding a new category without parent");
    }

    @Step("User adds a new category with parent")
    public void addNewCategoryWithParent(Category category, Category parentCategory) {
        enterCategoryName(category.getName());
        enterCategorySlug(category.getSlug());
        chooseParentCategory(parentCategory.getName());
        enterCategoryDescription(category.getDescription());
        clickAddNewCategoryButton();
        logger.info("Adding a new category with parent");
    }

    @Step("User searches for the category with name = '{name}'")
    public void searchCategory(String name) {
        WebElement searchCategoriesFieldElement = Browser.waitForElementToBeVisible(SEARCH_CATEGORIES_FIELD_LOCATOR);
        searchCategoriesFieldElement.clear();
        searchCategoriesFieldElement.sendKeys(name);
        WebElement searchCategoriesButtonElement = Browser.waitForElementToBeClickable(SEARCH_CATEGORIES_BUTTON_LOCATOR);
        searchCategoriesButtonElement.click();
        logger.info("Searching for the category");
    }

    @Step("Checking whether the category is added")
    public boolean isCategoryAdded() {
        WebElement addedCategoryElement = Browser.waitForElementToBeVisible(CATEGORY_IS_ADDED_LOCATOR);
        return addedCategoryElement != null && addedCategoryElement.isDisplayed();
    }

    @Step("User deletes a category")
    public void deleteCategory(Category category) {
        WebElement categoryElement = Browser.waitForElementToBeVisible(By.xpath("//a[contains(text(),'" + category.getName() + "')]"));
        Browser.moveToElement(categoryElement).perform();
        WebElement deleteCategoryElement = Browser.waitForElementToBeClickable(By.xpath("//a[@aria-label='Delete “" + category.getName() + "”']"));
        Browser.click(deleteCategoryElement);
        Browser.acceptAlert();
        Browser.waitDeleting(deleteCategoryElement);
        logger.info("Deleting a category");
    }

    @Step("Checking whether the category is deleted")
    public boolean isCategoryDeleted(Category category) {
        searchCategory(category.getName());
        WebElement noCategoriesFoundElement = Browser.waitForElementToBeVisible(NO_CATEGORIES_FOUND_LOCATOR);
        return noCategoriesFoundElement != null && noCategoriesFoundElement.isDisplayed();
    }
}
