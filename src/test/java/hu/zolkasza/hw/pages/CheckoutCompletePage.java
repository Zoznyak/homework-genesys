package hu.zolkasza.hw.pages;

import hu.zolkasza.hw.contexts.ui.AssertionContext;
import hu.zolkasza.hw.contexts.ui.SauceContext;
import hu.zolkasza.hw.tools.Configuration;
import org.openqa.selenium.By;

public class CheckoutCompletePage extends PageObject {

    private static final String PURCHASE_COMPLETE = "Thank you for your order!";
    private final By checkoutCompleteContainer = By.cssSelector("[data-test='checkout-complete-container']");
    private final By completeText = By.cssSelector("[data-test='complete-header']");

    public CheckoutCompletePage(SauceContext context, Configuration config, AssertionContext assertionContext) {
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
