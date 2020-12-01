package com.nopcommerce.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class BaseClass {

	public WebDriver driver;
	public Properties configPropObj;
	
	public Logger logger=LogManager.getLogger(this.getClass());  //Log4j2
	
	@BeforeClass(alwaysRun=true)// Add alwaysRun=true
	@Parameters("browser")
	public void setup(String br) throws IOException
	{
		//Load config.properties file
		configPropObj=new Properties();
		FileInputStream configfile=new FileInputStream(System.getProperty("user.dir")+"\\resources\\config.properties");
		configPropObj.load(configfile);
		// end of loading config.properties file
		
		if(br.equals("chrome"))
		{
		//ChromeOptions options=new ChromeOptions();
		//options.setHeadless(true);
			
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		}
		else if(br.equals("edge"))
		{
			//EdgeOptions options=new EdgeOptions();
			//options.setHeadless(true);
			
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else if(br.equals("firefox"))
		{
			//FirefoxOptions options=new FirefoxOptions();
			//options.setHeadless(true);
			
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // implicit wait
	}
	

	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "\\screenshots\\" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public String randomestring() {
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return (generatedString1);
	}
	
	public int randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (Integer.parseInt(generatedString2));
	}
	
	
}
