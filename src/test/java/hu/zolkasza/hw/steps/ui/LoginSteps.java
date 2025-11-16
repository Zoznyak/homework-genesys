package hu.zolkasza.hw.steps.ui;

import hu.zolkasza.hw.pages.LoginPage;
import hu.zolkasza.hw.pages.MainPage;

public class LoginSteps {

    private final LoginPage loginPage;
    private final MainPage mainPage;

    public LoginSteps(LoginPage loginPage, MainPage mainPage) {
        this.loginPage = loginPage;
        this.mainPage = mainPage;
    }

    public void loginWithValidUser() {
        loginPage.loadPage();
        loginPage.pageIsLoaded();
        loginPage.setUsernameField("performance_glitch_user");
        loginPage.setPasswordField("secret_sauce");
        loginPage.clickLoginButton();
        mainPage.pageIsLoaded();
    }
}
