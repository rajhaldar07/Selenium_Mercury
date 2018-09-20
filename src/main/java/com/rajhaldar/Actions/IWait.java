package com.rajhaldar.Actions;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class IWait {

    private static final Logger logger = LoggerFactory.getLogger(IWait.class);

    public boolean implicit_wait(WebDriver driver, long time) {
        try {
            driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean explicit_wait(WebDriver driver, WebElement el, int timeInSeconds) {
        try {
            new WebDriverWait(driver, timeInSeconds).ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOf(el));
        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean explicit_wait(WebDriver driver, WebElement el) {
        return explicit_wait(driver, el, 60);
    }

    public boolean explicit_wait(WebDriver driver, List<WebElement> el, int timeInSeconds) {
        try {
            new WebDriverWait(driver, timeInSeconds).ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOfAllElements(el));
        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean explicit_wait(WebDriver driver, List<WebElement> el) {
        return explicit_wait(driver, el, 60);
    }

}
