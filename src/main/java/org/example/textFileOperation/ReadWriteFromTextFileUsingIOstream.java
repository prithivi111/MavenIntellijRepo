package org.example.textFileOperation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ReadWriteFromTextFileUsingIOstream {
    public static void main(String[] args) {
        try {
            File file = new File("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/input.txt");
            //to read data from file

            FileInputStream fileInputStream = new FileInputStream(file);
            int c;
            String inputData = "";
            //reading data from text file
            while ((c = fileInputStream.read()) != -1) {
                // read() is used to read all bytes of data (entire text) from a stream.
                System.out.println(c);
                inputData = inputData + (char)c;
            }

            System.out.println("Received Data : "+ inputData);
            inputData = inputData.trim() + " -- Ok Fine.."; //.trim() helps to remove extra space at the end of a string
            System.out.println("Updated Data :" +inputData);

            //write back data to file (same text file, the file which is created in the first)
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(inputData.getBytes()); //convert back from string to bytes (set of Ascii value)
            System.out.println("Write back to file successfully!!");
            outputStream.close();

            //write back data to the new file (nex text file should be made by myself, ie. output.txt)
            FileOutputStream outputStream1 = new FileOutputStream("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/output1.txt");
            outputStream1.write(inputData.getBytes()); //convert back from string to bytes (set of Ascii value)

           // Question: FileOutputStream is meant for writing streams of raw bytes such as image data.
            // For writing streams of characters, consider using FileWriter.

            System.out.println("Write back to file successfully!!");
            outputStream1.close();

        } catch(Exception e) {
            System.err.println("Error Details ::" +e.getMessage());
        }
    }
}


//String class--> getBytes() is a method of String Class and does the encoding of string into the sequence of bytes
// and keeps it in an array of bytes.
//Example:
/*1. public class StringGetBytesExample{
    public static void main(String args[]){
        String s1="ABCDEFG";
        byte[] barr=s1.getBytes();
        for(int i=0;i<barr.length;i++){
            System.out.println(barr[i]);
        }
    }}   OUTPUT: 65 66 67 68 69 70 71
        // Here one character is at a time is stored in array of byte.*/

/*2. public class StringGetBytesExample2 {
    public static void main(String[] args) {
        String s1 = "ABCDEFG";
        byte[] barr = s1.getBytes();
        for(int i=0;i<barr.length;i++){
            System.out.println(barr[i]);
        }
        // Getting string back
        String s2 = new String(barr);
        System.out.println(s2);
    }  OUTPUT: 65 66 67 68 69 70 71 ABCDEFG
    The method returns a byte array that again can be passed to the String constructor to get String
}  */
