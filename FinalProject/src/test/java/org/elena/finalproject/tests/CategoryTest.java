package org.elena.finalproject.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.log4j.Logger;
import org.elena.finalproject.users.UserEnum;
import org.elena.finalproject.models.Category;
import org.elena.finalproject.pages.DashboardPage;
import org.elena.finalproject.pages.LoginPage;
import org.elena.finalproject.pages.posts.CategoriesPage;
import org.elena.finalproject.utils.DataGenerator;
import org.elena.finalproject.webDriver.Browser;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class CategoryTest {

    private static final Category PARENT_CATEGORY = Category.builder()
            .name(DataGenerator.getRandomCategory())
            .slug(DataGenerator.getRandomString(5))
            .description(DataGenerator.getRandomString(20)).build();
    private static final Category CATEGORY = Category.builder()
            .name(DataGenerator.getRandomCategory())
            .slug(DataGenerator.getRandomString(5))
            .description(DataGenerator.getRandomString(20)).build();

    private final LoginPage loginPage = new LoginPage();
    private final DashboardPage dashboardPage = new DashboardPage();
    private final CategoriesPage categoriesPage = new CategoriesPage();

    private Logger logger = Logger.getLogger(this.getClass());

    @BeforeClass
    public void setUp() {
        try {
            loginPage.openLoginPage();
            Assert.assertTrue(loginPage.isPageOpened(), "'Login' page is not opened");
            loginPage.logIn(UserEnum.ADMINISTRATOR.getUsername(), System.getenv("admin_test"));
            Assert.assertTrue(dashboardPage.isPageOpened(), "'Dashboard' page is not opened");
        } catch (AssertionError | NullPointerException e) {
            logger.error(e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        categoriesPage.getHeader().logOut();
        Browser.close();
    }

    @BeforeMethod
    public void beforeMethod() {
        try {
            categoriesPage.openCategoriesPage();
            Assert.assertTrue(categoriesPage.isPageOpened(), "'Categories' page is not opened");
        } catch (AssertionError e) {
            logger.error(e.getMessage());
        }
    }

    @AfterMethod
    public void afterMethod(Method method) {
        Browser.makeScreenshot();
        System.out.println("Test " + method.getName() + " is finished");
    }

    @Epic(value = "Category")
    @Test
    @Description(value = "Test validates whether category without parent can be created")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCategoryWithoutParentCanBeAdded() {
        try {
            categoriesPage.addNewCategoryWithoutParent(PARENT_CATEGORY);
            Assert.assertTrue(categoriesPage.isCategoryAdded(), "Category is not added");
            categoriesPage.searchCategory(PARENT_CATEGORY.getName());
        } catch (AssertionError | NullPointerException e) {
            logger.error(e.getMessage());
        }
    }

    @Epic(value = "Category")
    @Test(dependsOnMethods = {"testCategoryWithoutParentCanBeAdded"})
    @Description(value = "Test validates whether category with parent can be created")
    @Severity(value = SeverityLevel.MINOR)
    public void testCategoryWithParentCanBeAdded() {
        try {
            categoriesPage.addNewCategoryWithParent(CATEGORY, PARENT_CATEGORY);
            Assert.assertTrue(categoriesPage.isCategoryAdded(), "Category is not added");
            categoriesPage.searchCategory(CATEGORY.getName());
        } catch (AssertionError | NullPointerException e) {
            logger.error(e.getMessage());
        }
    }

    @DataProvider(name = "paramsCategoriesToBeDeleted")
    public static Object[][] paramsCategoriesToBeDeleted() {
        return new Object[][]{
                {CATEGORY, CATEGORY.getName()},
                {PARENT_CATEGORY, PARENT_CATEGORY.getName()}
        };
    }

    @Epic(value = "Category")
    @Test(dependsOnMethods = {"testCategoryWithoutParentCanBeAdded", "testCategoryWithParentCanBeAdded"},
            dataProvider = "paramsCategoriesToBeDeleted")
    @Description(value = "Test validates whether category can be deleted")
    @Severity(value = SeverityLevel.MINOR)
    public void testCategoryCanBeDeleted(Category category, String name) {
        try {
            categoriesPage.searchCategory(name);
            categoriesPage.deleteCategory(category);
            Assert.assertTrue(categoriesPage.isCategoryDeleted(category), "Category is not deleted");
        } catch (AssertionError | NullPointerException e) {
            logger.error(e.getMessage());
        }
    }
}
