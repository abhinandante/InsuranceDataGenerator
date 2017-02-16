package com.dummy.data.InsuranceData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.fluttercode.datafactory.impl.DataFactory;

import au.com.bytecode.opencsv.CSVWriter;

public class InsuranceMockUp {
	
	private static final long unixTime = System.currentTimeMillis() / 1000L;
	private static final DataFactory df = new DataFactory();
	private static final String[] countryArray = { "Singapore", "Burma", "Malaysia", "Phillippines", "Bangladesh" };
	private static final String[] businessArray = { "0", "1", "0", "1", "0" };
	private static final String[] AssetsTypes = {"farmland","tractor","farming", "equipments","seeds"};
	private static final String[] crop={"Rice","Wheat","Maize","Barley"};
	private static final String[] InsuranceTypes={"fire","flood", "disease of crops", "insect attack", "birds attack", "storm","others"};
	private static final String[] InsuranceNames={"Fire_Savior","flood_Protector", "crops_controller", "insect_salvager", "birds_salvager", "storm_Guardian","others"};

	//create Calendar instance
    private static final Calendar now = Calendar.getInstance();
	
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
		String clientAssetswriterInfoHeader = "AssetsID,ZoneID,Address,Country,types_of_assets,types_of_crops,total_yeild_yearly,multicropping,assets_type(shared/own)";
		clientAssetswriter.writeNext(clientAssetswriterInfoHeader.split(","));
		clientAssetswriter.flush();
		
		//Client FH_Insurance_Types Generation
		String filePath2 = saveDir + "/policyTypes" + unixTime + ".csv";
		CSVWriter fhInsuranceType = new CSVWriter(new FileWriter(filePath2), CSVWriter.DEFAULT_SEPARATOR,
				CSVWriter.NO_QUOTE_CHARACTER);
		String fhInsuranceTypeHeader = "PolicyID,Types_of_Insurance,Insurance_Name,Insurance_Amount,Premium_Amount,Coverage_Amount";
		fhInsuranceType.writeNext(fhInsuranceTypeHeader.split(","));
		fhInsuranceType.flush();
		
		//Client Client_Policy Generation
		String filePath3 = saveDir + "/clientPolicy" + unixTime + ".csv";
		CSVWriter clientPolicy = new CSVWriter(new FileWriter(filePath3), CSVWriter.DEFAULT_SEPARATOR,
				CSVWriter.NO_QUOTE_CHARACTER);
		String clientPolicyHeader = "Id,PolicyID,Asset_ID,Zone_ID,Client_ID";
		clientPolicy.writeNext(clientPolicyHeader.split(","));
		clientPolicy.flush();
		
		//Client Pest Control Generation
		String filePath4 = saveDir + "/pestControl" + unixTime + ".csv";
		CSVWriter pestControl = new CSVWriter(new FileWriter(filePath4), CSVWriter.DEFAULT_SEPARATOR,
						CSVWriter.NO_QUOTE_CHARACTER);
		String pestControlHeader = "ID,HYM_Seed,Pesticide_Use,Recent_Disease,Recent_loss,Asset ID,Zone ID";
		pestControl.writeNext(pestControlHeader.split(","));
		pestControl.flush();
		
		
		//Client IoT Data
		String filePath5 = saveDir + "/iotData" + unixTime + ".csv";
		CSVWriter iotData = new CSVWriter(new FileWriter(filePath5), CSVWriter.DEFAULT_SEPARATOR,
						CSVWriter.NO_QUOTE_CHARACTER);
		String iotDataHeader = "Id,Time_Stamp,Soil_Mosisture,Temperature,Humidity,Nutrient_Lvl,Vibration,CO2_Concentration,Smoke_Alert,Sound_vibration,Asset_id,Zone_ID";
		iotData.writeNext(iotDataHeader.split(","));
		iotData.flush();
		
