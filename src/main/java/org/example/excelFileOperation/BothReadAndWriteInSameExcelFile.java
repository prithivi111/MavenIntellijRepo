package org.example.excelFileOperation;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.model.employees;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

public class BothReadAndWriteInSameExcelFile {
    public static void main(String[] args) {
        try {
            File file = new File("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/employee.xlsx");
            FileInputStream fileInputStream = new FileInputStream(file);

            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet xssfSheet= xssfWorkbook.getSheetAt(0);

            //creating an ARRAY to store employee data
            employees[] employee = new employees[xssfSheet.getLastRowNum()];
            // getLastRowNum or getLastCellNum gives the indexes of row or column while
            //getPhysicalNumberOfRows() gives the actual rows or columns present.

            //
            //
            // System.out.println("The last row number of the employee excel sheet is:: "+ xssfSheet.getLastRowNum());

            Iterator<Row> rowIterator = xssfSheet.rowIterator();
            while(rowIterator.hasNext()){
                Row row = rowIterator.next();

                //Trying to confirm in which row we are doing our data calculation
                System.out.println("Row Number Details:: " + row.getRowNum());

                if(row.getRowNum() == 0){

                   //Creating a new column in as Employee Salary:

                    Cell cellHeading = row.createCell(row.getPhysicalNumberOfCells());
                    cellHeading.setCellValue("Employee Salary");

                    continue;
                }

                //Creating an Object of Employee
                employees emp= new employees();

                //Creating a new cell
                Iterator<Cell> cellIterator = row.cellIterator();
                while(cellIterator.hasNext()){
                         Cell cell = cellIterator.next();
                        System.out.println("Cell Number Details:: " + cell.getColumnIndex());
                        if (cell.getColumnIndex()>=0 && cell.getColumnIndex()<=4) {
                             switch (cell.getColumnIndex()) {

                                 case 0:
                                     emp.setEmployeeID((double) fetchCellData(cell));
                                    break;
                                 case 1:
                                    emp.setEmployeeName(fetchCellData(cell).toString());
                                    break;
                                 case 2:
                                    emp.setEmployeeAddress(fetchCellData(cell).toString());
                                    break;
                                 case 3:
                                     emp.setEmployeeEmail(fetchCellData(cell).toString());
                                     break;
                                 case 4:
                                    double input = (double) fetchCellData(cell);
                                    emp.setEmployeeExperience(input);
                                    double salary = salaryCalculation(input);

                                     // Creating a new cell and inserting salary.
                                    Cell newCell = row.createCell(row.getLastCellNum()+1);
                                    newCell.setCellValue(salary);
                                    break;
                              }
                        }
                }
                    employee[row.getRowNum()-1] = emp;




            }
            displayData(employee);
            //write back to excel
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            xssfWorkbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Successfully Save data into excel!!");


        } catch(Exception e) {
            System.err.println("Error is::" + e.getMessage());

        }
    }

  public static Object fetchCellData(Cell cell){
       return switch(cell.getCellType()){
           case STRING -> {
               System.out.println(cell.getStringCellValue());
               yield cell.getStringCellValue();
           }
           case NUMERIC -> {
               System.out.println(cell.getNumericCellValue());
               yield cell.getNumericCellValue();
           }
           case BOOLEAN -> {
               System.out.println(cell.getBooleanCellValue());
               yield cell.getBooleanCellValue();
           }
           default -> null;
       };
    }

    public static void displayData(employees[] employee) {
        System.out.println();
        for (int i = 0; i < employee.length; i++) {
            System.out.print("Employee Id :"+ employee[i].getEmployeeID() +
                    " |  Employee Name: "+ employee[i].getEmployeeName() +
                    " |  Employee Address: "+ employee[i].getEmployeeAddress() +
                    " |  Employee Email: "+ employee[i].getEmployeeEmail() +
                    " |  Employee Experience: "+ employee[i].getEmployeeExperience());
            System.out.println();
        }

    }

    public static double salaryCalculation(double input){
        double salary;
        if (input<=5 ) {
            salary = 1000 * 5;
        } else if (input<=10 && input >5){
                salary = 2500 * 5;
            } else if(input <=20 && input >10) {
                salary = 5000 * 5;
                } else {
                salary = 8000 * 5;
                         }
        return salary;
        }

}
