package hu.zolkasza.hw.pages;

import hu.zolkasza.hw.contexts.ui.SauceContext;
import hu.zolkasza.hw.tools.Configuration;
import org.openqa.selenium.By;

public class CheckoutInfoPage extends PageObject {

    private final By checkoutInfoContainer = By.cssSelector("[data-test='checkout-info-container']");
    private final By firstNameField = By.cssSelector("[data-test='firstName']");
    private final By lastNameField = By.cssSelector("[data-test='lastName']");
    private final By zipCodeField = By.cssSelector("[data-test='postalCode']");
    private final By continueButton = By.cssSelector("[data-test='continue']");

    public CheckoutInfoPage(SauceContext context, Configuration config) {
        super(context, config);
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
