package Jira;

import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import Payloads.JiraPayloads;
import Payloads.ReUsableMethods;

import static io.restassured.RestAssured.*;
import java.io.File;

import io.restassured.*;
import io.restassured.path.json.JsonPath;

public class CreateBug {

	int id;

	@Test
	public void CreateIssue() {
		Reporter.log("Create Bug started", true);;
		RestAssured.baseURI = "https://softmicronet.atlassian.net/";

		String response = given().log().all().header("Content-Type", "Application/Json")
				.header("Authorization", JiraPayloads.auth()) // Auth form payload
				.body(JiraPayloads.addissue()) // add issue data/ Payload
				.when().post("rest/api/3/issue").then().log().all().statusCode(201).extract().response().asString();

		JsonPath js = ReUsableMethods.rawToJson(response);
		System.out.println(js);
		id = js.getInt("id");
		System.out.println(id);

	}

	@AfterTest
	public void addFile() {

		// Add a file to bug newly created bug

		given().log().all().pathParam("key", id) // Passing issue id
				.header("Atlassian-Token", "no-check").header("Authorization", JiraPayloads.auth()) // Auth form payload
				.multiPart("file", new File("D:\\Automation_Projects\\APITEST\\attachment")) // passing attachment file
																								// location
				.log().all().when().post("rest/api/3/issue/{key}/attachments") // path parameter with key variable
				.then().statusCode(200);
	}

}
