package gmapAPI;

import org.testng.annotations.Test;

import Payloads.ReUsableMethods;
import Payloads.payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

import org.testng.annotations.DataProvider;

public class AddBookTestNg {
	
	//Parameterization of Json
	@Test(dataProvider = "booksData")
	
	public void Addbook(String isbn, String aisle) {
        // Base URL
        RestAssured.baseURI = "http://216.10.245.166";

        // Send request to add book
        String response = given().log().all().header("Content-Type","application/json")
                .body(payloads.Addbook(isbn, aisle)) //Parameterization of Json
                .when().post("Library/Addbook.php") //Addbook Payload from payload class 
                .then().log().all().statusCode(200)
                .body("Msg", equalTo("successfully added"))
                .extract().response().asString();

        // Parse JSON response to get the book ID
        JsonPath js = ReUsableMethods.rawToJson(response); //Reusable Json to string method
        String bookId = js.get("ID");
        System.out.println("Added Book ID: " + bookId);
    }
	
	@DataProvider
	public Object[][] booksData() { //Data from Array
		{
			return new Object[][] { { "Sceince", "1211" }, { "Math", "1212" }, { "History", "1213" } };

		}
	}
	
}
