package hu.zolkasza.hw.pages.guru;

import hu.zolkasza.hw.contexts.ui.AssertionContext;
import hu.zolkasza.hw.contexts.ui.UiContext;
import hu.zolkasza.hw.pages.PageObject;
import hu.zolkasza.hw.tools.ConfigurationLoader;
import org.openqa.selenium.By;

public class GuruSeleniumTutorialPage extends PageObject {

    private final By image = By.cssSelector("img[alt='Testiny']");
    private final By submitButton = By.cssSelector("div[id*='cbox'] button[type='submit']");

    public GuruSeleniumTutorialPage(UiContext context, ConfigurationLoader config, AssertionContext assertionContext) {
        super(context, config, assertionContext);
    }

    public void pageIsLoaded() {
        waitForElementVisibility(image);
    }

    public void verifyButton(String text) {
        verifyElementText(submitButton, text);
    }

}
