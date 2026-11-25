package crm_ContactTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import crm_Generic_FileUtility.ExcelUtility;
import crm_Generic_FileUtility.FileUtility;
import crm_Generic_WebDriverUtility.JavaUtility;
import crm_Generic_WebDriverUtility.WebDriverUtility;

public class CreateContactWithOrgTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		/*Create object */
	    	FileUtility FU= new FileUtility();
		 ExcelUtility EU= new ExcelUtility();
		 JavaUtility JU= new JavaUtility();
		 WebDriverUtility WU = new WebDriverUtility();
		 
		 
		//PROPERTIES FILE (Login)
           String BROWSER=FU.getDataFromPropertiesFile("browser");
		   String URL=FU.getDataFromPropertiesFile("url");
		   String USERNAME=FU.getDataFromPropertiesFile("username");
		   String PASSWORD=FU.getDataFromPropertiesFile("password");
		   
			
		  //READ DATA FROM EXCEL
	        String orgName=EU.getDataFromExcel("contact", 7, 2)+JU.getRandomNumber();
			String contactLastName=EU.getDataFromExcel("contact", 7, 3);

		 //CROSS BROWSER
			    WebDriver driver=null;
				   
				   if(BROWSER.equals("chrome")) {
					   driver=new ChromeDriver();
				   }else if(BROWSER.equals("firefox")) {
					   driver=new FirefoxDriver();
				   }else if(BROWSER.equals("edge")) {
					   driver=new EdgeDriver();
				   }else {
					   driver=new ChromeDriver();
				   }
				   
		 //LOGIN
				   driver.get(URL);
				   driver.manage().window().maximize();
				   WU.waitForPageToLoad(driver);
				   driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				   driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
			       driver.findElement(By.id("submitButton")).click();
			       
		//CREATE CONTACT
			           
				
			       //1.navigate to organization
				   driver.findElement(By.linkText("Organizations")).click();
			        
			       //2.click on "create organization"
					driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

			       //3.Enter Organization name and save it 
					
					driver.findElement(By.name("accountname")).sendKeys(orgName);
				    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

				   //4.Verify the orgName 

				    String actualorg=driver.findElement(By.id("dtlview_Organization Name")).getText();
		             if(actualorg.equals(orgName)) {
						  System.out.println(orgName + "is Created==PASS");
					  }else {
						  System.out.println(orgName + "is not Created==FAIL");
					  }
	             
	         	//5.navigate to contact
		             driver.findElement(By.linkText("Contacts")).click();   
		             
		        //6.click on "create contact"
					driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
						
				//7.Enter last name
					driver.findElement(By.name("lastname")).sendKeys(contactLastName);
				
			   //8.Select OrgName from searchbox
					driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
					
					//switch to child window
					WU.switchToTabOnURL(driver, "module=Accounts");
					
				
					driver.findElement(By.id("search_txt")).sendKeys(orgName);
					driver.findElement(By.name("search")).click();
					driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click(); //Convert this 'Seena_463' static xpath into Dynamic xpath,xpath getting created in the runtime is dynamic xpath
					
						
					//switch to parent window
					WU.switchToTabOnURL(driver, "Contacts&action");

					driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();		
					
					//Verify the searched orgname is same as excelsheet orgname
					String actualorgName=driver.findElement(By.id("mouseArea_Organization Name")).getText();
					  if(actualorgName.contains(orgName)) {
						  System.out.println(orgName + "is Created==PASS");
					  }else {
						  System.out.println(orgName + "is not Created==FAIL");
					  }
					  
					//Verify the contactLastname is same as expected result
						String actcontactLN=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
						  if(actcontactLN.contains(contactLastName)) {
							  System.out.println(contactLastName + "is Created==PASS");
						  }else {
							  System.out.println(contactLastName + "is not Created==FAIL");
						  }
					
					driver.quit();
 
	}
}
