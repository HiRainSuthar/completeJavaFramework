package com.nopcommerce.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage {

    public WebDriverWait webDriverWait;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @FindBy(id = "Email")
    @CacheLookup
    WebElement emailTxtbox;

    @FindBy(id = "Password")
    @CacheLookup
    WebElement passwordTxtbox;

    @FindBy(xpath = "//div[@class='buttons']/button") //changed from  //input[@value='Log in']
    @CacheLookup
    WebElement loginBtn;

    @FindBy(css = "#navbarText ul li:nth-child(3) a") //updated from xpath = "//a[contains(normalize-space(),'Logout')]
    @CacheLookup
    WebElement logoutBtn;

    private By errorText = By.className("message-error");

    public void login(String userEmail, String userPassword){
        emailTxtbox.clear();
        emailTxtbox.sendKeys(userEmail);
        passwordTxtbox.clear();
        passwordTxtbox.sendKeys(userPassword);
        loginBtn.click();
    }

    public void clickLogout() {
        logoutBtn.click();
    }

    public String getErrorText(){
        WebElement error = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(errorText));
        return error.getText();
    }
}
