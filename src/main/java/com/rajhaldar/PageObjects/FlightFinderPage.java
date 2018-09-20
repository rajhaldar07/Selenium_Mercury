package com.rajhaldar.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FlightFinderPage {

    @FindBy(xpath = "//*[@name='tripType'][@value='oneway']")
    public WebElement oneWay;

    @FindBy(xpath = "//*[@name='tripType'][@value='roundtrip']")
    public WebElement roundTrip;

    @FindBy(xpath = "//*[@name='passCount']")
    public WebElement passengerCount;

    @FindBy(xpath = "//*[@name='fromPort']")
    public WebElement departingFrom;

    @FindBy(xpath = "//*[@name='fromMonth']")
    public WebElement departingMonth;

    @FindBy(xpath = "//*[@name='fromDay']")
    public WebElement departingDay;

    @FindBy(xpath = "//*[@name='toPort']")
    public WebElement arrivingIn;

    @FindBy(xpath = "//*[@name='toMonth']")
    public WebElement arrivingMonth;

    @FindBy(xpath = "//*[@name='toDay']")
    public WebElement arrivingDay;

    @FindBy(xpath = "//*[@name='servClass'][@value='Coach']")
    public WebElement coachClass;

    @FindBy(xpath = "//*[@name='servClass'][@value='Business']")
    public WebElement businessClass;

    @FindBy(xpath = "//*[@name='servClass'][@value='First']")
    public WebElement firstClass;

    @FindBy(xpath = "//*[@name='airline']")
    public WebElement airlinePreference;

    @FindBy(xpath = "//*[@name='findFlights']")
    public WebElement continueButton;



}
