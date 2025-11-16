package hu.zolkasza.hw.pages;

import hu.zolkasza.hw.contexts.ui.SauceContext;
import hu.zolkasza.hw.model.ui.SauceLabItem;
import org.openqa.selenium.By;

public class MainPage extends PageObject {

    private final By inventoryContainer = By.cssSelector("[data-test='inventory-container']");
    private final By title = By.cssSelector("[data-test='title']");
    private final By cartBadgeText = By.cssSelector("[data-test='shopping-cart-badge']");
    private final By cartButton = By.cssSelector("[data-test='shopping-cart-link']");

    public MainPage(SauceContext context) {
        super(context);
    }

    public void pageIsLoaded() {
        waitForElementPresence(inventoryContainer);
        waitForElementVisibility(title);
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
