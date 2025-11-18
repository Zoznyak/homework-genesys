package hu.zolkasza.hw.steps.ui.htmleditor;

import hu.zolkasza.hw.model.ui.htmleditor.TextFormat;
import hu.zolkasza.hw.pages.htmleditor.HtmlEditorMainPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HtmlEditorSteps {

    private static final Logger logger = LogManager.getLogger(HtmlEditorSteps.class);

    private final HtmlEditorMainPage mainPage;

    public HtmlEditorSteps(HtmlEditorMainPage mainPage) {
        this.mainPage = mainPage;
    }

    public void openApplication() {
        mainPage.loadPage();
        mainPage.pageIsLoaded();
    }

    public void typeText(String text) {
        mainPage.setTextArea(text);
    }

    public void typeBoldedText(String text) {
        mainPage.turnOnBold();
        mainPage.setTextArea(text);
        mainPage.turnOffBold();
    }

    public void typeUnderlinedText(String text) {
        mainPage.turnOnUnderline();
        mainPage.setTextArea(text);
        mainPage.turnOffUnderline();
    }

    public void assertTextIsPresent(String text, TextFormat format) {
        mainPage.assertTextIsPresent(text, format);
    }

}
