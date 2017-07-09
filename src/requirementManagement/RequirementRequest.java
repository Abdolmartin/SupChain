package requirementManagement;

import java.util.Date;

import userManagement.UserProfile;

public class RequirementRequest {
	int id;
	UserProfile submittingCustomer;
	String body;
	Date date;
	boolean responseStatus;
	
	public RequirementRequest(UserProfile submittingCustomer, String body, Date date) {
		super();
		this.submittingCustomer = submittingCustomer;
		this.body = body;
		this.date = date;
		this.responseStatus = false;
	}
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public UserProfile getSubmittingCustomer() {
		return submittingCustomer;
	}



	public void setSubmittingCustomer(UserProfile submittingCustomer) {
		this.submittingCustomer = submittingCustomer;
	}



	public String getBody() {
		return body;
	}



	public void setBody(String body) {
		this.body = body;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public boolean isResponseStatus() {
		return responseStatus;
	}



	public void setResponseStatus(boolean responseStatus) {
		this.responseStatus = responseStatus;
	}



	public void recordResponse(String response){
		this.setResponseStatus(true);
	}
}
