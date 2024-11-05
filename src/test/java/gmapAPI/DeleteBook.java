package gmapAPI;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Payloads.payloads;
import io.restassured.RestAssured;

public class DeleteBook {
	
	//ParameterizationJson
	
  @Test(dataProvider = "booksData")
  
	public void Deletebook(String isbn, String aisle) {
      // Base URL
      RestAssured.baseURI = "http://216.10.245.166";
      System.out.println("Deleteing books now");
      String bookID=isbn+aisle;
      System.out.println("Book id is  "+bookID);
      // Send request to delete book
      String response = given().log().all().header("Content-Type", "application/json")
              .body(payloads.Deletebook(bookID))
              .when().post("Library/DeleteBook.php")
              .then().statusCode(200)
              .body("msg", equalTo("book is successfully deleted"))
              .extract().response().asString();

      System.out.println("Delete Response: " + response);
  }
	
	
	
	@DataProvider
	public Object[][] booksData() {
		{
			return new Object[][] { { "Sceince", "1211" }, { "Math", "1212" }, { "History", "1213" } };

		}
	}
   

   }

