package com.ninza.hrm.api.genericutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	/**
	 * read the data From properties File based on key
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String readDataFromPropertiesFile(String key) throws IOException {
		FileInputStream fis=new FileInputStream("./config_EnvData/configEnvdata.properties");
		Properties pObj=new Properties();
		pObj.load(fis);
		String data=pObj.getProperty(key);
		
	return data;
	}

}
