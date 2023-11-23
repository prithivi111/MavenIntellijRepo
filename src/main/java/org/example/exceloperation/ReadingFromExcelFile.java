package org.example.exceloperation;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class ReadingFromExcelFile {
    public static void main(String[] args) {
        try {
            //Read the content of the excel --> convert into binary --> convert that binary to java object
            //Read excel (.xlsx / .xls) file from specified location
            File file = new File("C:/PracticeJava/students.xlsx");
            FileInputStream fileInputStream = new FileInputStream(file);
            System.out.println(fileInputStream);

            //Create Workbook instance to capture the data from excel
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            int noOfSheet = workbook.getNumberOfSheets();
            System.out.println("Number of Sheet : "+noOfSheet);

            //Read the sheet
            for(int i = 0; i<noOfSheet; i++) {
                XSSFSheet xssfSheet = workbook.getSheetAt(i);
                //Read the rows one by one
                Iterator<Row>  rowIterator = xssfSheet.iterator(); //equivalent to for loop
                while(rowIterator.hasNext()) {  //if i have next row to iterate
                    Row row = rowIterator.next();// next() method is giving the entire content of the row.
                    //System.out.println("Row Number Details ::" + row.getRowNum()); //It will display in which row we are and display.

                    //fetch Cell/column one by one
                    Iterator<Cell> cellIterator = row.cellIterator();

                    while(cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();

                       // System.out.println("Cell number details::" + cell.getColumnIndex()); //THis is giving cell no details.

                        switch (cell.getCellType()) {
                            case STRING:
                                System.out.print(cell.getStringCellValue() + "  ");
                                break;
                            case NUMERIC:
                                System.out.print(cell.getNumericCellValue() + "  ");
                                break;
                            case BOOLEAN:
                                System.out.print(cell.getBooleanCellValue() + "  ");
                                break;
                        }

                    }
                    System.out.println("");
                }
            }
        } catch (Exception e) {
            System.err.println("Error is ::"+e.getMessage());
        }





    }
}