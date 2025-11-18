package hu.zolkasza.hw.pages.guru;

import hu.zolkasza.hw.contexts.ui.AssertionContext;
import hu.zolkasza.hw.contexts.ui.UiContext;
import hu.zolkasza.hw.pages.PageObject;
import hu.zolkasza.hw.tools.ConfigurationLoader;
import org.openqa.selenium.By;

public class GuruSeleniumPracticePage extends PageObject {

    private final By projectSummary = By.cssSelector("img[alt='Selenium Live Project']");
    private final By title = By.cssSelector(".entry-title");

    public GuruSeleniumPracticePage(UiContext context, ConfigurationLoader config, AssertionContext assertionContext) {
        super(context, config, assertionContext);
    }

    public void pageIsLoaded() {
        switchToNewTab();
        waitForElementVisibility(projectSummary);
    }

    public void assertTitle(String text) {
        verifyElementText(title, text);
    }

    public void closePage() {
        closeCurrentTabAndReturnToOriginal();
    }

}
