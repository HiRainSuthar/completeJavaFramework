package com.nopcommerce.testBase;

import com.nopcommerce.utilities.OptionsManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

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
    public Properties configPropObj;
    public Logger logger = LogManager.getLogger(this.getClass());
    OptionsManager optionsManager = new OptionsManager();


    public WebDriver getDriver() {
        //Get driver from ThreadLocalMap
        return driver.get();
    }

    @BeforeClass(alwaysRun = true)// Add alwaysRun=true
    @Parameters("browser")
    public void setup(String browser) throws IOException {
        //Load config.properties file
        configPropObj = new Properties();
        FileInputStream configfile = new FileInputStream(System.getProperty("user.dir") + "\\resources\\config.properties");
        configPropObj.load(configfile);
        //end of loading config.properties file

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver(optionsManager.getChromeOptions()));
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
        } else if (browser.contains("remote")) {
            if (browser.contains("chrome")) {
                driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), optionsManager.getChromeOptions()));
            } else if (browser.contains("firefox")) {
                driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), optionsManager.getFirefoxOptions()));
            } else if (browser.contains("edge")) {
                driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), optionsManager.getEdgeOptions()));
            }
        } else if (browser.contains("browserstack")) {
            if (browser.contains("chrome")) {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromeOptions());
                caps.setCapability("os", "Windows");
                caps.setCapability("os_version", "10");
                caps.setCapability("browser", "Chrome");
                caps.setCapability("browser_version", "80");
                caps.setCapability("name", "My First Test");
                caps.setCapability("build", "Build #1");
                caps.setCapability("project", "Sample sandbox project");

                driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps));
            } else if (browser.contains("firefox")) {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability(ChromeOptions.CAPABILITY, optionsManager.getFirefoxOptions());
                caps.setCapability("os", "Windows");
                caps.setCapability("os_version", "10");
                caps.setCapability("browser", "Firefox");
                caps.setCapability("browser_version", "80");
                caps.setCapability("name", "My First Test");
                caps.setCapability("build", "Build #1");
                caps.setCapability("project", "Sample sandbox project");

                driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps));
            } else if (browser.contains("edge")) {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability(ChromeOptions.CAPABILITY, optionsManager.getEdgeOptions());
                caps.setCapability("os", "Windows");
                caps.setCapability("os_version", "10");
                caps.setCapability("browser", "Edge");
                caps.setCapability("browser_version", "80");
                caps.setCapability("name", "My First Test");
                caps.setCapability("build", "Build #1");
                caps.setCapability("project", "Sample sandbox project");

                driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps));
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
        String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
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
