package hu.zolkasza.hw.steps.ui.guru;

import hu.zolkasza.hw.pages.guru.GuruMainPage;
import hu.zolkasza.hw.pages.guru.GuruSeleniumPracticePage;
import hu.zolkasza.hw.pages.guru.GuruSeleniumTutorialPage;
import hu.zolkasza.hw.tools.TextResourceLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuruSteps {

    private static final Logger logger = LogManager.getLogger(GuruSteps.class);

    private final GuruMainPage guruMainPage;
    private final GuruSeleniumPracticePage seleniumPracticePage;
    private final GuruSeleniumTutorialPage seleniumTutorialPage;
    private final TextResourceLoader textResourceLoader;

    public GuruSteps(GuruMainPage guruMainPage, GuruSeleniumPracticePage seleniumPracticePage, GuruSeleniumTutorialPage seleniumTutorialPage, TextResourceLoader textResourceLoader) {
        this.guruMainPage = guruMainPage;
        this.seleniumPracticePage = seleniumPracticePage;
        this.seleniumTutorialPage = seleniumTutorialPage;
        this.textResourceLoader = textResourceLoader;
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
        String title = textResourceLoader.get("selenium.practice.title");
        seleniumPracticePage.assertTitle(title);
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
        String buttonText = textResourceLoader.get("selenium.tutorial.button");
        seleniumTutorialPage.verifyButton(buttonText);
    }
}
