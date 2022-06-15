package com.employees.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	public static RequestSpecification httpRequest;
    public static Response res;
    
    //Hard coded - Input for Get details of Single Employee & update employee
    public String empID = "10";
    
    public Logger log;
    
    @BeforeClass
    public void setup()
    {
    	log = Logger.getLogger("EmployeesRestAPI");  //added Logger
    	PropertyConfigurator.configure("Log4j.properties");
    	log.setLevel(Level.DEBUG);
    }
}
