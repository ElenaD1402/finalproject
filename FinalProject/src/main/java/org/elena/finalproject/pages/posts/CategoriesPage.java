package org.elena.finalproject.pages.posts;

import io.qameta.allure.Step;
import org.elena.finalproject.models.Category;
import org.elena.finalproject.pages.BasePage;
import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class CategoriesPage extends BasePage {

    private static final By PAGE_LOCATOR = By.xpath("//div[@class='wrap nosubsub']/h1[contains(text(),'Categories')]");
    private static final By NEW_CATEGORY_NAME_LOCATOR = By.id("tag-name");
    private static final By NEW_CATEGORY_SLUG_LOCATOR = By.id("tag-slug");
    private static final By PARENT_CATEGORY_LIST_LOCATOR = By.id("parent");
    private static final By PARENT_CATEGORY_LOCATOR = By.xpath("//select[@id='parent']/option");
    private static final By NEW_CATEGORY_DESCRIPTION_LOCATOR = By.id("tag-description");
    private static final By ADD_NEW_CATEGORY_BUTTON_LOCATOR = By.id("submit");
    private static final By CATEGORY_IS_ADDED_LOCATOR = By.xpath("//div[@id='ajax-response']//*[contains(text(),'Category added.')]");

    public CategoriesPage() {
        super();
    }

    @Step("Checking whether 'Categories' page is opened")
    @Override
    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }

    @Step("Adding a new category without parent")
    public void addNewCategoryWithoutParent(Category category)  {
        WebElement nameElement = Browser.waitForElementToBeVisible(NEW_CATEGORY_NAME_LOCATOR);
        nameElement.clear();
        nameElement.sendKeys(category.getName());
        WebElement slugElement = Browser.waitForElementToBeVisible(NEW_CATEGORY_SLUG_LOCATOR);
        slugElement.clear();
        slugElement.sendKeys(category.getSlug());
        WebElement descriptionElement = Browser.waitForElementToBeVisible(NEW_CATEGORY_DESCRIPTION_LOCATOR);
        descriptionElement.clear();
        descriptionElement.sendKeys(category.getDescription());
        WebElement addNewCategoryButtonElement = Browser.waitForElementToBeClickable(ADD_NEW_CATEGORY_BUTTON_LOCATOR);
        addNewCategoryButtonElement.click();
    }

    @Step("Adding a new category with parent")
    public void addNewCategoryWithParent(Category category)  {
        WebElement nameElement = Browser.waitForElementToBeVisible(NEW_CATEGORY_NAME_LOCATOR);
        nameElement.clear();
        nameElement.sendKeys(category.getName());
        WebElement slugElement = Browser.waitForElementToBeVisible(NEW_CATEGORY_SLUG_LOCATOR);
        slugElement.clear();
        slugElement.sendKeys(category.getSlug());
        WebElement parentCategoriesElement = Browser.waitForElementToBeClickable(PARENT_CATEGORY_LIST_LOCATOR);
        parentCategoriesElement.click();
        List<WebElement> parentCategoriesList = Browser.waitForElementsToBeVisible(PARENT_CATEGORY_LOCATOR);
        Random random = new Random();
        WebElement parentCategory = parentCategoriesList.get(random.nextInt(parentCategoriesList.size()));
        parentCategory.click();
        WebElement descriptionElement = Browser.waitForElementToBeVisible(NEW_CATEGORY_DESCRIPTION_LOCATOR);
        descriptionElement.clear();
        descriptionElement.sendKeys(category.getDescription());
        WebElement addNewCategoryButtonElement = Browser.waitForElementToBeClickable(ADD_NEW_CATEGORY_BUTTON_LOCATOR);
        addNewCategoryButtonElement.click();
    }

    @Step("Checking whether the category is added")
    public boolean isCategoryAdded() {
        WebElement addedCategoryElement = Browser.waitForElementToBeVisible(CATEGORY_IS_ADDED_LOCATOR);
        return addedCategoryElement != null && addedCategoryElement.isDisplayed();
    }

    @Step("Deleting the category")
    public void deleteCategory(Category category) {
        WebElement categoryElement = Browser.waitForElementToBeVisible(By.xpath("//a[contains(text(),'" + category.getName() + "')]"));
        Browser.moveToElement(categoryElement).perform();
        WebElement deleteCategoryElement = Browser.waitForElementToBeClickable(By.xpath("//a[@aria-label='Delete “" + category.getName() + "”']"));
        Browser.click(deleteCategoryElement);
        Browser.acceptAlert();
        Browser.waitDeleting(categoryElement);
    }

    @Step("Checking whether the category is deleted")
    public boolean isCategoryDeleted(Category category) {
        WebElement deletedCategoryElement = Browser.waitForElementToBeVisible(By.xpath("//a[contains(text(),'" + category.getName() + "')]"));
        return deletedCategoryElement == null;
    }
}
