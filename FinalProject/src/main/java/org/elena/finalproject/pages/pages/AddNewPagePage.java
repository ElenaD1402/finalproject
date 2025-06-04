package org.elena.finalproject.pages.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.elena.finalproject.models.Page;
import org.elena.finalproject.webDriver.Browser;
import org.elena.finalproject.webDriver.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class AddNewPagePage {

    private static final By PAGE_LOCATOR = By.xpath("//h1[contains(text(),'Choose a pattern')]");
    private static final By CLOSE_CHOOSE_PATTERN_LOCATOR = By.xpath("//button[@aria-label='Close']");
    private static final By ABOUT_PATTERN_LOCATOR = By.id("twentytwentyfour/page-about-business");
    private static final By IFRAME_LOCATOR = By.xpath("//iframe[@title='Editor canvas']");
    private static final By ADD_TITLE_LOCATOR = By.xpath("//h1[contains(@class,'wp-block-post-title')]");
    private static final By TEXT_BLOCK_LOCATOR = By.xpath("//div[contains(@class,'wp-block-post-content')]/p");
    private static final By SAVE_DRAFT_BUTTON_LOCATOR = By.xpath("//button[contains(text(),'Save draft')]");
    private static final By PUBLISH_BUTTON_LOCATOR = By.xpath("//button[contains(text(),'Publish')]");
    private static final By POST_PUBLISH_BUTTON_LOCATOR = By.xpath("//div[@class='editor-post-publish-panel__header-publish-button']");
    private static final By VIEW_PAGES_LOCATOR = By.xpath("//a[@href='edit.php?post_type=page'][@aria-label='View Pages']");

    private Logger logger = Logger.getLogger(this.getClass());

    @Step("User opens 'Add New Page' page")
    public void openAddNewPagePage() {
        Browser.getWebDriver().get(Configuration.getBaseUrl() + "/post-new.php?post_type=page");
        logger.info("Opening 'Add New Page' page");
    }

    @Step("Checking whether 'Add New Page' page is opened")
    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }

    @Step("User closes 'Choose a pattern'")
    private void closeChoosePattern() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        WebElement closeChoosePatternElement = Browser.waitForElementToBeClickable(CLOSE_CHOOSE_PATTERN_LOCATOR);
        closeChoosePatternElement.click();
        Browser.waitDeleting(pageElement);
        logger.info("Closing 'Choose a pattern'");
    }

    @Step("User chooses 'About' pattern")
    private void chooseAboutPattern() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        WebElement chooseAboutPatternElement = Browser.waitForElementToBeClickable(ABOUT_PATTERN_LOCATOR);
        chooseAboutPatternElement.click();
        Browser.waitDeleting(pageElement);
        logger.info("Choosing 'About' pattern");
    }

    @Step("User enters a title")
    private void enterTitle(String title) {
        WebElement titleElement = Browser.waitForElementToBeVisible(ADD_TITLE_LOCATOR);
        titleElement.clear();
        titleElement.sendKeys(title);
        logger.info("Entering a title");
    }

    @Step("User enters a text block")
    private void enterTextBlock(String textBlock) {
        WebElement textBlockElement = Browser.waitForElementToBeVisible(TEXT_BLOCK_LOCATOR);
        textBlockElement.clear();
        textBlockElement.sendKeys(textBlock);
        logger.info("Entering a text block");
    }

    @Step("User clicks 'Save draft' button")
    private void clickSaveDraft() {
        WebElement saveDraftButtonElement = Browser.waitForElementToBeClickable(SAVE_DRAFT_BUTTON_LOCATOR);
        saveDraftButtonElement.click();
        logger.info("Clicking 'Save draft' button");
    }

    @Step("User clicks 'Publish' button")
    private void clickPublish() {
        WebElement publishButtonElement = Browser.waitForElementToBeClickable(PUBLISH_BUTTON_LOCATOR);
        publishButtonElement.click();
        WebElement postPublishButtonElement = Browser.waitForElementToBeClickable(POST_PUBLISH_BUTTON_LOCATOR);
        postPublishButtonElement.click();
        logger.info("Clicking 'Publish' button");
    }

    @Step("User creates a new 'Draft' page")
    public void addNewPageDraft(Page page) {
        closeChoosePattern();
        WebElement iframeElement = Browser.waitForElementToBePresent(IFRAME_LOCATOR);
        Browser.switchToFrame(iframeElement);
        enterTitle(page.getTitle());
        Browser.pressKey(Keys.ENTER);
        enterTextBlock(page.getBlock());
        Browser.switchToDefaultContent();
        clickSaveDraft();
        logger.info("Adding a new 'Draft' page");
    }

    @Step("User creates a new page without pattern")
    public void addNewPageWithoutPattern(Page page) {
        closeChoosePattern();
        WebElement iframeElement = Browser.waitForElementToBePresent(IFRAME_LOCATOR);
        Browser.switchToFrame(iframeElement);
        enterTitle(page.getTitle());
        Browser.pressKey(Keys.ENTER);
        enterTextBlock(page.getBlock());
        Browser.switchToDefaultContent();
        clickPublish();
        logger.info("Adding a new page without pattern");
    }

    @Step("User creates a new page with 'About' pattern")
    public void addNewPageWithAboutPattern(Page page) {
        chooseAboutPattern();
        WebElement iframeElement = Browser.waitForElementToBePresent(IFRAME_LOCATOR);
        Browser.switchToFrame(iframeElement);
        enterTitle(page.getTitle());
        Browser.switchToDefaultContent();
        clickPublish();
        logger.info("Adding a new page with 'About' pattern");
    }

    @Step("User clicks 'View Pages'")
    public void goToPagesPage() {
        WebElement viewPagesElement = Browser.waitForElementToBeClickable(VIEW_PAGES_LOCATOR);
        viewPagesElement.click();
        logger.info("Clicking 'View Pages'. Opening 'All Pages' page");
    }
}
