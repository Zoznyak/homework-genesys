package hu.zolkasza.hw.pages.sauce;

import hu.zolkasza.hw.contexts.ui.AssertionContext;
import hu.zolkasza.hw.contexts.ui.UiContext;
import hu.zolkasza.hw.pages.PageObject;
import hu.zolkasza.hw.tools.ConfigurationLoader;
import org.openqa.selenium.By;

public class CheckoutCompletePage extends PageObject {

    private static final String PURCHASE_COMPLETE = "Thank you for your order!";
    private final By checkoutCompleteContainer = By.cssSelector("[data-test='checkout-complete-container']");
    private final By completeText = By.cssSelector("[data-test='complete-header']");

    public CheckoutCompletePage(UiContext context, ConfigurationLoader config, AssertionContext assertionContext) {
        super(context, config, assertionContext);
    }

    public void pageIsLoaded() {
        waitForElementPresence(checkoutCompleteContainer);
    }

    public void assertCompleteText() {
        waitForElementVisibility(completeText);
        verifyElementText(completeText, PURCHASE_COMPLETE);
    }

}
