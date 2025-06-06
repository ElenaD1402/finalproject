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

    private static final String PAGE_TITLE_1 = DataGenerator.getRandomString(15);
    private static final String PAGE_BLOCK_1 = DataGenerator.getRandomString(50);
    private static final Page PAGE_1 = Page.builder().title(PAGE_TITLE_1).block(PAGE_BLOCK_1).build();
    private static final String PAGE_TITLE_2 = DataGenerator.getRandomString(15);
    private static final String PAGE_BLOCK_2 = DataGenerator.getRandomString(50);
    private static final Page PAGE_2 = Page.builder().title(PAGE_TITLE_2).block(PAGE_BLOCK_2).build();
    private static final String PAGE_TITLE_3 = DataGenerator.getRandomString(15);
    private static final Page PAGE_3 = Page.builder().title(PAGE_TITLE_3).build();

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
        addNewPagePage.addNewPageDraft(PAGE_1);
        addNewPagePage.goToPagesPage();
        Assert.assertTrue(pagesPage.isPageOpened(), "'Pages' page is not opened");
        Assert.assertTrue(pagesPage.isDraftPageAdded(PAGE_1), "'Draft' page is not added");
        Browser.makeScreenshot();
    }

    @Test(priority = 1)
    public void testPageWithoutPatternCanBeCreated() {
        addNewPagePage.openAddNewPagePage();
        Assert.assertTrue(addNewPagePage.isPageOpened(), "'Add New Page' page is not opened");
        addNewPagePage.addNewPageWithoutPattern(PAGE_2);
        addNewPagePage.goToPagesPage();
        Assert.assertTrue(pagesPage.isPageOpened(), "'Pages' page is not opened");
        Assert.assertTrue(pagesPage.isPageAdded(PAGE_2), "Page is not added");
        Browser.makeScreenshot();
    }

    @Test(priority = 1)
    public void testPageWithPatternCanBeCreated() {
        addNewPagePage.openAddNewPagePage();
        Assert.assertTrue(addNewPagePage.isPageOpened(), "'Add New Page' page is not opened");
        addNewPagePage.addNewPageWithAboutPattern(PAGE_3);
        addNewPagePage.goToPagesPage();
        Assert.assertTrue(pagesPage.isPageOpened(), "'Pages' page is not opened");
        Assert.assertTrue(pagesPage.isPageAdded(PAGE_3), "Page is not added");
        Browser.makeScreenshot();
    }

    @DataProvider(name = "paramsPagesToBeDeleted")
    public static Object[][] paramsPagesToBeDeleted() {
        return new Object[][]{
                {PAGE_1, PAGE_TITLE_1},
                {PAGE_2, PAGE_TITLE_2},
                {PAGE_3, PAGE_TITLE_3}
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
