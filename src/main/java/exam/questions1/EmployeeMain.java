package exam.questions1;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;

public class EmployeeMain {

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the number of employee details you want to input:");
            int noOfEmp = sc.nextInt();

            //Creating an array of Class EmployeeInfo
            EmployeeInfo[] employees = new EmployeeInfo[noOfEmp];

            for (int i = 0; i < noOfEmp; i++) {

                EmployeeInfo emp = new EmployeeInfo();
                System.out.println("Enter empID: ");
                emp.setEmpID(sc.next());
                System.out.println("Enter empName: ");
                emp.setEmpName(sc.next());
                System.out.println("Enter empAddress: ");
                emp.setEmpAddress(sc.next());
                System.out.print("Enter empMobileNo: ");
                emp.setEmpMobileNo(sc.nextLong());
                System.out.println("Enter empQualification: ");
                emp.setEmpQualification(sc.next());
                System.out.println("Enter empStatus: 'Active' / 'New' ");
                emp.setEmpStatus(sc.next());
                System.out.println("Enter username: ");
                emp.setUsername(sc.next());
                System.out.println("Enter password: ");
                emp.setPassword(sc.next());

                employees[i] = emp;

            }
            employeeInfoStore(noOfEmp, employees); // For Qno. 1

            fetchEmployeeInfo(); // For Qno.2

