package gmapAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import Payloads.ReUsableMethods;
import Payloads.payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {

	public static void main(String[] args) {
		
		/* Base URL */
		RestAssured.baseURI = "http://216.10.245.166";
		
		 String id="bcd227"; 
			String name = "John foe";
			
			/* To validate Book Already Exists */
			
			  String Exresponse = given().log().all().header("Content-Type", "application/Json")
				.body("{\r\n" + " \r\n" + "\"name\":\"Learn Appium Automation with Java\",\r\n"
						+ "\"isbn\":\"bcd\",\r\n" + "\"aisle\":\"227\",\r\n" + "\"author\":\"John foe\"\r\n" + "}")
				.when().post("Library/Addbook.php").then().log().all().statusCode(200)
				.body("Msg", equalTo("Book Already Exists")).body("ID", equalTo("bcd227")).extract().response()
				.asString();
			  System.out.println(Exresponse);
			
			/* Delete a book */

			String DeleteResponse = given().log().all().header("Content-Type", "application/Json")
					.body(payloads.Deletebook(id)).when().post("/Library/DeleteBook.php").then().log().all().statusCode(200)
					.extract().response().asString();

			System.out.println("Delete response is  "+DeleteResponse);
			
			/* Get the book author name */

			String Authorbooks = given().log().all().queryParam("AuthorName", "" + name + "").when()
					.get("/Library/GetBook.php").then().log().all().assertThat().statusCode(200).extract().response()
					.asString();

			System.out.println("Authors details are  "+Authorbooks);

			/* Get the book ID */

			String bookdetails = given().log().all().queryParam("ID", "" + id + "").when().get("/Library/GetBook.php")
					.then().log().all().assertThat().statusCode(200).extract().response().asString();

			System.out.println("book details are  "+bookdetails);
			
			
		/* Add a Book with Dynamic payload*/
		
		  String response = given().log().all().header("Content-Type",
		  "application/Json") .body(payloads.Addbook("aisle",
		  "227")).when().post("Library/Addbook.php").then().log().all()
		  .statusCode(200).body("Msg",
		  equalTo("successfully added")).extract().response().asString();
		  
		  JsonPath js = ReUsableMethods.rawToJson(response); String msg =
		  js.getString("Msg"); 
		  String iddetails = js.getString("ID");
		  System.out.println("Msg is " + msg + " and id is  " + iddetails);
		 
		
	}

}
