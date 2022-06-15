/******************************************************
Test Name:Get all employees data
URI: http://dummy.restapiexample.com/api/v1/employees
Request Type: GET
Request Payload(Body): NA
********* Validations **********
Status Code : 200
Status Line : HTTP/1.1 200 OK
Content Type : text/html; charset=UTF-8
Content Encoding : gzip
Content Length <800
 *********************************************************/

package com.employees.testCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employees.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import junit.framework.Assert;

public class TC_001_Get_All_Employees extends TestBase{
	
	@BeforeClass
	void getAllEmployees() throws InterruptedException
	{
		log.info("********Started TC001_Get_All_Employees********");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		res = httpRequest.request(Method.GET,"/employees");
		
		Thread.sleep(2500);
	}
	
	@Test
	void checkResponseBody()
	{
		log.info("************** Checking Response Body  ******************");
		
		String resBody = res.getBody().asString();
		log.info("Response Body===>"+resBody);
		Assert.assertTrue(resBody!=null);
	}
	
	@Test
	void checkStatusCode()
	{
		log.info("************* Checking status code *************");
		
		int statusCode = res.getStatusCode();
		log.info("Status Code is ===>"+statusCode); //200
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkResponseTime()
	{
		log.info("************** Checking Response Time **************");
	    
		long responseTime = res.getTime();
		log.info("Response Time is ===>" + responseTime);
		
		if(responseTime>10000) {
			log.warn("Response Time is greater than 2000");
		}
		Assert.assertTrue(responseTime<10000);
	}
	
	@Test
	void checkstatusLine()
	{
		log.info("***********  Checking Status Line **********");
		
		String statusLine = res.getStatusLine(); // Getting status Line
		log.info("Status Line is ==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType()
	{
		log.info("***********  Checking Content Type **********");
		
		String contentType = res.header("Content-Type");
		log.info("Content type is ==>" + contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}
	
	@Test
	void checkcontentEncoding()
	{
		log.info("***********  Checking Content Encoding**********");
		
		String contentEncoding = res.header("Content-Encoding");
		log.info("Content Encoding is==>" +contentEncoding); 
		Assert.assertEquals(contentEncoding, "gzip");
		
	}
	
	@Test
	void checkContentLenght()
	{
		log.info("***********  Checking Content Lenght**********");
		
		String contentLength = res.header("Content-Length");
		log.info("Content Length is==>" +contentLength); 
		
		if(Integer.parseInt(contentLength)<100)
			log.warn("Content Length is less than 100");
		
		Assert.assertTrue(Integer.parseInt(contentLength)>100);	
	}
	
	@AfterClass
	void tearDown()
	{
		log.info("*********  Finished TC001_Get_All_Employees **********");
	}

}
