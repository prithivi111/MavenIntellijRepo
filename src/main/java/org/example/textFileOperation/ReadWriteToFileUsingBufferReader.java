package org.example.textFileOperation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

//Reading and writing text file using bufferReader
public class ReadWriteToFileUsingBufferReader {
    public static void main(String[] args) {
        String data = "aa '''''''''''''''''''''''''''''''''''        ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''          ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::";
        try{
            // create File Writer
           FileWriter filewriter = new FileWriter("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/sample.txt");


            //Buffer works on the entire string whereas IOstream works on the byte data.
            //Difference between buffered and IOstream..??
            //Actually Buffer includes all the things what IOstream.
            // In read() method, we convert the text into text into bytes and compare with ASCII value.
            // But in buffer method we can directly read and write.

            //create BufferWriter
           // BufferedWriter bufferedWriter = new BufferedWriter(filewriter);

            //write data to empty file first
          //  bufferedWriter.write(data);

            //close the writer
            //bufferedWriter.close();
          //  System.out.println("Saved Data Successfully!!");
         //   bufferedWriter.flush(); // to clear any internal things remained

            //Reading the file
       FileReader fileReader = new FileReader("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/sample.txt");

            //Create buffer reader
          BufferedReader bufferedReader = new BufferedReader(fileReader);

            //read the data

            //String retrievedData = bufferedReader.readLine();
            //This is reading whole line but considering only one line...

            //But what if we have multiple lines in the text file, we have another way to read the contents of the file other than the bufferedReader.
            //So we can use the other option
           BufferedWriter bufferedWriter = new BufferedWriter(filewriter);
            //bufferedReader.readLine();

            String line;
            int count =0;
            String retrievedData =" ";
            String [] retrievedArr = new String[3];

            while((line = bufferedReader.readLine()) != null){
                  retrievedArr[count] = line;
                  count++;

                  //it returns the entire content in a single string
                  //retrievedData = retrievedData + line;
            }

            System.out.println("Retrieved Data ::" + Arrays.asList(retrievedArr));

            //close the buffer reader
            bufferedReader.close();


         //write data to file
         for(int i =0; i<retrievedArr.length; i++){
             bufferedWriter.write(retrievedArr[i]);
             bufferedWriter.newLine();

         }


        } catch(Exception e){
            System.err.println(e.getMessage());
        }

    }
}
