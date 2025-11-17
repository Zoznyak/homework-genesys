package hu.zolkasza.hw.pages;

import hu.zolkasza.hw.contexts.ui.SauceContext;
import hu.zolkasza.hw.tools.Configuration;
import org.openqa.selenium.By;

public class CheckoutPage extends PageObject {

    private final By cartContentsContainer = By.cssSelector("[data-test='cart-contents-container']");
    private final By checkoutButton = By.cssSelector("[data-test='checkout']");

    public CheckoutPage(SauceContext context, Configuration config) {
        super(context, config);
    }

    public void pageIsLoaded() {
        waitForElementPresence(cartContentsContainer);
    }

    public void clickCheckoutButton() {
        click(checkoutButton);
    }

}
