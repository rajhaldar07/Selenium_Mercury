package com.rajhaldar;

import com.jayway.restassured.path.json.JsonPath;
import com.rajhaldar.Actions.InitializeBrowser;
import com.rajhaldar.Actions.LaunchApplication;
import com.rajhaldar.Actions.Login;
import com.rajhaldar.Utilities.Environment;
import com.rajhaldar.Utilities.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

import static com.rajhaldar.Actions.LaunchApplication.logger;

public class HeadlessTest {

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
        this.fType = jsonPath.getString("LondonToParis.flightType");
        this.pass = jsonPath.getString("LondonToParis.passengers");
        this.dCity = jsonPath.getString("LondonToParis.departingFrom");
        this.aCity = jsonPath.getString("LondonToParis.arrivingIn");
        this.dMonth = jsonPath.getString("LondonToParis.departingMonth");
        this.dDay = jsonPath.getString("LondonToParis.departingDay");
        this.aMonth = jsonPath.getString("LondonToParis.arrivingMonth");
        this.aDay = jsonPath.getString("LondonToParis.arrivingDay");
        this.airline = jsonPath.getString("LondonToParis.airlinePreference");
        this.sClass = jsonPath.getString("LondonToParis.serviceClass");

    }

    @BeforeMethod
    @Parameters({"browser"})
    public void initializeWebDriver(String browser) {

        driver = new InitializeBrowser().initializeBrowserSettings(browser, driver, true);

    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void headlessTestValidation(){

        Assert.assertTrue(new LaunchApplication().launchApp(driver, url));
        Assert.assertTrue(new Login().login(driver, userName, password));
    }


}
