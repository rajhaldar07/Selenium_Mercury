package com.rajhaldar.Actions;

import com.rajhaldar.PageObjects.SelectFlightPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectFlights {

    public static final Logger logger = LoggerFactory.getLogger(SelectFlights.class);

    public boolean SelectDepartingFlight(WebDriver driver){

        SelectFlightPage sf = PageFactory.initElements(driver, SelectFlightPage.class);

        try {
            sf.bsa360_270.click();
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }
    }

    public boolean SelectReturningFlight(){
        return true;
    }

    public boolean ValidateDepartureRates(WebDriver driver){
        SelectFlightPage sf = PageFactory.initElements(driver, SelectFlightPage.class);

        try {
            if (sf.bsa360_270.isDisplayed()){
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }


    }
}
