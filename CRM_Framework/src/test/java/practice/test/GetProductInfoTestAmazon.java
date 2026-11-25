package practice.test;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import crm_Generic_FileUtility.ExcelUtility;

public class GetProductInfoTestAmazon {

	@Test(dataProvider ="getData")
	public void getProductInfoTest(String brandName,String productName) {
		
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.amazon.in/");
		
		//search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
		
		//capture product info
		String x=  "//span[text()='"+productName+"']/ancestor::div[@class='a-section a-spacing-small a-spacing-top-small']/descendant::span[@class='a-price-whole']";  //iPhone 18 Pro (256 GB) - Silver --using brandname
		String price=driver.findElement(By.xpath(x)).getText();
		System.out.println(price); //1,64,900
		driver.quit();
	}
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException{
		ExcelUtility EU= new ExcelUtility();
	    int rowCount=EU.getRowCount("Product");
	   
		Object[][] objArr = new Object[rowCount][2];
		
		for(int i=0;i<rowCount;i++) {
		objArr[i][0] = EU.getDataFromExcel("Product", i+1, 0);
		objArr[i][1] = EU.getDataFromExcel("Product", i+1, 1);

		}

		return objArr;
		
		
	}
		
		
	}
