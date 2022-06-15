/******************************************************
Test Name: Delete an employee record
URI: http://dummy.restapiexample.com/api/v1/delete/{id}
Request Type: DELETE
Request Payload(Body): NA
********* Validations **********
Response Payload(Body) : {"success":{"text":"successfully! deleted Records"}}
Status Code : 200
Status Line : HTTP/1.1 200 OK
Content Type : text/html; charset=UTF-8
Content Encoding : gzip
**********************************************************/

package com.employees.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employees.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC005_Delete_Employee_Record extends TestBase{
	
	@BeforeClass
	void deleteEmployee() throws InterruptedException
	{
		log.info("*********Started TC005_Delete_Employee_Record **********");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		res = httpRequest.request(Method.GET, "/employees");
				
		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = res.jsonPath();
			 
		//Capture id
		String empID=jsonPathEvaluator.get("[0].id");
		res = httpRequest.request(Method.DELETE, "/delete/"+empID); //Pass ID to delete record
		
		Thread.sleep(3000);
	}
	
	@Test
	void checkResponseBody()
	{
		String responseBody = res.getBody().asString();
		Assert.assertEquals(responseBody.contains("successfully! deleted Records"), true);
	}
	
	@Test
	void checkStatusCode()
	{
		int statusCode = res.getStatusCode(); // Gettng status code
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkStatusLine()
	{
		String statusLine = res.getStatusLine(); // Gettng status Line
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType()
	{
		String contentType = res.header("Content-Type");
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}
	
	@Test
	void checkContentEncoding()
	{
		String contentEncoding = res.header("Content-Encoding");
		Assert.assertEquals(contentEncoding, "gzip");
	}
	
	@AfterClass
	void tearDown()
	{
		log.info("*********  Finished TC005_Delete_Employee_Record **********");
	}

}
