package com.rajhaldar.Actions;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LaunchApplication {

    public static final Logger logger = LoggerFactory.getLogger(LaunchApplication.class);

    public boolean launchApp(WebDriver driver, String url) {

        try {
            driver.get(url);
            Thread.sleep(3000);
            driver.manage().window().maximize();
            String title = driver.getTitle();
            if (title.contains("Welcome: Mercury Tours")) {
                return true;
            } else {
                logger.info("The expected URL did not launch !!");
                return false;
            }

        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }
    }
}
