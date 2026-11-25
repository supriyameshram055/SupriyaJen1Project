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

public class CreateOrganizationWithIndustries {

	public static void main(String[] args) throws IOException {
		

		/*Create object */
		FileUtility FU= new FileUtility();
		 ExcelUtility EU= new ExcelUtility();
		 JavaUtility JU= new JavaUtility();
		   
		   String BROWSER=FU.getDataFromPropertiesFile("browser");
		   String URL=FU.getDataFromPropertiesFile("url");
		   String USERNAME=FU.getDataFromPropertiesFile("username");
		   String PASSWORD=FU.getDataFromPropertiesFile("password");
		   
	
			
		  //READ DATA FROM EXCEL
			    
				String orgName=EU.getDataFromExcel("organization", 4, 2)+JU.getRandomNumber();
				String industry=EU.getDataFromExcel("organization", 4, 3);
				String type=EU.getDataFromExcel("organization", 4, 4);

			    
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
					
					//INDUSTRY
					WebElement industryDD=driver.findElement(By.name("industry"));
					Select sel1=new Select(industryDD);
					sel1.selectByVisibleText(industry); 
					
					//PRESS
					WebElement pressDD=driver.findElement(By.name("accounttype"));
					Select sel2=new Select(pressDD);
					sel2.selectByVisibleText(type);//giving the source of Excel data here to print in 
					
					driver.findElement(By.xpath
				    		("//b[text()='Organization Information']/parent::td/parent::tr/preceding-sibling::tr//input[@title='Save [Alt+S]']")).click();
					
		 
				  
				 //Verify Dropdown industry and Type info
				  
	             String industryDD1=driver.findElement(By.id("dtlview_Industry")).getText();//use span tage not name
	          
	             if(industryDD1.equals(industry))
	             {
					  System.out.println(industry + "is verified==PASS");
				  }
	             else
	             {
					  System.out.println(industry + "is not verified==FAIL");
				  }
	
	             String pressDD1=driver.findElement(By.id("dtlview_Type")).getText();//use span tage not name
	             if(pressDD1.equals(type)) {
					  System.out.println(type + "is verified==PASS");
				  }else {
					  System.out.println(type + "is not verified==FAIL");
				  }
                driver.quit();
	}
}
