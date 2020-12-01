package com.nopcommerce.utilities;

import java.net.URL;

//Extent report 5.x

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext)
	{		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		repName="Test-Report-"+timeStamp+".html";
				
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);//specify location of the report
				
		sparkReporter.config().setDocumentTitle("nopCommerce Automation Report"); // Title of report
		sparkReporter.config().setReportName("nopCommerce  Functional Testing"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);
				
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "nopCommerce");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("user","pavan");
	}
	
		
	public void onTestSuccess(ITestResult result)
	{
		//test=extent.createTest(result.getTestContext().getName());
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
	}
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getName()); //test=extent.createTest(result.getTestContext().getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
			
		String screenshotPath=System.getProperty("user.dir")+"\\screenshots\\"+result.getName()+".png";
		test.addScreenCaptureFromPath(screenshotPath);// adding screen shot	 
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName()); //test=extent.createTest(result.getTestContext().getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
		
		/* try {
		URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		
		 // Create the email message
		 ImageHtmlEmail email = new ImageHtmlEmail();
		  email.setDataSourceResolver(new DataSourceUrlResolver(url));
		  email.setHostName("smtp.googlemail.com");
		  email.setSmtpPort(465);
		  email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com", "password"));
		  email.setSSLOnConnect(true);
		  email.setFrom("pavanoltraining@gmail.com");   //Sender
		  email.setSubject("Test Results");
		  email.setMsg("Please find Attached Report....");
		  email.addTo("pavankumar.busyqa@gmail.com");   //Receiver
		  email.attach(url, "extent report", "please check report...");
		  email.send();   // send the email
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
		}
	
}
