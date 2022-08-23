package com.nopcommerce.testCases;

import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.testBase.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_LoginTest_001 extends BaseClass {

    public static Logger log = LogManager.getLogger(TC_LoginTest_001.class.getName());
    private final String exp_title = "Dashboard / nopCommerce administration";
    LoginPage loginPage;

    @Test(groups = {"sanity", "regression", "master"})
    public void loginTest()  {
        log.info("*******  Starting TC_LoginTest_001 ***** ");

        getDriver().get(configReader.baseURL());
        loginPage= new LoginPage(getDriver());

        log.info("******* Providing login details ***** ");
/*        lp.setUserName(configReader.useremail());
        lp.setPassword(configReader.password());
        lp.clickLogin();*/
        loginPage.login(configReader.useremail(), configReader.password());
        Assert.assertEquals(getDriver().getTitle(), exp_title, "Page title validation failed");
//        if (exp_title.equals(act_title)) {
//            logger.info("******* Login passed ***** ");
//            Assert.assertTrue(true);
//        } else {
//            logger.error("******* Login failed ***** ");
//            captureScreen(getDriver(), "loginTest");
//            Assert.assertTrue(false);
//        }
        log.info("*******  Finished TC_LoginTest_001 ***** ");
    }
}
