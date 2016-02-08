package com.suhane.utilApps.csvcompare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ParseCSV {

	private static String cvsSplitBy = "\\t+";

	public List<String> parseCSV(String file) {

    	
    	File csvFormat=returnCSV(file);
    	if(csvFormat==null)
    		System.exit(1);
		BufferedReader br = null;
    	String line = "";
    	int i=0;
    	int columnCount=0;
    	int columnNumber=0;
    	final List<String> testName = new ArrayList<String>();
    	try {
    		br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(csvFormat.getAbsolutePath()), "UTF8"));

    		//br = new BufferedReader(new FileReader(csvFormat.getAbsolutePath()));
    		while ((line = br.readLine()) != null) {

    			if((i++)!=0){	
    			String[] column = line.split(cvsSplitBy);
    			if(column.length==columnCount){
    				testName.add(column[columnNumber].toString());
    			}
    			}
    			else{
    				String[] headercolumn=line.split(cvsSplitBy);
    				columnCount=headercolumn.length;
    				for(int k=0;k<headercolumn.length;k++){
    					if(headercolumn[k].equalsIgnoreCase("Test Name") || headercolumn[k].equalsIgnoreCase("TestName")){
    						columnNumber=k;
    						break;
    					}
    				}
    			}

    		}
    		

    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	} finally {
    		if (br != null) {
    			try {
    				br.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}

    	return testName;
      }

	private File returnCSV(String file) {
		if(file.endsWith(".xlsx")){
			ExcelToCSV obj= new ExcelToCSV();
			File CSV=obj.convertToXlsx(new File(file));
			cvsSplitBy=",";
			return CSV;
		}
		if(file.endsWith(".xls")){
			ExcelToCSV obj= new ExcelToCSV();
			File CSV=obj.convertToXls(new File(file));
			cvsSplitBy=",";
			return CSV;
		}
		if(file.endsWith(".csv")){
			return new File(file);
		}
		System.out.println("The file format should be .csv or .xlsx or .xls");
		return null;
	}
	
}
