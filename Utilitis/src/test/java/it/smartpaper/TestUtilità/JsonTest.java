package it.smartpaper.TestUtilità;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import it.smartpaper.Utilità.JsonUtils;

public class JsonTest {
	private static final String TEST_FILE_PATH = "test.json";

	@Test
	public void testWriteJSON() {
		// Crea un oggetto JSON per il test
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Name", "Test Name");
		jsonObject.put("Course", "Test Course");
		JSONArray subjects = new JSONArray();
		subjects.add("Subject1");
		subjects.add("Subject2");
		jsonObject.put("Subjects", subjects);

		// Scrivi l'oggetto JSON sul file
		boolean result = JsonUtils.writeJSON(TEST_FILE_PATH, jsonObject);
		Assert.assertTrue(result, "Failed to write JSON to file");
	}

	@Test(dependsOnMethods = { "testWriteJSON" })
	public void testReadJSON() {
		// Leggi l'oggetto JSON dal file
		JSONObject jsonObject = JsonUtils.readJSON(TEST_FILE_PATH);
		Assert.assertNotNull(jsonObject, "Failed to read JSON from file");

		// Verifica i dati letti
		Assert.assertEquals(jsonObject.get("Name"), "Test Name");
		Assert.assertEquals(jsonObject.get("Course"), "Test Course");

		JSONArray subjects = (JSONArray) jsonObject.get("Subjects");
		assertNotNull(subjects);
		assertEquals(subjects.size(), 2);
		assertTrue(subjects.contains("Subject1"));
		assertTrue(subjects.contains("Subject2"));
	}

}
