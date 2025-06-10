package org.elena.finalproject.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.log4j.Logger;
import org.elena.finalproject.credentials.UserEnum;
import org.elena.finalproject.models.Tag;
import org.elena.finalproject.pages.DashboardPage;
import org.elena.finalproject.pages.LoginPage;
import org.elena.finalproject.pages.posts.TagsPage;
import org.elena.finalproject.utils.DataGenerator;
import org.elena.finalproject.webDriver.Browser;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class TagTest {

    private static final Tag TAG = Tag.builder()
            .name(DataGenerator.getRandomTag())
            .slug(DataGenerator.getRandomString(5))
            .description(DataGenerator.getRandomString(20)).build();

    private final LoginPage loginPage = new LoginPage();
    private final DashboardPage dashboardPage = new DashboardPage();
    private final TagsPage tagsPage = new TagsPage();

    private Logger logger = Logger.getLogger(this.getClass());

    @BeforeClass
    public void setUp() {
        try {
            loginPage.openLoginPage();
            Assert.assertTrue(loginPage.isPageOpened(), "'Login' page is not opened");
            loginPage.logIn(UserEnum.ADMINISTRATOR.getUsername(), UserEnum.ADMINISTRATOR.getPassword());
            Assert.assertTrue(dashboardPage.isPageOpened(), "'Dashboard' page is not opened");
            tagsPage.openTagsPage();
            Assert.assertTrue(tagsPage.isPageOpened(), "'Tags' page is not opened");
        } catch (AssertionError | NullPointerException e) {
            logger.error(e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        tagsPage.getHeader().logOut();
        Browser.close();
    }

    @AfterMethod
    public void afterMethod(Method method) {
        Browser.makeScreenshot();
        System.out.println("Test " + method.getName() + " is finished");
    }

    @Epic(value = "Tag")
    @Test
    @Description(value = "Test validates whether tag can be created")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testTagCanBeAdded() {
        try {
            tagsPage.addNewTag(TAG);
            Assert.assertTrue(tagsPage.isTagAdded(), "Tag is not added");
            tagsPage.searchTag(TAG.getName());
        } catch (AssertionError | NullPointerException e) {
            logger.error(e.getMessage());
        }
    }

    @Epic(value = "Tag")
    @Test(dependsOnMethods = {"testTagCanBeAdded"})
    @Description(value = "Test validates whether tag can be deleted")
    @Severity(value = SeverityLevel.MINOR)
    public void testTagCanBeDeleted() {
        try {
            tagsPage.deleteTag(TAG);
            Assert.assertTrue(tagsPage.isTagDeleted(TAG), "Tag is not deleted");
        } catch (AssertionError | NullPointerException e) {
            logger.error(e.getMessage());
        }
    }
}
