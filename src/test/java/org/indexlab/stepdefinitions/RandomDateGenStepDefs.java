package org.indexlab.stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.indexlab.pageObjects.RandomDateGen;
import org.indexlab.utilities.DateUtils;
import org.indexlab.utilities.Driver;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class RandomDateGenStepDefs {

    private RandomDateGen randomDateGen = PageFactory.initElements(Driver.driver,RandomDateGen.class);

    private DateUtils dateUtils = new DateUtils();

    private String selectedFormat = null;

    private String startDate = null;

    private String endDate = null;

    private List<String> actualDates = new ArrayList<>();

    @Given("^The user is on the codeBeautify random date generator page$")
    public void the_user_is_on_the_codebeautify_random_date_generator_page() {
        randomDateGen.goToCodeBeautifyUrl();
    }

    @When("^User enters (.*) on input field to generate random dates$")
    public void user_wants_to_generate_random_dates(int number) {
        randomDateGen.enterNumberOfDatesToBeGenerated(number);
    }

    @When("^User selects date time format as \"([^\"]*)\"$")
    public void user_selects_date_time_format_as(String format) {
        if(!format.equalsIgnoreCase("Custom date format")) {
            selectedFormat = format;
        }
        randomDateGen.selectDateTimeFormat(format);
    }

    @When("^User wants to generate the dates within the following range$")
    public void user_wants_to_generate_the_dates_within_the_following_range(List<Map<String,String>> dateRange) {
        for(Map<String,String> dates : dateRange) {
            if(dates.containsKey( "start date")) {
                startDate = dates.get("start date");
                randomDateGen.setStartDate(startDate);
            }
            if(dates.containsKey("end date")) {
                endDate = dates.get("end date");
                randomDateGen.setEndDate(endDate);
            }
        }
    }

    @Then("^User should see (\\d+) random dates generated$")
    public void user_should_see_random_dates_generated_with_the_selected_range(int expectedDateSize) {
        actualDates = randomDateGen.getOutputDates();
        assertThat("Number of Dates generated should be same as expected ",actualDates.size(),is(expectedDateSize));
    }

    @Then("^User should see date time in selected format$")
    public void user_should_see_date_time_in_selected_format() {
        assertThat("Dates generated should be in the selected Format ",dateUtils.isDateTimeGeneratedSelectedFormat(actualDates,selectedFormat),is(true));
    }


    @And("^User should see generated dates within the selected range$")
    public void userShouldSeeGeneratedDatesWithinTheSelectedRange() {
        assertThat("Dates generated should be within the range ",dateUtils.isGeneratedDateTimeWithinGivenRange(actualDates,selectedFormat,startDate,endDate),is(true));
    }

    @And("^User clicks on Generate random dates button$")
    public void userClicksOnGenerateRandomDatesButton() {
        actualDates = randomDateGen.getOutputDates();
        randomDateGen.clickOnRandomGeneratorButton();
    }

    @Then("^User should see different random dates generated$")
    public void userShouldSeeDifferentRandomDatesGenerated() {
        List<String> randomDates = randomDateGen.getOutputDates();
        assertThat("Dates generated should be different",actualDates,not(randomDates));
    }

    @And("^User enter Custom date format as \"([^\"]*)\"$")
    public void userEnterCustomDateFormatAs(String customFormat) {
        selectedFormat = customFormat;
        randomDateGen.enterCustomDateTimeFormat(customFormat);
    }
}
