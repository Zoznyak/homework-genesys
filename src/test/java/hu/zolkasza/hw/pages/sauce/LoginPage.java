package hu.zolkasza.hw.pages.sauce;

import hu.zolkasza.hw.contexts.ui.AssertionContext;
import hu.zolkasza.hw.contexts.ui.SauceContext;
import hu.zolkasza.hw.pages.PageObject;
import hu.zolkasza.hw.tools.Configuration;
import org.openqa.selenium.By;

public class LoginPage extends PageObject {

    private final By usernameField = By.cssSelector("[data-test='username']");
    private final By passwordField = By.cssSelector("[data-test='password']");
    private final By loginButton = By.cssSelector("[data-test='login-button']");
    private final By error = By.cssSelector("[data-test='error']");

    private final Configuration config;

    public LoginPage(SauceContext context, Configuration config, AssertionContext assertionContext) {
        super(context, config, assertionContext);
        this.config = config;
    }

    public void loadPage() {
        navigateTo(config.getSauceUiUrl());
    }

    public void pageIsLoaded() {
        waitForElementPresence(usernameField);
        waitForElementVisibility(loginButton);
    }

    public void setUsernameField(String username) {
        sendKeys(usernameField, username, true);
    }

    public void setPasswordField(String password) {
        sendKeys(passwordField, password, false);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public void assertLoginError() {
        waitForElementVisibility(error);
        verifyElementText(error, "Epic sadface: Username is required");
    }

}
