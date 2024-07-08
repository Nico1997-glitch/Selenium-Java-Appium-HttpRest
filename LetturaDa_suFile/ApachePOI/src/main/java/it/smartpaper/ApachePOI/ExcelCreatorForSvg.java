package it.smartpaper.ApachePOI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelCreatorForSvg {


    public static List<String[]> readExcel(Sheet sheet) {
        List<String[]> data = new ArrayList<>();
        for (Row row : sheet) {
            List<String> rowData = new ArrayList<>();
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        rowData.add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        rowData.add(String.valueOf(cell.getNumericCellValue()));
                        break;
                    case BOOLEAN:
                        rowData.add(String.valueOf(cell.getBooleanCellValue()));
                        break;
                    case FORMULA:
                        rowData.add(cell.getCellFormula());
                        break;
                    case BLANK:
                        rowData.add("");
                        break;
                    default:
                        rowData.add("UNKNOWN");
                }
            }
            data.add(rowData.toArray(new String[0]));
        }
        return data;
    }

    public static void main(String[] args) {
        String filePath = "./output.xlsx";
        try {
            // Dati da scrivere su un nuovo file Excel
            Object[][] dataToWrite = { { "admin", "Carmine" }, { "Nicola", "giugiug" }, { "Nerino", "franco" } };

            // Scrittura su file Excel
            writeExcel(filePath, dataToWrite);

            // Legge il percorso del file Excel
            FileInputStream excelFile = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);

            // Legge i dati dall'Excel
            List<String[]> dataFromExcel = readExcel(sheet);

            // Stampa i dati letti
            for (String[] rowData : dataFromExcel) {
                System.out.println("Name: " + rowData[0]);
                System.out.println("Password: " + rowData[1]);
                System.out.println(); // Aggiunge una riga vuota tra le righe stampate
            }

            // Chiude il workbook
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeExcel(String filePath, Object[][] data) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Output");

        int rowNum = 0;
        for (Object[] rowData : data) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : rowData) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        }
        workbook.close();
    }
}
