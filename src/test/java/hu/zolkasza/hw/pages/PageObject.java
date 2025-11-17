package hu.zolkasza.hw.pages;

import hu.zolkasza.hw.contexts.ui.UiContext;
import hu.zolkasza.hw.tools.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageObject {

    private static final Logger logger = LogManager.getLogger(PageObject.class);

    private final UiContext context;
    private final Configuration config;
    private WebDriver driver;
    private WebDriverWait wait;


    public PageObject(UiContext context, Configuration config) {
        this.context = context;
        this.config = config;
    }

    protected void waitForElementPresence(By locator) {
        try {
            logger.debug("Waiting for element presence: {}", locator);
            getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception ex) {
            logger.error("Error while waiting for element presence: {}", locator, ex);
            Assertions.fail("Element did not appear in the DOM within " + config.getLongTimeoutSeconds() + "s: " + locator);
        }
    }

    protected void waitForElementVisibility(By locator) {
        try {
            logger.debug("Waiting for element visibility: {}", locator);
            getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception ex) {
            logger.error("Error while waiting for element visibility: {}", locator, ex);
            Assertions.fail("Element did not become visible within " + config.getLongTimeoutSeconds() + "s: " + locator);
        }
    }

    protected void waitForElementClickable(By locator) {
        try {
            logger.debug("Waiting for element to be clickable: {}", locator);
            getWait().until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception ex) {
            logger.error("Error while waiting for element to be clickable: {}", locator, ex);
            Assertions.fail("Element did not become clickable within " + config.getLongTimeoutSeconds() + "s: " + locator);
        }
    }

    protected String getElementText(By locator) {
        logger.debug("Getting text from element: {}", locator);
        waitForElementVisibility(locator);
        WebElement element = getDriver().findElement(locator);
        String text = element.getText();
        logger.debug("Found text: '{}'", text);
        return text;
    }

    protected void verifyElementText(By locator, String expectedText) {
        logger.debug("Verifying text for element: {}. Expected: '{}'", locator, expectedText);
        String actualText = getElementText(locator);
        Assertions.assertEquals(expectedText, actualText, "The element's text does not match the expected value!");
    }

    protected void click(By locator) {
        waitForElementClickable(locator);
        logger.debug("Clicking on element: {}", locator);
        getDriver().findElement(locator).click();
    }

    protected void sendKeys(By locator, String text, boolean displayInLog) {
        if (displayInLog) {
            logger.debug("Sending keys '{}' to element: {}", text, locator);
        } else {
            logger.debug("Sending keys to element: {} (text=****)", locator);
        }
        waitForElementVisibility(locator);
        getDriver().findElement(locator).sendKeys(text);
    }

    protected void navigateTo(String url) {
        logger.info("Navigating to URL: {}", url);
        getDriver().get(url);
    }

    private WebDriver getDriver() {
        if (this.driver == null) {
            this.driver = context.getDriver();
        }
        return this.driver;
    }

    private WebDriverWait getWait() {
        if (this.wait == null) {
            this.wait = new WebDriverWait(getDriver(), Duration.ofSeconds(config.getLongTimeoutSeconds()));
        }
        return this.wait;
    }

}
