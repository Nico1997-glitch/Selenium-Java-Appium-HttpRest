package it.smartpaper.apiTests;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.openqa.selenium.devtools.v125.cachestorage.model.CachedResponse;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class E2E_Test {
    @Test
    public void RegistrationSuccessful() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com";
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        /*I have put a unique username and password as below,
        you can enter any as per your liking. */
        requestParams.put("UserName", "TOOLSQA-Test");
        requestParams.put("Password", "Test@@123");

        request.body(requestParams.toJSONString());
        Response response = request.post("/Account/v1/User");

        assertEquals(response.getStatusCode(), 201);
        // We will need the userID in the response body for our tests, please save it in a local variable
        String userID = response.getBody().jsonPath().getString("userID");
    }
}
