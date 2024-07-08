package it.smartpaper.ReadOnSvg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReadSvg{
	protected static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) throws IOException {

		// Legge il file CSV e divide le righe in base al delimitatore ";"
		List<String[]> collect = Files.lines(Paths.get("src/main/resources/input.csv")).map(line -> line.split(";"))
				.collect(Collectors.toList());

		// Stampa i dati letti
		for (String[] array : collect) {
			logger.info("Username: " + array[0] + ", Password: " + array[1]);
		}
		
		 // Dati da scrivere su un nuovo file CSV
		 List<String[]> dataToWrite = Arrays.asList(
		            new String[]{"admin", "Carmine"},
		            new String[]{"password", "manolo"}
		        );

		        // Scrittura su file CSV
		        writeCsv("src/main/resources/output.csv", dataToWrite);
		    }

		    public static void writeCsv(String filePath, List<String[]> data) throws IOException {
		        // Costruisce le righe CSV dai dati
		        List<String> lines = data.stream()
		                                 .map(array -> String.join(";", array))
		                                 .collect(Collectors.toList());

		        // Scrive le righe su un file
		        Files.write(Paths.get(filePath), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		    }
	}

//App2