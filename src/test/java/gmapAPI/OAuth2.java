package gmapAPI;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Payloads.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class OAuth2 {

	private String token;

	@BeforeTest
	public void getToken() {

		Reporter.log("Start of Auth request token ", true);

		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		String response =

				given()

						.formParams("client_id",
								"692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")

						.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")

						.formParams("grant_type", "client_credentials")

						.formParams("scope", "trust")

						.when().log().all()

						.post("oauthapi/oauth2/resourceOwner/token").asString();

		JsonPath js = ReUsableMethods.rawToJson(response);
		token = js.get("access_token");
		System.out.println("\nValue of token is   " + token);

	}

	@Test
	public void getCourses() {
		Reporter.log("\nStart of Get Courses details ", true);
		String response = given().queryParam("access_token", token)

				.when().get("oauthapi/getCourseDetails")

				.then().log().all().body("instructor", equalTo("RahulShetty")).extract().response().asString();

		System.out.println(response);

	}

	@AfterTest
	public void afteremethod() {

		System.out.println("\nTest is completed");

	}
}