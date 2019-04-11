package com.cjc.log4jDemo;

import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class MercuryTest 
{
	public WebDriver driver;
	static Logger logger=Logger.getLogger(MercuryTest.class);
	
  @Test(priority=1)
  public void openBrowser() 
	{
		PropertyConfigurator.configure("E:\\Core Java\\Log4jExample\\log4j.properties");
		System.setProperty("webdriver.chrome.driver","F:\\Selenium Setup\\Setup\\chromedriver_win32\\chromedriver.exe");
		  driver=new ChromeDriver();
		  logger.info(" chro***** Browser get opened successfully");
	}
  @Test(priority=2)
  public void enterApplUrl() 
  {
	  logger.info("Open the URL");
	  driver.get("http://newtours.demoaut.com/");
	  driver.manage().window().maximize();
	  logger.debug("New tourse Url open successfully");
  }
  @Test(priority=3)
  public void loginwithValidDetails() 
  {
	  logger.info("In loginwithValidDetails method under @Test");
//	  String act_title=driver.getTitle();
//	  String exp_title="Welcome: Mercury Tours";
//	  Assert.assertEquals(act_title, exp_title);
	  driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("diptichopade");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("chopade123");
	  driver.findElement(By.xpath("//input[@name='login']")).click();
	  System.out.println("Login Successfully");
	  
	  boolean act_flag=driver.findElement(By.xpath("//img[@src='/images/masts/mast_flightfinder.gif']")).isDisplayed();
	  boolean exp_flag=true;
	  Assert.assertEquals(act_flag, exp_flag);
	  System.out.println("Flight finder is diplayed");
	  driver.findElement(By.linkText("SIGN-OFF")).click();
  }
//  @Test(priority=4)
//  public void loginwithInvalidds()
//  {
//	  driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("dipti");
//	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("chopade123");
//	  driver.findElement(By.xpath("//input[@name='login']")).click();
//	  logger.warn("Invalid username");
//	  logger.error("Login with Invalid Details");
//	  
//	  driver.findElement(By.linkText("SIGN-OFF")).click();
//  }
  
  @AfterMethod
  public void captureScreenshot()
  {
	  logger.info("Caputure the Screenshot");
	  File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  try {
		FileUtils.copyToDirectory(src, new File("E:\\Core Java\\Log4jExample\\src\\test\\resources\\Screenshot\\rfg.jpg"));
	} catch (IOException e) {
		logger.fatal("Capture successfully");
		e.printStackTrace();
	}
	  
	  
  }
}
