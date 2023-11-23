package org.example.exceloperation;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.model.Student;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class ReadingFromExcelAndStoringInArray {
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
                System.out.println("Last Row Number :: "+xssfSheet.getLastRowNum());
                Student[] students = new Student[xssfSheet.getLastRowNum()];
                //Read the rows one by one
                Iterator<Row>  rowIterator = xssfSheet.iterator();
                while(rowIterator.hasNext()) {
                    Student student = new Student();
                    Row row = rowIterator.next();
                    System.out.println("Row Number Details ::"+row.getRowNum());
                    if (row.getRowNum() == 0) {
                        continue;
                    }
                    //fetch Cell/column one by one
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while(cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        System.out.println("Cell Number Details ::"+cell.getColumnIndex());
                        switch (cell.getColumnIndex()) {
                            case 0:
                               double studentId = (double)fetchData(cell); // do this way in the below cases as well.
                               student.setStudentId (studentId);
                               break;
                            case 1:
                               student.setStudentName(fetchData(cell).toString());
                               break;
                            case 2:
                               student.setSub1Score((double)fetchData(cell));
                               break;
                            case 3:
                                student.setSub2Score((double)fetchData(cell));
                                break;
                            case 4:
                                student.setSub3Score((double)fetchData(cell));
                                break;
                            case 5:
                                student.setFinalScore((double)fetchData(cell));
                                break;
                        }
                    }
                    students[row.getRowNum() - 1] = student;
                }
                displayData(students);
            }
        } catch (Exception e) {
            System.err.println("Error is ::"+e.getMessage());
        }
    }

    public static Object fetchData(Cell cell) {

        return switch (cell.getCellType()) {
            case STRING -> {
                System.out.println(cell.getStringCellValue() + "\t");
                yield cell.getStringCellValue();
            }
            case NUMERIC -> {
                System.out.println(cell.getNumericCellValue() + "\t");
                yield cell.getNumericCellValue();
            }
            case BOOLEAN -> {
                System.out.println(cell.getBooleanCellValue() + "\t");
                yield cell.getBooleanCellValue();
            }
            default -> null;
        };
    }

    public static void displayData(Student[] students) {
        for (int i = 0; i < students.length; i++) {
            System.out.println("Student Id :"+students[i].getStudentId() +
                    ", Student Name: "+ students[i].getStudentName() +
                    ", Sub Score 1: "+ students[i].getSub1Score() +
                    ", Sub Score 2: "+ students[i].getSub2Score() +
                    ", Sub Score 3: "+ students[i].getSub3Score() +
                    ", Final Score: "+ students[i].getFinalScore());
        }

    }
}
