package com.crm.basetest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import crm_Generic_FileUtility.FileUtility;
import crm_ObjectRepository_Utility.HomePage;
import crm_ObjectRepository_Utility.LoginPage;

public class BaseClass {
   FileUtility FU= new FileUtility();
   WebDriver driver=null;
   
	@BeforeSuite
	public void BS(){
		System.out.println("==== Connect to DB , REport Config======");
	}
	
	@BeforeClass
	public void configBC() throws IOException {
		System.out.println("=====Launch the browser=======");
		String BROWSER=FU.getDataFromPropertiesFile("browser");
		 
		   
		   if(BROWSER.equals("chrome")) {
			   driver=new ChromeDriver();
		   }else if(BROWSER.equals("firefox")) {
			   driver=new FirefoxDriver();
		   }else if(BROWSER.equals("edge")) {
			   driver=new EdgeDriver();
		   }else {
			   driver=new ChromeDriver();
		   }

		   
	}

	@BeforeMethod
	public void configBM() throws IOException {
		System.out.println("=Login=");
		String URL=FU.getDataFromPropertiesFile("url");
		String USERNAME=FU.getDataFromPropertiesFile("username");
		String PASSWORD=FU.getDataFromPropertiesFile("password");

		LoginPage lp=new LoginPage(driver);
		lp.loginToapp(URL,USERNAME,PASSWORD);//get this data from properties file
	}
	
	
	@AfterMethod
	public void configAfterMethod() throws InterruptedException {
		
		System.out.println("=Logout=");
		HomePage hp=new HomePage(driver);
		hp.logout();
		
	}
	
	@AfterClass
	public void configAC() {
		System.out.println("======close browser======");
		driver.quit();
	}
	@AfterSuite
	public void AS(){
		System.out.println("=====close DB , Report backup=====");
	}
}
