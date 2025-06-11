package org.elena.finalproject.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.log4j.Logger;
import org.elena.finalproject.users.UserEnum;
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

    private static final Page PAGE_1 = Page.builder()
            .title(DataGenerator.getRandomString(15))
            .block(DataGenerator.getRandomString(50)).build();
    private static final Page PAGE_2 = Page.builder()
            .title(DataGenerator.getRandomString(15))
            .block(DataGenerator.getRandomString(50)).build();
    private static final Page PAGE_3 = Page.builder()
            .title(DataGenerator.getRandomString(15)).build();

    private final LoginPage loginPage = new LoginPage();
    private final DashboardPage dashboardPage = new DashboardPage();
    private final AddNewPagePage addNewPagePage = new AddNewPagePage();
    private final PagesPage pagesPage = new PagesPage();
    private final DeletionPage deletionPage = new DeletionPage();

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
        pagesPage.getHeader().logOut();
        Browser.close();
    }

    @AfterMethod
    public void afterMethod(Method method) {
        Browser.makeScreenshot();
        System.out.println("Test " + method.getName() + " is finished");
    }

    @Epic(value = "Page")
    @Test
    @Description(value = "Test validates whether draft page can be created")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testDraftPageCanBeCreated() {
        try {
            addNewPagePage.openAddNewPagePage();
            Assert.assertTrue(addNewPagePage.isPageOpened(), "'Add New Page' page is not opened");
            addNewPagePage.addNewPageDraft(PAGE_1);
            addNewPagePage.goToPagesPage();
            Assert.assertTrue(pagesPage.isPageOpened(), "'Pages' page is not opened");
            Assert.assertTrue(pagesPage.isDraftPageAdded(PAGE_1), "'Draft' page is not added");
        } catch (AssertionError | NullPointerException e) {
            logger.error(e.getMessage());
        }
    }

    @Epic(value = "Page")
    @Test
    @Description(value = "Test validates whether page without pattern can be published")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testPageWithoutPatternCanBeCreated() {
        try {
            addNewPagePage.openAddNewPagePage();
            Assert.assertTrue(addNewPagePage.isPageOpened(), "'Add New Page' page is not opened");
            addNewPagePage.addNewPageWithoutPattern(PAGE_2);
            addNewPagePage.goToPagesPage();
            Assert.assertTrue(pagesPage.isPageOpened(), "'Pages' page is not opened");
            Assert.assertTrue(pagesPage.isPageAdded(PAGE_2), "Page is not added");
        } catch (AssertionError | NullPointerException e) {
            logger.error(e.getMessage());
        }
    }

    @Epic(value = "Page")
    @Test
    @Description(value = "Test validates whether page with pattern can be published")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testPageWithPatternCanBeCreated() {
        try {
            addNewPagePage.openAddNewPagePage();
            Assert.assertTrue(addNewPagePage.isPageOpened(), "'Add New Page' page is not opened");
            addNewPagePage.addNewPageWithAboutPattern(PAGE_3);
            addNewPagePage.goToPagesPage();
            Assert.assertTrue(pagesPage.isPageOpened(), "'Pages' page is not opened");
            Assert.assertTrue(pagesPage.isPageAdded(PAGE_3), "Page is not added");
        } catch (AssertionError | NullPointerException e) {
            logger.error(e.getMessage());
        }
    }

    @DataProvider(name = "paramsPagesToBeDeleted")
    public static Object[][] paramsPagesToBeDeleted() {
        return new Object[][]{
                {PAGE_1, PAGE_1.getTitle()},
                {PAGE_2, PAGE_2.getTitle()},
                {PAGE_3, PAGE_3.getTitle()}
        };
    }

    @Epic(value = "Page")
    @Test(dependsOnMethods = {"testDraftPageCanBeCreated", "testPageWithoutPatternCanBeCreated",
            "testPageWithPatternCanBeCreated"}, dataProvider = "paramsPagesToBeDeleted")
    @Description(value = "Test validates whether page can be deleted")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testPageCanBeDeleted(Page page, String title) {
        try {
            pagesPage.openPagesPage();
            Assert.assertTrue(pagesPage.isPageOpened(), "'Pages' page is not opened");
            pagesPage.deletePage(page);
            Assert.assertTrue(deletionPage.isPageOpened(), "Page '" + title + "' is not deleted");
            pagesPage.openPagesPage();
            Assert.assertTrue(pagesPage.isPageOpened(), "'Pages' page is not opened");
            Assert.assertTrue(pagesPage.isPageDeleted(page), "Page '" + title + "' is not deleted");
        } catch (AssertionError | NullPointerException e) {
            logger.error(e.getMessage());
        }
    }
}
