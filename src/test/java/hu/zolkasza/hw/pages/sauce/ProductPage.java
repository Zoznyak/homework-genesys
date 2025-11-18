package hu.zolkasza.hw.pages.sauce;

import hu.zolkasza.hw.contexts.ui.AssertionContext;
import hu.zolkasza.hw.contexts.ui.UiContext;
import hu.zolkasza.hw.pages.PageObject;
import hu.zolkasza.hw.tools.Configuration;
import org.openqa.selenium.By;

public class ProductPage extends PageObject {

    private final By inventoryContainer = By.cssSelector("[data-test='inventory-container']");
    private final By title = By.cssSelector("[data-test='title']");
    private final By footerText = By.cssSelector("[data-test='footer-copy']");

    public ProductPage(UiContext context, Configuration config, AssertionContext assertionContext) {
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
