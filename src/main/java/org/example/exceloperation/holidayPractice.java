package org.example.exceloperation;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class holidayPractice {
    public static void main(String[] args) {
    try {
        File file = new File("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/Book4.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);

        System.out.println(fileInputStream); //This will give the garbage value.

        //creating a workbook instance  and working at the first sheet
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);

        System.out.println(xssfSheet.getLastRowNum());//This will print the last row no.

        //Traversing the row
        Iterator<Row> rowIterator = xssfSheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            System.out.println("The current row no is::" + row.getRowNum());

            Iterator<Cell> cellIterator = row.cellIterator();
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();

                    //System.out.println("The current column index is::" + cell.getColumnIndex());

                    switch (cell.getCellType())
                    {
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "  ");
                            break;
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue()+ "  ");
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue()+ "  ");
                            break;
                    }

                }
            System.out.println();
        }

    } catch (Exception e){
        System.out.println(e.getMessage());
    }


    }

}

