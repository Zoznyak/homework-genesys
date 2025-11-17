package hu.zolkasza.hw.pages;

import hu.zolkasza.hw.contexts.ui.AssertionContext;
import hu.zolkasza.hw.contexts.ui.SauceContext;
import hu.zolkasza.hw.tools.Configuration;
import org.openqa.selenium.By;

public class CheckoutSummaryPage extends PageObject {

    private final By checkoutSummaryContainer = By.cssSelector("[data-test='checkout-summary-container']");
    private final By finishButton = By.cssSelector("[data-test='finish']");

    public CheckoutSummaryPage(SauceContext context, Configuration config, AssertionContext assertionContext) {
        super(context, config, assertionContext);
    }

    public void pageIsLoaded() {
        waitForElementPresence(checkoutSummaryContainer);
    }

    public void clickFinishButton() {
        click(finishButton);
    }

}
