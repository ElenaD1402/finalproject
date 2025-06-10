package org.elena.finalproject.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.log4j.Logger;
import org.elena.finalproject.credentials.UserEnum;
import org.elena.finalproject.pages.DashboardPage;
import org.elena.finalproject.pages.DeletionPage;
import org.elena.finalproject.pages.LoginPage;
import org.elena.finalproject.pages.media.AddNewMediaFilePage;
import org.elena.finalproject.pages.media.MediaPage;
import org.elena.finalproject.webDriver.Browser;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class MediaTest {

    private final LoginPage loginPage = new LoginPage();
    private final DashboardPage dashboardPage = new DashboardPage();
    private final AddNewMediaFilePage addNewMediaFilePage = new AddNewMediaFilePage();
    private final MediaPage mediaPage = new MediaPage();
    private final DeletionPage deletionPage = new DeletionPage();

    private Logger logger = Logger.getLogger(this.getClass());

    @BeforeClass
    public void setUp() {
        try {
            loginPage.openLoginPage();
            Assert.assertTrue(loginPage.isPageOpened(), "'Login' page is not opened");
            loginPage.logIn(UserEnum.ADMINISTRATOR.getUsername(), UserEnum.ADMINISTRATOR.getPassword());
            Assert.assertTrue(dashboardPage.isPageOpened(), "'Dashboard' page is not opened");
            addNewMediaFilePage.openAddNewMediaFilePage();
            Assert.assertTrue(addNewMediaFilePage.isPageOpened(), "'Add New Media File' page is not opened");
            addNewMediaFilePage.uploadMediaFile();
            Browser.makeScreenshot();
        } catch (AssertionError | NullPointerException e) {
            logger.error(e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        mediaPage.getHeader().logOut();
        Browser.close();
    }

    @AfterMethod
    public void afterMethod(Method method) {
        System.out.println("Test " + method.getName() + " is finished");
    }

    @Epic(value = "Media")
    @Test
    @Description(value = "Test validates whether media file can be uploaded")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testMediaFileCanBeUploaded() {
        try {
            Assert.assertTrue(addNewMediaFilePage.isFileUploaded(), "Media file is not uploaded");
            mediaPage.openMediaPage();
            mediaPage.chooseTableView();
            Browser.makeScreenshot();
            Assert.assertTrue(mediaPage.isPageOpened(), "'Media Library' page is not opened");
            Assert.assertTrue(mediaPage.isFileUploaded(), "Media file is not uploaded");
        } catch (AssertionError | NullPointerException e) {
            logger.error(e.getMessage());
        }
    }

    @Epic(value = "Media")
    @Test(dependsOnMethods = {"testMediaFileCanBeUploaded"})
    @Description(value = "Test validates whether media file can be deleted")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testMediaFileCanBeDeleted() {
        try {
            mediaPage.deleteMediaFile();
            Assert.assertTrue(deletionPage.isPageOpened(), "Media file is not deleted");
            mediaPage.openMediaPage();
            Browser.makeScreenshot();
            Assert.assertTrue(mediaPage.isPageOpened(), "'Media Library' page is not opened");
            Assert.assertTrue(mediaPage.isFileDeleted(), "Media file is not deleted");
        } catch (AssertionError | NullPointerException e) {
            logger.error(e.getMessage());
        }
    }
}
