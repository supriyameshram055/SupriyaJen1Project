package crm_ContactTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

import crm_Generic_FileUtility.ExcelUtility;
import crm_Generic_FileUtility.FileUtility;
import crm_Generic_WebDriverUtility.JavaUtility;

public class CreateContactWithSupportDateTest {

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
			String LastName=EU.getDataFromExcel("contact", 4, 2)+JU.getRandomNumber();

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
			       
		//CREATE CONTACT
			           
			    String startDate= JU.getSystemDateYYYYDDMM();
			    String endDate= JU.getRequiredDateYYYYDDMM(30);

			       //navigate to organization
				   driver.findElement(By.linkText("Contacts")).click();
			        
			       //click on "create organization"
					driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

			       //Enter details and save
					driver.findElement(By.name("lastname")).sendKeys(LastName);
					
					driver.findElement(By.name("support_start_date")).clear();
					driver.findElement(By.name("support_start_date")).sendKeys(startDate);


					driver.findElement(By.name("support_end_date")).clear();
					driver.findElement(By.name("support_end_date")).sendKeys(endDate);
					
					
					driver.findElement(By.xpath
				    		("//input[@title='Save [Alt+S]']")).click();
					
		 
				  
				 //Verify the Last Name  
				 
				
	             String actstartDate=driver.findElement(By.id("dtlview_Support Start Date")).getText();//inspect after verifying the last name means after saving(select the lastname and inspect)
	             if(actstartDate.equals(startDate))
	             {
					  System.out.println(startDate + "is verified==PASS");
				  }
	             else{
					  System.out.println(startDate + "is not verified==FAIL");
				  }
	             
	         	Thread.sleep(3000);
	             String actendDate=driver.findElement(By.id("dtlview_Support End Date")).getText();//inspect after verifying the last name means after saving(select the lastname and inspect)
	             if(actendDate.equals(endDate))
	             {
					  System.out.println(endDate + "is verified==PASS");
				  }
	             else{
					  System.out.println(endDate + "is not verified==FAIL");
	             }
	             driver.quit();
	            
	}
}
