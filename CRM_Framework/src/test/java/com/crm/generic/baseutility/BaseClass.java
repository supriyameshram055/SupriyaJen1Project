package com.crm.generic.baseutility;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class BaseClass {

	@BeforeSuite
	public void BS(){
		System.out.println("==== Connect to DB , REport Config======");
	}
	
	@BeforeClass
	public void configBC() {
		System.out.println("=====Launch browser=======");
		
	}

	@BeforeMethod
	public void configBM() {
		System.out.println("=Login=");
	}
	
	
	@AfterMethod
	public void configAfterMethod() {
		
		System.out.println("=Logout=");
	}
	
	@AfterClass
	public void configAC() {
		System.out.println("======close browser======");
		
	}
	@AfterSuite
	public void AS(){
		System.out.println("=====close DB , Report backup=====");
	}
}
