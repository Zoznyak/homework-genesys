package hu.zolkasza.hw.steps.ui.sauce;

import hu.zolkasza.hw.model.ui.sauce.SauceLabUser;
import hu.zolkasza.hw.pages.sauce.LoginPage;
import hu.zolkasza.hw.pages.sauce.MainPage;

public class LoginSteps {

    private final LoginPage loginPage;
    private final MainPage mainPage;

    public LoginSteps(LoginPage loginPage, MainPage mainPage) {
        this.loginPage = loginPage;
        this.mainPage = mainPage;
    }

    public void openApplication() {
        loginPage.loadPage();
        loginPage.pageIsLoaded();
    }

    public void loginWithUser(SauceLabUser user) {
        loginPage.pageIsLoaded();
        loginPage.setUsernameField(user.getUsername());
        loginPage.setPasswordField(user.getPassword());
        loginPage.clickLoginButton();
        mainPage.pageIsLoaded();
    }

    public void loginWithoutCredentials() {
        loginPage.pageIsLoaded();
        loginPage.setUsernameField("");
        loginPage.setPasswordField("");
        loginPage.clickLoginButton();
    }

    public void assertLoginError() {
        loginPage.assertLoginError();
    }

    public void assertFooterContains(String text) {
        mainPage.assertFooterTextContains(text);
    }

}
