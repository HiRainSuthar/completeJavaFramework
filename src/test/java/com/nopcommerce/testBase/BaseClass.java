package com.nopcommerce.testBase;

import com.nopcommerce.utilities.PropertyReader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static final String USERNAME = "YOUR_USERNAME";
    public static final String AUTOMATE_KEY = "YOUR_ACCESS_KEY";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    public ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected Logger logger = LogManager.getLogger(this.getClass());

    BrowserOptionsManager browserOptionsManager;
    CloudDesiredCapabilities cloudDesiredCapabilities;
    String remoteURL = "http://localhost:4444/wd/hub";
    BrowserFactory browserFactory;
    String runmode;
    PropertyReader propertyReader;

    public WebDriver getDriver() {
        //Get driver from ThreadLocalMap
        return driver.get();
    }

    @BeforeTest
    public void constructObject(){
        browserOptionsManager = new BrowserOptionsManager();
        browserFactory = new BrowserFactory();
        cloudDesiredCapabilities = new CloudDesiredCapabilities();
        propertyReader = new PropertyReader();
    }

    @BeforeClass(alwaysRun = true)// Add alwaysRun=true
    @Parameters("browser")
    public void setup(String browser) throws IOException {
        //Load config.properties file

        runmode = propertyReader.getPropertyValue("runmode");
        //end of loading config.properties file
        if (runmode.equalsIgnoreCase("local")) {
            switch (browser) {
                case "chrome":
                    driver.set(browserFactory.getDriver("chrome"));
                    break;
                case "firefox":
                    driver.set(browserFactory.getDriver("firefox"));
                    break;
                case "edge":
                    driver.set(browserFactory.getDriver("edge"));
                    break;
            }
        }
         else if (runmode.equalsIgnoreCase("remote")) {
            switch (browser) {
                case "chrome":
                    driver.set(new RemoteWebDriver(new URL(remoteURL), browserOptionsManager.getChromeOptions()));
                    break;
                case "firefox":
                    driver.set(new RemoteWebDriver(new URL(remoteURL), browserOptionsManager.getFirefoxOptions()));
                    break;
                case "edge":
                    driver.set(new RemoteWebDriver(new URL(remoteURL), browserOptionsManager.getEdgeOptions()));
                    break;
            }
        }
        else if (runmode.equalsIgnoreCase("browserstack")) {

            switch (browser) {
                case "chrome":
                    driver.set(new RemoteWebDriver(new URL(remoteURL), cloudDesiredCapabilities.getChromeCapabilities()));
                    break;
                case "firefox":
                    driver.set(new RemoteWebDriver(new URL(remoteURL), cloudDesiredCapabilities.getFirefoxCapabilities()));
                    break;
                case "edge":
                    driver.set(new RemoteWebDriver(new URL(remoteURL), cloudDesiredCapabilities.getEdgeCapabilities()));
                    break;
            }
        }
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        getDriver().manage().window().maximize();
        logger.info("Driver invoked");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        getDriver().close();
    }


//    public void captureScreen(WebDriver driver, String tname) throws IOException {
//        TakesScreenshot ts = (TakesScreenshot) driver;
//        File source = ts.getScreenshotAs(OutputType.FILE);
//        File target = new File(System.getProperty("user.dir") + "\\screenshots\\" + tname + ".png");
//        FileUtils.copyFile(source, target);
//        logger.info("Screenshot taken");
//    }

    public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
    }

    public String randomestring() {
        String generatedString1 = RandomStringUtils.randomAlphabetic(5);
        return (generatedString1);
    }

    public int randomeNum() {
        String generatedString2 = RandomStringUtils.randomNumeric(4);
        return (Integer.parseInt(generatedString2));
    }
}
