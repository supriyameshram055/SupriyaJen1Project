package crm_Generic_FileUtility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {

	public String getDataFromJsonFile(String Key) throws IOException, ParseException {
		
		FileReader fileR= new FileReader("./ConfigAppData/CommonData.json");//Read data from json file
		
		JSONParser parser=new JSONParser();
		Object obj=parser.parse(fileR); //parse that json file into actual java obj
		
		//Step 2 Convert java obj into json obj using downcasting
		JSONObject map= (JSONObject)obj;
		String data=(String)map.get(Key);
		
		return data;
 
		
		
	}
}
