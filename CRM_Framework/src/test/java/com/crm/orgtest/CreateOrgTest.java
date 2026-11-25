package com.crm.orgtest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.crm.generic.baseutility.BaseClass;

public class CreateOrgTest extends BaseClass{

	
	
	@Test     //without test it will not run
	public void createOrgTest() {
		
		System.out.println("execute createOrgTest and verify");
	}
	
	@Test     //without test it will not run
	public void createOrgWithIndustries() {
		
		System.out.println("execute create Org with Industries and verify");
	}
	
	
}
