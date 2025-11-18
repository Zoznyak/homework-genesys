package hu.zolkasza.hw.pages;

import hu.zolkasza.hw.contexts.ui.AssertionContext;
import hu.zolkasza.hw.contexts.ui.UiContext;
import hu.zolkasza.hw.tools.ConfigurationLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PageObject {

    private static final Logger logger = LogManager.getLogger(PageObject.class);

    private final UiContext context;
    private final ConfigurationLoader config;
    protected final AssertionContext assertionContext;
    private WebDriver driver;
    private WebDriverWait wait;

    public PageObject(UiContext context, ConfigurationLoader config, AssertionContext assertionContext) {
        this.context = context;
        this.config = config;
        this.assertionContext = assertionContext;
    }

    protected void waitForElementPresence(By locator) {
        try {
            logger.debug("Waiting for element presence: {}", locator);
            getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception ex) {
            logger.error("Error while waiting for element presence: {}", locator, ex);
            Assertions.fail("Element did not appear in the DOM within " + config.getTimeoutSeconds() + "s: " + locator);
        }
    }

    protected void waitForElementVisibility(By locator) {
        try {
            logger.debug("Waiting for element visibility: {}", locator);
            getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception ex) {
            logger.error("Error while waiting for element visibility: {}", locator, ex);
            Assertions.fail("Element did not become visible within " + config.getTimeoutSeconds() + "s: " + locator);
        }
    }

    protected void waitForElementClickable(By locator) {
        try {
            logger.debug("Waiting for element to be clickable: {}", locator);
            getWait().until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception ex) {
            logger.error("Error while waiting for element to be clickable: {}", locator, ex);
            Assertions.fail("Element did not become clickable within " + config.getTimeoutSeconds() + "s: " + locator);
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
        assertionContext.get().assertThat(actualText)
                .withFailMessage("Element text mismatch for locator '%s'. Expected: '%s', but was: '%s'",
                        locator, expectedText, actualText)
                .isEqualTo(expectedText);
    }

    protected void verifyElementTextContains(By locator, String expectedText) {
        logger.debug("Verifying text for element: {}. Contains: '{}'", locator, expectedText);
        String actualText = getElementText(locator);
        assertionContext.get().assertThat(actualText)
                .withFailMessage("Element text does not contain expected substring for locator '%s'. Expected substring: '%s', but actual text was: '%s'",
                        locator, expectedText, actualText)
                .contains(expectedText);
    }

    protected void verifyTextIsBold(By parentLocator, String text) {
        waitForElementVisibility(parentLocator);
        WebElement parentElement = getDriver().findElement(parentLocator);
        String relativeXpath = String.format("./descendant::strong[text()='%s']", text);
        By textLocator = By.xpath(relativeXpath);
        List<WebElement> foundElements = parentElement.findElements(textLocator);
        logger.debug("Soft Assert: Checking element '{}' for bold text '{}'. Found {} elements.",
                parentLocator, text, foundElements.size());
        assertionContext.get().assertThat(foundElements.isEmpty())
                .withFailMessage("Failure: Expected text '%s' was not found inside a <strong> tag within the element located by '%s'. The text was not bolded or missing.",
                        text, parentLocator)
                .isFalse();
    }

    protected void verifyTextIsUnderline(By parentLocator, String text) {
        waitForElementVisibility(parentLocator);
        WebElement parentElement = getDriver().findElement(parentLocator);
        String relativeXpath = String.format("./descendant::u[text()='%s']", text);
        By textLocator = By.xpath(relativeXpath);
        List<WebElement> foundElements = parentElement.findElements(textLocator);
        logger.debug("Soft Assert: Checking element '{}' for underline text '{}'. Found {} elements.",
                parentLocator, text, foundElements.size());
        assertionContext.get().assertThat(foundElements.isEmpty())
                .withFailMessage("Failure: Expected text '%s' was not found inside a <u> tag within the element located by '%s'. The text was not underlined or missing.",
                        text, parentLocator)
                .isFalse();
    }

    protected void click(By locator) {
        try {
            WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(locator));
            scrollToElement(element);
            logger.debug("Clicking on element: {}", locator);
            element.click();
        } catch (Exception ex) {
            logger.error("Failed to click element: {}", locator, ex);
            Assertions.fail("Failed to click element " + locator, ex);
        }
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

    protected void switchToIframe(By locator) {
        try {
            logger.debug("Switching to iframe located by: {}", locator);
            waitForElementPresence(locator);
            WebElement iframeElement = getDriver().findElement(locator);
            scrollToElement(iframeElement);
            getWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeElement));
            logger.debug("Successfully switched to iframe.");
        } catch (Exception ex) {
            logger.error("Failed to switch to iframe: {}", locator, ex);
            Assertions.fail("Failed to switch to iframe: " + locator);
        }
    }

    protected void switchToDefaultContent() {
        logger.debug("Switching back to default content.");
        getDriver().switchTo().defaultContent();
    }

    protected void scrollToElement(WebElement element) {
        try {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
            logger.debug("Scrolled element into view: {}", element);
        } catch (Exception ex) {
            logger.warn("Failed to scroll element into view", ex);
        }
    }

    protected void hoverOverElement(By locator) {
        try {
            waitForElementVisibility(locator);
            WebElement element = getDriver().findElement(locator);
            scrollToElement(element);
            Actions actions = new Actions(getDriver());
            actions.moveToElement(element);
            actions.perform();
            logger.debug("Successfully hovered over element: {}", locator);
        } catch (Exception ex) {
            logger.error("Failed to hover over element: {}", locator, ex);
            Assertions.fail("Failed to hover over element: " + locator, ex);
        }
    }

    protected void storeOriginalTabHandle() {
        context.setOriginalTabHandle(getDriver().getWindowHandle());
        logger.debug("Stored original tab handle: {}", context.getOriginalTabHandle());
    }

    protected void switchToNewTab() {
        logger.debug("Attempting to switch to new tab...");
        String originalHandle = context.getOriginalTabHandle();
        try {
            getWait().until(d -> d.getWindowHandles().size() > 1);
        } catch (Exception e) {
            Assertions.fail("No new tab opened within the timeout period.");
        }
        for (String handle : getDriver().getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                logger.info("Successfully switched to new tab: {}", handle);
                getDriver().switchTo().window(handle);
                return;
            }
        }
        Assertions.fail("Could not find a new tab to switch to.");
    }

    protected void closeCurrentTabAndReturnToOriginal() {
        String originalHandle = context.getOriginalTabHandle();
        getDriver().close();
        logger.info("Switching back to original tab: {}", originalHandle);
        getDriver().switchTo().window(originalHandle);
    }

    protected boolean hasClass(By locator, String className) {
        waitForElementVisibility(locator);
        WebElement element = getDriver().findElement(locator);
        String classes = element.getAttribute("class");
        for (String c : classes.split("\\s+")) {
            if (c.equals(className)) {
                return true;
            }
        }
        return false;
    }

    private WebDriver getDriver() {
        if (this.driver == null) {
            this.driver = context.getDriver();
        }
        return this.driver;
    }

    private WebDriverWait getWait() {
        if (this.wait == null) {
            this.wait = new WebDriverWait(getDriver(), Duration.ofSeconds(config.getTimeoutSeconds()));
        }
        return this.wait;
    }

}
