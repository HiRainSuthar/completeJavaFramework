package com.nopcommerce.testCases;

import com.nopcommerce.pageObjects.AddcustomerPage;
import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.testBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.nopcommerce.utilities.FakeDataGenerator.*;

public class TC_AddCustomerTest_003 extends BaseClass {

    @Test(groups = {"sanity", "regression", "master"})
    public void addNewCustomer() throws InterruptedException {
        logger.info("********* starting TC_AddCustomerTest_003 *************");

        logger.info("BaseURL is -------> " + configReader.baseURL());
        getDriver().get(configReader.baseURL());
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(configReader.useremail(), configReader.password());
        Thread.sleep(3000);

        logger.info("*********Adding new customer *************");

        AddcustomerPage addcust = new AddcustomerPage(getDriver());

        addcust.clickOnCustomersMenu();
        addcust.clickOnCustomersMenuItem();
        addcust.clickOnAddnew();
        Thread.sleep(2000);

        logger.info("***************  Providing customer details  *********** ");

        addcust.setEmail(getEmail());
        addcust.setPassword("test123");
        addcust.setFirstName(getFirstName());
        addcust.setLastName(getLastName());
        addcust.setGender("Male");
        addcust.setDob("7/05/1985"); // Format: MM/DD/YYY
        addcust.setCompanyName("busyQA");
        addcust.setCustomerRoles("Vendors");
        Thread.sleep(3000);
        addcust.setManagerOfVendor("Vendor 2");
        addcust.setAdminContent("This is for testing.........");
        addcust.clickOnSave();
        Thread.sleep(3000);

        // validation
        if (addcust.verifyConfirmationMsg()) {
            logger.info("***************  Customer added succesfully *********** ");
            Assert.assertTrue(true);

        } else {
            logger.error("*************** Customer Not added succesfully *********** ");
            //captureScreen(getDriver(),"addNewCustomer");
            Assert.assertTrue(false);
        }
        logger.info("***************   TC_AddCustomerTest_003 Finished  *********** ");
    }

}
