package db.utils;


import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import common.utils.ConfigManager;

public class ExcelUtils {
	
	public static Workbook setUpWorkbook() {
		FileInputStream fis = null;
		Workbook workbook = null;
		
		try {
			fis = new FileInputStream(ConfigManager.get("excelPath") + ConfigManager.get("excelFile"));
			workbook = new XSSFWorkbook(fis);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(fis != null) {
					fis.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return workbook;
	}
	
	
	public static void writeToWorkbook(Workbook workbook) {
		
		try {
			FileOutputStream fos = new FileOutputStream(ConfigManager.get("excelPath") + ConfigManager.get("excelFile"));
			workbook.write(fos);
			fos.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Workbook workbook) {
			try {
				if(workbook != null) {
					workbook.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}

	}
	
	
	
	public static void main(String[] args) {
		
		try {
			FileInputStream fis = new FileInputStream(ConfigManager.get("excelPath") + ConfigManager.get("excelTestFile"));
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet("users");
			
			clearDataRows(sheet);
			
			FileOutputStream fos = new FileOutputStream(ConfigManager.get("excelPath") + ConfigManager.get("excelTestFile"));
			workbook.write(fos);
			fos.close();
			workbook.close();
			fis.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public static String getLastEmail(Sheet sheet) {
		return getLastCellValueByColumnName(sheet, "email");
	}
	
	public static String getLastPassword(Sheet sheet) {
		return getLastCellValueByColumnName(sheet, "password");
	}
	
	 public static String getLastCellValueByColumnName(Sheet sheet, String columnName) {
	        if (sheet == null || columnName == null || columnName.isEmpty()) {
	            throw new IllegalArgumentException("Sheet and column name must not be null or empty.");
	        }

	        Row headerRow = sheet.getRow(0); // Assumes headers are in the first row
	        if (headerRow == null) {
	            throw new IllegalStateException("Header row is missing.");
	        }

	        int targetColIndex = -1;

	        // Find column index for given header
	        for (Cell cell : headerRow) {
	            if (cell.getCellType() == CellType.STRING &&
	                cell.getStringCellValue().trim().equalsIgnoreCase(columnName.trim())) {
	                targetColIndex = cell.getColumnIndex();
	                break;
	            }
	        }

	        if (targetColIndex == -1) {
	            throw new IllegalArgumentException("Column '" + columnName + "' not found.");
	        }

	        // Iterate backwards to find the last non-empty cell in the column
	        for (int i = sheet.getLastRowNum(); i >= 1; i--) {
	            Row row = sheet.getRow(i);
	            if (row == null) continue;

	            Cell cell = row.getCell(targetColIndex);
	            if (cell != null && cell.getCellType() != CellType.BLANK) {
	                return getCellValueAsString(cell);
	            }
	        }

	        return null; // No value found
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
	
	public static void clearLastRow(Sheet sheet) {
		int lastRowNum = sheet.getLastRowNum();
		Row row = sheet.getRow(lastRowNum);
		
		if(row != null) {
			sheet.removeRow(row);
		}
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
	
	 private static String getCellValueAsString(Cell cell) {
	        switch (cell.getCellType()) {
	            case STRING:
	                return cell.getStringCellValue();
	            case NUMERIC:
	                return String.valueOf(cell.getNumericCellValue());
	            case BOOLEAN:
	                return String.valueOf(cell.getBooleanCellValue());
	            case FORMULA:
	                return cell.getCellFormula();
	            default:
	                return "";
	        }
	    }
}
