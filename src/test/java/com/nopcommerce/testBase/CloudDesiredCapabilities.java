package com.nopcommerce.testBase;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;

public class CloudDesiredCapabilities {

    BrowserOptionsManager browserOptionsManager = new BrowserOptionsManager();

    public DesiredCapabilities getChromeCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, browserOptionsManager.getChromeOptions());
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browserVersion", "latest");
        HashMap<String, Object> browserstackOptions = new HashMap<>();
        browserstackOptions.put("os", "Windows");
        browserstackOptions.put("osVersion", "11");
        browserstackOptions.put("local", "false");
        browserstackOptions.put("seleniumVersion", "4.2.2");
        capabilities.setCapability("bstack:options", browserstackOptions);
        return capabilities;
    }

    public DesiredCapabilities getFirefoxCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, browserOptionsManager.getFirefoxOptions());
        capabilities.setCapability("browserName", "Firefox");
        capabilities.setCapability("browserVersion", "latest");
        HashMap<String, Object> browserstackOptions = new HashMap<>();
        browserstackOptions.put("os", "Windows");
        browserstackOptions.put("osVersion", "11");
        browserstackOptions.put("local", "false");
        browserstackOptions.put("seleniumVersion", "4.2.2");
        capabilities.setCapability("bstack:options", browserstackOptions);
        return capabilities;
    }

    public DesiredCapabilities getEdgeCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(EdgeOptions.CAPABILITY, browserOptionsManager.getEdgeOptions());
        capabilities.setCapability("browserName", "Edge");
        capabilities.setCapability("browserVersion", "latest");
        HashMap<String, Object> browserstackOptions = new HashMap<>();
        browserstackOptions.put("os", "Windows");
        browserstackOptions.put("osVersion", "11");
        browserstackOptions.put("local", "false");
        browserstackOptions.put("seleniumVersion", "4.2.2");
        capabilities.setCapability("bstack:options", browserstackOptions);
        return capabilities;
    }
}
