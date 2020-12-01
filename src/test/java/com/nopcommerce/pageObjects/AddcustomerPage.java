package com.nopcommerce.pageObjects;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddcustomerPage {

	public WebDriver driver;
	WebElement listitem;
		
	public AddcustomerPage(WebDriver driver)
	{
		this.driver=driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //Implit wait
	}

	By lnkCustomers_menu=By.xpath("//a[@href='#']//span[contains(text(),'Customers')]");
	By lnkCustomers_menuitem=By.xpath("//span[@class='menu-item-title'][contains(text(),'Customers')]");
	
	By btnAddnew=By.xpath("//a[@class='btn bg-blue']"); //Add new
		
	By txtEmail=By.xpath("//input[@id='Email']");
	By txtPassword=By.xpath("//input[@id='Password']");
	
	By txtcustomerRoles=By.xpath("//div[10]//div[2]//div[1]//div[1]//div[1]");
	By txtcustomerRolesDefaultItem=By.xpath("//span[@title='delete']");//Register
	
	By lstitemAdministrators=By.xpath("//li[contains(text(),'Administrators')]");
	By lstitemRegistered=By.xpath("//li[contains(text(),'Registered')]");
	By lstitemGuests=By.xpath("//li[contains(text(),'Guests')]");
	By lstitemVendors=By.xpath("//li[contains(text(),'Vendors')]");
	By lstitemForumModerators=By.xpath("//li[contains(text(),'Forum Moderators')]");
	
	
	By drpmgrOfVendor=By.xpath("//*[@id='VendorId']");
	By rdMaleGender=By.id("Gender_Male");
	By rdFeMaleGender=By.id("Gender_Female");
	
	By txtFirstName=By.xpath("//input[@id='FirstName']");
	By txtLastName=By.xpath("//input[@id='LastName']");
	
	By txtDob=By.xpath("//input[@id='DateOfBirth']");
	
	By txtCompanyName=By.xpath("//input[@id='Company']");
		
	By txtAdminContent=By.xpath("//textarea[@id='AdminComment']");
	
	By btnSave=By.xpath("//button[@name='save']");
	
	By txtmsg=By.xpath("//div[@class='alert alert-success alert-dismissable']");
			
	//Action methods
		public void clickOnCustomersMenu() {
			driver.findElement(lnkCustomers_menu).click();
		}

		public void clickOnCustomersMenuItem() {
			driver.findElement(lnkCustomers_menuitem).click();
		}
		
		public void clickOnAddnew() {
			driver.findElement(btnAddnew).click();
		}
		
		public void setEmail(String email)
		{
			driver.findElement(txtEmail).sendKeys(email);
		}
		
		public void setPassword(String password)
		{
			driver.findElement(txtPassword).sendKeys(password);
		}
		
		public void setFirstName(String fname)
		{
			driver.findElement(txtFirstName).sendKeys(fname);
		}
		
		public void setLastName(String lname)
		{
			driver.findElement(txtLastName).sendKeys(lname);
		}
		
		public void setGender(String gender)
		{
			if(gender.equals("Male"))
			{
				driver.findElement(rdMaleGender).click();
			}
			else if(gender.equals("Female"))
			{
				driver.findElement(rdFeMaleGender).click();
			}
			else
			{
				driver.findElement(rdMaleGender).click();//Default
			}
			
		}
		
		public void setDob(String dob)
		{
			driver.findElement(txtDob).sendKeys(dob);
		}
		
		public void setCompanyName(String comname)
		{
			driver.findElement(txtCompanyName).sendKeys(comname);
		}
	
		public void setManagerOfVendor(String value)
		{
			Select drp=new Select(driver.findElement(drpmgrOfVendor));
			drp.selectByVisibleText(value);
		}
			
		public void setAdminContent(String content)
		{
			driver.findElement(txtAdminContent).sendKeys(content);
		}
		
		public void clickOnSave()
		{
			driver.findElement(btnSave).click();
		}
		
		public void setCustomerRoles(String role) throws InterruptedException 
		{		
			driver.findElement(txtcustomerRoles).click();
		
			if(role.equals("Guests"))
			{
			
				driver.findElement(txtcustomerRolesDefaultItem).click();
				driver.findElement(lstitemGuests).click();
			}
			else if(role.equals("Vendors"))
			{
				driver.findElement(lstitemVendors).click();
			}
			else if(role.equals("Administrators"))
			{
				driver.findElement(lstitemAdministrators).click(); 
			}
			else if(role.equals("Forum Moderators"))
			{			
				driver.findElement(lstitemForumModerators).click(); 
			}
			else
			{
				System.out.println("Default option is Registered role");
			}
		
			}
		
		
		public boolean verifyConfirmationMsg()
		{
			String msg=driver.findElement(txtmsg).getText();
			if (msg.contains("The new customer has been added successfully"))
			{
				return true;
			}
			else
			{
				return false;
			}
	
		}
		
}
