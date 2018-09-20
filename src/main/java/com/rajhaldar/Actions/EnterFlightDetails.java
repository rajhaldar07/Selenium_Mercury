package com.rajhaldar.Actions;

import com.rajhaldar.PageObjects.FlightFinderPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnterFlightDetails {

    public static final Logger logger = LoggerFactory.getLogger(EnterFlightDetails.class);

    public boolean EnterDetails(WebDriver driver, String flightType, String passengers, String departingCity, String arrivingCity) {

        FlightFinderPage ff = PageFactory.initElements(driver, FlightFinderPage.class);

        try {
            if (flightType.contains("one")) {
                ff.oneWay.click();
            } else {
                ff.roundTrip.click();
            }

            new Select(ff.passengerCount).selectByVisibleText(passengers);
            new Select(ff.departingFrom).selectByVisibleText(departingCity);
            new Select(ff.arrivingIn).selectByVisibleText(arrivingCity);
            return true;

        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }
    }


    public boolean EnterDates(WebDriver driver, String departingMonth, String departingDay, String arrivingMonth, String arrivingDay){

        FlightFinderPage ff = PageFactory.initElements(driver, FlightFinderPage.class);

        try {
            new Select(ff.departingMonth).selectByVisibleText(departingMonth);
            new Select(ff.departingDay).selectByVisibleText(departingDay);
            new Select(ff.arrivingMonth).selectByVisibleText(arrivingMonth);
            new Select(ff.arrivingDay).selectByVisibleText(arrivingDay);
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }
    }

    public boolean FlightPreferencesAndContinue(WebDriver driver, String serviceClass, String airline){

        FlightFinderPage ff = PageFactory.initElements(driver, FlightFinderPage.class);

        try {

            if (serviceClass.equalsIgnoreCase("Business")){
                ff.businessClass.click();
            } else if (serviceClass.equalsIgnoreCase("First")){
                ff.firstClass.click();
            } else {
                ff.coachClass.click();
            }
            new Select(ff.airlinePreference).selectByVisibleText(airline);
            ff.continueButton.click();
            return true;
        } catch (Exception e){
            logger.info(e.getMessage());
            return false;
        }
    }

}
