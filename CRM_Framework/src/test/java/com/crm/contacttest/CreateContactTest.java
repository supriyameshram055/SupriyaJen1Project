package com.crm.contacttest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.crm.generic.baseutility.BaseClass;

public class CreateContactTest extends BaseClass {

	
	
	@Test     //without test it will not run
	public void createContact() {
		
		System.out.println("execute createContact and Verify");
	}
	
	@Test     //without test it will not run
	public void createContactWithDate() {
		
		System.out.println("execute createContact with Date and verify");
	}
	
	
	
}
