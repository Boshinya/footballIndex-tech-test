package org.indexlab.pageObjects;

import org.indexlab.utilities.Driver;
import org.indexlab.utilities.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class RandomDateGen extends Driver {

    @FindBy(css=".select-wrapper .input-option")
    private WebElement dateFormatSelect;

    @FindBy(css=".option-row")
    private List<WebElement> inputFields;

    @FindBy(css=".randomjson")
    private WebElement outputDates;

    @FindBy(css=".sides-primary-button")
    private WebElement generateDatesButton;

    TestContext testContext = TestContext.getInstance();


    public void goToCodeBeautifyUrl() {
        driver.get(testContext.getProperty("base.url"));
    }


    public void enterNumberOfDatesToBeGenerated(int number) {
        WebElement element = getWebElementByTitleText("count");
        element.clear();
        element.sendKeys(String.valueOf(number));
    }

    public void selectDateTimeFormat(String format) {
        Select selectDateTime = new Select(dateFormatSelect);
        try {
            selectDateTime.selectByVisibleText(format);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("No Option found for option "+ format);
        }
    }

    public void setStartDate(String startDate) {
        WebElement element = getWebElementByTitleText("start");
        element.clear();
        element.sendKeys(startDate);
    }

    public void setEndDate(String endDate) {
        WebElement element = getWebElementByTitleText("end");
        element.clear();
        element.sendKeys(endDate);
    }


    private WebElement getWebElementByTitleText(String fieldName) {
        WebElement inputElement = inputFields.stream().filter(element -> element.getAttribute("title").contains(fieldName)).findAny().orElseThrow(()-> new RuntimeException("No Element Found with " + fieldName));
        return inputElement.findElement(By.cssSelector(".input-option"));
    }

    public List<String> getOutputDates() {
        if(outputDates.getAttribute("value").isEmpty()) {
             return new ArrayList<>();
        } else {
            return Arrays.asList(outputDates.getAttribute("value").split("\n+"));
        }
    }

    public void clickOnRandomGeneratorButton() {
        generateDatesButton.click();
    }

    public void enterCustomDateTimeFormat(String format) {
        WebElement element = getWebElementByTitleText("custom-format");
        element.clear();
        element.sendKeys(format);
    }
}
