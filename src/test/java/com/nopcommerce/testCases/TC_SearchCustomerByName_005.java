package com.nopcommerce.testCases;

import java.io.IOException;

import com.nopcommerce.utilities.ConfigProperties;
import com.nopcommerce.utilities.PropertyReader;
import org.aeonbits.owner.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.AddcustomerPage;
import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.pageObjects.SearchCustomerPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_SearchCustomerByName_005 extends BaseClass {

	@Test(groups={"master"})
	public void searchCustomerbyName() throws InterruptedException, IOException
	{
		logger.info("********* starting TC_SearchCustomerByName_005 *************");
		
		getDriver().get(configReader.baseURL());
		LoginPage lp=new LoginPage(getDriver());
/*		lp.setUserName(configReader.useremail());
		lp.setPassword(configReader.password());
		lp.clickLogin();*/
		
		//Go to search page
		AddcustomerPage addcust=new AddcustomerPage(getDriver());
		
		addcust.clickOnCustomersMenu();
		addcust.clickOnCustomersMenuItem();
		
		//Name
		SearchCustomerPage searchcust=new SearchCustomerPage(getDriver());
		searchcust.setFirstName("Victoria");
		searchcust.setLastName("Terces");
		searchcust.clickSearch();
		Thread.sleep(3000);
		
		boolean status=searchcust.searchCustomerByName("Victoria Terces");
		if(status==true)
		{
			logger.info("********* Search customer by name is passed *************");
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("********* Search customer by name is failed*************");
			//captureScreen(getDriver(),"searchCustomerbyName");
			Assert.assertTrue(false);
		}
		logger.info("********* End of TC_SearchCustomerByName_005 *************");
	}
	
}
