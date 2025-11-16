package hu.zolkasza.hw.workflows.api;

import hu.zolkasza.hw.steps.api.GetContactsStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class ContactsWorkflow {

    private GetContactsStep getContactsStep;

    public ContactsWorkflow(GetContactsStep getContactsStep) {
        this.getContactsStep = getContactsStep;
    }

    @Given("There are contacts in the system")
    public void there_are_contacts_in_the_system() {
        // We assume there are contacts. Conditional contact creation could happen here.
    }
    @When("I collect all contacts")
    public void i_collect_all_contacts() throws IOException {
        getContactsStep.getAllContacts();
        getContactsStep.logContacts();
    }
    @Then("The first email address contains {string}")
    public void the_first_email_address_contains(String str) {
        getContactsStep.assertNthContactContains(0, str);
    }

}
