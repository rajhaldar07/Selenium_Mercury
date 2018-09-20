package com.rajhaldar;


import com.jayway.restassured.path.json.JsonPath;
import com.rajhaldar.Actions.*;
import com.rajhaldar.Utilities.Environment;
import com.rajhaldar.Utilities.TestData;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.IOException;

public class AcapulcoToFrankfurtValidateRate {

    private String url, userName, password;
    private String fType, pass, dCity, aCity;
    private String dMonth, dDay, aMonth, aDay;
    private String airline, sClass;
    WebDriver driver;

    @BeforeSuite
    @Parameters({"testDataFile"})
    private void getTestData(String testDataFile) throws IOException {

        Environment.fileName = testDataFile;
        JsonPath jsonPath = new TestData().get(testDataFile);
        this.url = jsonPath.getString("URL");
        this.password = jsonPath.getString("password");
        this.userName = jsonPath.getString("userName");
        this.fType = jsonPath.getString("AcapulcoToFrankfurt.flightType");
        this.pass = jsonPath.getString("AcapulcoToFrankfurt.passengers");
        this.dCity = jsonPath.getString("AcapulcoToFrankfurt.departingFrom");
        this.aCity = jsonPath.getString("AcapulcoToFrankfurt.arrivingIn");
        this.dMonth = jsonPath.getString("AcapulcoToFrankfurt.departingMonth");
        this.dDay = jsonPath.getString("AcapulcoToFrankfurt.departingDay");
        this.aMonth = jsonPath.getString("AcapulcoToFrankfurt.arrivingMonth");
        this.aDay = jsonPath.getString("AcapulcoToFrankfurt.arrivingDay");
        this.airline = jsonPath.getString("AcapulcoToFrankfurt.airlinePreference");
        this.sClass = jsonPath.getString("AcapulcoToFrankfurt.serviceClass");

    }

    @BeforeMethod
    @Parameters({"browser"})
    public void initializeWebDriver(String browser) {

        driver = new InitializeBrowser().initializeBrowserSettings(browser, driver, false);

    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

    @Test()
    public void ValidateRate(){
        Assert.assertTrue(new LaunchApplication().launchApp(driver, url));
        Assert.assertTrue(new Login().login(driver, userName, password));
        Assert.assertTrue(new EnterFlightDetails().EnterDetails(driver, fType, pass, dCity, aCity));
        Assert.assertTrue(new EnterFlightDetails().EnterDates(driver, dMonth, dDay, aMonth, aDay));
        Assert.assertTrue(new EnterFlightDetails().FlightPreferencesAndContinue(driver, sClass, airline));
        Assert.assertTrue(new SelectFlights().ValidateDepartureRates(driver));
    }
}
