package hu.zolkasza.hw.workflows.ui;

import hu.zolkasza.hw.model.ui.sauce.SauceLabCustomer;
import hu.zolkasza.hw.model.ui.sauce.SauceLabItem;
import hu.zolkasza.hw.model.ui.sauce.SauceLabUser;
import hu.zolkasza.hw.steps.ui.sauce.LoginSteps;
import hu.zolkasza.hw.steps.ui.sauce.PurchaseSteps;
import hu.zolkasza.hw.tools.TestDataLoader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class SauceDemoWorkflow {

    private final TestDataLoader testDataLoader;
    private final LoginSteps loginSteps;
    private final PurchaseSteps purchaseSteps;

    public SauceDemoWorkflow(TestDataLoader testDataLoader, LoginSteps loginSteps, PurchaseSteps purchaseSteps) {
        this.testDataLoader = testDataLoader;
        this.loginSteps = loginSteps;
        this.purchaseSteps = purchaseSteps;
    }

    @Given("{string} user is logged in")
    public void user_is_logged_in(String str) throws IOException {
        loginSteps.openApplication();
        SauceLabUser user = testDataLoader.loadSauceLabUser(str.toLowerCase() + "-user.json");
        loginSteps.loginWithUser(user);
    }

    @When("I add items to the cart")
    public void i_add_items_to_the_cart() {
        purchaseSteps.addItemToCart(SauceLabItem.BACKPACK);
        purchaseSteps.addItemToCart(SauceLabItem.JACKET);
    }

    @Then("the cart shows the number of items placed in it")
    public void the_cart_shows_the_number_of_items_placed_in_it() {
        purchaseSteps.assertItemNumbers(2);
    }

    @Given("I have items in my cart")
    public void i_have_items_in_my_cart() {
        purchaseSteps.addItemToCart(SauceLabItem.BACKPACK);
        purchaseSteps.addItemToCart(SauceLabItem.JACKET);
    }

    @When("I complete the purchase")
    public void i_complete_the_purchase() throws IOException {
        SauceLabCustomer customer = testDataLoader.loadSauceLabCustomer("valid-customer.json");
        purchaseSteps.checkoutItems();
        purchaseSteps.fillCustomerData(customer);
        purchaseSteps.finishCheckout();
    }

    @Then("a message about the successful purchase appears")
    public void a_message_about_the_successful_purchase_appears() {
        purchaseSteps.assertCheckoutIsComplete();
    }

    @Given("the login page is opened")
    public void the_login_page_is_opened() {
        loginSteps.openApplication();
    }

    @When("I click the login button")
    public void i_click_the_login_button() {
        loginSteps.loginWithoutCredentials();
    }

    @Then("a message about missing credentials appears")
    public void a_message_about_missing_credentials_appears() {
        loginSteps.assertLoginError();
    }

    @When("I log in as a {string} user")
    public void i_log_in_as_a_user(String str) throws IOException {
        loginSteps.openApplication();
        SauceLabUser user = testDataLoader.loadSauceLabUser(str.toLowerCase() + "-user.json");
        loginSteps.loginWithUser(user);
    }

    @Then("products page is opened")
    public void products_page_is_opened() {
        loginSteps.assertProductPageIsLoaded();
    }

    @Then("footer text contains {string}")
    public void footer_text_contains(String str) {
        loginSteps.assertFooterContains(str);
    }

}