package org.elena.finalproject.tests;

import org.elena.finalproject.credentials.UserEnum;
import org.elena.finalproject.elements.LeftMenuEnum;
import org.elena.finalproject.pages.BasePage;
import org.elena.finalproject.pages.DashboardPage;
import org.elena.finalproject.pages.DeletionPage;
import org.elena.finalproject.pages.LoginPage;
import org.elena.finalproject.pages.media.AddNewMediaFilePage;
import org.elena.finalproject.pages.media.MediaPage;
import org.elena.finalproject.webDriver.Browser;
import org.elena.finalproject.webDriver.Configuration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class LoginTest {

    @Test
    public void testLogIn() throws InterruptedException {
        Browser.getWebDriver().get(Configuration.getBaseUrl());
        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");
        loginPage.logIn(UserEnum.ADMINISTRATOR);
        DashboardPage dashboardPage = new DashboardPage();
        Assert.assertTrue(dashboardPage.isPageOpened(), "Dashboard page is not opened");
        dashboardPage.getLeftMenu().hoverOverItem(LeftMenuEnum.MEDIA);
        dashboardPage.getLeftMenu().getMediaMenu().addNewMediaFile();
        AddNewMediaFilePage addNewMediaFilePage = new AddNewMediaFilePage();
        addNewMediaFilePage.uploadMediaFile();
        Assert.assertTrue(addNewMediaFilePage.isFileUploaded());
        Thread.sleep(5000);
        addNewMediaFilePage.getLeftMenu().getMediaMenu().goToMediaPage();
        Thread.sleep(5000);
        MediaPage mediaPage = new MediaPage();
        Assert.assertTrue(mediaPage.isPageOpened(), "Media page is not opened");
        mediaPage.chooseTableView();
        Assert.assertTrue(mediaPage.isFileUploaded());
        mediaPage.deleteMediaFile();
        Thread.sleep(5000);
        DeletionPage deletionPage = new DeletionPage();
        Assert.assertTrue(deletionPage.isPageOpened(), "File wasn't deleted");
        Thread.sleep(5000);
        deletionPage.getHeader().goToBasePage();
        Thread.sleep(5000);
        BasePage basePage = new BasePage() {
            @Override
            public boolean isPageOpened() {
                return true;
            }
        };
        basePage.getLeftMenu().clickItem(LeftMenuEnum.MEDIA);
        Assert.assertTrue(mediaPage.isFileDeleted(), "File isn't deleted");

    }

    @AfterMethod
    public void tearDown(Method method) {
        System.out.println("Test " + method.getName() + " is finished");
        Browser.close();
    }
}