		//Client claims Data
		String filePath6 = saveDir + "/claimsData" + unixTime + ".csv";
		CSVWriter claimsData = new CSVWriter(new FileWriter(filePath6), CSVWriter.DEFAULT_SEPARATOR,
								CSVWriter.NO_QUOTE_CHARACTER);
		String claimsDataHeader = "ID,Claim_Amount,claim_Date,Defaulter,Premium_Qualified,client_id,policy_id";
		claimsData.writeNext(claimsDataHeader.split(","));
		claimsData.flush();
		
		
		
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
			String Client_Assets = generateClinetAsset(address,country,ClientID,clientAssetswriter);
			String PolicyID = generatePolicyTypes(family_income, fhInsuranceType);
			String Client_Assets_Zone[] = Client_Assets.split("_");
			generateClientPolicy(PolicyID, Client_Assets_Zone[0], Client_Assets_Zone[1], ClientID, clientPolicy);
			genetateClaims(ClientID, PolicyID, claimsData);
			generatePestControl(Client_Assets_Zone[0], Client_Assets_Zone[1], pestControl);
			iotData(Client_Assets_Zone[0], Client_Assets_Zone[1], iotData);
			writer.writeNext(csvRow.split(","));
			writer.flush();
		}
		
		writer.close();
		clientAssetswriter.close();
		fhInsuranceType.close();
		clientPolicy.close();
		claimsData.close();
		pestControl.close();
		iotData.close();
		
		
	}
	
	
	public static String generateClinetAsset(String Address,String Country,String ClientID, CSVWriter writer) throws IOException{
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
		return AssetsID+"_"+zoneID;
		
	}
	
	public static String generatePolicyTypes(int income,CSVWriter writer) throws IOException{
		String policyID=df.getRandomChars(2)+String.valueOf(df.getNumberBetween(11111111, 999999999));
		int index=df.getNumberBetween(0, 6);
		String typesofInsurance=InsuranceTypes[index];
		String Insurance_Name=InsuranceNames[index];
		int Insurance_Ammount=df.getNumberBetween(income/10, (income/10)*2);
		int Premium_Ammount=df.getNumberBetween(200, 1500);
		int Coverage_Amount=df.getNumberBetween(50000, 100000);
		String csvRow=policyID+","+typesofInsurance+","+Insurance_Name+","+Insurance_Ammount+","+Premium_Ammount+","+Coverage_Amount;
		writer.writeNext(csvRow.split(","));
		writer.flush();
		return policyID;
	}
	
	
	public static void generateClientPolicy(String policy_id,String Asset_id,String Zone_id,String Client_id,CSVWriter writer) throws IOException{
		String clientPolicyID = df.getRandomChars(2)+String.valueOf(df.getNumberBetween(1121578, 999999999));
		String csvRow=clientPolicyID+","+policy_id+","+Asset_id+","+Zone_id+","+Client_id;
		writer.writeNext(csvRow.split(","));
		writer.flush();
	} 
	
	public static void iotData(String AssetsID, String zoneID, CSVWriter writer) throws IOException {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		String ID = df.getRandomChars(2)+String.valueOf(df.getNumberBetween(10000000, 999999999));
		String iotDateTime = ft.format(dNow);
		String zoneid = zoneID;
		String Assetsid = AssetsID;
		String latitude = "1.35" + df.getNumberBetween(20, 99);
		String logitude = "103.81" + df.getNumberBetween(20, 99);
		String soilmoisture = "0." + df.getNumberBetween(0, 45);
		String temperature = String.valueOf(df.getNumberBetween(0, 40));
		String humidity = String.valueOf(df.getNumberBetween(0, 100));
		String nutrientlevel = String.valueOf(df.getNumberBetween(0, 15));
		int smokealarm = df.getNumberBetween(0, 2);
		int co2concentration = df.getNumberBetween(250, 500);
		int vibration = df.getNumberBetween(10, 180);
		int sound_vibration = df.getNumberBetween(10, 180);
		String csvRow = ID+","+iotDateTime + "," + soilmoisture + "," + temperature + "," + humidity + "," + nutrientlevel + ","
				+ vibration + "," + co2concentration + "," + smokealarm + "," + sound_vibration + "," + Assetsid + ","
				+ zoneid;
		writer.writeNext(csvRow.split(","));
		writer.flush();

	}
	
	
	public static void generatePestControl(String AssetsID, String zoneID, CSVWriter writer) throws IOException {
		
		String pestID=df.getRandomChars(1)+String.valueOf(df.getNumberBetween(1000000, 999999999));
		String pesticides = businessArray[df.getNumberBetween(0, 4)];
		String seeds = businessArray[df.getNumberBetween(0, 4)];
		String disease = businessArray[df.getNumberBetween(0, 4)];
		int lossSuffered = df.getNumberBetween(0, 10000);
		if (disease == "0") {
			lossSuffered = 0;
		}

		String csvRow = pestID + "," + seeds +"," +pesticides+ "," + disease + "," + lossSuffered + "," + AssetsID + "," + zoneID ;
		writer.writeNext(csvRow.split(","));
		writer.flush();
	}
	
	
	public static void genetateClaims(String Client_ID,String policyID, CSVWriter writer) throws IOException{
		
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		String claimsID=df.getRandomChars(1)+String.valueOf(df.getNumberBetween(22222222, 888888888));
		int claimAmmount=df.getNumberBetween(50000, 100000);
		now.add(Calendar.MONTH, -3);
		String claimsDate =ft.format(df.getDateBetween(now.getTime(), new Date()));
		String defaulter = businessArray[df.getNumberBetween(0, 4)]; 
		int preminumQualified = df.getNumberBetween(10000, 80000);
		String csvRow=claimsID+","+claimAmmount+","+claimsDate+","+defaulter+","+preminumQualified+","+Client_ID+","+policyID;
		writer.writeNext(csvRow.split(","));
		writer.flush();
	}
	
	
	public static void main(String args[]) throws IOException{
     
		String saveDir="/home/abhinandan/Desktop/dump";
		long row=1000;
		generateClientInformation(saveDir,row);
		
	}

}
