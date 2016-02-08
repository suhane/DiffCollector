package com.suhane.utilApps.csvcompare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



/**
 * Hello world!
 *
 */
public class CSVdiffCollector 
{
    
	private static String currentCSV;
	private static String previousCSV;

	public static void main( String[] args )
    {
    	System.out.println("Passed " + args.length + " arguments");
    	if(args.length!=2)
    	{
	    	System.out.println("Required 2 arguments.Exiting the application");
	    	System.exit(1);
    	}
    	if((args[0].contains("current=") && args[1].contains("previous=")) || (args[1].contains("current=") && args[0].contains("previous="))){
    		if((args[0].contains("current=") && args[1].contains("previous="))){
    			currentCSV=args[0].split("=")[1];
    			previousCSV=args[1].split("=")[1];
    		}
    		else{
    			currentCSV=args[1].split("=")[1];
    			previousCSV=args[0].split("=")[1];
    		}
    	}
    	else{
    		System.out.println("Improper format of arguments. Should be passed as --current=<path-to-csv> --previous=<path-to-csv>");
    		System.exit(1);
    	}
    	ParseCSV obj = new ParseCSV();
    	List<String> targetList=obj.parseCSV(currentCSV);
    	List<String> masterList=obj.parseCSV(previousCSV);
    	writeOutputCSV(targetList,masterList);
    	}

	private static void writeOutputCSV(List<String> targetList,
			List<String> masterList) {
		System.out.println(targetList);
		System.out.println(masterList);
		List<String> outputList= new ArrayList<String>();
		HashMap<String,Integer> hm=new HashMap<String,Integer>();
		int index=0;
		while(index<masterList.size()){
			hm.put(masterList.get(index), index++);
		}
		for(int i=0;i<targetList.size();i++){
			System.out.println(targetList.get(i));
			System.out.println(hm.get(targetList.get(i)));
			if(hm.containsKey(targetList.get(i))){
				outputList.add(targetList.get(i));
			}
		}
		System.out.println(outputList);	
	}
		
	
    }
    

