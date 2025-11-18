package hu.zolkasza.hw.steps.ui.guru;

import hu.zolkasza.hw.pages.guru.GuruMainPage;
import hu.zolkasza.hw.pages.guru.GuruSeleniumPracticePage;
import hu.zolkasza.hw.pages.guru.GuruSeleniumTutorialPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuruSteps {

    private static final Logger logger = LogManager.getLogger(GuruSteps.class);
    private static final String TITLE = "Selenium Live Project: FREE Real Time Project for Practice";
    private static final String BUTTON_TEXT = "Join now";

    private final GuruMainPage guruMainPage;
    private final GuruSeleniumPracticePage seleniumPracticePage;
    private final GuruSeleniumTutorialPage seleniumTutorialPage;

    public GuruSteps(GuruMainPage guruMainPage, GuruSeleniumPracticePage seleniumPracticePage, GuruSeleniumTutorialPage seleniumTutorialPage) {
        this.guruMainPage = guruMainPage;
        this.seleniumPracticePage = seleniumPracticePage;
        this.seleniumTutorialPage = seleniumTutorialPage;
    }

    public void openApplication() {
        logger.info("Opening Guru application");
        guruMainPage.loadPage();
        guruMainPage.pageIsLoaded();
    }

    public void openSeleniumPractice() {
        logger.info("Opening Selenium practice page");
        guruMainPage.clickIframe();
        seleniumPracticePage.pageIsLoaded();
    }

    public void verifyTitle() {
        logger.info("Verifying title");
        seleniumPracticePage.assertTitle(TITLE);
    }

    public void closeSeleniumPractice() {
        logger.info("Closing Selenium practice page");
        seleniumPracticePage.closePage();
        guruMainPage.pageIsLoaded();
    }

    public void navigateToSeleniumMenu() {
        logger.info("Navigating to Selenium menu");
        guruMainPage.hooverOverTestingMenu();
        guruMainPage.clickSeleniumLink();
        seleniumTutorialPage.pageIsLoaded();
    }

    public void verifyButton() {
        logger.info("Verifying button text");
        seleniumTutorialPage.verifyButton(BUTTON_TEXT);
    }
}
