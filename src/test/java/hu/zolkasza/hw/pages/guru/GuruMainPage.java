package hu.zolkasza.hw.pages.guru;

import hu.zolkasza.hw.contexts.ui.AssertionContext;
import hu.zolkasza.hw.contexts.ui.UiContext;
import hu.zolkasza.hw.pages.PageObject;
import hu.zolkasza.hw.tools.Configuration;
import org.openqa.selenium.By;

public class GuruMainPage extends PageObject {

    private final By page = By.id("rt-page-surround");
    private final By iframe = By.cssSelector("iframe[src='ads.html']");
    private final By iframeLink = By.cssSelector("a[href='http://www.guru99.com/live-selenium-project.html']");
    private final By testingMenu = By.cssSelector("a[href='https://www.guru99.com/software-testing.html']");
    private final By seleniumLink = By.cssSelector("a[href='https://www.guru99.com/selenium-tutorial.html']");

    private final Configuration config;

    public GuruMainPage(UiContext context, Configuration config, AssertionContext assertionContext) {
        super(context, config, assertionContext);
        this.config = config;
    }

    public void loadPage() {
        navigateTo(config.getGuruUiUrl());
    }

    public void pageIsLoaded() {
        waitForElementPresence(page);
    }

    public void clickIframe() {
        storeOriginalTabHandle();
        switchToIframe(iframe);
        click(iframeLink);
        switchToDefaultContent();
    }

    public void hooverOverTestingMenu() {
        hoverOverElement(testingMenu);
    }

    public void clickSeleniumLink() {
        click(seleniumLink);
    }

}
