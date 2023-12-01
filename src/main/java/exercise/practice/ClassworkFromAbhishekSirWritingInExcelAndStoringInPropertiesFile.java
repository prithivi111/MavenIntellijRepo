package exercise.practice;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;
import java.util.Properties;
/*
Write a java program to Create an excel file having below set of information,
        Username  password   email
        AB         ab@123   ab@test.com
        BC         bc@123   bc@test.com
        AD         ad@123   ad@test.com
Once Excel file created, read the same excel file and store information in properties files as
username1:AB
password1:ab@123
email: ab@test.com

username2:BC
password2:bc@123
email: ab@test.com

username3:AD
password3:ad@123
email: ad@test.com

 */

public class ClassworkFromAbhishekSirWritingInExcelAndStoringInPropertiesFile {
    public static void main(String[] args) throws IOException {

        try {
            File file = new File("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/zorba.xlsx");
            FileInputStream fileInputStream = new FileInputStream(file);

            //create workbook
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);

            Information info1 = new Information("AB", "ab@123", "ab@test.com");
            Information info2 = new Information("BC", "bc@123", "bc@test.com");
            Information info3 = new Information("AD", "ad@123", "ad@test.com");

            // Create an array.....
            Information info[] = new Information[]{info1, info2, info3};

            //Displaying from the array
            for(int i=0; i<3;i++){
                System.out.print(info[i].getUsername() + " | " + info[i].getPassword() + " | " + info[i].getEmail());
                System.out.println();
            }

            //creating a row

            for (int i = 0; i < 4; i++) {
                Row row;
                if (xssfSheet.getLastRowNum() == 0) {
                    row = xssfSheet.createRow(i);
                } else {
                    row = xssfSheet.createRow(xssfSheet.getLastRowNum() + 1);
                }

                if(row.getRowNum() == 0){
                    for(int j=0; j<3; j++) {
                        Cell cell = row.createCell(j);
                        switch(j) {
                            case 0:
                                cell.setCellValue("Username");
                                break;
                                case 1:
                                cell.setCellValue("password");
                                break;
                            case 2:
                                cell.setCellValue("email");
                                break;
                                }
                        }

                }else {
                        for (int j = 0; j < 3; j++) {
                        Cell cell = row.createCell(j);
                        switch (j) {
                            case 0:
                                cell.setCellValue(info[i-1].getUsername());
                                break;
                            case 1:
                                cell.setCellValue(info[i-1].getPassword());
                                break;
                            case 2:
                                cell.setCellValue(info[i-1].getEmail());
                                break;
                        }

                    }
                }
            }


            //writing in the excel
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            xssfWorkbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Successfully Save data into excel!!");

        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
            //Reading back from excel to retrieve the data and store in properties file
        try {
            File file1 = new File("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/zorba.xlsx");
            FileInputStream fileInputStream1 = new FileInputStream(file1);
            XSSFWorkbook xssfWorkbook1 = new XSSFWorkbook(fileInputStream1);

            XSSFSheet xssfSheet1 = xssfWorkbook1.getSheetAt(0);
            System.out.println("The last row no. of the given excel file is: " + xssfSheet1.getLastRowNum());

            //creating the properties and writing in the properties file
            File file = new File("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/information.properties");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            Properties properties = new Properties();

            int count = 0;
            Iterator<Row> rowIterator = xssfSheet1.rowIterator();

                while(rowIterator.hasNext()) {
                    Row row1 = rowIterator.next();
                    if (row1.getRowNum() != 0) {
                        String A = "";
                        String B = "";
                        String C = "";
                        Iterator<Cell> cellIterator = row1.cellIterator();
                        count = count + 1;
                        while (cellIterator.hasNext()) {
                            Cell cell1 = cellIterator.next();
                            switch (cell1.getColumnIndex()) {
                                case 0:
                                    A = cell1.getStringCellValue();
                                    properties.setProperty("Username" + (count), A);
                                    break;
                                case 1:
                                    B = cell1.getStringCellValue();
                                    properties.setProperty("Password" + (count), B);
                                    break;
                                case 2:
                                    C = cell1.getStringCellValue();
                                    properties.setProperty("Email" + (count), C);
                                    break;
                            }

                        }
                        System.out.println();
                    }
                }
                properties.store(fileOutputStream, "New properties added");
                fileOutputStream.close();


        } catch(Exception e){
            System.err.println(e.getMessage());
        }

    }
}
