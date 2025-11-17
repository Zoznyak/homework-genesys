package hu.zolkasza.hw.steps.ui;

import hu.zolkasza.hw.model.ui.SauceLabCustomer;
import hu.zolkasza.hw.model.ui.SauceLabItem;
import hu.zolkasza.hw.pages.*;
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
        headerPanel.panelIsLoaded();
        headerPanel.clickAddCartButton(item);
    }

    public void assertItemNumbers(int expectedNumberOfItems) {
        headerPanel.assertCartBadgeText(expectedNumberOfItems);
    }

    public void checkoutItems() {
        headerPanel.clickCartButton();
        checkoutPage.pageIsLoaded();
        checkoutPage.clickCheckoutButton();
    }

    public void fillCustomerData(SauceLabCustomer customer) {
        checkoutInfoPage.pageIsLoaded();
        checkoutInfoPage.setFirstNameField(customer.getFirstName());
        checkoutInfoPage.setLastNameField(customer.getLastName());
        checkoutInfoPage.setZipCodeField(customer.getZipCode());
    }

    public void finishCheckout() {
        checkoutInfoPage.clickContinueButton();
        checkoutSummaryPage.pageIsLoaded();
        checkoutSummaryPage.clickFinishButton();
    }

    public void assertCheckoutIsComplete() {
        checkoutCompletePage.pageIsLoaded();
        checkoutCompletePage.assertCompleteText();
    }
}
