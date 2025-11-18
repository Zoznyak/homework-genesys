package hu.zolkasza.hw.steps.ui.sauce;

import hu.zolkasza.hw.model.ui.sauce.SauceLabUser;
import hu.zolkasza.hw.pages.sauce.LoginPage;
import hu.zolkasza.hw.pages.sauce.MainPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginSteps {

    private static final Logger logger = LogManager.getLogger(LoginSteps.class);
    private final LoginPage loginPage;
    private final MainPage mainPage;

    public LoginSteps(LoginPage loginPage, MainPage mainPage) {
        this.loginPage = loginPage;
        this.mainPage = mainPage;
    }

    public void openApplication() {
        logger.info("Opening Sauce Labs application");
        loginPage.loadPage();
        loginPage.pageIsLoaded();
    }

    public void loginWithUser(SauceLabUser user) {
        logger.info("Logging in with user: {}", user.getUsername());
        loginPage.pageIsLoaded();
        loginPage.setUsernameField(user.getUsername());
        loginPage.setPasswordField(user.getPassword());
        loginPage.clickLoginButton();
        mainPage.pageIsLoaded();
    }

    public void loginWithoutCredentials() {
        logger.info("Logging in without credentials");
        loginPage.pageIsLoaded();
        loginPage.setUsernameField("");
        loginPage.setPasswordField("");
        loginPage.clickLoginButton();
    }

    public void assertLoginError() {
        logger.info("Asserting login error");
        loginPage.assertLoginError();
    }

    public void assertFooterContains(String text) {
        logger.info("Asserting footer contains: {}", text);
        mainPage.assertFooterTextContains(text);
    }

}
