package hu.zolkasza.hw.pages.sauce;

import hu.zolkasza.hw.contexts.ui.AssertionContext;
import hu.zolkasza.hw.contexts.ui.UiContext;
import hu.zolkasza.hw.pages.PageObject;
import hu.zolkasza.hw.tools.Configuration;
import org.openqa.selenium.By;

public class CheckoutPage extends PageObject {

    private final By cartContentsContainer = By.cssSelector("[data-test='cart-contents-container']");
    private final By checkoutButton = By.cssSelector("[data-test='checkout']");

    public CheckoutPage(UiContext context, Configuration config, AssertionContext assertionContext) {
        super(context, config, assertionContext);
    }

    public void pageIsLoaded() {
        waitForElementPresence(cartContentsContainer);
    }

    public void clickCheckoutButton() {
        click(checkoutButton);
    }

}
