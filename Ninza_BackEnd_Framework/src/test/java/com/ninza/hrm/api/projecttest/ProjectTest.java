package com.ninza.hrm.api.projecttest;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.ninza.hrm.api.basetest.BaseAPIClass;
import com.ninza.hrm.api.pojoclass.ProjectPojo;
import com.ninza.hrm.constants.endpoint.IEndPoint;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.io.IOException;


public class ProjectTest extends BaseAPIClass {
	
	
	ProjectPojo pObj;
	
	/*@BeforeSuite
	public void configBS() throws IOException {
		System.out.println("======ConnectToDB=========");
		//dblib.getConnectionToDataBase();
		
	}*/
	
	@Test
	public void addSingleProjectWithCreateTest() throws IOException  {
		
		//generating random number
		int ranno=jlib.getRandomNumber();
		
		//reading data From PropertiesFile
		
		
		
		String projectName="POJK_"+ranno;
		String expectedMsg= "Successfully Added";
		
		pObj=new ProjectPojo("Shubh_51",projectName,"Created",0);
		
		//Verify the projectName in API Layer
		
		Response res=given().spec(specObj)
		.body(pObj)
		.when()
		.post(IEndPoint.addProject);
		
		res.then().spec(specResObj);
		res.then().assertThat().statusCode(201);
		res.then().assertThat().time(Matchers.lessThan(3000L)).log().all();
		
		String actMsg=res.jsonPath().get("msg");
		
		Assert.assertEquals(expectedMsg, actMsg);
		
		//Verify the  projectName in DB Layer
		
		/*Driver drivereff=new Driver();
		DriverManager.registerDriver(drivereff);
		Connection conn=DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm","root@%","root");
		Statement stat=conn.createStatement();
		ResultSet set=stat.executeQuery("select * from project");
		while(set.next()) {
			System.out.println(set.getString(4));
		}
		conn.close();*/
	
	}
	
	@Test(dependsOnMethods="addSingleProjectWithCreateTest")
	public void createDublicateProjectTest() throws IOException {
		
		
		
		given().spec(specObj)
		.body(pObj)
		.post(IEndPoint.addProject)
		.then()
		.assertThat()
		.spec(specResObj)
		.statusCode(409)
		.log()
		.all();
		
		
	}

	
	/*@AfterSuite
	public void configAS() throws SQLException {
		//dblib.closeDBConnection();
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
}
