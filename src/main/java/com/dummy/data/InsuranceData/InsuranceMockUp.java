package com.dummy.data.InsuranceData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.fluttercode.datafactory.impl.DataFactory;

import au.com.bytecode.opencsv.CSVWriter;

public class InsuranceMockUp {
	
	private static final long unixTime = System.currentTimeMillis() / 1000L;
	private static final DataFactory df = new DataFactory();
	private static final String[] countryArray = { "Singpore", "Burma", "Malayasia", "Phillipines", "Bangladesh" };
	private static final String[] businessArray = { "0", "1", "0", "1", "0" };
	private static final String[] AssetsTypes = {"farmland","tractor","farming", "equipments","seeds"};
	private static final String[] crop={"Rice","Wheat","Maize","Barley"};
	private static final String[] InsuranceTypes={"fire","flood", "disease of crops", "insect","attack", "birds attack", "storm","others"};
	
	public static void createDirectoryIfNeeded(String directoryName) {
		File theDir = new File(directoryName);
		// if the directory does not exist, create it
		if (!theDir.exists() && !theDir.isDirectory()) {
			theDir.mkdirs();
		}
	}
	
	public static void generateClientInformation(String saveDir,long row) throws IOException{
		
		//Client Dataset Generation
		String filePath = saveDir + "/client_" + unixTime + ".csv";
		CSVWriter writer = new CSVWriter(new FileWriter(filePath), CSVWriter.DEFAULT_SEPARATOR,
				CSVWriter.NO_QUOTE_CHARACTER);
		String clientInfoHeader = "ClientID,FName,LName,Address,Country,Phone Number,Family Business,Age,Family_Member_Cnt,Income,Experience";
		writer.writeNext(clientInfoHeader.split(","));
		writer.flush();
		
		//Client Assets Dataset Generation
		String filePath1 = saveDir + "/clientAssets" + unixTime + ".csv";
		CSVWriter clientAssetswriter = new CSVWriter(new FileWriter(filePath1), CSVWriter.DEFAULT_SEPARATOR,
				CSVWriter.NO_QUOTE_CHARACTER);
		String clientAssetswriterInfoHeader = "AssetsID,ZoneID,Address,Country,types_of_assets,types_of_crops,total_yeild_yearly,multicropping,assets_type";
		clientAssetswriter.writeNext(clientAssetswriterInfoHeader.split(","));
		clientAssetswriter.flush();
		
		//Client FH_Insurance_Types Generation
		String filePath2 = saveDir + "/clientAssets" + unixTime + ".csv";
		CSVWriter fhInsuranceType = new CSVWriter(new FileWriter(filePath2), CSVWriter.DEFAULT_SEPARATOR,
				CSVWriter.NO_QUOTE_CHARACTER);
		String fhInsuranceTypeHeader = "PolicyID,Types_of_Insurance,Insurance_Name,Insurance_Amount,Premium_Amount,Coverage_Amount";
		fhInsuranceType.writeNext(fhInsuranceTypeHeader.split(","));
		fhInsuranceType.flush();
		
		for(long i=0;i<row;i++){
			String ClientID=df.getRandomChars(1)+String.valueOf((df.getNumberBetween(100000000, 999999999)));
			String fName = df.getFirstName();
			String lName = df.getLastName();
			String address = df.getAddress();
			String country = countryArray[df.getNumberBetween(0, 4)];
			String phno = df.getNumberText(10);
			String family_business= businessArray[df.getNumberBetween(0, 4)];
			int age = df.getNumberBetween(18, 70);
			int family_member_cnt = df.getNumberBetween(0, 4);
			int family_income = df.getNumberBetween(1000, 10000);
			int experince = df.getNumberBetween(2, 15);
			
			String csvRow = ClientID+","+fName+","+lName+","+address+","+country+","+phno+","+family_business+","+age+","+family_member_cnt+","+family_income+","+experince;
			writer.writeNext(csvRow.split(","));
			writer.flush();
		}
		
	}
	
	
	public static void generateClinetAsset(String Address,String Country,String ClientID, CSVWriter writer) throws IOException{
		String AssetsID=df.getRandomChars(2)+String.valueOf(df.getNumberBetween(100000000, 999999999));
		String zoneID = df.getRandomChars(2)+String.valueOf(df.getNumberBetween(100000000, 999999999));
		String types_of_assets = AssetsTypes[df.getNumberBetween(0, 4)];
		String types_of_crops = crop[df.getNumberBetween(0, 3)];
		int total_yeild_yearly=df.getNumberBetween(2000, 8000);
		String multicropping = businessArray[df.getNumberBetween(0, 4)];
		String assets_type=businessArray[df.getNumberBetween(0, 4)];
		String csvRow=AssetsID+","+zoneID+","+Address+","+Country+","+types_of_assets+","+types_of_crops+","+total_yeild_yearly+","+multicropping+","+assets_type;
		writer.writeNext(csvRow.split(","));
		writer.flush();
		
	}
	
	public static void generatePolicyTypes(int income,CSVWriter writer) throws IOException{
		String policyID=df.getRandomChars(2)+String.valueOf(df.getNumberBetween(11111111, 999999999));
		String typesofInsurance=InsuranceTypes[df.getNumberBetween(0, 8)];
		String Insurance_Name="";
		int Insurance_Ammount=df.getNumberBetween(income/10, (income/10)*2);
		int Premium_Ammount=0;
		int Coverage_Amount=0;
		String csvRow=policyID+","+typesofInsurance+","+Insurance_Name+","+Insurance_Ammount+","+Premium_Ammount+","+Coverage_Amount;
		writer.writeNext(csvRow.split(","));
		writer.flush();
	}
	
	
	public static void generateClientPolicy(String policy_id,String Asset_id,String Zone_id,String Client_id,CSVWriter writer) throws IOException{
		String clientPolicyID = df.getRandomChars(2)+String.valueOf(df.getNumberBetween(1121578, 999999999));
		String csvRow=clientPolicyID+","+policy_id+","+Asset_id+","+Zone_id+","+Client_id;
		writer.writeNext(csvRow.split(","));
		writer.flush();
	} 

}
