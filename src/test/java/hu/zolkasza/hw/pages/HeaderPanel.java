package hu.zolkasza.hw.pages;

import hu.zolkasza.hw.contexts.ui.SauceContext;
import hu.zolkasza.hw.model.ui.SauceLabItem;
import hu.zolkasza.hw.tools.Configuration;
import org.openqa.selenium.By;

public class HeaderPanel extends PageObject {

    private final By headerContainer = By.cssSelector("[data-test='header-container']");
    private final By cartBadgeText = By.cssSelector("[data-test='shopping-cart-badge']");
    private final By cartButton = By.cssSelector("[data-test='shopping-cart-link']");

    public HeaderPanel(SauceContext context, Configuration config) {
        super(context, config);
    }

    public void pageIsLoaded() {
        waitForElementPresence(headerContainer);
    }

    public void clickAddCartButton(SauceLabItem item) {
        By button = By.cssSelector(String.format("[data-test='add-to-cart-sauce-labs-%s']", item.getSelectorName()));
        click(button);
    }

    public void assertCartBadgeText(int numberOfItems) {
        waitForElementVisibility(cartBadgeText);
        verifyElementText(cartBadgeText, String.valueOf(numberOfItems));
    }

    public void clickCartButton() {
        click(cartButton);
    }

}
