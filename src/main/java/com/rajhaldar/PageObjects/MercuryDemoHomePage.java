package com.rajhaldar.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MercuryDemoHomePage {

    @FindBy(xpath = "//*[@name='userName']")
    public WebElement userName;

    @FindBy(xpath = "//*[@name='password']")
    public WebElement password;

    @FindBy(xpath = "//*[@name='login']")
    public WebElement loginButton;
}
