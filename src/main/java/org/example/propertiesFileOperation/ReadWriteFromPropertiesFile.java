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

            //Reading from the properties file
            String userName = properties.getProperty("username"); //passing the key here i.e. "username"
            System.out.println("User Name Details ::" + userName); //getting the value here i.e. "hello"
            String password = properties.getProperty("password"); //passing the key here
            System.out.println("Password Details ::" + password); //getting the value here



            //Write to property file  //Below we do not need to create the object of property becuase it is already created above.
            FileWriter fileWriter = new FileWriter("C:/Users/s011271sur/intellij/FirstMavenProject/src/main/resources/application.properties");
            properties.setProperty("database" , "mysql"); //here, database is the key, and mysql is the value.
            properties.setProperty("url", "test.com"); //here, url is the key, and test.com is the value.

            //Modify the existing property
            properties.setProperty("url", "facebook.com");
            String aa = properties.getProperty("url");
            System.out.println(aa);

            //store the property to file
            properties.store(fileWriter, "Updating: name");


        } catch(Exception e) {
            System.err.println("Error Details :"+e.getMessage());
        }
    }
}
