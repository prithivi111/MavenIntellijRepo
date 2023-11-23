package org.example.exceloperation;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class WriteToExcelFile {
    public static void main(String[] args) {
        try {
            //Read the excel file
            File file = new File("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/test.xlsx");
            FileInputStream fileInputStream = new FileInputStream(file);

            //Create workbook
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
            //Read first sheet
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
            System.out.println("Last Row number ::"+xssfSheet.getLastRowNum());
            //Create a new row
            for (int i = 0; i < 2; i++) {
                Row row;
                if (xssfSheet.getLastRowNum() == 0) {
                    row = xssfSheet.createRow(i);
                } else {
                    row = xssfSheet.createRow(xssfSheet.getLastRowNum() + 1);
                }
                for(int j = 0; j < 3; j++) {
                    Cell cell = row.createCell(j);
                    switch (j) {
                        case 0:
                            if (i == 0) {
                                cell.setCellValue("Hello");
                            } else {
                                cell.setCellValue("All");
                            }
                            break;
                        case 1:
                            if (i == 0) {
                                cell.setCellValue(234);
                            } else {
                                cell.setCellValue(456);
                            }
                            break;
                        case 2:
                            if (i == 0) {
                                cell.setCellValue(false);
                            } else {
                                cell.setCellValue(true);
                            }
                            break;
                    }
                }
            }

            //write back to excel
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            xssfWorkbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Successfully Save data into excel!!");
        } catch(Exception e) {
            System.err.println("Exception Details ::" +e);
        }


    }
}
