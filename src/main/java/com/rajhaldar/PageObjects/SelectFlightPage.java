package com.rajhaldar.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SelectFlightPage {

    @FindBy(xpath = "//*[@name='outFlight'][@value='Blue Skies Airlines$360$270$5:03']")
    public WebElement bsa360_270;

    @FindBy(xpath = "//*[@name='outFlight'][@value='Blue Skies Airlines$361$271$7:10']")
    public WebElement bsa361_271;

    @FindBy(xpath = "//*[@name='outFlight'][@value='Pangea Airlines$362$274$9:17']")
    public WebElement pa362_274;

    @FindBy(xpath = "//*[@name='outFlight'][@value='Unified Airlines$363$281$11:24']")
    public WebElement us363_281;

    @FindBy(xpath = "//*[@name='outFlight'][@value='Blue Skies Airlines$630$270$12:23']")
    public WebElement bsa630;

    @FindBy(xpath = "//*[@name='outFlight'][@value='Blue Skies Airlines$631$273$14:30']")
    public WebElement bsa631;

    @FindBy(xpath = "//*[@name='outFlight'][@value='Pangea Airlines$632$282$16:37']")
    public WebElement pa632;

    @FindBy(xpath = "//*[@name='outFlight'][@value='Unified Airlines$633$303$18:44']")
    public WebElement us633;
}
