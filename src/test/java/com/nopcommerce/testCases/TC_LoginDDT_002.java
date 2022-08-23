package com.nopcommerce.testCases;

import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.testBase.BaseClass;
import com.nopcommerce.utilities.XLUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_LoginDDT_002 extends BaseClass {

	LoginPage loginPage;

	public static Logger log = LogManager.getLogger(TC_LoginDDT_002.class.getName());

	@Test(dataProvider="LoginData",groups={"master"})
	public void loginTestDDt(String user,String pwd,String exp) {

		log.info("*******   Starting TC_LoginDDT_002 *******");

		getDriver().get(configReader.baseURL());
		loginPage = new LoginPage(getDriver());
		loginPage.login(user, pwd);

		String exp_title="Dashboard / nopCommerce administration";

		if (loginPage.isWelcomeTextDisplayed()){
			Assert.assertEquals(getDriver().getTitle(), "Your store. Login");
			log.warn("***** login failed *****");
		}
		else {
			Assert.assertEquals(getDriver().getTitle(), exp_title);
			loginPage.clickLogout();
			log.info("****** login test passed *****");
		}
/*		if(exp_title.equals(act_title))
		{
			if(exp.equals("Pass"))
			{
				log.info("****** login test passed *****");
				loginPage.clickLogout();
				//Thread.sleep(3000);
				Assert.assertTrue(true);
			}
			else if(exp.equals("Fail"))
			{
				log.info("****** login test failed *****");
				loginPage.clickLogout();
				//Thread.sleep(3000);
				Assert.assertTrue(false);
			}
			
		else if(!exp_title.equals(act_title))
			{
				if(exp.equals("Pass"))
				{
					log.warn("***** login failed *****");
					Assert.assertTrue(false);
				}
				else if(exp.equals("Fail"))
				{
					log.info("***** login passed *****");
					Assert.assertTrue(true);
				}
			}
		}*/
		log.info("*******  Finished TC_LoginDDT_002 ********");
	}
	
	@DataProvider(name="LoginData")
	public Object[] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/testData/LoginData.xlsx";
		
		int totalrows=XLUtils.getRowCount(path, "Sheet1");   //4
		int totalcols=XLUtils.getCellCount(path, "Sheet1", 1); //3
		
		String[][] logindata =new String[totalrows][totalcols];
		
		for(int i=1;i<=totalrows;i++)
		{
			for(int j=0;j<totalcols;j++)
			{
				logindata[i-1][j]= XLUtils.getCellData(path, "Sheet1",i, j);  //1,0
			}
		}
		return logindata;
	}
}










