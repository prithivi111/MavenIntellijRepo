package org.example.pdfFileOperation.test;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;

public class WriteToPdfFile {
    public static void main(String[] args) {

    try{
        File file = new File("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/test.pdf");

        //Creating an empty pdf
        PDDocument pdDocument = new PDDocument();

        //Creating a new page
        PDPage pdPage = new PDPage();
        pdDocument.addPage(pdPage);

        //Writing content to the pdf file
        PDPageContentStream pdPageContentStream = new PDPageContentStream (pdDocument,pdPage, PDPageContentStream.AppendMode.APPEND, true, false);

        //Setting the font
        pdPageContentStream.setFont(PDType1Font.TIMES_ROMAN,12);

        //beginning of the writing of the text
        pdPageContentStream.beginText(); //THis line can be written before setting the font also.

        //set the test position
        pdPageContentStream.newLineAtOffset(20, 600);
        pdPageContentStream.setLeading(14.5f);

        String text = "First PDF page generation and write back to test.pdf.file....";
        String text1 = "Second PDF line to be printed...";

        //for one line

       //  pdPageContentStream.showText(text);
        // pdPageContentStream.newLine();

        //adding one more line

        // pdPageContentStream.showText(text1);
        // pdPageContentStream.newLine();


        // creating one more page
      //  pdDocument.addPage(pdPage);

       // pdPageContentStream.close();


        // or if you want to write the multiple lines in pdf file
        String[] mulText = new String [6];
        mulText[0] = "First PDF page generation and write back to test.pdf.file....";
        mulText[1] = "Second PDF page generation and write back to test.pdf.file....";
        mulText[2] = "Third PDF page generation and write back to test.pdf.file....";
        mulText[3] = "Fourth PDF page generation and write back to test.pdf.file....";
        mulText[4] = "Fifth PDF page generation and write back to test.pdf.file....";
        mulText[5] = "Sixth PDF page generation and write back to test.pdf.file....";

        for(int i=0; i<mulText.length; i++){
            pdPageContentStream.showText((mulText[i]));
            pdPageContentStream.newLine();
        }
        pdPageContentStream.close();



        //save the content to pdf file
        pdDocument.save(file);
        pdDocument.close();

    } catch(Exception e){
        System.err.println(e.getMessage());

    }
}
}
