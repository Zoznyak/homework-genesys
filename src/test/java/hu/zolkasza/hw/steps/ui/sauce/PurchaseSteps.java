package hu.zolkasza.hw.steps.ui.sauce;

import hu.zolkasza.hw.model.ui.sauce.SauceLabCustomer;
import hu.zolkasza.hw.model.ui.sauce.SauceLabItem;
import hu.zolkasza.hw.pages.sauce.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PurchaseSteps {

    private static final Logger logger = LogManager.getLogger(PurchaseSteps.class);
    private final HeaderPanel headerPanel;
    private final CheckoutPage checkoutPage;
    private final CheckoutInfoPage checkoutInfoPage;
    private final CheckoutSummaryPage checkoutSummaryPage;
    private final CheckoutCompletePage checkoutCompletePage;

    public PurchaseSteps(HeaderPanel headerPanel, CheckoutPage checkoutPage, CheckoutInfoPage checkoutInfoPage, CheckoutSummaryPage checkoutSummaryPage, CheckoutCompletePage checkoutCompletePage) {
        this.headerPanel = headerPanel;
        this.checkoutPage = checkoutPage;
        this.checkoutInfoPage = checkoutInfoPage;
        this.checkoutSummaryPage = checkoutSummaryPage;
        this.checkoutCompletePage = checkoutCompletePage;
    }

    public void addItemToCart(SauceLabItem item) {
        logger.info("Adding item to cart: {}", item);
        headerPanel.panelIsLoaded();
        headerPanel.clickAddCartButton(item);
    }

    public void assertItemNumbers(int expectedNumberOfItems) {
        logger.info("Asserting item numbers: {}", expectedNumberOfItems);
        headerPanel.assertCartBadgeText(expectedNumberOfItems);
    }

    public void checkoutItems() {
        logger.info("Checkout items");
        headerPanel.clickCartButton();
        checkoutPage.pageIsLoaded();
        checkoutPage.clickCheckoutButton();
    }

    public void fillCustomerData(SauceLabCustomer customer) {
        logger.info("Filling customer data: {}", customer);
        checkoutInfoPage.pageIsLoaded();
        checkoutInfoPage.setFirstNameField(customer.getFirstName());
        checkoutInfoPage.setLastNameField(customer.getLastName());
        checkoutInfoPage.setZipCodeField(customer.getZipCode());
    }

    public void finishCheckout() {
        logger.info("Finishing checkout");
        checkoutInfoPage.clickContinueButton();
        checkoutSummaryPage.pageIsLoaded();
        checkoutSummaryPage.clickFinishButton();
    }

    public void assertCheckoutIsComplete() {
        logger.info("Asserting checkout is complete");
        checkoutCompletePage.pageIsLoaded();
        checkoutCompletePage.assertCompleteText();
    }

}
