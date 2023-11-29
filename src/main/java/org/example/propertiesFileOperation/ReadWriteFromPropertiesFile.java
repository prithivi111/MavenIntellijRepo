package org.example.propertiesFileOperation;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

public class ReadWriteFromPropertiesFile {
    public static void main(String[] args) {
        //The moment I create property file (like pdf,txt), it always work with key value pair
        //You cannot put any normal value or text file in properties file.
        //Every Data base information should be kept in properties file.
        try {
            //read the property file
            FileReader fileReader = new FileReader("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/application.properties");

            //creating properties object and load the file content
            Properties properties = new Properties();
            properties.load(fileReader);

            String userName = properties.getProperty("username");
            System.out.println("User Name Details ::" + userName);
            String password = properties.getProperty("password");
            System.out.println("Password Details ::" + password);

            //Write to property file
            FileWriter fileWriter = new FileWriter("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/application.properties");
            properties.setProperty("database", "mysql");
            properties.setProperty("url", "test.com");

            //store the property to file
            properties.store(fileWriter, "Updating: name");

        } catch(Exception e) {
            System.err.println("Error Details :"+e.getMessage());
        }
    }
}
