package it.smartpaper.TestUtilità;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import it.smartpaper.Utilità.ApachePoiUtils;

public class ApachePoiTest {
	private String filePath = "./test_data.xlsx";

	@BeforeClass
	public void setUp() {
		// Dati da scrivere su un nuovo file Excel
		Object[][] dataToWrite = { { "admin", "Carmine" }, { "Nicola", "giugiug" }, { "Nerino", "franco" } };

		// Scrive i dati su un file Excel
		ApachePoiUtils.writeExcel(filePath, dataToWrite);
	}

	@Test
	public void testReadExcel() {
		List<String[]> dataFromExcel = ApachePoiUtils.readExcel(filePath);

		assertEquals(dataFromExcel.size(), 3, "Il numero di righe dovrebbe essere 3");

		String[] row1 = dataFromExcel.get(0);
		assertEquals(row1[0], "admin", "La prima cella della prima riga dovrebbe essere 'admin'");
		assertEquals(row1[1], "Carmine", "La seconda cella della prima riga dovrebbe essere 'Carmine'");

		String[] row2 = dataFromExcel.get(1);
		assertEquals(row2[0], "Nicola", "La prima cella della seconda riga dovrebbe essere 'Nicola'");
		assertEquals(row2[1], "giugiug", "La seconda cella della seconda riga dovrebbe essere 'giugiug'");

		String[] row3 = dataFromExcel.get(2);
		assertEquals(row3[0], "Nerino", "La prima cella della terza riga dovrebbe essere 'Nerino'");
		assertEquals(row3[1], "franco", "La seconda cella della terza riga dovrebbe essere 'franco'");
	}

	@Test
	public void testWriteExcel() {
		// Dati da scrivere su un nuovo file Excel
		Object[][] newDataToWrite = { { "user1", "password1" }, { "user2", "password2" }, { "user3", "password3" } };

		String newFilePath = "./test_output.xlsx";
		ApachePoiUtils.writeExcel(newFilePath, newDataToWrite);

		List<String[]> newDataFromExcel = ApachePoiUtils.readExcel(newFilePath);

		assertEquals(newDataFromExcel.size(), 3, "Il numero di righe dovrebbe essere 3");

		String[] row1 = newDataFromExcel.get(0);
		assertEquals(row1[0], "user1", "La prima cella della prima riga dovrebbe essere 'user1'");
		assertEquals(row1[1], "password1", "La seconda cella della prima riga dovrebbe essere 'password1'");

		String[] row2 = newDataFromExcel.get(1);
		assertEquals(row2[0], "user2", "La prima cella della seconda riga dovrebbe essere 'user2'");
		assertEquals(row2[1], "password2", "La seconda cella della seconda riga dovrebbe essere 'password2'");

		String[] row3 = newDataFromExcel.get(2);
		assertEquals(row3[0], "user3", "La prima cella della terza riga dovrebbe essere 'user3'");
		assertEquals(row3[1], "password3", "La seconda cella della terza riga dovrebbe essere 'password3'");
	}
}
