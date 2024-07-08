package it.smartpaper.RestAssuredTest;

public class JSONSuccessResponse {

// Note: The name should be exactly as the JSON node name 
// Variable SuccessCode will contain value of SuccessCode node 
	public String successCode;

// Variable Message will contain the value of Message node 
	public String message;

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSuccessCode() {
		return successCode;
	}

	public String getMessage() {
		return message;
	}
}
