package hu.zolkasza.hw.workflows.ui;

import hu.zolkasza.hw.contexts.ui.SauceContext;
import hu.zolkasza.hw.model.ui.SauceLabItem;
import hu.zolkasza.hw.steps.ui.CartSteps;
import hu.zolkasza.hw.steps.ui.LoginSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SauceDemoWorkflow {

    private final SauceContext sauceContext;
    private final LoginSteps loginSteps;
    private final CartSteps cartSteps;

    public SauceDemoWorkflow(SauceContext sauceContext, LoginSteps loginSteps, CartSteps cartSteps) {
        this.sauceContext = sauceContext;
        this.loginSteps = loginSteps;
        this.cartSteps = cartSteps;
    }

    @Given("The application is opened")
    public void the_application_is_opened() {
        loginSteps.loginWithValidUser();
    }

    @When("I add items to the cart")
    public void i_add_items_to_the_cart() {
        cartSteps.addItemToCart(SauceLabItem.BACKPACK);
        cartSteps.addItemToCart(SauceLabItem.JACKET);
    }

    @Then("The cart shows the number of items placed in it")
    public void the_cart_shows_the_number_of_items_placed_in_it() {
        cartSteps.assertItemNumbers();
    }

    @Given("I have items in my cart")
    public void i_have_items_in_my_cart() {
        cartSteps.addItemToCart(SauceLabItem.BACKPACK);
        cartSteps.addItemToCart(SauceLabItem.JACKET);
    }

    @When("I complete the purchase")
    public void i_complete_the_purchase() {
        cartSteps.completePurchase();
    }

    @Then("A message about the successful purchase appears")
    public void a_message_about_the_successful_purchase_appears() {
        // Write code here that turns the phrase above into concrete actions
    }

}