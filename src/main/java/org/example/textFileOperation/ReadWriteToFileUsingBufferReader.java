package org.example.textFileOperation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
//Reading and writing text file using bufferReader
public class ReadWriteToFileUsingBufferReader {
    public static void main(String[] args) {
        String data = "Hi, Hello, How are you? Are you there";
        try{
            // create File Writer
            FileWriter filewriter = new FileWriter("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/sample.txt");


            //Buffer works on the entire string whereas IOstream works on the byte data.
            //Difference between buffered and IOstream..??
            //Actually Buffer includes all the things what IOstream.
            // In read() method, we convert the text into text into bytes and compare with ASCII value.
            // But in buffer method we can directly read and write.

            //create BufferWriter
            BufferedWriter bufferedWriter = new BufferedWriter(filewriter);

            //write data to empty file first
            bufferedWriter.write(data);

            //close the writer
            bufferedWriter.close();
            System.out.println("Saved Data Successfully!!");
            bufferedWriter.flush(); // to clear any internal things remained

            //Reading the file
            FileReader fileReader = new FileReader("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/sample.txt");

            //Create buffer reader
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //read the data

            String retrievedData = bufferedReader.readLine();
            //This is reading whole line but considering only one line...

            //But what if we have multiple lines in the text file, we have another way to read the contents of the file other than the bufferedReader.
            //So we can use the other option

            int c;
            String retrievedData =" ";
            while((c = bufferedReader.read()) != -1 ){
                  retrievedData = retrievedData + (char)c;
            }
            System.out.println("Retrieved Data::" + retrievedData);

            System.out.println("Retrieved Data:: ");

        } catch(Exception e){
            System.err.println(e.getMessage());
        }

    }
}
