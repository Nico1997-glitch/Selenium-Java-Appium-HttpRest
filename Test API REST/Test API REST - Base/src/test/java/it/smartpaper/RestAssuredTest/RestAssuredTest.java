package it.smartpaper.RestAssuredTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class RestAssuredTest {
	protected static final Logger logger = LogManager.getLogger();

	// Questo metodo invia una richiesta GET all'endpoint dei libri e stampa lo
	// stato della risposta ricevuta.
	@Test
	public void GetBooksDetails() {
		// Specifica l'URL di base per il servizio web RESTful
		RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
		// Ottieni la RequestSpecification della richiesta da inviare al server.
		RequestSpecification httpRequest = RestAssured.given();
		// specifica il tipo di metodo (GET) e i parametri se presenti.
		// In questo caso, la richiesta non prende alcun parametro
		Response response = httpRequest.request(Method.GET, "");
		// Stampa lo stato e il corpo del messaggio della risposta ricevuta dal server
		logger.info("Status received => " + response.getStatusLine());

	}

	// Questo metodo verifica che la risposta alla richiesta GET abbia un codice di
	// stato 200, indicando
	// successo.
	@Test
	public void GetBookResponse() {
		// Specifica l'URL di base per il servizio web RESTful
		RestAssured.baseURI = "http://speakingjs.com";
		// Ottieni la RequestSpecification della richiesta da inviare al server.
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("");
		// Ottieni il codice di stato della richiesta.
		// Se la richiesta ha successo, il codice di stato sarà 200
		int statusCode = response.getStatusCode();
		// Assicura che venga restituito il codice di stato corretto.
		assertEquals(statusCode, 200, "Correct status code returned");
		logger.info("Code => " + statusCode);
	}
	
	// Questo metodo itera su tutti gli header della risposta e li stampa.
		@Test
		public void IteratingHeaders() {
			RestAssured.baseURI = "http://www.addyosmani.com/resources/essentialjsdesignpatterns/book";
			RequestSpecification httpRequest = RestAssured.given();
			Response response = httpRequest.get("");
			// Ottengo tutti gli headers e poi itera su allHeaders per stampare ciascun
			// header
			Headers allHeaders = response.headers();
			// Itera su tutti gli Headers
			for (Header header : allHeaders) {
				logger.info("Keys: " + header.getName());
				logger.info("");
				logger.info("Value: " + header.getValue());
			}
		}

//		Questo metodo accede e stampa specifici header dalla risposta, come "Content-Type", "Server" e
//		"Content-Encoding".
		@Test
		public void GetBookHeaders() {
			RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
			RequestSpecification httpRequest = RestAssured.given();
			Response response = httpRequest.get("");
			// Accedo all'header con un nome specifico.
			String contentType = response.header("Content-Type");
			System.out.println("Valore Content-Type: " + contentType);
			// Accedo all'header con un nome specifico.
			String serverType = response.header("Server");
			logger.info("Valore del server: " + serverType);
			// Accedo all'header con un nome specifico. Header = Content-Encoding
			String acceptLanguage = response.header("Content-Encoding");
			logger.info("Content-Encoding: " + acceptLanguage);
		}

//		Questo metodo verifica che specifici header, come "Content-Type" e "Server",
//		abbiano i valori attesi.
		@Test
		public void ValidateBookHeaders() {
			RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
			RequestSpecification httpRequest = RestAssured.given();
			Response response = httpRequest.get("");
			// Accedo all'header con un dato nome. Header = Content-Type
			String contentType = response.header("Content-Type");
			assertEquals(contentType, "application/json; charset=utf-8");
			logger.info("Content-Type: " + contentType);
			// Accedo all'header con un dato nome. Header = Server
			String serverType = response.header("Server");
			assertEquals(serverType, "nginx/1.17.10 (Ubuntu)");
			logger.info("Server-Type: " + serverType);
		}

	@Test
	public void WeatherMessageBody() {
		RestAssured.baseURI = "https://api.open-meteo.com/v1/forecast";
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.queryParam("latitude", "35.6895");
		httpRequest.queryParam("longitude", "139.6917");
		httpRequest.queryParam("current_weather", "true");
		Response response = httpRequest.get();
		// Recupera il corpo della risposta
		ResponseBody<?> body = response.getBody();
		// Utilizzando il metodo ResponseBody.asString(), possiamo convertire il corpo
		// in una rappresentazione stringa.
		logger.info("Il corpo della risposta è: " + body.asString());
	}

	// Questo metodo invia una richiesta GET con un parametro aggiuntivo
	// "/Hyderabad" e stampa il corpo
	// della risposta. Nota che l'endpoint non è corretto per l'URL base fornito.
	@Test
	public void WeatherMessageTest() {
		RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Activities");
		// Retrieve the body of the Response
		ResponseBody body = response.getBody();
		// To check for sub string presence get the Response body as a String.
		// Do a String.contains/Activities
		String bodyAsString = body.asString();
		assertEquals(bodyAsString.contains("Hyderabad"), false, "Response body contains Hyderabad");
		System.out.println("Titolo ricevuto dalla risposta: " + bodyAsString);
		
	}
	
	@Test
	public void VerifyCityInJsonResponse() {
		RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Activities");
		// Prima ottieni l'oggetto JsonPath dall'interfaccia Response
		JsonPath jsonPathEvaluator = response.jsonPath();
		// Supponiamo che tu voglia ottenere il titolo dell'attività con indice 2
		// Recupera il campo title della terza attività (gli indici partono da 0)
		String title = jsonPathEvaluator.getString("[1].title");
		// Stampiamo la variabile title per vedere cosa abbiamo ottenuto
		System.out.println("Titolo ricevuto dalla risposta: " + title);
		// Valida la risposta
		// Supponiamo che tu voglia verificare che il titolo della terza attività sia
		// "Activity 2"
		assertEquals(title, "Activity 2", "Titolo corretto ricevuto nella risposta");

	}
	
	@Test
	public void queryParameter() {
		//Defining the base URI
		RestAssured.baseURI= "https://bookstore.toolsqa.com/BookStore/v1";
		RequestSpecification httpRequest = RestAssured.given();
		//Passing the resource details
		Response res = httpRequest.queryParam("ISBN","9781449331818").get("/Book");
		//Retrieving the response body using getBody() method
		ResponseBody body = res.body();
		//Converting the response body to string object
		String rbdy = body.asString();
		//Creating object of JsonPath and passing the string response body as parameter
		JsonPath jpath = new JsonPath(rbdy);
		//Storing publisher name in a string variable
		String title = jpath.getString("title");
		System.out.println("The book title is - "+title);
	}
}
