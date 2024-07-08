package it.smartpaper.ApachePOI;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelFileCreator {
	private static final String SAMPLE_XLSX_FILE_PATH = "./sample-xlsx-file.xlsx";

	public static void main(String[] args) {
		Workbook workbook = new XSSFWorkbook();

		// Crea un foglio di lavoro
		Sheet sheet = workbook.createSheet("Foglio1");

		// Crea una riga e alcune celle con dati di esempio
		for (int rowIndex = 0; rowIndex < 6; rowIndex++) {
			Row row = sheet.createRow(rowIndex);
			for (int colIndex = 0; colIndex < 6; colIndex++) {
				Cell cell = row.createCell(colIndex);
				cell.setCellValue("Dato " + rowIndex + "," + colIndex);
			}
		}

		// Scrivi il workbook su un file
		try (FileOutputStream fileOut = new FileOutputStream(SAMPLE_XLSX_FILE_PATH)) {
			workbook.write(fileOut);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Chiudi il workbook
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
