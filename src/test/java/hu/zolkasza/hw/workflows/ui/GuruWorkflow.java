package hu.zolkasza.hw.workflows.ui;

import hu.zolkasza.hw.steps.ui.guru.GuruSteps;
import io.cucumber.java.en.Given;

public class GuruWorkflow {

    private final GuruSteps guruSteps;

    public GuruWorkflow(GuruSteps guruSteps) {
        this.guruSteps = guruSteps;
    }

    @Given("Open guru page")
    public void open_guru_page() {
        guruSteps.openApplication();
    }

    @Given("Open selenium practice page")
    public void open_selenium_practice_page() {
        guruSteps.openSeleniumPractice();
    }

    @Given("Verify title")
    public void verify_title() {
        guruSteps.verifyTitle();
    }

    @Given("Close selenium practice page")
    public void close_selenium_practice_page() {
        guruSteps.closeSeleniumPractice();
    }

    @Given("Click on Selenium link")
    public void click_on_selenium_link() {
        guruSteps.navigateToSeleniumMenu();
    }

    @Given("Verify button")
    public void verify_button() {
        guruSteps.verifyButton();
    }

}