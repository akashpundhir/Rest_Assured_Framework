package gmapAPI;

import org.testng.Assert;

import Payloads.payloads;
import io.restassured.path.json.JsonPath;

public class NestedJsonParse {

	public static void RetrievingJson() {
				
		
		JsonPath js=new JsonPath(payloads.MockApi());
		int sum= 0;
		
		/* Retrieving the Json Array Size and its elements using JsonPath */
		
		
		/* Print no. of courses returned by API */
		int count=js.getInt("courses.size()");
		System.out.println("No. of courses available are "+count);
		
		/* Print purchase price */
		int amount=js.getInt("dashboard.purchaseAmount");
		System.out.println("Total purchase amount "+amount);
		
		/* Print the title of the first course */
		String coursename= js.getString("courses[0].title");
		System.out.println(coursename);
		
		
		/* Print all course titles, Copies and their respective prices */
		
		for (int i = 0; i < count; i++) {
			
			System.out.println("title:"+js.getString("courses["+i+"].title"));
			System.out.println("Price:"+js.getInt("courses["+i+"].price"));
			System.out.println("Copies:"+ js.getInt("courses["+i+"].copies"));
		}
		
		/* Check how many copies sold for RPA course */
			for (int i = 0; i < count; i++) {
			
			String courseTitle= js.getString("courses["+i+"].title");
			if (courseTitle.equalsIgnoreCase("RPA"))
			{
				//Copies sold
				System.out.println("Sold RPA Copies:"+ js.getInt("courses["+i+"].copies"));
				break;
			}
		
			}
			
			
			/* Print no. of courses returned by API */
			
					
			for (int i = 0; i <count; i++) {
				
			int price=	js.getInt("courses["+i+"].price");
			int copies =js.getInt("courses["+i+"].copies");
			
			int tamount=price * copies;
			System.out.println(tamount);
				sum =sum +tamount;
				System.out.println(sum);
			
			}
			Assert.assertEquals(amount, sum);
			System.out.println("Amount of total purchase :" + amount+ " and copies sold is equal :" + sum+" ");
			
		
	}

}
