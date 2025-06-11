package org.elena.finalproject.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.log4j.Logger;
import org.elena.finalproject.users.UserEnum;
import org.elena.finalproject.elements.LeftMenuEnum;
import org.elena.finalproject.pages.DashboardPage;
import org.elena.finalproject.pages.LoginPage;
import org.elena.finalproject.webDriver.Browser;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class AdminMenuListTest {

    private final LoginPage loginPage = new LoginPage();
    private final DashboardPage dashboardPage = new DashboardPage();

    private Logger logger = Logger.getLogger(this.getClass());

    @BeforeClass
    public void setUp() {
        try {
            loginPage.openLoginPage();
            Assert.assertTrue(loginPage.isPageOpened(), "'Login' page is not opened");
            loginPage.logIn(UserEnum.ADMINISTRATOR.getUsername(), System.getenv("admin_test"));
            Browser.makeScreenshot();
            Assert.assertTrue(dashboardPage.isPageOpened(), "'Dashboard' page is not opened");
        } catch (AssertionError | NullPointerException e) {
            logger.error(e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        dashboardPage.getHeader().logOut();
        Browser.close();
    }

    @AfterMethod
    public void afterMethod(Method method) {
        System.out.println("Test " + method.getName() + " is finished");
    }

    @DataProvider(name = "paramsAdminMenuList")
    public static Object[][] paramsAdminMenuList() {
        return new Object[][]{
                {LeftMenuEnum.DASHBOARD.getId(), "Dashboard"},
                {LeftMenuEnum.POSTS.getId(), "Posts"},
                {LeftMenuEnum.MEDIA.getId(), "Media"},
                {LeftMenuEnum.PAGES.getId(), "Pages"},
                {LeftMenuEnum.COMMENTS.getId(), "Comments"},
                {LeftMenuEnum.APPEARANCE.getId(), "Appearance"},
                {LeftMenuEnum.PLUGINS.getId(), "Plugins"},
                {LeftMenuEnum.USERS.getId(), "Users"},
                {LeftMenuEnum.TOOLS.getId(), "Tools"},
                {LeftMenuEnum.SETTINGS.getId(), "Settings"},
                {LeftMenuEnum.PERFORMANCE.getId(), "Performance"},
                {LeftMenuEnum.SMUSH.getId(), "Smush"}
        };
    }

    @Epic(value = "Menu")
    @Test(dataProvider = "paramsAdminMenuList")
    @Description(value = "Test validates the list of left menu items for Administrator user")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testAdminMenuList(String id, String expectedMenuItem) {
        try {
            Assert.assertTrue(dashboardPage.getLeftMenu().isMenuItemPresent(id), expectedMenuItem + " is not present");
        } catch (AssertionError | NullPointerException e) {
            logger.error(e.getMessage());
        }
    }
}
