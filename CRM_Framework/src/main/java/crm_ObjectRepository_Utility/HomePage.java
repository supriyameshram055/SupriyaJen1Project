package crm_ObjectRepository_Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {// 1. Create separte java class
   
	//Object initialization
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//2. Object creation
    @FindBy(linkText="Organizations")
    private WebElement orgLink;

	@FindBy(linkText="Contacts")
    private WebElement contactlnk;
	
	@FindBy(linkText="Campaigns")
    private WebElement campaignlnk;
	

	@FindBy(linkText="More")
    private WebElement morelnk;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
    private WebElement adminImg;
	
	@FindBy(linkText="Sign Out")
    private WebElement signOutLnk;
	
	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getContactlnk() {
		return contactlnk;
	}
	public WebElement getCampaignlnk() {
		return campaignlnk;
	}

	public WebElement getMorelnk() {
		return morelnk;
	}

   public void navigateToCampaignPage() {
	   Actions act=new Actions(driver);
	   act.moveToElement(morelnk).perform();
	   campaignlnk.click();   
	   
   }

  public void logout() throws InterruptedException {
	  Thread.sleep(2000);
	  Actions act = new Actions(driver);
	  act.moveToElement(adminImg).perform();
	  signOutLnk.click();
  }
    


}
