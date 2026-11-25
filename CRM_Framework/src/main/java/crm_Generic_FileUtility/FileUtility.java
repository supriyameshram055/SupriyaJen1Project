package crm_Generic_FileUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {

	public String getDataFromPropertiesFile(String Key) throws IOException {
		
		FileInputStream fis=new FileInputStream("./ConfigAppData/CommonData.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String data=prop.getProperty(Key);
		
		return data;
	}
}
