package org.example.pdfFileOperation.test;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;

public class ReadFromPdfFile {
    public static void main(String[] args) {

        try {
            File file = new File("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/test.pdf");
            //Loading pdf file
            PDDocument pdDocument = PDDocument.load(file);

            //read the content
            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            //fetch the document
            String fetchText = pdfTextStripper.getText(pdDocument);
            System.out.println(fetchText);

            //closing the document
            pdDocument.close();

        } catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
