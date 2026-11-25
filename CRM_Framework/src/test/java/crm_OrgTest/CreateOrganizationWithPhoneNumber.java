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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import crm_Generic_FileUtility.ExcelUtility;
import crm_Generic_FileUtility.FileUtility;
import crm_Generic_WebDriverUtility.JavaUtility;

public class CreateOrganizationWithPhoneNumber {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		/*Create object */
		FileUtility FU= new FileUtility();
		 ExcelUtility EU= new ExcelUtility();
		 JavaUtility JU= new JavaUtility();
		 
		 
		   String BROWSER=FU.getDataFromPropertiesFile("browser");
		   String URL=FU.getDataFromPropertiesFile("url");
		   String USERNAME=FU.getDataFromPropertiesFile("username");
		   String PASSWORD=FU.getDataFromPropertiesFile("password");
		   
		  
			
		  //READ DATA FROM EXCEL
			
		  String orgName=EU.getDataFromExcel("organization", 7, 2)+JU.getRandomNumber();
		  String PhoneNumber=EU.getDataFromExcel("organization", 7, 3);

			    
			    
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
				   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
				   driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				   driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
			       driver.findElement(By.id("submitButton")).click();
			       
		//CREATE ORGANIZATION
			       
			       //navigate to organization
				   driver.findElement(By.linkText("Organizations")).click();
			        
			       //click on "create organization"
					driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

			       //Enter details and save
					driver.findElement(By.name("accountname")).sendKeys(orgName);
					driver.findElement(By.id("phone")).sendKeys(PhoneNumber);
					
					driver.findElement(By.xpath
				    		("//b[text()='Organization Information']/parent::td/parent::tr/preceding-sibling::tr//input[@title='Save [Alt+S]']")).click();
					
		 
				  
				 //Verify the phone number 
				 
				Thread.sleep(2000);
	             String actPhone=driver.findElement(By.id("dtlview_Phone")).getText();//inspect after verifying the phone number means after saving
	             
	             if(actPhone.equals(PhoneNumber))
	             {
					  System.out.println(PhoneNumber + "is verified==PASS");
				  }
	             else {
					  System.out.println(PhoneNumber + "is not verified==FAIL");
				  }
	       driver.quit();

	
	}
}
