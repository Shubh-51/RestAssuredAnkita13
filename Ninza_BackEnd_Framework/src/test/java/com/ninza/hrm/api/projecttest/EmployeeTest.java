package com.ninza.hrm.api.projecttest;

import java.io.IOException;
import java.sql.SQLException;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import com.ninza.hrm.api.basetest.BaseAPIClass;
import com.ninza.hrm.api.pojoclass.EmployeePojoclass;
import com.ninza.hrm.api.pojoclass.ProjectPojo;
import com.ninza.hrm.constants.endpoint.IEndPoint;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


public class EmployeeTest extends BaseAPIClass {
	
	
	@Test
	public void addEmployeeTest() throws SQLException, IOException {
		//First create the Project
		int ranno=jlib.getRandomNumber();
		
		
		//boolean flag=false;
		
		String projectName="GHJF_"+ranno;
		String empName="user_"+ranno;
		ProjectPojo pObj=new ProjectPojo("Shubh_51",projectName, "Created", 0);
		
		given()
		.spec(specObj)
		.body(pObj)
		.post(IEndPoint.addProject)
		.then().log().all();
		
		
		//add Employee
		
		EmployeePojoclass empObj=new EmployeePojoclass("DoubleEngineer","12/12/2024","Asdf12@gmail.com", empName, 0, "9876543210", projectName,"Manager",empName);
		
		Response res=given()
		.spec(specObj)
		.body(empObj)
		.post(IEndPoint.addemployee);
		res.then()
		.assertThat().statusCode(201)
		.and().assertThat().time(Matchers.lessThan(3000L));
		
		
		res.then().spec(specResObj);
		res.then().log().all();
		
		/*//Verify Employee name in Database
		
		Driver drivereff=new Driver();
		DriverManager.registerDriver(drivereff);
		Connection conn=DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm","root@%","root");
		Statement stat=conn.createStatement();
		ResultSet set=stat.executeQuery("select * from project");
		while(set.next()) {
			if(set.getString(5).equals(empName)) {
				flag=true;
				break;
			}
		}
		conn.close();
		Assert.assertTrue(flag,"empName in DB is not verify");*/
		
		
		}
	
	
	@Test
	public void addEmployeeWithoutEmailId() throws IOException {
		//First create the Project
		
		int ranno=jlib.getRandomNumber();
		
		//boolean flag=false;
		String projectName="GHJF_"+ranno;
		String empName="user_"+ranno;
		ProjectPojo pObj=new ProjectPojo("Shubh_51",projectName, "Created", 0);
		given()
		.spec(specObj)
		.body(pObj)
		.post(IEndPoint.addProject)
		.then().log().all();
		
		
		//add Employee
		
		EmployeePojoclass empObj=new EmployeePojoclass("DoubleEngineer","12/12/2024","", empName, 0, "9876543210", projectName,"Manager",empName);
		
		Response res=given()
		.spec(specObj)
		.body(empObj)
		.post(IEndPoint.addemployee);
		
		res.then()
		.assertThat().statusCode(500)
		.and().assertThat().time(Matchers.lessThan(3000L))
		.log().all();
		
		res.then().spec(specResObj);
	}
	
		

}
