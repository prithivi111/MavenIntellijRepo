package exercise.practice;

import java.io.*;

public class ReadingAndWritingInTheTextFileUsingIOstream {
    public static void main(String[] args) throws IOException {
        File file = new File("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/input.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        String a = " ";
        int c;
        while((c = fileInputStream.read()) != -1){
            a = a + (char)c;

        }
        System.out.println("The contents of the text file is as below::");
        System.out.println(a);

        //let's add some more data into the text file
        a = a.trim() + "\n" + " Give me some sunshine";
        a = a.trim() + "\n" + "Give me some rain";

        //writing back to text file
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(a.getBytes());
        System.out.println("added to the text file successfully!! please go and check the file");
        fileOutputStream.close();


    }
}
