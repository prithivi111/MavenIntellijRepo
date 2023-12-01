package exercise.practice;

import java.io.*;

public class ReadingAndWritingInTheTextFileUsingBuffer {
    public static void main(String[] args) throws IOException {

        //Writing into the file
        String[] names= {"John", "Carl", "Hero"};
        FileWriter fileWriter = new FileWriter("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/input.txt");
        BufferedWriter bufferedWriter = new BufferedWriter (fileWriter);
        for(String n:names) {
            bufferedWriter.write("\n" + n);

        }
        bufferedWriter.close();

        //Reading from the file
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/input.txt"));
        String line;
        while((line = bufferedReader.readLine()) != null ) {
            System.out.println(line);
        }
        bufferedReader.close();
    }
}
