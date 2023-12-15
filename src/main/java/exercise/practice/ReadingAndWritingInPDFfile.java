package exercise.practice;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class ReadingAndWritingInPDFfile {
    public static void main(String[] args) throws IOException {

        //Reading the pdffile
         File file = new File("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/test.pdf");
       // PDDocument pdDocument = PDDocument.load(file);

        //PDFTextStripper pdfTextStripper = new PDFTextStripper();

        //String fetchText = pdfTextStripper.getText(pdDocument);
        //System.out.println(fetchText);

        //writing into the pdf file

        PDDocument pdDocument = new PDDocument();
        PDPage pdPage = new PDPage();
        pdDocument.addPage(pdPage);


        PDPageContentStream pdPageContentStream = new PDPageContentStream(pdDocument, pdPage, PDPageContentStream.AppendMode.APPEND, true,false);
        //we are starting the text
        pdPageContentStream.beginText();
        //before we start the text to be written we need to set font name and size
        pdPageContentStream.setFont(PDType1Font.TIMES_ROMAN,18);
        //we also need to write the space gap between two lines.. leading is the space between two lines of text
        pdPageContentStream.setLeading(16.0f);
        //we need to set the position from where we want to start our text to be written.
        //here we need to remember that the PDF box lies in the first quadrant.
        //it means the pdf box starts from the bottom left corner of the page.
        //so you can imagine as if in x and y axis, when it will go upside, its y value will increase
        // and when it will go rights-ide, its x value will increase.

        pdPageContentStream.newLineAtOffset(25, pdPage.getTrimBox().getHeight()-25);
        String text1 = "Hi all";
        String text2 = "My name is Suraj";

        //lets add these text in pdf
        pdPageContentStream.showText(text1); //its like write back to pdf as we do xssfworkbook.write in excel.
        pdPageContentStream.newLine();
        pdPageContentStream.showText(text2);
        pdPageContentStream.newLine();

        pdPageContentStream.endText();
        pdPageContentStream.close();

        pdDocument.save(file);
        pdDocument.close();
        System.out.println("PDF created successfully");


    }
}
