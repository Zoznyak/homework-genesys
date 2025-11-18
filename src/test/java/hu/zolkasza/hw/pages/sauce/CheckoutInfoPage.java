package hu.zolkasza.hw.pages.sauce;

import hu.zolkasza.hw.contexts.ui.AssertionContext;
import hu.zolkasza.hw.contexts.ui.UiContext;
import hu.zolkasza.hw.pages.PageObject;
import hu.zolkasza.hw.tools.Configuration;
import org.openqa.selenium.By;

public class CheckoutInfoPage extends PageObject {

    private final By checkoutInfoContainer = By.cssSelector("[data-test='checkout-info-container']");
    private final By firstNameField = By.cssSelector("[data-test='firstName']");
    private final By lastNameField = By.cssSelector("[data-test='lastName']");
    private final By zipCodeField = By.cssSelector("[data-test='postalCode']");
    private final By continueButton = By.cssSelector("[data-test='continue']");

    public CheckoutInfoPage(UiContext context, Configuration config, AssertionContext assertionContext) {
        super(context, config, assertionContext);
    }

    public void pageIsLoaded() {
        waitForElementPresence(checkoutInfoContainer);
    }

    public void setFirstNameField(String firstName) {
        sendKeys(firstNameField, firstName, true);
    }

    public void setLastNameField(String lastName) {
        sendKeys(lastNameField, lastName, true);
    }

    public void setZipCodeField(String zipCode) {
        sendKeys(zipCodeField, zipCode, true);
    }

    public void clickContinueButton() {
        click(continueButton);
    }

}
