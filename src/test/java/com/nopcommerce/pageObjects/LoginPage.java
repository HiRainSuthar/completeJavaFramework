package com.nopcommerce.pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriverWait webDriverWait;
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

    @FindBy(xpath = "//a[contains(normalize-space(),'Logout')]")
    @CacheLookup
    WebElement logoutBtn;

    @FindBy(xpath = "//strong")
    private WebElement welcomeTxt;

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

    public boolean isWelcomeTextDisplayed(){
        boolean isWelcomeTextDisplayed = false;
        try {
            isWelcomeTextDisplayed= welcomeTxt.isDisplayed();
        }
        catch (NoSuchElementException e){
            isWelcomeTextDisplayed = true;
            System.out.println(e.getMessage());
        }
        return isWelcomeTextDisplayed;
    }
}
