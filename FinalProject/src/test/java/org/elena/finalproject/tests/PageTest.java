package org.elena.finalproject.tests;

import org.elena.finalproject.credentials.UserEnum;
import org.elena.finalproject.models.Page;
import org.elena.finalproject.pages.DashboardPage;
import org.elena.finalproject.pages.DeletionPage;
import org.elena.finalproject.pages.LoginPage;
import org.elena.finalproject.pages.pages.AddNewPagePage;
import org.elena.finalproject.pages.pages.PagesPage;
import org.elena.finalproject.utils.DataGenerator;
import org.elena.finalproject.webDriver.Browser;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class PageTest {

    private static final String pageTitle1 = DataGenerator.getRandomString(15);
    private static final String pageBlock1 = DataGenerator.getRandomString(50);
    private static final Page page1 = Page.builder().title(pageTitle1).block(pageBlock1).build();
    private static final String pageTitle2 = DataGenerator.getRandomString(15);
    private static final String pageBlock2 = DataGenerator.getRandomString(50);
    private static final Page page2 = Page.builder().title(pageTitle2).block(pageBlock2).build();
    private static final String pageTitle3 = DataGenerator.getRandomString(15);
    private static final Page page3 = Page.builder().title(pageTitle3).build();

    private final LoginPage loginPage = new LoginPage();
    private final DashboardPage dashboardPage = new DashboardPage();
    private final AddNewPagePage addNewPagePage = new AddNewPagePage();
    private final PagesPage pagesPage = new PagesPage();
    private final DeletionPage deletionPage = new DeletionPage();

    @BeforeClass
    public void setUp() {
        loginPage.openLoginPage();
        Assert.assertTrue(loginPage.isPageOpened(), "'Login' page is not opened");
        loginPage.logIn(UserEnum.ADMINISTRATOR.getUsername(), UserEnum.ADMINISTRATOR.getPassword());
        Assert.assertTrue(dashboardPage.isPageOpened(), "'Dashboard' page is not opened");
    }

    @AfterClass
    public void tearDown() {
        pagesPage.getHeader().logOut();
        Browser.close();
    }

    @AfterMethod
    public void afterMethod(Method method) {
        System.out.println("Test " + method.getName() + " is finished");
    }

    @Test(priority = 1)
    public void testDraftPageCanBeCreated() {
        addNewPagePage.openAddNewPagePage();
        Assert.assertTrue(addNewPagePage.isPageOpened(), "'Add New Page' page is not opened");
        addNewPagePage.addNewPageDraft(page1);
        addNewPagePage.goToPagesPage();
        Assert.assertTrue(pagesPage.isPageOpened(), "'Pages' page is not opened");
        Assert.assertTrue(pagesPage.isDraftPageAdded(page1), "'Draft' page is not added");
        Browser.makeScreenshot();
    }

    @Test(priority = 1)
    public void testPageWithoutPatternCanBeCreated() {
        addNewPagePage.openAddNewPagePage();
        Assert.assertTrue(addNewPagePage.isPageOpened(), "'Add New Page' page is not opened");
        addNewPagePage.addNewPageWithoutPattern(page2);
        addNewPagePage.goToPagesPage();
        Assert.assertTrue(pagesPage.isPageOpened(), "'Pages' page is not opened");
        Assert.assertTrue(pagesPage.isPageAdded(page2), "Page is not added");
        Browser.makeScreenshot();
    }

    @Test(priority = 1)
    public void testPageWithPatternCanBeCreated() {
        addNewPagePage.openAddNewPagePage();
        Assert.assertTrue(addNewPagePage.isPageOpened(), "'Add New Page' page is not opened");
        addNewPagePage.addNewPageWithAboutPattern(page3);
        addNewPagePage.goToPagesPage();
        Assert.assertTrue(pagesPage.isPageOpened(), "'Pages' page is not opened");
        Assert.assertTrue(pagesPage.isPageAdded(page3), "Page is not added");
        Browser.makeScreenshot();
    }

    @DataProvider(name = "paramsPagesToBeDeleted")
    public static Object[][] paramsPagesToBeDeleted() {
        return new Object[][]{
                {page1, pageTitle1},
                {page2, pageTitle2},
                {page3, pageTitle3},
        };
    }

    @Test(priority = 2, dataProvider = "paramsPagesToBeDeleted")
    public void testPageCanBeDeleted(Page page, String title) {
        pagesPage.openPagesPage();
        Assert.assertTrue(pagesPage.isPageOpened(), "'Pages' page is not opened");
        pagesPage.deletePage(page);
        Assert.assertTrue(deletionPage.isPageOpened(), "Page '" + title + "' is not deleted");
        pagesPage.openPagesPage();
        Assert.assertTrue(pagesPage.isPageOpened(), "'Pages' page is not opened");
        Assert.assertTrue(pagesPage.isPageDeleted(page), "Page '" + title + "' is not deleted");
        Browser.makeScreenshot();
    }
}
