package org.elena.finalproject.tests;

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

    private static final String TAG_NAME = DataGenerator.getRandomTag();
    private static final String TAG_SLUG = TAG_NAME.toLowerCase();
    private static final String TAG_DESCRIPTION = DataGenerator.getRandomString(20);
    private static final Tag TAG = Tag.builder().name(TAG_NAME).slug(TAG_SLUG).description(TAG_DESCRIPTION).build();

    private final LoginPage loginPage = new LoginPage();
    private final DashboardPage dashboardPage = new DashboardPage();
    private final TagsPage tagsPage = new TagsPage();

    @BeforeClass
    public void setUp() {
        loginPage.openLoginPage();
        Assert.assertTrue(loginPage.isPageOpened(), "'Login' page is not opened");
        loginPage.logIn(UserEnum.ADMINISTRATOR.getUsername(), UserEnum.ADMINISTRATOR.getPassword());
        Assert.assertTrue(dashboardPage.isPageOpened(), "'Dashboard' page is not opened");
        tagsPage.openTagsPage();
        Assert.assertTrue(tagsPage.isPageOpened(), "'Tags' page is not opened");
    }

    @AfterClass
    public void tearDown() {
        tagsPage.getHeader().logOut();
        Browser.close();
    }

    @AfterMethod
    public void afterMethod(Method method) {
        System.out.println("Test " + method.getName() + " is finished");
    }

    @Test
    public void testTagCanBeAdded() {
        tagsPage.addNewTag(TAG);
        Assert.assertTrue(tagsPage.isTagAdded(), "Tag is not added");
        tagsPage.searchTag(TAG_NAME);
        Browser.makeScreenshot();
    }

    @Test(dependsOnMethods = {"testTagCanBeAdded"})
    public void testTagCanBeDeleted() {
        tagsPage.deleteTag(TAG);
        Assert.assertTrue(tagsPage.isTagDeleted(TAG), "Tag is not deleted");
        Browser.makeScreenshot();
    }
}
