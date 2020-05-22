package org.indexlab.utilities;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.logging.Logger;

public class Hooks {

    private static final Logger logger = Logger.getLogger(Hooks.class.getName());

    @Before
    public void setupScenario(Scenario scenario) {
        logger.info("##########" + scenario.getName() + "  Started" + "############");
    }

    @After
    public void afterScenario(Scenario scenario) {

        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) Driver.driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
        logger.info("###########" + scenario.getName() + " Finished with status  " + scenario.getStatus() + "##########");
    }
}
