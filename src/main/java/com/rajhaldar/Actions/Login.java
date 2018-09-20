package com.rajhaldar.Actions;

import com.rajhaldar.PageObjects.FlightFinderPage;
import com.rajhaldar.PageObjects.MercuryDemoHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Login {

    public static final Logger logger = LoggerFactory.getLogger(Login.class);

    public boolean login(WebDriver driver, String userName, String password){

        MercuryDemoHomePage mdHome = PageFactory.initElements(driver, MercuryDemoHomePage.class);
        FlightFinderPage ff = PageFactory.initElements(driver, FlightFinderPage.class);
        mdHome.userName.sendKeys(userName);
        mdHome.password.sendKeys(password);
        mdHome.loginButton.click();

        try {
            Thread.sleep(15000);
            if (ff.continueButton.isDisplayed()){
                return true;
            } else {
                logger.info("User was not able to login");
                return false;
            }

        } catch (Exception e) {
            return false;
        }

    }
}
