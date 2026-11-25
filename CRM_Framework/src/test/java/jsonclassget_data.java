import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import crm_Generic_FileUtility.JsonUtility;

public class jsonclassget_data {

	public static void main(String[] args) throws IOException, ParseException {
		
		
		
		// Create object of utility class
		JsonUtility util = new JsonUtility();

		// Get browser and URL from JSON
		String browser = util.getDataFromJsonFile("browser");
		String url = util.getDataFromJsonFile("url");
		
        System.out.println(browser);
        System.out.println(url);

		// Launch browser
		WebDriver driver = new ChromeDriver();

		// Open URL
		driver.get(url);
		
		
		
		
	}
}
