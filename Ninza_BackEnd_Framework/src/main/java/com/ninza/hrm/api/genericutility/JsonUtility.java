package com.ninza.hrm.api.genericutility;

import java.io.IOException;
import java.util.ArrayList;

import com.jayway.jsonpath.JsonPath;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class JsonUtility {
	
	FileUtility flib=new FileUtility();
			
	/**
	 * get the jsondata from based on json complex xpath
	 * @param res
	 * @param jsonXpath
	 * @return 
	 */
	public String getDataOnJsonPath(Response res,String jsonXPath) {
		ArrayList<Object> list=JsonPath.read(res.asString(),jsonXPath);
		return list.get(0).toString();
	}
	
	/**
	 * get the xmldata from based on xml complex xpath
	 * @par
	 */
	public String getDataOnXpathPath(Response res,String xpath) {
		return res.xmlPath().getString(xpath);
	}
	
	public boolean VerifyDataOnJsonPath(Response res,String jsonXPath,String expectedData) {
		ArrayList<String> list=JsonPath.read(res.asString(),jsonXPath);
		
		boolean flag=false;
		
		for(String str:list ) {
			if(str.equals(expectedData)) {
				System.out.println(expectedData+" is available===PASS");
				flag=true;
			}
		}
		if(flag=false) {
			System.out.println(expectedData+" is available===PASS");
		}
	return flag;	
	}
	
	
	public String getAcccessToken() throws IOException {
		Response res=given()
				.formParam("client_id",flib.readDataFromPropertiesFile("ninza-client"))
				.formParam("client_secret",flib.readDataFromPropertiesFile("ClientSecret"))
				.formParam("grant_type","client_credentials")
				.when()
				.post("http:49.249.28.218:8180/auth/realms/ninza/protocol/openid-coonect/token");
		res.then().log().all();
		//capture data From the Response
		String token=res.jsonPath().get("access_token");
		return token;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
