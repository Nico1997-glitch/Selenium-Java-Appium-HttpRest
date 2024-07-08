package it.smartpaper.ApachePOITest;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class ExcelReaderTest {

	private static final Logger logger = LoggerFactory.getLogger(ExcelReaderTest.class);
	private static final String SAMPLE_XLSX_FILE_PATH = "./sample-xlsx-file.xlsx";

	@Test
	public void testExcelReader() throws IOException, InvalidFormatException {
		// Crea il file di lavoro
		Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
		assertNotNull("Il workbook non dovrebbe essere nullo", workbook);

		// Recupera i fogli
		workbook.forEach(sheet -> {
			logger.info("=> {}", sheet.getSheetName());
		});

		// Recupera righe e colonne
		Sheet sheet = workbook.getSheetAt(0);
		assertNotNull("Il foglio non dovrebbe essere nullo", sheet);

		DataFormatter dataFormatter = new DataFormatter();

		sheet.forEach(row -> {
			row.forEach(cell -> {
				String cellValue = dataFormatter.formatCellValue(cell);
				logger.info("{}", cellValue);
			});
			logger.info("\n");
		});

		// Chiudi il foglio di lavoro
		workbook.close();
	}
}

