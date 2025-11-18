package hu.zolkasza.hw.pages.sauce;

import hu.zolkasza.hw.contexts.ui.AssertionContext;
import hu.zolkasza.hw.contexts.ui.UiContext;
import hu.zolkasza.hw.model.ui.sauce.SauceLabItem;
import hu.zolkasza.hw.pages.PageObject;
import hu.zolkasza.hw.tools.ConfigurationLoader;
import org.openqa.selenium.By;

public class HeaderPanel extends PageObject {

    private final By headerContainer = By.cssSelector("[data-test='header-container']");
    private final By cartBadgeText = By.cssSelector("[data-test='shopping-cart-badge']");
    private final By cartButton = By.cssSelector("[data-test='shopping-cart-link']");

    public HeaderPanel(UiContext context, ConfigurationLoader config, AssertionContext assertionContext) {
        super(context, config, assertionContext);
    }

    public void panelIsLoaded() {
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
