package com.nopcommerce.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReporter {

    static ExtentReports extent;
    static String repName;

    public static ExtentReports getReportObject() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
        repName="Test-Report-"+timeStamp+".html";
        String path = System.getProperty("user.dir") + "/reports/" + repName;
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Rahul Shetty");
        return extent;
    }
}
