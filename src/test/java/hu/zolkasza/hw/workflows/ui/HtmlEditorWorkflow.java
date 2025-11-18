package hu.zolkasza.hw.workflows.ui;

import hu.zolkasza.hw.model.ui.htmleditor.TextFormat;
import hu.zolkasza.hw.steps.ui.htmleditor.HtmlEditorSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HtmlEditorWorkflow {

    private final HtmlEditorSteps htmlEditorSteps;
    private String boldText;
    private String underlineText;
    private String plainText;

    public HtmlEditorWorkflow(HtmlEditorSteps htmlEditorSteps) {
        this.htmlEditorSteps = htmlEditorSteps;
    }

    @Given("the user is on the HTML editor page")
    public void the_user_is_on_the_html_editor_page() {
        htmlEditorSteps.openApplication();
    }

    @When("the user enters text with mixed formatting")
    public void the_user_enters_text_with_mixed_formatting() {
        boldText = "Automation";
        plainText = " Test ";
        underlineText = "Example";
        htmlEditorSteps.typeBoldedText(boldText);
        htmlEditorSteps.typeText(plainText);
        htmlEditorSteps.typeUnderlinedText(underlineText);
    }

    @Then("the editor content should contain the complete text {string}")
    public void the_editor_content_should_contain_the_complete_text(String string) {
        htmlEditorSteps.assertTextIsPresent(string, TextFormat.PLAIN);
    }

    @Then("the sub-string {string} should be bolded")
    public void the_sub_string_should_be_bolded(String string) {
        htmlEditorSteps.assertTextIsPresent(boldText, TextFormat.BOLD);
    }

    @Then("the sub-string {string} should be underlined")
    public void the_sub_string_should_be_underlined(String string) {
        htmlEditorSteps.assertTextIsPresent(underlineText, TextFormat.UNDERLINE);
    }

}