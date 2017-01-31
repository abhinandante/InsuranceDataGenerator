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
		CSVWriter clientPastClaimSummarywriter = new CSVWriter(new FileWriter(filePath1),CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER);
		
		String filePath2=saveDir + "/experienceinfarming" + unixTime + ".csv";
		CSVWriter Experienceinfarming = new CSVWriter(new FileWriter(filePath2),CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER);
		
		String filePath3=saveDir + "/technologyusage" + unixTime + ".csv";
		CSVWriter technologyusage = new CSVWriter(new FileWriter(filePath3),CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER);

		
		String filePath4=saveDir + "/otherInsuranceownedbyclient" + unixTime + ".csv";
		CSVWriter OtherInsuranceownedby = new CSVWriter(new FileWriter(filePath4),CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER);

		String filePath6=saveDir + "/Iotinformationusage" + unixTime + ".csv";
		CSVWriter Iotinformationusage = new CSVWriter(new FileWriter(filePath6),CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER);

		String filePath7=saveDir + "/Coverageofpremium" + unixTime + ".csv";
		CSVWriter Coverageofpremium = new CSVWriter(new FileWriter(filePath7),CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER);

		
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
			clientPastClaimSummary(name,address,country,clientPastClaimSummarywriter);
			experienceInFarming(name,address,country,Experienceinfarming);
			technologyUsage(name,address,country,technologyusage);
			otherInsuranceownedbyclient(name,address,country,OtherInsuranceownedby);
			iotInformationUsage(name,address,country,Iotinformationusage);
			coverageOfPremium(Coverageofpremium);
			writer.flush();			
		}
		
		writer.close();
		clientPastClaimSummarywriter.close();
		Experienceinfarming.close();
		technologyusage.close();
		OtherInsuranceownedby.close();
		Coverageofpremium.close();
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
		
	}
	
	public static void experienceInFarming(String name,String address,String country,CSVWriter writer) throws IOException{
		
		int noYearFarming=df.getNumberBetween(1, 35);
		String possesOwnLand=businessArray[df.getNumberBetween(0, 4)];
		String multiCrop=businessArray[df.getNumberBetween(0, 4)];
		String technologyFarming=businessArray[df.getNumberBetween(0, 4)];
		int avgYeild=df.getNumberBetween(500, 10000);
		String farmingBusiness=businessArray[df.getNumberBetween(0, 4)];
		String csvRow=name+","+address+","+country+","+noYearFarming+","+possesOwnLand+","+multiCrop+","+technologyFarming+","+avgYeild+","+farmingBusiness;
		writer.writeNext(csvRow.split(","));
		writer.flush();
		
	}
	
	
	public static void technologyUsage(String name,String address,String country,CSVWriter writer) throws IOException{
		
		int areaFarming=df.getNumberBetween(50, 500);
		int typeofCrop=df.getNumberBetween(1, 4);
		String pesticides=businessArray[df.getNumberBetween(0, 4)];
		int totalamount=df.getNumberBetween(500, 10000);
		String seeds=businessArray[df.getNumberBetween(0, 4)];
		String disease=businessArray[df.getNumberBetween(0, 4)];
		int lossSuffered=df.getNumberBetween(0, 10000);
		
		String csvRow=name+","+address+","+country+","+areaFarming+","+typeofCrop+","+pesticides+","+totalamount+","+seeds+","+disease+","+lossSuffered;
		writer.writeNext(csvRow.split(","));
		writer.flush();
	}
	
	
	
	public static void otherInsuranceownedbyclient(String name,String address,String country,CSVWriter writer) throws IOException{
		
		String insuranceontractors=businessArray[df.getNumberBetween(0, 4)];
		int premiumPaid=df.getNumberBetween(200, 500);
		String insuranceonbarns=businessArray[df.getNumberBetween(0, 4)];
		int premiumPaidbarns=df.getNumberBetween(200, 400);
		String incomeInsurance=businessArray[df.getNumberBetween(0, 4)];
		int premiumInsurance=df.getNumberBetween(200, 400);
		
		String csvRow=name+","+address+","+country+","+insuranceontractors+","+premiumPaid+","+insuranceonbarns+","+premiumPaidbarns+","+incomeInsurance+","+premiumInsurance;
		writer.writeNext(csvRow.split(","));
		writer.flush();
		
	}
	
	
	public static void iotInformationUsage(String name,String address,String country,CSVWriter writer) throws IOException{
		String iotInformation=businessArray[df.getNumberBetween(0, 4)];
		String detectMoisture=businessArray[df.getNumberBetween(0, 4)];
		String weather=businessArray[df.getNumberBetween(0, 4)];
		String waterfarming=businessArray[df.getNumberBetween(0, 4)];
		int cropsgrown=df.getNumberBetween(1, 5);
		
		String csvRow=name+","+address+","+country+","+iotInformation+","+detectMoisture+","+weather+","+waterfarming+","+cropsgrown;
		writer.writeNext(csvRow.split(","));
		writer.flush();
	}
	
	
	public static void coverageOfPremium(CSVWriter writer) throws IOException{
		int years=df.getNumberBetween(2000, 2016);
		int priceCrops=df.getNumberBetween(140, 180);
		int previousclaims=df.getNumberBetween(250, 500);
		int nopremiers=df.getNumberBetween(15000, 20000);
		int noofpremiers=df.getNumberBetween(10, 50);
		String naturaldisaster=businessArray[df.getNumberBetween(0, 4)];
		String diseaseofcrops=businessArray[df.getNumberBetween(0, 4)];
		int amountpaidocompany=df.getNumberBetween(5000, 15000);
		
		String csvRow=years+","+priceCrops+","+previousclaims+","+nopremiers+","+noofpremiers+","+naturaldisaster+","+diseaseofcrops+","+amountpaidocompany;
		writer.writeNext(csvRow.split(","));
		writer.flush();
	}
	
	
    public static void main( String[] args ) throws IOException
    {
        generateClientInformation();
    }
    
    
    
}
