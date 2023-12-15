package ResturantBillGenerationByObjectCreation;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.*;
import java.util.Scanner;

public class RestaurantMain {

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("***Enter the details of menu you want to provide in the menu***");

            //Finding the file where to store
            File file = new File("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/resturant1.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file);


            //Automated MenuId generation and object creation ... Question No.1
            for (int i = 0; i < 1; i++) {
                BillOperations billOperations = new BillOperations();
                billOperations.setMenuId(BillOperations.no);

                System.out.println("Enter the name of the menu: ");
                billOperations.setMenuName(sc.next());
                System.out.println("Enter the description of the menu: ");
                billOperations.setDescription(sc.next());
                System.out.println("Enter the price of the menu: ");
                billOperations.setPrice(sc.nextDouble());

                //storing in the text file ..... Question No. 1
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(billOperations);
            }


            //Displaying menu to the customer by fetching the stored (unreadable objects) objects in the text file ... Question No. 2
            displayMenu(file);

            //Taking the order from the customer and saving in different objects... Question NO. 3
            CustomerOrder receivedCustomerOrder = takingOrder();

            //Calculating the total price of the customer order and generating the bill in pdf ... Question No. 4
            billGenerationInPdf(receivedCustomerOrder, file);

            sc.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void displayMenu(File file) {
        try {
            //Reading from the text file
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            System.out.println(":::This is the menu of Zorba Resturant:::");
            BillOperations Menu = (BillOperations) objectInputStream.readObject();
            for (int i = 0; i < 1; i++) {
                System.out.println(Menu.getMenuName() + " | " + Menu.getMenuId() + " | " + Menu.getDescription() + " | " + Menu.getPrice());
                System.out.println();

            }
            objectInputStream.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public static CustomerOrder takingOrder() {

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the menu item you want to take out of the displayed menu..");
            String aa = sc.next();
            System.out.println("How many you want to take.");
            int bb = sc.nextInt();
            CustomerOrder customerOrder = new CustomerOrder(aa, bb );

            //We are returning the customerOrder object (details of customer order to the main file)
            return customerOrder;
    }

    public static void billGenerationInPdf(CustomerOrder receivedCustomerOrder, File file) {
        try {

            //Trying to match retrieve the object from the text file by matching the customer ordered items.
            //For that we need to read the text file again first.
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            //Reading from the text file.
            BillOperations Menu = (BillOperations) objectInputStream.readObject();

            //As we need to print the bill in the pdf file, so we need to write in pdf
            File pdfFile = new File("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/billreceipt.pdf");
            PDDocument pdDocument = new PDDocument();
            PDPage pdPage = new PDPage();
            pdDocument.addPage(pdPage);

            PDPageContentStream pdPageContentStream = new PDPageContentStream(pdDocument, pdPage);
            pdPageContentStream.beginText();

            pdPageContentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
            pdPageContentStream.setLeading(14.6f); //
            pdPageContentStream.newLineAtOffset(20, 700);

            String heading = "Thank you for visiting ZORBA Resturant";
            //Heading inserting
            pdPageContentStream.showText(heading);
            pdPageContentStream.newLine();


                for (int i = 0; i < 1; i++) {

                    // Now trying to match the menuName from customerOrder object and the text file object
                    if(Menu.getMenuName().equals(receivedCustomerOrder.getItems())){

                        int number = receivedCustomerOrder.getNoOfEachItems();
                        double pricePerUnit = Menu.getPrice();
                        double totalPrice = pricePerUnit * number;

                        //Bill detail inserting
                        pdPageContentStream.showText(Menu.getMenuId() + "  " + Menu.getMenuName() + "  " + Menu.getPrice() + "  " + receivedCustomerOrder.getNoOfEachItems() + "  ");
                        //We are not adding the new line here because we need to add the total price at the end of the line.
                        // pdPageContentStream.newLine();
                        pdPageContentStream.showText(String.valueOf(totalPrice));
                        pdPageContentStream.newLine();

                    } else {
                        System.out.println(":::Customer ordered items not listed in the Menu::::");
                    }
                }

                pdPageContentStream.endText();
            pdPageContentStream.close();

            //save the content to pdf file
            pdDocument.save(pdfFile);
            pdDocument.close();


       } catch(Exception e){
            System.err.println(e.getMessage());
            }
    }
}