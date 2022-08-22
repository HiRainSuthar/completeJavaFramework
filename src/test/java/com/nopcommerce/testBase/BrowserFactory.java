package com.nopcommerce.testBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BrowserFactory {

    BrowserOptionsManager browserOptionsManager = new BrowserOptionsManager();

    private final Supplier<WebDriver> chromeSupplier = () -> {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(browserOptionsManager.getChromeOptions());
    };

    private final Supplier<WebDriver> firefoxSupplier = () -> {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(browserOptionsManager.getFirefoxOptions());
    };

    private final Supplier<WebDriver> edgeSupplier = () -> {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver(browserOptionsManager.getEdgeOptions());
    };

    Map<String, Supplier<WebDriver>> browserMap = new HashMap<>();

    {
        browserMap.put("chrome", chromeSupplier);
        browserMap.put("firefox", firefoxSupplier);
        browserMap.put("edge", edgeSupplier);
    }

    public WebDriver getDriver(String browser){
        return browserMap.get(browser).get();
    }

}
