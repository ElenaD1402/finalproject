package org.elena.finalproject.tests;

import org.elena.finalproject.credentials.UserEnum;
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

    private static final String PARENT_CATEGORY_NAME = DataGenerator.getRandomCategory();
    private static final String PARENT_CATEGORY_SLUG = PARENT_CATEGORY_NAME.toLowerCase();
    private static final String PARENT_CATEGORY_DESCRIPTION = DataGenerator.getRandomString(20);
    private static final Category PARENT_CATEGORY = Category.builder().name(PARENT_CATEGORY_NAME).slug(PARENT_CATEGORY_SLUG)
            .description(PARENT_CATEGORY_DESCRIPTION).build();
    private static final String CATEGORY_NAME = DataGenerator.getRandomCategory();
    private static final String CATEGORY_SLUG = CATEGORY_NAME.toLowerCase();
    private static final String CATEGORY_DESCRIPTION = DataGenerator.getRandomString(20);
    private static final Category CATEGORY = Category.builder().name(CATEGORY_NAME).slug(CATEGORY_SLUG)
            .description(CATEGORY_DESCRIPTION).build();

    private final LoginPage loginPage = new LoginPage();
    private final DashboardPage dashboardPage = new DashboardPage();
    private final CategoriesPage categoriesPage = new CategoriesPage();

    @BeforeClass
    public void setUp() {
        loginPage.openLoginPage();
        Assert.assertTrue(loginPage.isPageOpened(), "'Login' page is not opened");
        loginPage.logIn(UserEnum.ADMINISTRATOR.getUsername(), UserEnum.ADMINISTRATOR.getPassword());
        Assert.assertTrue(dashboardPage.isPageOpened(), "'Dashboard' page is not opened");
    }

    @AfterClass
    public void tearDown() {
        categoriesPage.getHeader().logOut();
        Browser.close();
    }

    @BeforeMethod
    public void beforeMethod() {
        categoriesPage.openCategoriesPage();
        Assert.assertTrue(categoriesPage.isPageOpened(), "'Categories' page is not opened");
    }

    @AfterMethod
    public void afterMethod(Method method) {
        System.out.println("Test " + method.getName() + " is finished");
    }

    @Test
    public void testCategoryWithoutParentCanBeAdded() {
        categoriesPage.addNewCategoryWithoutParent(PARENT_CATEGORY);
        Assert.assertTrue(categoriesPage.isCategoryAdded(), "Category is not added");
        categoriesPage.searchCategory(PARENT_CATEGORY_NAME);
        Browser.makeScreenshot();
    }

    @Test(dependsOnMethods = {"testCategoryWithoutParentCanBeAdded"})
    public void testCategoryWithParentCanBeAdded() {
        categoriesPage.addNewCategoryWithParent(CATEGORY, PARENT_CATEGORY);
        Assert.assertTrue(categoriesPage.isCategoryAdded(), "Category is not added");
        categoriesPage.searchCategory(CATEGORY_NAME);
        Browser.makeScreenshot();
    }

    @DataProvider(name = "paramsCategoriesToBeDeleted")
    public static Object[][] paramsCategoriesToBeDeleted() {
        return new Object[][]{
                {CATEGORY, CATEGORY_NAME},
                {PARENT_CATEGORY, PARENT_CATEGORY_NAME}
        };
    }

    @Test(dependsOnMethods = {"testCategoryWithParentCanBeAdded"}, dataProvider = "paramsCategoriesToBeDeleted")
    public void testCategoryCanBeDeleted(Category category, String name) {
        categoriesPage.searchCategory(name);
        categoriesPage.deleteCategory(category);
        Assert.assertTrue(categoriesPage.isCategoryDeleted(category), "Category is not deleted");
        Browser.makeScreenshot();
    }
}
