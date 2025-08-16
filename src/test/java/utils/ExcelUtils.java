package utils;


import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	public static void main(String[] args) {
		
		try {
			FileInputStream fis = new FileInputStream(ConfigManager.get("excelData") + ConfigManager.get("excelTestFile"));
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet("users");
			
			clearDataRows(sheet);
			
			FileOutputStream fos = new FileOutputStream(ConfigManager.get("excelData") + ConfigManager.get("excelTestFile"));
			workbook.write(fos);
			fos.close();
			workbook.close();
			fis.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public static void updateByColumnHeader(Sheet sheet, String columnName, String value) {
	    Row headerRow = sheet.getRow(0);
	    if (headerRow == null) {
	        throw new IllegalArgumentException("Header row (index 0) not found.");
	    }

	    int targetColumnIndex = -1;
	    int totalColumns = headerRow.getLastCellNum();

	    // Locate column index by header name
	    for (Cell cell : headerRow) {
	        if (cell.getStringCellValue().trim().equalsIgnoreCase(columnName.trim())) {
	            targetColumnIndex = cell.getColumnIndex();
	            break;
	        }
	    }

	    if (targetColumnIndex == -1) {
	        throw new IllegalArgumentException("Column '" + columnName + "' not found.");
	    }

	    int targetRowIndex = -1;
	    int lastRowNum = sheet.getLastRowNum();

	    // Look for a partially filled row where this column is blank
	    for (int i = 1; i <= lastRowNum; i++) {
	        Row row = sheet.getRow(i);
	        if (row == null) continue;

	        Cell targetCell = row.getCell(targetColumnIndex);
	        boolean isTargetCellBlank = (targetCell == null) || 
	            (targetCell.getCellType() == CellType.BLANK) || 
	            (targetCell.getCellType() == CellType.STRING && targetCell.getStringCellValue().trim().isEmpty());

	        boolean isRowPartiallyFilled = false;
	        for (int j = 0; j < totalColumns; j++) {
	            if (j == targetColumnIndex) continue;
	            Cell otherCell = row.getCell(j);
	            if (otherCell != null && otherCell.getCellType() != CellType.BLANK &&
	               !(otherCell.getCellType() == CellType.STRING && otherCell.getStringCellValue().trim().isEmpty())) {
	                isRowPartiallyFilled = true;
	                break;
	            }
	        }

	        if (isTargetCellBlank && isRowPartiallyFilled) {
	            targetRowIndex = i;
	            break;
	        }
	    }

	    // If no such row is found, append to a new row
	    if (targetRowIndex == -1) {
	        targetRowIndex = lastRowNum + 1;
	    }

	    Row targetRow = sheet.getRow(targetRowIndex);
	    if (targetRow == null) {
	        targetRow = sheet.createRow(targetRowIndex);
	    }

	    Cell cellToWrite = targetRow.getCell(targetColumnIndex);
	    if (cellToWrite == null) {
	        cellToWrite = targetRow.createCell(targetColumnIndex);
	    }

	    cellToWrite.setCellValue(value);
	}
	
	public static void clearDataRows(Sheet sheet) {
	    int lastRowNum = sheet.getLastRowNum();

	    // Start from row 1 to preserve the header at index 0
	    for (int i = lastRowNum; i >= 1; i--) {
	        Row row = sheet.getRow(i);
	        if (row != null) {
	            sheet.removeRow(row);
	        }
	    }
	}
}
