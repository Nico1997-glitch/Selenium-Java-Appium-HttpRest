package it.smartpaper.RestAssuredTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class RestAssuredTestII {

	@Test
	public void UserRegistration() {
		RestAssured.baseURI = "https://demoqa.com/Account/v1";
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("userName", "test_rest");
		requestParams.put("password", "Testrest@123");
		request.body(requestParams.toJSONString());
		Response response = request.put("/User");
		ResponseBody body = response.getBody();
		System.out.println(response.getStatusLine());
		System.out.println(body.asString());

	}

	@Test
	public void UserRegistrationSerializz() {
		RestAssured.baseURI = "https://demoqa.com";
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("UserName", "test_rest");
		requestParams.put("Password", "rest@123");
		request.body(requestParams.toJSONString());
		Response response = request.post("/Account/v1/User");
		ResponseBody body = response.getBody();
		System.out.println(response.body().asString());
		if (response.statusCode() == 200) {
			// Deserialize the Response body into JSONFailureResponse
			JSONFailureResponse responseBody = body.as(JSONFailureResponse.class);
			// Use the JSONFailureResponse class instance to Assert the values of Response.
			assertEquals("User already exists", responseBody.FaultId);
			assertEquals("FAULT_USER_ALREADY_EXISTS", responseBody.fault);
		} else if (response.statusCode() == 201) {
			// Deserialize the Response body into JSONSuccessResponse
			JSONSuccessResponse responseBody = body.as(JSONSuccessResponse.class);
			// Use the JSONSuccessResponse class instance to Assert the values of response.
			assertEquals("OPERATION_SUCCESS", responseBody.getSuccessCode());
			assertEquals("Operation completed successfully", responseBody.getMessage());
		}
	}

	@Test
	public void UserRegistrationDese() {
		RestAssured.baseURI = "https://demoqa.com";
		RequestSpecification request = RestAssured.given();
		// Creazione del corpo della richiesta JSON
		JSONObject requestParams = new JSONObject();
		requestParams.put("userName", "test_rest");
		requestParams.put("password", "rest@123");
		request.body(requestParams.toJSONString());
		// Invio della richiesta POST
		Response response = request.post("/Account/v1/User");
		// Verifica dello stato della risposta
		int statusCode = response.getStatusCode();
		if (statusCode != 200) {
			System.out.println("Request failed with status code: " + statusCode);
			return;
		}
		// Deserializzazione del corpo della risposta
		ResponseBody body = response.getBody();
		JSONSuccessResponse responseBody = body.as(JSONSuccessResponse.class);
		// Utilizzo dell'istanza JSONSuccessResponse per verificare i valori della
		// risposta
		assertEquals("OPERATION_SUCCESS", responseBody.getSuccessCode());
		assertEquals("Operation completed successfully", responseBody.getMessage());

		System.out.println("Test passed successfully.");
	}

	@Test
	public void AuthenticationBasics() {
		RestAssured.baseURI = "https://reqres.in/api/login";
		RequestSpecification request = RestAssured.given();
		Response response = request.get();
		System.out.println("Status code: " + response.getStatusCode());
		String statusMessage = response.body().asString();
		String formattedStatusMessage = statusMessage.replace(" ", "\n");
		System.out.println("Status message:\n" + formattedStatusMessage);
	}

	@Test
	public void getData() {
		RequestSpecification httpRequest = RestAssured.given().auth().basic("postman", "password");
		Response res = httpRequest.get("https://postman-echo.com/basic-auth");
		ResponseBody body = res.body();
		// Converting the response body to string
		String rbdy = body.asString();
		System.out.println("Data from the GET API- " + rbdy);
	}

	@Test
	public void getUserData() {
		RequestSpecification httpRequest = RestAssured.given().auth().preemptive().basic("postman", "password");
		Response res = httpRequest.get("https://postman-echo.com/basic-auth");
		ResponseBody body = res.body();
		String rbdy = body.asString();
		System.out.println("Data from the GET API- " + rbdy);
	}
}
