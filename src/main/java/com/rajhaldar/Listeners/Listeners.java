package com.rajhaldar.Listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.rajhaldar.Actions.LaunchApplication.logger;

public class Listeners implements ITestListener {
    public void onTestStart(ITestResult result) {
    }

    public void onTestSuccess(ITestResult result) {
    }

    public void onTestFailure(ITestResult result) {
        logger.info("Test case Failed " + result.getName());
    }

    public void onTestSkipped(ITestResult result) {
        logger.info("Test case Skipped " + result.getName());

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onStart(ITestContext context) {

    }

    public void onFinish(ITestContext context) {

    }
}
