package it.smartpaper.jsonReader;

import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonReader {
	public static void main(String[] args) {

		JSONParser parser = new JSONParser();// parser JSON utilizzato per l'accesso in sola lettura ai dati JSON
		
		try {
			//Leggo il percorso del file json dove il file json si trova nel sistema
			Object obj = parser.parse(new FileReader("C:\\Users\\ncapano\\eclipse-workspace\\Json\\src\\main\\resources\\ProvaJason.json"));
			JSONObject jsonObject = (JSONObject) obj;
			String name = (String) jsonObject.get("Name");
			String course = (String) jsonObject.get("Course");
			JSONArray subjects = (JSONArray) jsonObject.get("Subjects");
			System.out.println("Name: " + name);
			System.out.println("Course: " + course);
			System.out.println("Subjects:");
			Iterator iterator = subjects.iterator(); // iteratore usato per scorrere gli argomenti presenti in un file o in una raccolta
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
