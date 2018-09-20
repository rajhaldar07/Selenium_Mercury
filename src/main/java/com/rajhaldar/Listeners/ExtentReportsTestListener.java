package com.parchment.Listener;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportsTestListener implements ITestListener {

    protected ExtentReports extentReports;
    protected ExtentTest extentTest;


    @Override
    public void onTestStart(ITestResult result) {
        this.extentTest = extentReports
                .createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        this.extentTest.assignCategory(result.getTestContext().getName());

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        this.extentTest.log(Status.PASS, String.format("%s is passed", result.getMethod().getMethodName()));

    }

    @Override
    public void onTestFailure(ITestResult result) {
        this.extentTest.log(Status.FAIL, String.format("%s is failed", result.getMethod().getMethodName()));
        this.extentTest.log(extentTest.getStatus(), result.getThrowable());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        this.extentTest.log(Status.SKIP, String.format("%s is skipped", result.getMethod().getMethodName()));

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        String reportName = context.getCurrentXmlTest().getSuite().getName();
        String fileName = reportName.concat(".html");
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle(reportName.concat(" Results"));
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(reportName);
        this.extentReports = new ExtentReports();
        this.extentReports.attachReporter(htmlReporter);
        this.extentReports.setAnalysisStrategy(AnalysisStrategy.CLASS);

    }

    @Override
    public void onFinish(ITestContext context) {
        this.extentReports.flush();

    }
}
