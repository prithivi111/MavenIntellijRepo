package exercise.practice;

import java.io.*;
import java.util.Properties;

public class ReadingAndWritingInPropertiesFile {
    public static void main(String[] args) throws IOException {
        // reading from properties file
       /* FileReader fileReader = new FileReader("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/application.properties");
        Properties properties = new Properties();
        properties.load(fileReader);
        String name = properties.getProperty("Hari");
        System.out.println(name);
        String password = properties.getProperty("Ram");
        System.out.println(password);
*/
        //writing into properties file

   File file = new File("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/application.properties");
    FileOutputStream fileOutputStream = new FileOutputStream(file);

    Properties properties = new Properties();
        properties.setProperty("Ram", "Suraj1234@");
                properties.setProperty("Hari", "Suraj12345@");

                properties.store(fileOutputStream, "Properties Added");

                fileOutputStream.close();

    }


}