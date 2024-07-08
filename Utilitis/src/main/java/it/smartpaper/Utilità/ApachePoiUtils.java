package it.smartpaper.Utilità;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;

public class ApachePoiUtils {

	public static Document readSVG(String filePath) {
		Document document = null;
		try {
			File svgFile = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			document = dBuilder.parse(svgFile);
			document.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}

	public static void writeSVG(String filePath, Document document) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(filePath));
			transformer.transform(domSource, streamResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public static List<String[]> readExcel(String filePath) {
        List<String[]> data = new ArrayList<>();
        try (FileInputStream excelFile = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(excelFile)) {
            Sheet sheet = workbook.getSheetAt(0);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void writeExcel(String filePath, Object[][] data) {
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream outputStream = new FileOutputStream(filePath)) {
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
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

