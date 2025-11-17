package hu.zolkasza.hw.workflows.ui;

import hu.zolkasza.hw.model.ui.SauceLabCustomer;
import hu.zolkasza.hw.model.ui.SauceLabItem;
import hu.zolkasza.hw.model.ui.SauceLabUser;
import hu.zolkasza.hw.steps.ui.LoginSteps;
import hu.zolkasza.hw.steps.ui.PurchaseSteps;
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

    @Given("The application is opened")
    public void the_application_is_opened() throws IOException {
        SauceLabUser user = testDataLoader.loadSauceLabUser("valid-user.json");
        loginSteps.loginWithValidUser(user);
    }

    @When("I add items to the cart")
    public void i_add_items_to_the_cart() {
        purchaseSteps.addItemToCart(SauceLabItem.BACKPACK);
        purchaseSteps.addItemToCart(SauceLabItem.JACKET);
    }

    @Then("The cart shows the number of items placed in it")
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

    @Then("A message about the successful purchase appears")
    public void a_message_about_the_successful_purchase_appears() {
        purchaseSteps.assertCheckoutIsComplete();
    }

}