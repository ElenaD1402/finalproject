package org.elena.finalproject.tests;

import io.qameta.allure.*;
import org.apache.log4j.Logger;
import org.elena.finalproject.users.UserEnum;
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

    private static final Post POST_DRAFT = Post.builder()
            .title(DataGenerator.getRandomString(10))
            .block(DataGenerator.getRandomString(50)).build();
    private static final Post POST = Post.builder()
            .title(DataGenerator.getRandomString(10))
            .block(DataGenerator.getRandomString(50)).build();
    private static final Category CATEGORY_1 = Category.builder()
            .name(DataGenerator.getRandomCategory()).build();
    private static final Post POST_WITH_CATEGORY = Post.builder()
            .title(DataGenerator.getRandomString(10))
            .block(DataGenerator.getRandomString(50)).build();
    private static final Tag TAG_1 = Tag.builder()
            .name(DataGenerator.getRandomTag()).build();
    private static final Post POST_WITH_TAG = Post.builder()
            .title(DataGenerator.getRandomString(10))
            .block(DataGenerator.getRandomString(50)).build();
    private static final Category CATEGORY_2 = Category.builder()
            .name(DataGenerator.getRandomCategory()).build();
    private static final Tag TAG_2 = Tag.builder()
            .name(DataGenerator.getRandomTag()).build();
    private static final Post POST_WITH_CATEGORY_AND_TAG = Post.builder()
            .title(DataGenerator.getRandomString(10))
            .block(DataGenerator.getRandomString(50)).build();

    private final LoginPage loginPage = new LoginPage();
    private final DashboardPage dashboardPage = new DashboardPage();
    private final AddNewPostPage addNewPostPage = new AddNewPostPage();
    private final PostsPage postsPage = new PostsPage();
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
        postsPage.getHeader().logOut();
        Browser.close();
    }

    @AfterMethod
    public void afterMethod(Method method) {
        Browser.makeScreenshot();
        System.out.println("Test " + method.getName() + " is finished");
    }

    @Epic(value = "Post")
    @Test
    @Description(value = "Test validates whether draft post can be created")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testDraftPostCanBeCreated() {
        try {
            addNewPostPage.openAddNewPostPage();
            Assert.assertTrue(addNewPostPage.isPageOpened(), "'Add New Post' page is not opened");
            addNewPostPage.addNewPostDraft(POST_DRAFT);
            addNewPostPage.goToPostsPage();
            Assert.assertTrue(postsPage.isPageOpened(), "'Posts' page is not opened");
            Assert.assertTrue(postsPage.isDraftPostAdded(POST_DRAFT), "'Draft' post is not added");
        } catch (AssertionError | NullPointerException e) {
            logger.error(e.getMessage());
        }
    }

    @Epic(value = "Post")
    @Test
    @Description(value = "Test validates whether post can be published")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testPostCanBeCreated() {
        try {
            addNewPostPage.openAddNewPostPage();
            Assert.assertTrue(addNewPostPage.isPageOpened(), "'Add New Post' page is not opened");
            addNewPostPage.addNewPost(POST);
            addNewPostPage.goToPostsPage();
            Assert.assertTrue(postsPage.isPageOpened(), "'Posts' page is not opened");
            Assert.assertTrue(postsPage.isPostAdded(POST), "Post is not added");
        } catch (AssertionError | NullPointerException e) {
            logger.error(e.getMessage());
        }
    }

    @Epic(value = "Post")
    @Test
    @Description(value = "Test validates whether post with category can be published")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testPostWithCategoryCanBeCreated() {
        try {
            addNewPostPage.openAddNewPostPage();
            Assert.assertTrue(addNewPostPage.isPageOpened(), "'Add New Post' page is not opened");
            addNewPostPage.addNewPostWithCategory(POST_WITH_CATEGORY, CATEGORY_1);
            addNewPostPage.goToPostsPage();
            Assert.assertTrue(postsPage.isPageOpened(), "'Posts' page is not opened");
            Assert.assertTrue(postsPage.isPostWithCategoryAdded(POST_WITH_CATEGORY, CATEGORY_1),
                    "Post with category is not added");
        } catch (AssertionError | NullPointerException e) {
            logger.error(e.getMessage());
        }
    }

    @Epic(value = "Post")
    @Test
    @Flaky
    @Description(value = "Test validates whether post with tag can be published")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testPostWithTagCanBeCreated() {
        try {
            addNewPostPage.openAddNewPostPage();
            Assert.assertTrue(addNewPostPage.isPageOpened(), "'Add New Post' page is not opened");
            addNewPostPage.addNewPostWithTag(POST_WITH_TAG, TAG_1);
            addNewPostPage.goToPostsPage();
            Assert.assertTrue(postsPage.isPageOpened(), "'Posts' page is not opened");
            Assert.assertTrue(postsPage.isPostWithTagAdded(POST_WITH_TAG, TAG_1), "Post with tag is not added");
        } catch (AssertionError | NullPointerException e) {
            logger.error(e.getMessage());
        }
    }

    @Epic(value = "Post")
    @Test
    @Flaky
    @Description(value = "Test validates whether post with category and tag can be published")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testPostWithCategoryAndTagCanBeCreated() {
        try {
            addNewPostPage.openAddNewPostPage();
            Assert.assertTrue(addNewPostPage.isPageOpened(), "'Add New Post' page is not opened");
            addNewPostPage.addNewPostWithCategoryAndTag(POST_WITH_CATEGORY_AND_TAG, CATEGORY_2, TAG_2);
            addNewPostPage.goToPostsPage();
            Assert.assertTrue(postsPage.isPageOpened(), "'Posts' page is not opened");
            Assert.assertTrue(postsPage.isPostWithCategoryAndTagAdded(POST_WITH_CATEGORY_AND_TAG, CATEGORY_2, TAG_2),
                    "Post with category and tag is not added");
        } catch (AssertionError | NullPointerException e) {
            logger.error(e.getMessage());
        }
    }

    @DataProvider(name = "paramsPostsToBeDeleted")
    public static Object[][] paramsPostsToBeDeleted() {
        return new Object[][]{
                {POST_DRAFT, POST_DRAFT.getTitle()},
                {POST, POST.getTitle()},
                {POST_WITH_CATEGORY, POST_WITH_CATEGORY.getTitle()},
                {POST_WITH_TAG, POST_WITH_TAG.getTitle()},
                {POST_WITH_CATEGORY_AND_TAG, POST_WITH_CATEGORY_AND_TAG.getTitle()}
        };
    }

    @Epic(value = "Post")
    @Test(dependsOnMethods = {"testDraftPostCanBeCreated", "testPostCanBeCreated", "testPostWithCategoryCanBeCreated",
            "testPostWithTagCanBeCreated", "testPostWithCategoryAndTagCanBeCreated"}, dataProvider = "paramsPostsToBeDeleted")
    @Description(value = "Test validates whether post can be deleted")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testPostCanBeDeleted(Post post, String title) {
        try {
            postsPage.openPostsPage();
            Assert.assertTrue(postsPage.isPageOpened(), "'Posts' page is not opened");
            postsPage.deletePost(post);
            Assert.assertTrue(deletionPage.isPageOpened(), "Post '" + title + "' is not deleted");
            postsPage.openPostsPage();
            Assert.assertTrue(postsPage.isPageOpened(), "'Posts' page is not opened");
            Assert.assertTrue(postsPage.isPostDeleted(post), "Post '" + title + "' is not deleted");
        } catch (AssertionError | NullPointerException e) {
            logger.error(e.getMessage());
        }
    }
}
