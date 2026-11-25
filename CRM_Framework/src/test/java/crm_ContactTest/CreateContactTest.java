package crm_ContactTest;

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
import org.openqa.selenium.support.PageFactory;

import crm_Generic_FileUtility.ExcelUtility;
import crm_Generic_FileUtility.FileUtility;
import crm_Generic_WebDriverUtility.JavaUtility;
import crm_ObjectRepository_Utility.LoginPage;

public class CreateContactTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		 
		/*Create object */
		FileUtility FU= new FileUtility();
		 ExcelUtility EU= new ExcelUtility();
		 JavaUtility JU= new JavaUtility();
		 
		//PROPERTIES FILE (Login)
   
		   String BROWSER=FU.getDataFromPropertiesFile("browser");
		   String URL=FU.getDataFromPropertiesFile("url");
		   String USERNAME=FU.getDataFromPropertiesFile("username");
		   String PASSWORD=FU.getDataFromPropertiesFile("password");
			
		  //READ DATA FROM EXCEL
			String LastName=EU.getDataFromExcel("Contact", 1, 2)+JU.getRandomNumber();
			    
			    
			    
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
//				   driver.manage().window().maximize();
//				   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
 //                LoginPage lp=PageFactory.initElements(driver, LoginPage.class);
//				   lp.getUsernameEdt().sendKeys("admin");
//				   lp.getPasswordEdt().sendKeys("admin");
//				   lp.getLoginBtn().click();
				 
				   LoginPage lp = new LoginPage(driver);
				   lp.loginToapp("url","admin", "admin");  
		   
			       
		     //CREATE CONTACT
			       
			       //navigate to CONTACT
				   driver.findElement(By.linkText("Contacts")).click();
			        
			       //click on "create contact"
					driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

			       //Enter details and save
					driver.findElement(By.name("lastname")).sendKeys(LastName);
					Thread.sleep(4000);
					driver.findElement(By.xpath
				    		("//input[@title='Save [Alt+S]']")).click();
					
		 
				  
				 //Verify the Last Name  
				 
				
	             String actName=driver.findElement(By.id("dtlview_Last Name")).getText();//inspect after verifying the last name means after saving(select the lastname and inspect)
	             
	             if(actName.equals(LastName))
	             {
					  System.out.println(LastName + "is verified==PASS");
				  }else{
					  System.out.println(LastName + "is not verified==FAIL");
				  }
	           driver.quit();
	            
	}
}
