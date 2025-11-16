package hu.zolkasza.hw.pages;

import hu.zolkasza.hw.contexts.ui.SauceContext;
import org.openqa.selenium.By;

public class LoginPage extends PageObject{

    private final By usernameField = By.cssSelector("[data-test='username']");
    private final By passwordField = By.cssSelector("[data-test='password']");
    private final By loginButton = By.cssSelector("[data-test='login-button']");

    public LoginPage(SauceContext context) {
        super(context);
    }

    public void loadPage() {
        navigateTo("https://www.saucedemo.com/inventory.html");
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

}
