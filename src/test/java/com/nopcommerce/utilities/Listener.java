package com.nopcommerce.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.nopcommerce.testBase.BaseClass;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listener extends BaseClass implements ITestListener {

    ExtentTest test;
    ExtentReports extent = ExtentReporter.getReportObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public void onStart(ITestContext context) {
        context.setAttribute("driver", driver);
    }

    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
        logger.info("Starting test "+ result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
        logger.info("Test completed successfully "+ result.getMethod().getMethodName());
    }

    public void onTestFailure(ITestResult result) {
        logger.info("Test failed "+ result.getMethod().getMethodName());
        extentTest.get().fail(result.getThrowable());

        BaseClass base = (BaseClass) result.getInstance();

        try {
            extentTest.get().log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenshotAsBase64((base.getDriver()))).build());
        } catch (IOException e) {
            logger.error(e.getMessage(), e.getCause());
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        logger.info("Test Skipped "+ result.getMethod().getMethodName());

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
