package com.nopcommerce.testCases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_LoginTest_001 extends BaseClass {

    public static Logger log = LogManager.getLogger(TC_LoginTest_001.class.getName());

    @Test(groups = {"sanity", "regression", "master"})
    public void loginTest() throws IOException {
        log.info("*******  Starting TC_LoginTest_001 ***** ");

        getDriver().get(configPropObj.getProperty("baseURL"));

        LoginPage lp = new LoginPage(getDriver());

        log.info("******* Providing login details ***** ");
        lp.setUserName(configPropObj.getProperty("useremail"));
        lp.setPassword(configPropObj.getProperty("password"));
        lp.clickLogin();

        String exp_title = "Dashboard / nopCommerce administration";
        String act_title = getDriver().getTitle();
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
