package org.elena.finalproject.tests;

import io.qameta.allure.Flaky;
import org.elena.finalproject.models.Category;
import org.elena.finalproject.models.Post;
import org.elena.finalproject.models.Tag;
import org.elena.finalproject.pages.DashboardPage;
import org.elena.finalproject.pages.DeletionPage;
import org.elena.finalproject.pages.LoginPage;
import org.elena.finalproject.pages.posts.AddNewPostPage;
import org.elena.finalproject.pages.posts.PostsPage;
import org.elena.finalproject.utils.DataGenerator;
import org.elena.finalproject.webDriver.Browser;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class PostTest {

    private static final String POST_DRAFT_TITLE = DataGenerator.getRandomString(10);
    private static final String POST_DRAFT_BLOCK = DataGenerator.getRandomString(50);
    private static final Post POST_DRAFT = Post.builder().title(POST_DRAFT_TITLE).block(POST_DRAFT_BLOCK).build();
    private static final String POST_TITLE = DataGenerator.getRandomString(10);
    private static final String POST_BLOCK = DataGenerator.getRandomString(50);
    private static final Post POST = Post.builder().title(POST_TITLE).block(POST_BLOCK).build();
    private static final String CATEGORY_NAME_1 = DataGenerator.getRandomCategory();
    private static final Category CATEGORY_1 = Category.builder().name(CATEGORY_NAME_1).build();
    private static final String POST_WITH_CATEGORY_TITLE = DataGenerator.getRandomString(10);
    private static final String POST_WITH_CATEGORY_BLOCK = DataGenerator.getRandomString(50);
    private static final Post POST_WITH_CATEGORY = Post.builder().title(POST_WITH_CATEGORY_TITLE).block(POST_WITH_CATEGORY_BLOCK).build();
    private static final String TAG_NAME_1 = DataGenerator.getRandomTag();
    private static final Tag TAG_1 = Tag.builder().name(TAG_NAME_1).build();
    private static final String POST_WITH_TAG_TITLE = DataGenerator.getRandomString(10);
    private static final String POST_WITH_TAG_BLOCK = DataGenerator.getRandomString(50);
    private static final Post POST_WITH_TAG = Post.builder().title(POST_WITH_TAG_TITLE).block(POST_WITH_TAG_BLOCK).build();
    private static final String CATEGORY_NAME_2 = DataGenerator.getRandomCategory();
    private static final Category CATEGORY_2 = Category.builder().name(CATEGORY_NAME_2).build();
    private static final String TAG_NAME_2 = DataGenerator.getRandomTag();
    private static final Tag TAG_2 = Tag.builder().name(TAG_NAME_2).build();
    private static final String POST_WITH_CATEGORY_AND_TAG_TITLE = DataGenerator.getRandomString(10);
    private static final String POST_WITH_CATEGORY_AND_TAG_BLOCK = DataGenerator.getRandomString(50);
    private static final Post POST_WITH_CATEGORY_AND_TAG = Post.builder().title(POST_WITH_CATEGORY_AND_TAG_TITLE)
            .block(POST_WITH_CATEGORY_AND_TAG_BLOCK).build();

    private final LoginPage loginPage = new LoginPage();
    private final DashboardPage dashboardPage = new DashboardPage();
    private final AddNewPostPage addNewPostPage = new AddNewPostPage();
    private final PostsPage postsPage = new PostsPage();
    private final DeletionPage deletionPage = new DeletionPage();

    @BeforeClass
    public void setUp() {
        loginPage.openLoginPage();
        Assert.assertTrue(dashboardPage.isPageOpened(), "'Dashboard' page is not opened");
    }

    @AfterClass
    public void tearDown() {
        postsPage.getHeader().logOut();
        Browser.close();
    }

    @AfterMethod
    public void afterMethod(Method method) {
        System.out.println("Test " + method.getName() + " is finished");
    }

    @Test(priority = 1)
    public void testDraftPostCanBeCreated() {
        addNewPostPage.openAddNewPostPage();
        Assert.assertTrue(addNewPostPage.isPageOpened(), "'Add New Post' page is not opened");
        addNewPostPage.addNewPostDraft(POST_DRAFT);
        addNewPostPage.goToPostsPage();
        Assert.assertTrue(postsPage.isPageOpened(), "'Posts' page is not opened");
        Assert.assertTrue(postsPage.isDraftPostAdded(POST_DRAFT), "'Draft' post is not added");
        Browser.makeScreenshot();
    }

    @Test(priority = 1)
    public void testPostCanBeCreated() {
        addNewPostPage.openAddNewPostPage();
        Assert.assertTrue(addNewPostPage.isPageOpened(), "'Add New Post' page is not opened");
        addNewPostPage.addNewPost(POST);
        addNewPostPage.goToPostsPage();
        Assert.assertTrue(postsPage.isPageOpened(), "'Posts' page is not opened");
        Assert.assertTrue(postsPage.isPostAdded(POST), "Post is not added");
        Browser.makeScreenshot();
    }

    @Test(priority = 1)
    public void testPostWithCategoryCanBeCreated() {
        addNewPostPage.openAddNewPostPage();
        Assert.assertTrue(addNewPostPage.isPageOpened(), "'Add New Post' page is not opened");
        addNewPostPage.addNewPostWithCategory(POST_WITH_CATEGORY, CATEGORY_1);
        addNewPostPage.goToPostsPage();
        Assert.assertTrue(postsPage.isPageOpened(), "'Posts' page is not opened");
        Assert.assertTrue(postsPage.isPostWithCategoryAdded(POST_WITH_CATEGORY, CATEGORY_1),
                "Post with category is not added");
        Browser.makeScreenshot();
    }

    @Test(priority = 1)
    @Flaky
    public void testPostWithTagCanBeCreated() {
        addNewPostPage.openAddNewPostPage();
        Assert.assertTrue(addNewPostPage.isPageOpened(), "'Add New Post' page is not opened");
        addNewPostPage.addNewPostWithTag(POST_WITH_TAG, TAG_1);
        addNewPostPage.goToPostsPage();
        Assert.assertTrue(postsPage.isPageOpened(), "'Posts' page is not opened");
        Assert.assertTrue(postsPage.isPostWithTagAdded(POST_WITH_TAG, TAG_1), "Post with tag is not added");
        Browser.makeScreenshot();
    }

    @Test(priority = 1)
    public void testPostWithCategoryAndTagCanBeCreated() {
        addNewPostPage.openAddNewPostPage();
        Assert.assertTrue(addNewPostPage.isPageOpened(), "'Add New Post' page is not opened");
        addNewPostPage.addNewPostWithCategoryAndTag(POST_WITH_CATEGORY_AND_TAG, CATEGORY_2, TAG_2);
        addNewPostPage.goToPostsPage();
        Assert.assertTrue(postsPage.isPageOpened(), "'Posts' page is not opened");
        Assert.assertTrue(postsPage.isPostWithCategoryAndTagAdded(POST_WITH_CATEGORY_AND_TAG, CATEGORY_2, TAG_2),
                "Post with category and tag is not added");
        Browser.makeScreenshot();

    }

    @DataProvider(name = "paramsPostsToBeDeleted")
    public static Object[][] paramsPostsToBeDeleted() {
        return new Object[][]{
                {POST_DRAFT, POST_DRAFT_TITLE},
                {POST, POST_TITLE},
                {POST_WITH_CATEGORY, POST_WITH_CATEGORY_TITLE},
                {POST_WITH_TAG, POST_WITH_TAG_TITLE},
                {POST_WITH_CATEGORY_AND_TAG, POST_WITH_CATEGORY_AND_TAG_TITLE}
        };
    }

    @Test(priority = 2, dataProvider = "paramsPostsToBeDeleted")
    public void testPostCanBeDeleted(Post post, String title) {
        postsPage.openPostsPage();
        Assert.assertTrue(postsPage.isPageOpened(), "'Posts' page is not opened");
        postsPage.deletePost(post);
        Assert.assertTrue(deletionPage.isPageOpened(), "Post '" + title + "' is not deleted");
        postsPage.openPostsPage();
        Assert.assertTrue(postsPage.isPageOpened(), "'Posts' page is not opened");
        Assert.assertTrue(postsPage.isPostDeleted(post), "Post '" + title + "' is not deleted");
        Browser.makeScreenshot();
    }
}
