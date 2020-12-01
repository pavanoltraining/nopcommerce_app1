package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.AddcustomerPage;
import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_AddCustomerTest_003 extends BaseClass
{
	@Test(groups= {"master"})
	public void addNewCustomer() throws InterruptedException, IOException
	{
		logger.info("********* starting TC_AddCustomerTest_003 *************");
		
		driver.get(configPropObj.getProperty("baseURL"));
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(configPropObj.getProperty("useremail"));
		lp.setPassword(configPropObj.getProperty("password"));
		lp.clickLogin();
		Thread.sleep(3000);
		
		logger.info("*********Adding new customer *************");
		
		AddcustomerPage addcust=new AddcustomerPage(driver);
		
		addcust.clickOnCustomersMenu();
		addcust.clickOnCustomersMenuItem();
		addcust.clickOnAddnew();
		Thread.sleep(2000);

		logger.info("***************  Providing customer details  *********** ");

		String email=randomestring()+"@gmail.com";
		
		addcust.setEmail(email);
		addcust.setPassword("test123");
		addcust.setFirstName("Pavan");
		addcust.setLastName("Kumar");
		addcust.setGender("Male");
		addcust.setDob("7/05/1985"); // Format: MM/DD/YYY
		addcust.setCompanyName("busyQA");
		
		//***** Customer Roles - 
		
		/*					Vendors	||	Administrators	||	Forum Moderators
		--------------------------------------------------------------
			Registered		Yes		|	Yes				|	Yes
		--------------------------------------------------------------
			Guests			Yes		|	Yes				|	Yes

		Note: Registered Customers cannot be Guests */
		
		Thread.sleep(2000);
		addcust.setCustomerRoles("Registered"); //or addcust.setCustomerRoles("Guests");
		Thread.sleep(2000);
		//addcust.setCustomerRoles("Vendors");//addcust.setCustomerRoles("Administrators");//addcust.setCustomerRoles("Forum Moderators");
		//Thread.sleep(2000);
		
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
					captureScreen(driver,"addNewCustomer");
					Assert.assertTrue(false);
				}
				logger.info("***************   TC_AddCustomerTest_003 Finished  *********** ");
	}
	
}