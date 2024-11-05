package gmapAPI;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Payloads.ReUsableMethods;
import Payloads.payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;


public class MainTest {
	
	String placeid;
	String newAddress ="70 Summer walk, USA";
	
	//Validate if ADD place API is working as expected	

	//given -all input details
	//when - submit the API -resource, Http method
	//then - validate the response
	//Assert -Scope, status and server details 

	
	@BeforeTest
	public void addPlace() {

//		Add new place 
		Reporter.log("Add new place", true);
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		String response =given().log().all().queryParam("Key", "qaclick123").header("Content-Type", "application/json")
		.body(payloads.AddPlace()).when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		
	    JsonPath js =new JsonPath(response); //for parsing Josn
		placeid= js.getString("place_id");
		System.out.println("Palce id is  "+placeid);
		Reporter.log(response, true);
	}
	
	@AfterTest
	public void retrievingArray() {
		Reporter.log("\nNested json started", true);
		NestedJsonParse.RetrievingJson();
	}
	
	@Test
	public void updateTheAddress() { 		//Update address to  70 Summer walk, USA
		Reporter.log("updateTheAddress start", true);	
		given().log().all().queryParam("key", "qaclick123")
		.header("Content-Type", "application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeid+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200)
		.body("msg", equalTo("Address successfully updated"));
		Reporter.log("TestNg_Reports And Logs", true);
	}
		
	@AfterTest	
	public void getAddress() {		
//		Get Place --> Get place address details and validate 
	
		String getPlaceAddress = given().log().all()
		.queryParam("key", "qaclick123").queryParam("place_id", placeid)
		.when().get("/maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200)
		.body("address", equalTo("70 Summer walk, USA"))
		.extract().response().asString();
		
		JsonPath jss= ReUsableMethods.rawToJson(getPlaceAddress);
		//JsonPath jss=new JsonPath(getPlaceAddress);
		String getaddress=jss.get("address");		
		Assert.assertEquals(newAddress, getaddress);
		System.out.println(getaddress);
		Reporter.log("Get Address complete successfuly", true);
		
	}	
	@AfterTest
	public void toltalCopiesSold() {
		Reporter.log("Total no. pf sold copies", true);
		SumValidtaion.noOfCourses();
		Reporter.log("\nTest completes successfuly", true);
		
	}
		

}
