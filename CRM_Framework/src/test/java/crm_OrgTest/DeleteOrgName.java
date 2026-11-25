package crm_OrgTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import crm_Generic_FileUtility.ExcelUtility;
import crm_Generic_FileUtility.FileUtility;
import crm_Generic_WebDriverUtility.JavaUtility;
import crm_Generic_WebDriverUtility.WebDriverUtility;
import crm_ObjectRepository_Utility.CreateNewOrganizationPage;
import crm_ObjectRepository_Utility.HomePage;
import crm_ObjectRepository_Utility.LoginPage;
import crm_ObjectRepository_Utility.OrganizationInfoPage;
import crm_ObjectRepository_Utility.OrganizationPage;

public class DeleteOrgName {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		/*Create object */
		FileUtility FU= new FileUtility();
		 ExcelUtility EU= new ExcelUtility();
		 JavaUtility JU= new JavaUtility();
		 WebDriverUtility WU= new WebDriverUtility();
		//PROPERTIES FILE (Login)
   
		   String BROWSER=FU.getDataFromPropertiesFile("browser");
		   String URL=FU.getDataFromPropertiesFile("url");
		   String USERNAME=FU.getDataFromPropertiesFile("username");
		   String PASSWORD=FU.getDataFromPropertiesFile("password");
		   
			
		  //READ DATA FROM EXCEL
		   String orgName=EU.getDataFromExcel("organization", 10, 2)+JU.getRandomNumber();

			    
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
				   LoginPage lp = new LoginPage(driver);
				   lp.loginToapp("url","admin", "admin");  
			       
		//CREATE ORGANIZATION
			       
			       //navigate to organization
			        HomePage hp=new HomePage(driver);
			        hp.getOrgLink().click();
			        
			        //op.navigateToCampaignPage();
			        
			       //click on "create organization"
                   OrganizationPage orgpg= new OrganizationPage(driver);
                   orgpg.getCreateNewOrgBtn().click();
                   
			       //Enter details and save
                   CreateNewOrganizationPage corgpg= new CreateNewOrganizationPage(driver);
                   corgpg.createOrg(orgName); //Bussiness method (which is specific to the business and pass the orgName coming from excel sheet)
                   
                   
			      //Verify Header msg expected result
                 OrganizationInfoPage oip= new  OrganizationInfoPage(driver);
		         Thread.sleep(2000);
				 String actOrgName= oip.getHeaderMsg().getText();
		         if(actOrgName.contains(orgName)) {
		        	 System.out.println(orgName + "name is verified ==PASS");
		         }else {
		        	 System.out.println(orgName + "name is Not verified ==FAIL");
		         }
		         
		         //go back to org page
		        hp.getOrgLink().click();
		         
		         //search for organization
                orgpg.getSearchEdt().sendKeys(orgName);
                WU.select(orgpg.getSearchDD(),"Organization Name");
                orgpg.getSearchBtn().click();
                
                //In dynamic webtable select and delete org
                driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
                
	
   
			       //Logout
				 
				 hp.logout();
				 System.out.println("Logout");
				 driver.quit();
				
	
	}
}
