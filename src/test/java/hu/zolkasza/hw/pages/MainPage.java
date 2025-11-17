package hu.zolkasza.hw.pages;

import hu.zolkasza.hw.contexts.ui.AssertionContext;
import hu.zolkasza.hw.contexts.ui.SauceContext;
import hu.zolkasza.hw.tools.Configuration;
import org.openqa.selenium.By;

public class MainPage extends PageObject {

    private final By inventoryContainer = By.cssSelector("[data-test='inventory-container']");
    private final By title = By.cssSelector("[data-test='title']");
    private final By footerText = By.cssSelector("[data-test='footer-copy']");

    public MainPage(SauceContext context, Configuration config, AssertionContext assertionContext) {
        super(context, config, assertionContext);
    }

    public void pageIsLoaded() {
        waitForElementPresence(inventoryContainer);
        waitForElementVisibility(title);
    }

    public void assertFooterTextContains(String text) {
        waitForElementVisibility(footerText);
        verifyElementTextContains(footerText, text);
    }

}
