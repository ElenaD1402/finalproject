package org.elena.finalproject.tests;

import org.elena.finalproject.credentials.UserEnum;
import org.elena.finalproject.elements.LeftMenuEnum;
import org.elena.finalproject.models.Category;
import org.elena.finalproject.models.Post;
import org.elena.finalproject.pages.*;
import org.elena.finalproject.pages.media.AddNewMediaFilePage;
import org.elena.finalproject.pages.media.MediaPage;
import org.elena.finalproject.pages.posts.AddNewPostPage;
import org.elena.finalproject.pages.posts.CategoriesPage;
import org.elena.finalproject.pages.posts.PostsPage;
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
    public void testMedia() {
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
        addNewMediaFilePage.getLeftMenu().getMediaMenu().goToMediaPage();
        MediaPage mediaPage = new MediaPage();
        Assert.assertTrue(mediaPage.isPageOpened(), "Media page is not opened");
        mediaPage.chooseTableView();
        Assert.assertTrue(mediaPage.isFileUploaded());
        mediaPage.deleteMediaFile();
        DeletionPage deletionPage = new DeletionPage();
        Assert.assertTrue(deletionPage.isPageOpened(), "File is not deleted");
        deletionPage.getHeader().goToBasePage();
        HomePage homePage = new HomePage();
        homePage.getLeftMenu().clickItem(LeftMenuEnum.MEDIA);
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
    public void testCategory() {
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
        categoriesPage.deleteCategory(category);
        Assert.assertTrue(categoriesPage.isCategoryDeleted(category), "Category is not deleted");
    }

    @Test
    public void testDraftPost() {
        Browser.getWebDriver().get(Configuration.getBaseUrl());
        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");
        loginPage.logIn(UserEnum.ADMINISTRATOR);
        DashboardPage dashboardPage = new DashboardPage();
        Assert.assertTrue(dashboardPage.isPageOpened(), "Dashboard page is not opened");
        dashboardPage.getLeftMenu().hoverOverItem(LeftMenuEnum.POSTS);
        dashboardPage.getLeftMenu().getPostsMenu().addNewPost();
        AddNewPostPage addNewPostPage = new AddNewPostPage();
        Assert.assertTrue(addNewPostPage.isPageOpened(), "Add new post page is not opened");
        String postTitle = DataGenerator.getRandomString(15);
        String postBlock = DataGenerator.getRandomString(50);
        Post post = Post.builder().title(postTitle).block(postBlock).build();
        addNewPostPage.addNewPostDraft(post);
        addNewPostPage.goToPostsPage();
        PostsPage postsPage = new PostsPage();
        Assert.assertTrue(postsPage.isDraftPostAdded(post), "Draft post is not added");
        postsPage.deletePost(post);
        DeletionPage deletionPage = new DeletionPage();
        Assert.assertTrue(deletionPage.isPageOpened(), "Post is not deleted");
        deletionPage.getHeader().goToBasePage();
        HomePage homePage = new HomePage();
        homePage.getLeftMenu().clickItem(LeftMenuEnum.POSTS);
        Assert.assertTrue(postsPage.isPostDeleted(post), "Post is not deleted");
    }

    @Test
    public void testPost() {
        Browser.getWebDriver().get(Configuration.getBaseUrl());
        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");
        loginPage.logIn(UserEnum.ADMINISTRATOR);
        DashboardPage dashboardPage = new DashboardPage();
        Assert.assertTrue(dashboardPage.isPageOpened(), "Dashboard page is not opened");
        dashboardPage.getLeftMenu().hoverOverItem(LeftMenuEnum.POSTS);
        dashboardPage.getLeftMenu().getPostsMenu().addNewPost();
        AddNewPostPage addNewPostPage = new AddNewPostPage();
        Assert.assertTrue(addNewPostPage.isPageOpened(), "Add new post page is not opened");
        String postTitle = DataGenerator.getRandomString(15);
        String postBlock = DataGenerator.getRandomString(50);
        Post post = Post.builder().title(postTitle).block(postBlock).build();
        addNewPostPage.addNewPost(post);
        addNewPostPage.goToPostsPage();
        PostsPage postsPage = new PostsPage();
        Assert.assertTrue(postsPage.isPostAdded(post), "Post is not added");
        postsPage.deletePost(post);
        DeletionPage deletionPage = new DeletionPage();
        Assert.assertTrue(deletionPage.isPageOpened(), "Post is not deleted");
        deletionPage.getHeader().goToBasePage();
        HomePage homePage = new HomePage();
        homePage.getLeftMenu().clickItem(LeftMenuEnum.POSTS);
        Assert.assertTrue(postsPage.isPostDeleted(post), "Post is not deleted");
    }

    @Test
    public void testPostWithCategory() {
        Browser.getWebDriver().get(Configuration.getBaseUrl());
        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");
        loginPage.logIn(UserEnum.ADMINISTRATOR);
        DashboardPage dashboardPage = new DashboardPage();
        Assert.assertTrue(dashboardPage.isPageOpened(), "Dashboard page is not opened");
        dashboardPage.getLeftMenu().hoverOverItem(LeftMenuEnum.POSTS);
        dashboardPage.getLeftMenu().getPostsMenu().addNewPost();
        AddNewPostPage addNewPostPage = new AddNewPostPage();
        Assert.assertTrue(addNewPostPage.isPageOpened(), "Add new post page is not opened");
        String postTitle = DataGenerator.getRandomString(15);
        String postBlock = DataGenerator.getRandomString(50);
        String categoryName = DataGenerator.getRandomCategory();
        Post post = Post.builder().title(postTitle).block(postBlock).build();
        Category category = Category.builder().name(categoryName).build();
        addNewPostPage.addNewPostWithCategory(post, category);
    }

    @Test
    public void testPostWithTag() {
        Browser.getWebDriver().get(Configuration.getBaseUrl());
        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");
        loginPage.logIn(UserEnum.ADMINISTRATOR);
        DashboardPage dashboardPage = new DashboardPage();
        Assert.assertTrue(dashboardPage.isPageOpened(), "Dashboard page is not opened");
        dashboardPage.getLeftMenu().hoverOverItem(LeftMenuEnum.POSTS);
        dashboardPage.getLeftMenu().getPostsMenu().addNewPost();
        AddNewPostPage addNewPostPage = new AddNewPostPage();
        Assert.assertTrue(addNewPostPage.isPageOpened(), "Add new post page is not opened");
        String postTitle = DataGenerator.getRandomString(15);
        String postBlock = DataGenerator.getRandomString(50);
        String tagName = DataGenerator.getRandomTag();
        Post post = Post.builder().title(postTitle).block(postBlock).build();
        Tag tag = Tag.builder().name(tagName).build();
        addNewPostPage.addNewPostWithTag(post, tag);
    }

    @Test
    public void testPostWithCategoryAndTag() {
        Browser.getWebDriver().get(Configuration.getBaseUrl());
        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");
        loginPage.logIn(UserEnum.ADMINISTRATOR);
        DashboardPage dashboardPage = new DashboardPage();
        Assert.assertTrue(dashboardPage.isPageOpened(), "Dashboard page is not opened");
        dashboardPage.getLeftMenu().hoverOverItem(LeftMenuEnum.POSTS);
        dashboardPage.getLeftMenu().getPostsMenu().addNewPost();
        AddNewPostPage addNewPostPage = new AddNewPostPage();
        Assert.assertTrue(addNewPostPage.isPageOpened(), "Add new post page is not opened");
        String postTitle = DataGenerator.getRandomString(15);
        String postBlock = DataGenerator.getRandomString(50);
        String categoryName = DataGenerator.getRandomCategory();
        String tagName = DataGenerator.getRandomTag();
        Post post = Post.builder().title(postTitle).block(postBlock).build();
        Category category = Category.builder().name(categoryName).build();
        Tag tag = Tag.builder().name(tagName).build();
        addNewPostPage.addNewPostWithCategoryAndTag(post, category, tag);
    }

    @Test
    public void testPage() {






    }

    @AfterMethod
    public void tearDown(Method method) {
        System.out.println("Test " + method.getName() + " is finished");
        Browser.close();
    }
}
