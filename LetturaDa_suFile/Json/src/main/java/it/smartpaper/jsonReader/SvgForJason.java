package it.smartpaper.jsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class SvgForJason {

	public static void main(String[] args) {
		JSONParser parser = new JSONParser();

		try {
			// Leggo il percorso del file JSON
			Object obj = parser.parse(new FileReader(
					"C:\\Users\\ncapano\\eclipse-workspace\\Json\\src\\main\\resources\\input.json"));
			JSONObject jsonObject = (JSONObject) obj;
			String name = (String) jsonObject.get("Name");
			String password = (String) jsonObject.get("Password");
			

			// Stampo i dati letti
			System.out.println("Name: " + name);
			System.out.println("Password: " + password);

			// Dati da scrivere su un nuovo file JSON
			JSONObject dataToWrite = new JSONObject();
			dataToWrite.put("Name", "admin");
			dataToWrite.put("Password", "Carmine");

			// Scrittura su file JSON
			writeJson("C:\\Users\\ncapano\\eclipse-workspace\\Json\\src\\main\\resources\\output.json", dataToWrite);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeJson(String filePath, JSONObject data) throws IOException {
		// Scrive il contenuto JSON su un file
		try (FileWriter file = new FileWriter(filePath)) {
			file.write(data.toJSONString());
			file.flush();
		}
	}

}
