package hu.zolkasza.hw.steps.ui;

import hu.zolkasza.hw.contexts.ui.SauceContext;
import hu.zolkasza.hw.model.ui.SauceLabItem;
import hu.zolkasza.hw.pages.CheckoutInfoPage;
import hu.zolkasza.hw.pages.CheckoutPage;
import hu.zolkasza.hw.pages.CheckoutSummaryPage;
import hu.zolkasza.hw.pages.MainPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CartSteps {

    private static final Logger logger = LogManager.getLogger(CartSteps.class);
    private final SauceContext sauceContext;
    private final MainPage mainPage;
    private final CheckoutPage checkoutPage;
    private final CheckoutInfoPage checkoutInfoPage;
    private final CheckoutSummaryPage checkoutSummaryPage;

    public CartSteps(SauceContext sauceContext, MainPage mainPage, CheckoutPage checkoutPage, CheckoutInfoPage checkoutInfoPage, CheckoutSummaryPage checkoutSummaryPage) {
        this.sauceContext = sauceContext;
        this.mainPage = mainPage;
        this.checkoutPage = checkoutPage;
        this.checkoutInfoPage = checkoutInfoPage;
        this.checkoutSummaryPage = checkoutSummaryPage;
    }

    public void addItemToCart(SauceLabItem item) {
        mainPage.clickAddCartButton(item);
        sauceContext.setItemsAddedToCart(sauceContext.getItemsAddedToCart() + 1);
    }

    public void assertItemNumbers() {
        mainPage.assertCartBadgeText(sauceContext.getItemsAddedToCart());
    }

    public void completePurchase() {
        mainPage.clickCartButton();
        checkoutPage.pageIsLoaded();
        checkoutPage.clickCheckoutButton();
        checkoutInfoPage.pageIsLoaded();
        checkoutInfoPage.setFirstNameField("a");
        checkoutInfoPage.setLastNameField("b");
        checkoutInfoPage.setZipCodeField("1234");
        checkoutInfoPage.clickContinueButton();
        checkoutSummaryPage.pageIsLoaded();
        checkoutSummaryPage.clickFinishButton();
    }
}
