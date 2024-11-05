package gmapAPI;

import org.testng.Assert;
import Payloads.payloads;
import io.restassured.path.json.JsonPath;

public class SumValidtaion {
	
	
	
public static void noOfCourses() {
		
	
		/* first total the the no. of sold copies amount Then compare with total purchase amount */
		
		
		/* Initiate Mock API*/
		JsonPath js=new JsonPath(payloads.MockApi());
		
		/* Print purchase price */
		int amount=js.getInt("dashboard.purchaseAmount");
		System.out.println("Total purchase amount "+amount);
		
		int sum=0;
		/* Print no. of courses returned by API */
		int count=js.getInt("courses.size()");
		System.out.println("No. of courses available are "+count);
		
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

