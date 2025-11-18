package hu.zolkasza.hw.pages.htmleditor;

import hu.zolkasza.hw.contexts.ui.AssertionContext;
import hu.zolkasza.hw.contexts.ui.UiContext;
import hu.zolkasza.hw.model.ui.htmleditor.TextFormat;
import hu.zolkasza.hw.pages.PageObject;
import hu.zolkasza.hw.tools.Configuration;
import org.openqa.selenium.By;

public class HtmlEditorMainPage extends PageObject {

    private final By banner = By.cssSelector(".c-banner__main");
    private final By editor = By.cssSelector(".ck-editor__main");
    private final By textArea = By.cssSelector(".ck-editor__main p");
    private final By boldButton = By.cssSelector("button[data-cke-tooltip-text*='Bold']");
    private final By underlineButton = By.cssSelector("button[data-cke-tooltip-text*='Underline']");

    private final Configuration config;

    public HtmlEditorMainPage(UiContext context, Configuration config, AssertionContext assertionContext) {
        super(context, config, assertionContext);
        this.config = config;
    }

    public void loadPage() {
        navigateTo(config.getHtmlEditorUiUrl());
    }

    public void pageIsLoaded() {
        waitForElementPresence(banner);
        waitForElementPresence(editor);
    }

    public void setTextArea(String text) {
        sendKeys(textArea, text, true);
    }

    public void turnOnBold() {
        if (hasClass(boldButton, "ck-off")) {
            click(boldButton);
        }
    }

    public void turnOffBold() {
        if (hasClass(boldButton, "ck-on")) {
            click(boldButton);
        }
    }

    public void turnOnUnderline() {
        if (hasClass(underlineButton, "ck-off")) {
            click(underlineButton);
        }
    }

    public void turnOffUnderline() {
        if (hasClass(underlineButton, "ck-on")) {
            click(underlineButton);
        }
    }

    public void assertTextIsPresent(String text, TextFormat format) {
        switch (format) {
            case BOLD:
                verifyTextIsBold(textArea, text);
                break;
            case UNDERLINE:
                verifyTextIsUnderline(textArea, text);
                break;
            case PLAIN:
            default:
                verifyElementTextContains(textArea, text);
        }
    }

}