            pdfCreationOfEmployeeInfo(); //For Qno.3

        } catch (Exception e) {
            System.err.println(e.getMessage());

        }
    }

    public static void employeeInfoStore(int noOfEmp, EmployeeInfo[] employees) {

        //Reading the excel file:
        try {
            File file = new File("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/emp_info.xlsx");
            FileInputStream fileInputStream = new FileInputStream(file);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);  // Because we know that there is only one sheet

            //Creating title
            Row rowTitle;
            rowTitle = xssfSheet.createRow(xssfSheet.getLastRowNum() + 1);
            Cell cell;
            for (int m = 0; m < 8; m++) {
                cell = rowTitle.createCell(m);
                switch (cell.getColumnIndex()) {
                    case 0:
                        cell.setCellValue("empID");
                        break;
                    case 1:
                        cell.setCellValue("empName");
                        break;
                    case 2:
                        cell.setCellValue("empAddress");
                        break;
                    case 3:
                        cell.setCellValue("mobileNo");
                        break;
                    case 4:
                        cell.setCellValue("qualification");
                        break;
                    case 5:
                        cell.setCellValue("status");
                        break;
                    case 6:
                        cell.setCellValue("username");
                        break;
                    case 7:
                        cell.setCellValue("password");
                        break;
                }
            }

            //Creating Row contents
            for (int i = 0; i < noOfEmp; i++) {
                Row row;
                if (xssfSheet.getLastRowNum() == 0) {
                    row = xssfSheet.createRow(i + 1);
                } else {
                    row = xssfSheet.createRow(xssfSheet.getLastRowNum() + 1);
                }
                //Creating Column
                for (int j = 0; j < 8; j++) {
                    Cell cellContent;
                    cellContent = row.createCell(j);
                    switch (cellContent.getColumnIndex()) {
                        case 0:
                            cellContent.setCellValue(employees[i].getEmpID());
                            break;
                        case 1:
                            cellContent.setCellValue(employees[i].getEmpName());
                            break;
                        case 2:
                            cellContent.setCellValue(employees[i].getEmpAddress());
                            break;
                        case 3:
                            cellContent.setCellValue(employees[i].getEmpMobileNo());
                            break;
                        case 4:
                            cellContent.setCellValue(employees[i].getEmpQualification());
                            break;
                        case 5:
                            cellContent.setCellValue(employees[i].getEmpStatus());
                            break;
                        case 6:
                            cellContent.setCellValue(employees[i].getUsername());
                            break;
                        case 7:
                            cellContent.setCellValue(employees[i].getPassword());
                            break;
                    }
                }

            }

            //writing into excel file
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            xssfWorkbook.write(fileOutputStream);
            fileOutputStream.close();

            //Qno. 1 completed.
            System.out.println("Details of " + noOfEmp + " employees have been successfully uploaded into excel.");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void fetchEmployeeInfo() {
        try {
            //First reading from Excel file
            File file1 = new File("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/emp_info.xlsx");
            FileInputStream fileInputStream = new FileInputStream(file1);
            XSSFWorkbook xssfWorkbook1 = new XSSFWorkbook(fileInputStream);
            XSSFSheet xssfSheet1 = xssfWorkbook1.getSheetAt(0);

            //Properties file creation
            File file = new File("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/emp_credentials.properties");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            Properties properties = new Properties();

            Iterator<Row> rowIterator = xssfSheet1.rowIterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == 0) {
                    continue;
                }
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getColumnIndex()) {
                        case 0:
                            properties.setProperty("EmployeeID", cell.getStringCellValue()); // I think my properties are overridden here
                            break;
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                            break;
                        case 6:
                            properties.setProperty("UserName", cell.getStringCellValue());
                            break;
                        case 7:
                            properties.setProperty("Password", cell.getStringCellValue());
                            break;

                    }


                }
                properties.store(fileOutputStream, "Properties Added::");
            }

            fileOutputStream.close();
            // Question no. 2 completed
        } catch (Exception e) {
            System.err.println(e.getMessage());

        }
    }

    public static void pdfCreationOfEmployeeInfo() {
        try {
            //First reading from Excel file
            File file2 = new File("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/emp_info.xlsx");
            FileInputStream fileInputStream = new FileInputStream(file2);
            XSSFWorkbook xssfWorkbook2 = new XSSFWorkbook(fileInputStream);
            XSSFSheet xssfSheet2 = xssfWorkbook2.getSheetAt(0);

            //where we want to start our text
            //pdfbox lies in the first quadrant. It origin from the bottom left corner of the page.
            //when it goes upside, its y value will be incresed. When it goes right side, its x value will be increase.


            Iterator<Row> rowIterator = xssfSheet2.rowIterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == 0) {
                    continue;
                }

                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (cell.getColumnIndex() != 5) {
                        continue;
                    } else {
                        if (cell.getStringCellValue().equalsIgnoreCase("new")) {

                            //Creating a pdf file
                            //But we need to create different pdfFile for each match//Think about that
                            File file3 = new File("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/EmployeePDF.pdf");
                            PDDocument pdDocument = new PDDocument();
                            PDPage pdPage = new PDPage();
                            pdDocument.addPage(pdPage);

                            PDPageContentStream pdPageContentStream = new PDPageContentStream(pdDocument, pdPage, PDPageContentStream.AppendMode.APPEND, true, false);

                            pdPageContentStream.beginText();

                            pdPageContentStream.newLineAtOffset(50, pdPage.getTrimBox().getHeight() - 55);
                            pdPageContentStream.setFont(PDType1Font.HELVETICA, 12);


                            pdPageContentStream.setLeading(16.0f);  // space between two line of text
                            pdPageContentStream.showText(":::::::::::Agreement::::::::::");



                            pdPageContentStream.showText("Hi " + row.getCell(1).getStringCellValue());
                            pdPageContentStream.newLine();
                            pdPageContentStream.showText("We are here to inform you that you have been selected for the job. We will send ");
                            pdPageContentStream.newLine();
                            pdPageContentStream.showText("further communication on the " + row.getCell(2).getStringCellValue());
                            pdPageContentStream.newLine();
                            pdPageContentStream.showText("We are requesting you to accept the agreement and send us confirmation of the ");
                            pdPageContentStream.showText("joining");
                            pdPageContentStream.newLine();
                            pdPageContentStream.showText("Thanks");
                            pdPageContentStream.newLine();
                            pdPageContentStream.showText("HR Team");


                            pdPageContentStream.endText();
                            pdPageContentStream.close();
                            pdDocument.save(file3);
                            pdDocument.close();

                        }
                    }


                    // How to create multiple pdfs??
                    // How to add paragraphs ??
                }

            }   System.out.println("The pdf file successfully made.");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
