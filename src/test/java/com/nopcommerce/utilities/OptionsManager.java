package com.nopcommerce.utilities;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class OptionsManager {

    //Get Chrome Options
    public ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--headless");
        //options.addArguments("--incognito");
        return options;
        /*ChromeDriverService service = new ChromeDriverService.Builder()
                .usingAnyFreePort()
                .build();
        ChromeDriver driver = new ChromeDriver(service, options);*/
    }

    //Get Firefox Options
    public FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        FirefoxProfile profile = new FirefoxProfile();
        //Accept Untrusted Certificates
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(false);
        //Use No Proxy Settings
        profile.setPreference("network.proxy.type", 0);
        //Set Firefox profile to capabilities
        return options;
    }

    public EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        //options.UseChromium = true;
        options.addArguments("--headless");
        options.addArguments("disable-gpu");
        options.setHeadless(true);
        return options;
    }
}