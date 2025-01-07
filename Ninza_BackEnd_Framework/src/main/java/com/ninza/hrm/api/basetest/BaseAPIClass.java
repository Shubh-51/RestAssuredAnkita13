package com.ninza.hrm.api.basetest;



import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.ninza.hrm.api.genericutility.Databaseutility;
import com.ninza.hrm.api.genericutility.FileUtility;
import com.ninza.hrm.api.genericutility.JavaUtility;
import com.ninza.hrm.api.genericutility.JsonUtility;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseAPIClass {
	
	public FileUtility flib=new FileUtility();
	public JavaUtility jlib=new JavaUtility();
	public JsonUtility jsonlib=new JsonUtility();
	public Databaseutility dblib=new Databaseutility();
	public static RequestSpecification specObj;
	public  static ResponseSpecification specResObj;
	
	@BeforeSuite
	public void configBS() throws IOException {
		System.out.println("======ConnectionToDB========");
		//dblib.getConnectionToDataBase();
		RequestSpecBuilder bulider=new RequestSpecBuilder();
		bulider.setContentType(ContentType.JSON);
		//bulider.setAuth(basic("username", "password"));
	//bulider.addHeader("","");)
		bulider.setBaseUri(flib.readDataFromPropertiesFile("BaseURI"));
		specObj=bulider.build();
		
		ResponseSpecBuilder resBulider=new ResponseSpecBuilder();
		resBulider.expectContentType(ContentType.JSON);
		specResObj=resBulider.build();
		
	}
	@AfterSuite
	public void configAS() {
		System.out.println("======CloseDBConnection========");
		//dblib.getConnectionToDataBase();
	}


}
