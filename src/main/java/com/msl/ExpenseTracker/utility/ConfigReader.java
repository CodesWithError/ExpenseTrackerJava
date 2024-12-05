package com.msl.ExpenseTracker.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

//***Exception****
import java.io.IOException;

//*** property Class ****
import java.util.Properties;

public class ConfigReader {
	
	 public static Properties getPropertyObject() throws IOException {
		 FileInputStream fp=new FileInputStream("Config\\config.xml");
		 FileOutputStream fo=new FileOutputStream("Config\\config.xml");
		 
//		 Properties class is used  to read and write the property from/to a file.
		 Properties prop=new Properties();
		 
		 prop.setProperty("mobileRegexp", "^[0-9]{10}$");
		 prop.setProperty("nameRegexp", "^[a-zA-Z]{1,24}(?:\\s[a-zA-Z]{1,24}){0,24}$");
		 prop.setProperty("passwordRegexp", "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,12}$");
		 prop.setProperty("emailRegexp", "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");

//		 Storing key and value to xml file.
		 prop.storeToXML(fo, "Regular Expression data");
		 
//		 load the file
//		 prop.load(fp);
//		 load the xml file
		 prop.loadFromXML(fp);
		 
		 return prop;
	 }
	 
	 public static String getNameRegexp() throws IOException {
		 return getPropertyObject().getProperty("nameRegexp");
	 }
	 
	 public static String getPasswordRegexp() throws IOException {
		 return getPropertyObject().getProperty("passwordRegexp");
	 }
	 
	 public static String getMobileRegexp() throws IOException {
		 return getPropertyObject().getProperty("mobileRegexp");
	 }
	 
	 public static String getEmailRegexp() throws IOException {
		 return getPropertyObject().getProperty("emailRegexp");
	 }

}

