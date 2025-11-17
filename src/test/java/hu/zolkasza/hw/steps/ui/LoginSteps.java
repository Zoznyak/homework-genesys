package hu.zolkasza.hw.steps.ui;

import hu.zolkasza.hw.model.ui.SauceLabUser;
import hu.zolkasza.hw.pages.LoginPage;
import hu.zolkasza.hw.pages.MainPage;

import java.io.IOException;

public class LoginSteps {

    private final LoginPage loginPage;
    private final MainPage mainPage;

    public LoginSteps(LoginPage loginPage, MainPage mainPage) {
        this.loginPage = loginPage;
        this.mainPage = mainPage;
    }

    public void loginWithValidUser(SauceLabUser user) throws IOException {
        loginPage.loadPage();
        loginPage.pageIsLoaded();
        loginPage.setUsernameField(user.getUsername());
        loginPage.setPasswordField(user.getPassword());
        loginPage.clickLoginButton();
        mainPage.pageIsLoaded();
    }
}
