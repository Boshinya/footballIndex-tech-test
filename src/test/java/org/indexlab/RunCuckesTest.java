package org.indexlab;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.indexlab.utilities.Driver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
                 plugin = { "pretty", "html:target/cucumber-html-report",
                             "json:target/cucumber.json"},
                 tags =   {"@random-date-gen"},
                 features ="src/test/resources/features",
                 glue =   {"org.indexlab.utilities",
                          "org.indexlab.stepdefinitions"})

public class RunCuckesTest  {

    private static Driver driver = new Driver();

    @BeforeClass
    public static void setupDriver(){
        driver.initialiseDriver();
    }

    @AfterClass
    public static void destroyDriver(){
        if(Driver.driver != null){
            Driver.driver.quit();
            Driver.driver = null;
        }
    }

}
