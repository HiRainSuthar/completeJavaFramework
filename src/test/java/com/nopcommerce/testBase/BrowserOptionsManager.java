package com.nopcommerce.testBase;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserOptionsManager {

    //Other args --headless, --incognito
    public ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        return options;
    }

    public FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(true);
        options.setHeadless(false);
        return options;
    }

    //Other args options.UseChromium = true;
    public EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");
        options.addArguments("disable-gpu");
        options.setHeadless(true);
        return options;
    }
}