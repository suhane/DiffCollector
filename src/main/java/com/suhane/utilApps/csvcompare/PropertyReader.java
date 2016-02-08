package com.suhane.utilApps.csvcompare;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
public static Properties prop;
public static String propFileName;
	
	
		static{
		    prop = new Properties();
		    propFileName = System.getProperty("prop");
		    System.out.println("*********propFileName"+propFileName);
		    if (propFileName != null)
		    propFileName = propFileName+".properties";
		    FileInputStream inputStream;
			try {
			    inputStream = new FileInputStream(propFileName);
			    if (inputStream != null) {
			           try {
			                 prop.load(inputStream);
			           } catch (IOException e) {
			                 // TODO Auto-generated catch block
			                 e.printStackTrace();
			           }
			    } 
			} catch (FileNotFoundException e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			}
		    
		}

}
