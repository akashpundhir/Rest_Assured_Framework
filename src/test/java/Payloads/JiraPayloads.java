package Payloads;

public class JiraPayloads {
	
	
	
	public static String auth() {
		
		
		
		String auth="Basic Y2F0Y2hha2FzaG9ubWFpbEBnbWFpbC5jb206QVRBVFQzeEZmR0Ywa1lOWnJKREt0S0t5MjVmNmtaVWZVVmRPbnNSNW9mTXRlNHFiM3FJOTFKSkpzSkZoQVo1X1phTEhpNEN2T192QXdPX0N2b016WmJRZ19HREFQV0p0VDA2TGxONGhoQnJOcFpnVHJzNlJNdnY2a0paX1dscDFpa1BHaGNEclRiMjUxSDdSd29md01rUG5iLUExZ1VJaUllM1EwT3VpU182dFN2Um0tUF9GUkt3PTI1MjFFQTZD";
		return auth;
	}
	
	
	public static String addissue() {
		String payload="{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCURM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Bug Discriptions: 1st bug using API POST call.\",\r\n"
				+ "       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}";
		return payload;
	}
	
	

}
