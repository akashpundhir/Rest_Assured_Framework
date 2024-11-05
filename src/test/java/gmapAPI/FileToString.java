package gmapAPI;

import org.testng.annotations.Test;

import Payloads.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileToString {  //Static Json Payload and Payload as File  string 
  @Test
  public void addplace() throws IOException {
	  
	  
	  RestAssured.baseURI="https://rahulshettyacademy.com";
	  
	  
	  String response= 
	  given().log().all()
	  		.header("Content-Type","Application/Json")
	  		.body(new String(Files.readAllBytes(Paths.get("D:\\Automation_Projects\\APITEST\\StaticJsonFile"))))
	  		
	  .when()
	  		.post("/maps/api/place/add/json")
	  		
	  .then().log().all()
	  		.statusCode(200)
	  		.statusLine("HTTP/1.1 200 OK")
	  		.body("status",equalTo("OK"))
	  		.extract().response().asString();
	  
	  
	  JsonPath js= ReUsableMethods.rawToJson(response);
	  String details= js.get("Status");
	  System.out.println(details);
	  
	  
  }
}
