package it.smartpaper.Utilità;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonUtils {

	// Metodo per leggere un file JSON
	public static JSONObject readJSON(String filePath) {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(filePath));
			return (JSONObject) obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Metodo per scrivere su un file JSON
	public static boolean writeJSON(String filePath, JSONObject jsonObject) {
		try (FileWriter file = new FileWriter(filePath)) {
			file.write(jsonObject.toJSONString());
			file.flush();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}