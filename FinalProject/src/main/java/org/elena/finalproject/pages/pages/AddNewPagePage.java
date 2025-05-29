package org.elena.finalproject.pages.pages;

import org.elena.finalproject.models.Page;
import org.elena.finalproject.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class AddNewPagePage {

    private static final By PAGE_LOCATOR = By.xpath("//h1[contains(text(),'Choose a pattern')]");
    private static final By CLOSE_CHOOSE_PATTERN_LOCATOR = By.xpath("//button[@aria-label='Close']");
    private static final By ABOUT_PATTERN_LOCATOR = By.id("twentytwentyfour/page-about-business");
    private static final By BUSINESS_HOME_PATTERN_LOCATOR = By.id("twentytwentyfour/page-home-business");
    private static final By PORTFOLIO_HOME_IMAGE_GALLERY_PATTERN_LOCATOR = By.id("twentytwentyfour/page-home-gallery");
    private static final By PORTFOLIO_HOME_WITH_POST_FEATURED_IMAGES_PATTERN_LOCATOR = By.id("twentytwentyfour/page-home-portfolio");
    private static final By NEWSLETTER_LANDING_PATTERN_LOCATOR = By.id("twentytwentyfour/page-newsletter-landing");
    private static final By PORTFOLIO_PROJECT_OVERVIEW_PATTERN_LOCATOR = By.id("twentytwentyfour/page-portfolio-overview");
    private static final By RSVP_LANDING_PATTERN_LOCATOR = By.id("twentytwentyfour/page-rsvp-landing");
    private static final By IFRAME_LOCATOR = By.xpath("//iframe[@title='Editor canvas']");
    private static final By ADD_TITLE_LOCATOR = By.xpath("//h1[contains(@class,'wp-block-post-title')]");
    private static final By TEXT_BLOCK_LOCATOR = By.xpath("//div[contains(@class,'wp-block-post-content')]/p");
    private static final By SAVE_DRAFT_BUTTON_LOCATOR = By.xpath("//button[contains(text(),'Save draft')]");
    private static final By PUBLISH_BUTTON_LOCATOR = By.xpath("//button[contains(text(),'Publish')]");
    private static final By POST_PUBLISH_BUTTON_LOCATOR = By.xpath("//div[@class='editor-post-publish-panel__header-publish-button']");
    private static final By VIEW_PAGES_LOCATOR = By.xpath("//a[@href='edit.php?post_type=page'][@aria-label='View Pages']");

    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }

    public void addNewPageDraft(Page page) {
        WebElement closeChoosePatternElement = Browser.waitForElementToBeClickable(CLOSE_CHOOSE_PATTERN_LOCATOR);
        closeChoosePatternElement.click();
        Browser.waitDeleting(closeChoosePatternElement);
        WebElement iframeElement = Browser.waitForElementToBePresent(IFRAME_LOCATOR);
        Browser.switchToFrame(iframeElement);
        WebElement titleElement = Browser.waitForElementToBeVisible(ADD_TITLE_LOCATOR);
        titleElement.clear();
        titleElement.sendKeys(page.getTitle());
        Browser.pressKey(Keys.ENTER);
        WebElement textBlockElement = Browser.waitForElementToBeVisible(TEXT_BLOCK_LOCATOR);
        textBlockElement.clear();
        textBlockElement.sendKeys(page.getBlock());
        Browser.switchToDefaultContent();
        WebElement saveDraftButtonElement = Browser.waitForElementToBeClickable(SAVE_DRAFT_BUTTON_LOCATOR);
        saveDraftButtonElement.click();
    }

    public void addNewPageWithoutPattern(Page page) {
        WebElement closeChoosePatternElement = Browser.waitForElementToBeClickable(CLOSE_CHOOSE_PATTERN_LOCATOR);
        closeChoosePatternElement.click();
        WebElement iframeElement = Browser.waitForElementToBePresent(IFRAME_LOCATOR);
        Browser.switchToFrame(iframeElement);
        WebElement titleElement = Browser.waitForElementToBeVisible(ADD_TITLE_LOCATOR);
        titleElement.clear();
        titleElement.sendKeys(page.getTitle());
        Browser.pressKey(Keys.ENTER);
        WebElement textBlockElement = Browser.waitForElementToBeVisible(TEXT_BLOCK_LOCATOR);
        textBlockElement.clear();
        textBlockElement.sendKeys(page.getBlock());
        Browser.switchToDefaultContent();
        WebElement publishButtonElement = Browser.waitForElementToBeClickable(PUBLISH_BUTTON_LOCATOR);
        publishButtonElement.click();
        WebElement postPublishButtonElement = Browser.waitForElementToBeClickable(POST_PUBLISH_BUTTON_LOCATOR);
        postPublishButtonElement.click();
    }



    public void addNewPageWithAboutPattern(Page page) {
        WebElement patternElement = Browser.waitForElementToBeClickable(ABOUT_PATTERN_LOCATOR);
        patternElement.click();
        WebElement iframeElement = Browser.waitForElementToBePresent(IFRAME_LOCATOR);
        Browser.switchToFrame(iframeElement);
        WebElement titleElement = Browser.waitForElementToBeVisible(ADD_TITLE_LOCATOR);
        titleElement.clear();
        titleElement.sendKeys(page.getTitle());
        Browser.switchToDefaultContent();
        WebElement publishButtonElement = Browser.waitForElementToBeClickable(PUBLISH_BUTTON_LOCATOR);
        publishButtonElement.click();
        WebElement postPublishButtonElement = Browser.waitForElementToBeClickable(POST_PUBLISH_BUTTON_LOCATOR);
        postPublishButtonElement.click();
    }








    public void goToPagesPage() {
        WebElement viewPagesElement = Browser.waitForElementToBeClickable(VIEW_PAGES_LOCATOR);
        viewPagesElement.click();
    }
}
