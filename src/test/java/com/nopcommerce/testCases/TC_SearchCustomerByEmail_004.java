package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.AddcustomerPage;
import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.pageObjects.SearchCustomerPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_SearchCustomerByEmail_004 extends BaseClass {

	@Test(groups= {"regression","master"})
	public void searchCustomerbyEmail() throws InterruptedException, IOException
	{
		logger.info("********* starting TC_SearchCustomerByEmail_004 *************");
		
		driver.get(configPropObj.getProperty("baseURL"));
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(configPropObj.getProperty("useremail"));
		lp.setPassword(configPropObj.getProperty("password"));
		lp.clickLogin();
		
		//Go to search page
		AddcustomerPage addcust=new AddcustomerPage(driver);
		
		addcust.clickOnCustomersMenu();
		addcust.clickOnCustomersMenuItem();
		
		//Email ID
		SearchCustomerPage searchcust=new SearchCustomerPage(driver);
		searchcust.setEmail("victoria_victoria@nopCommerce.com");
		searchcust.clickSearch();
		Thread.sleep(3000);
		
		boolean status=searchcust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		if(status==true)
		{
			logger.info("********* Search customer by email is passed *************");
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("********* Search customer by email is failed*************");
			captureScreen(driver,"searchCustomerbyEmail");
			Assert.assertTrue(false);
		}
		logger.info("********* End of TC_SearchCustomerByEmail_004 *************");
	}
	
}
