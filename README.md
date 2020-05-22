# FootballIndex Automation Technical Test

# All test scenarios developed for following URL, which generates random dates
https://codebeautify.org/generate-random-date

## Notes about the Test Framework

This Test Automation Framework is built with Following tech stack:
* Java
* Cucumber
* Junit
* Maven
* Hamcrest Assertion Library

### How to run the tests

* Clone the repository locally (git clone https://github.com/Boshinya/footballIndex-tech-test.git)
* Create a Junit Runner with following VM options
* Runner Class to be selected is : org.indexlab.RunCuckesTest
    `-Dcucumber.options="--tags @@random-date-gen" -Dbrowser="chrome"`
 Some of the positive and negative scenarios from the manual test cases automated.   

### Supported Browsers
Chrome
Firefox
WebDriverManager library used to get the Drivers for the browsers dynamically.


## Test Results 
After the end of test execution, html report stored in the folder, /Users/bothi/football-index-test/target/cucumber-html-report/index.html
it would display outcome of all the scenarios.
