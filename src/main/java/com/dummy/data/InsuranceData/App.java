package com.dummy.data.InsuranceData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.fluttercode.datafactory.impl.DataFactory;

import au.com.bytecode.opencsv.CSVWriter;

/**
 * Hello world!
 *
 */
public class App 
{
	static final long unixTime = System.currentTimeMillis() / 1000L;
	static String[] countryArray = {"Singpore", "Burma", "Malayasia", "Phillipines", "Bangladesh"}; 
	static String[] businessArray = {"0","1","0","1","0"};
	static DataFactory df = new DataFactory();
	static int flag=0;
	
	public static void createDirectoryIfNeeded(String directoryName) {
		File theDir = new File(directoryName);
		// if the directory does not exist, create it
		if (!theDir.exists() && !theDir.isDirectory()) {
			theDir.mkdirs();
		}
	}
	
	public static void generateClientInformation() throws IOException{
		
		String saveDir="/home/abhinandan/Desktop/";
		createDirectoryIfNeeded(saveDir);
		String filePath=saveDir + "/clientData_" + unixTime + ".csv";
		CSVWriter writer = new CSVWriter(new FileWriter(filePath),CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER);
		
		String filePath1=saveDir + "/ClientPastClaimSummary" + unixTime + ".csv";
		CSVWriter writer1 = new CSVWriter(new FileWriter(filePath1),CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER);
		
		for(int i=0;i<100;i++){
			String name=df.getFirstName()+" "+df.getLastName();
			String address=df.getAddress();
			String country=countryArray[df.getNumberBetween(0, 4)];
			String business=businessArray[df.getNumberBetween(0, 4)];
			int age = df.getNumberBetween(18, 80);
			int familymember= df.getNumberBetween(1, 6);
			int annualincome=df.getNumberBetween(1000, 10000);
			int expenditure=df.getNumberBetween(700, annualincome);
			String csvRow=name+","+address+","+country+","+business+","+age+","+familymember+","+annualincome+","+expenditure;
			writer.writeNext(csvRow.split(","));
			clientPastClaimSummary(name,address,country,writer1);
			writer.flush();
			if(i==100){
				flag=1;
			}
			
		}
		
		writer.close();
	}
	
	public static void clientPastClaimSummary(String name,String address,String country,CSVWriter writer) throws IOException{
		
		
		
		
		int pastCliam=df.getNumberBetween(0, 5);
		int recieveCliam=df.getNumberBetween(50000, 100000);
		String paymentRecieve=businessArray[df.getNumberBetween(0, 4)];
		String defaulter= businessArray[df.getNumberBetween(0, 4)];
		int depositTermYear=df.getNumberBetween(0, 15);
		String csvRow=name+","+address+","+country+","+pastCliam+","+recieveCliam+","+paymentRecieve+","+defaulter+","+depositTermYear;
		writer.writeNext(csvRow.split(","));
		writer.flush();
		if(flag==1){
			writer.close();
		}
		
	}
	
    public static void main( String[] args ) throws IOException
    {
        generateClientInformation();
    }
    
    
    
}
