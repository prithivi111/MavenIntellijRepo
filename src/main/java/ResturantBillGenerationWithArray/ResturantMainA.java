package ResturantBillGenerationWithArray;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.*;
import java.util.Scanner;

public class ResturantMainA {

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("***Enter the details of menu you want to provide in the menu***");

            //Finding the file where to store
            File file = new File("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/resturant1.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            BillOperationsA[] bill = new BillOperationsA[3];

            //Automated MenuId generation and object creation ... Question No.1
            for (int i = 0; i < 3; i++) {

                System.out.println("Enter the name of the menu: ");
                String mn = sc.next();
                System.out.println("Enter the description of the menu: ");
                String nn = sc.next();
                System.out.println("Enter the price of the menu: ");
                double dd = sc.nextDouble();

                BillOperationsA billOperations = new BillOperationsA (mn,nn,dd);
                bill[i] = billOperations;

                //storing in the text file ..... Question No. 1
                bufferedWriter.write(String.format("%-20s%-20s%-20s%-20s%n",
                        bill[i].getMenuId(), bill[i].getMenuName(), bill[i].getDescription(), bill[i].getPrice()));


            }
                bufferedWriter.close();

            //Displaying menu to the customer by fetching the stored contents in the text file ... Question No. 2
            displayMenu(bill);

            //Taking the order from the customer.. Question NO. 3
            CustomerOrderA receivedCustomerOrder = takingOrder();

            //Calculating the total price of the customer order and generating the bill in pdf ... Question No. 4
            billGenerationInPdf(receivedCustomerOrder, bill);

            sc.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void displayMenu(BillOperationsA[] bill) {
        try {
            System.out.println(":::This is the menu of Zorba Resturant:::");

            System.out.printf("%-20s%-20s%-20s%-20s%n", "Menu Id", "Menu Name", "Description", "Price");

            for (BillOperationsA b : bill) {
                System.out.printf("%-20s%-20s%-20s%-20s%n",
                        b.getMenuId(),
                        b.getMenuName(),
                        b.getDescription(),
                        b.getPrice());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public static CustomerOrderA takingOrder() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the menu item name you want to take out of the displayed menu..");
        String aa = sc.next();
        System.out.println("How many of the mentioned menu item you want to take.");
        int bb = sc.nextInt();

        //TO DO ask the customer if he wants to take additional menu item and if yes, how many?

        CustomerOrderA customerOrder = new CustomerOrderA(aa, bb);

        //We are returning the customerOrder object (details of customer order to the main file)
        return customerOrder;
    }

    public static void billGenerationInPdf(CustomerOrderA receivedCustomerOrder, BillOperationsA[] bill) {
        try {

            //As we need to print the bill in the pdf file, so we need to write in pdf
            File pdfFile = new File("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/billreceipt.pdf");
            PDDocument pdDocument = new PDDocument();
            PDPage pdPage = new PDPage();
            pdDocument.addPage(pdPage);

            PDPageContentStream pdPageContentStream = new PDPageContentStream(pdDocument, pdPage);
            pdPageContentStream.beginText();

            pdPageContentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
            pdPageContentStream.setLeading(14.6f); //
            pdPageContentStream.newLineAtOffset(25, pdPage.getTrimBox().getHeight()-25);

            String heading = "*********Thank you for visiting ZORBA Resturant***********";
            //Heading inserting
            pdPageContentStream.showText(heading);
            pdPageContentStream.newLine();
            pdPageContentStream.newLine();


            for (int i = 0; i < 3; i++) {

                // Now trying to match the menuName from customerOrder object and the text file object
                if (bill[i].getMenuName().equals(receivedCustomerOrder.getItems())) {

                    int number = receivedCustomerOrder.getNoOfEachItems();
                    double pricePerUnit = bill[i].getPrice();
                    double totalPrice = pricePerUnit * number;

                    //Bill detail inserting

                    pdPageContentStream.showText(bill[i].getMenuId() + "  " + bill[i].getMenuName() + "  " + bill[i].getPrice() + "  " +
                            receivedCustomerOrder.getNoOfEachItems() + "  ");
                    pdPageContentStream.newLine();
                    pdPageContentStream.showText("Total Amount:                       ");
                    //We are not adding the new line here because we need to add the total price at the end of the line.
                    // pdPageContentStream.newLine();
                    pdPageContentStream.showText(String.valueOf(totalPrice));
                    pdPageContentStream.newLine();

                }
            }

            pdPageContentStream.endText();
            pdPageContentStream.close();

            //save the content to pdf file
            pdDocument.save(pdfFile);
            pdDocument.close();

            System.out.println("The PDF Receipt has been successfully generated.");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
