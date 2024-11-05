package pojo;

public class Courses {

	//Courses are inside nested Json
	
	private String courseTitle;
	private String price;
	private String webAutomation;
	private String api;
	
	
	
	
	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	private String mobile;

	public String getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(String webAutomation) {
		this.webAutomation = webAutomation;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	
}
