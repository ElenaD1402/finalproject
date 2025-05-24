package org.elena.finalproject.tests;

import org.elena.finalproject.credentials.UserEnum;
import org.elena.finalproject.elements.LeftMenuEnum;
import org.elena.finalproject.models.Category;
import org.elena.finalproject.pages.BasePage;
import org.elena.finalproject.pages.DashboardPage;
import org.elena.finalproject.pages.DeletionPage;
import org.elena.finalproject.pages.LoginPage;
import org.elena.finalproject.pages.media.AddNewMediaFilePage;
import org.elena.finalproject.pages.media.MediaPage;
import org.elena.finalproject.pages.posts.CategoriesPage;
import org.elena.finalproject.pages.posts.TagsPage;
import org.elena.finalproject.models.Tag;
import org.elena.finalproject.utils.DataGenerator;
import org.elena.finalproject.webDriver.Browser;
import org.elena.finalproject.webDriver.Configuration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class LoginTest {

    @Test
    public void testMedia() throws InterruptedException {
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
        Assert.assertTrue(deletionPage.isPageOpened(), "File is not deleted");
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
        Assert.assertTrue(mediaPage.isFileDeleted(), "File is not deleted");
    }

    @Test
    public void testTag() {
        Browser.getWebDriver().get(Configuration.getBaseUrl());
        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");
        loginPage.logIn(UserEnum.ADMINISTRATOR);
        DashboardPage dashboardPage = new DashboardPage();
        Assert.assertTrue(dashboardPage.isPageOpened(), "Dashboard page is not opened");
        dashboardPage.getLeftMenu().hoverOverItem(LeftMenuEnum.POSTS);
        dashboardPage.getLeftMenu().getPostsMenu().goToTagsPage();
        TagsPage tagsPage = new TagsPage();
        Assert.assertTrue(tagsPage.isPageOpened(), "Tags page is not opened");
        String tagName = DataGenerator.getRandomTag();
        String tagSlug = tagName.toLowerCase();
        String tagDescription = DataGenerator.getRandomString(10);
        Tag tag = Tag.builder().name(tagName).slug(tagSlug).description(tagDescription).build();
        tagsPage.addNewTag(tag);
        Assert.assertTrue(tagsPage.isTagAdded(), "Tag is not added");
        tagsPage.deleteTag(tag);
        Assert.assertTrue(tagsPage.isTagDeleted(tag), "Tag is not deleted");
    }

    @Test
    public void testCategory() throws InterruptedException {
        Browser.getWebDriver().get(Configuration.getBaseUrl());
        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");
        loginPage.logIn(UserEnum.ADMINISTRATOR);
        DashboardPage dashboardPage = new DashboardPage();
        Assert.assertTrue(dashboardPage.isPageOpened(), "Dashboard page is not opened");
        dashboardPage.getLeftMenu().hoverOverItem(LeftMenuEnum.POSTS);
        dashboardPage.getLeftMenu().getPostsMenu().goToCategoriesPage();
        CategoriesPage categoriesPage = new CategoriesPage();
        Assert.assertTrue(categoriesPage.isPageOpened(), "Categories page is not opened");
        String categoryName = DataGenerator.getRandomCategory();
        String categorySlug = categoryName.toLowerCase();
        String categoryDescription = DataGenerator.getRandomString(10);
        Category category = Category.builder().name(categoryName).slug(categorySlug).description(categoryDescription).build();
        categoriesPage.addNewCategoryWithParent(category);
        Assert.assertTrue(categoriesPage.isCategoryAdded(), "Category is not added");
        Thread.sleep(5000);
        categoriesPage.deleteCategory(category);
        Assert.assertTrue(categoriesPage.isCategoryDeleted(category), "Category is not deleted");
    }

    @AfterMethod
    public void tearDown(Method method) {
        System.out.println("Test " + method.getName() + " is finished");
        Browser.close();
    }
}
