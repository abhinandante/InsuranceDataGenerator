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
public class App {
	static final long unixTime = System.currentTimeMillis() / 1000L;
	static String[] countryArray = { "Singpore", "Burma", "Malayasia", "Phillipines", "Bangladesh" };
	static String[] businessArray = { "0", "1", "0", "1", "0" };
	static DataFactory df = new DataFactory();
	static int flag = 0;

	public static void createDirectoryIfNeeded(String directoryName) {
		File theDir = new File(directoryName);
		// if the directory does not exist, create it
		if (!theDir.exists() && !theDir.isDirectory()) {
			theDir.mkdirs();
		}
	}

	public static void generateClientInformation(String saveDir, int row) throws IOException {

		 //String saveDir="/home/abhinandan/Desktop/dump";
		createDirectoryIfNeeded(saveDir);
		String filePath = saveDir + "/clientData_" + unixTime + ".csv";
		CSVWriter writer = new CSVWriter(new FileWriter(filePath), CSVWriter.DEFAULT_SEPARATOR,
				CSVWriter.NO_QUOTE_CHARACTER);
		String clientInfoHeader="Name,Address,Country,Family business,age,number of family members,annual income,average annual expenditure";
        writer.writeNext(clientInfoHeader.split(","));
        writer.flush();
		
		String filePath1 = saveDir + "/ClientPastClaimSummary" + unixTime + ".csv";
		CSVWriter clientPastClaimSummarywriter = new CSVWriter(new FileWriter(filePath1), CSVWriter.DEFAULT_SEPARATOR,
				CSVWriter.NO_QUOTE_CHARACTER);
		String clientPastSummaryHeader="Name,Address,Country,Number of past cliam,Money recieved on past claim,Claims received,Defaulter,Time for which premium is deposited";
		clientPastClaimSummarywriter.writeNext(clientPastSummaryHeader.split(","));
		clientPastClaimSummarywriter.flush();
		
		String filePath2 = saveDir + "/experienceinfarming" + unixTime + ".csv";
		CSVWriter Experienceinfarming = new CSVWriter(new FileWriter(filePath2), CSVWriter.DEFAULT_SEPARATOR,
				CSVWriter.NO_QUOTE_CHARACTER);
		String experienceInfarmingHeading="Name,Address,Country,Number of years in farming,Posses own land,Uses multicropping techniques,Use of tecnology in farming,Average amount of yeild,Type of farming business";
		Experienceinfarming.writeNext(experienceInfarmingHeading.split(","));
		Experienceinfarming.flush();
		
		String filePath3 = saveDir + "/technologyusage" + unixTime + ".csv";
		CSVWriter technologyusage = new CSVWriter(new FileWriter(filePath3), CSVWriter.DEFAULT_SEPARATOR,
				CSVWriter.NO_QUOTE_CHARACTER);
		String technologyusageHeader="Name,Address,Country,total area of farming,Types of crop produced,pesticides used,total amount of yeild,Use of special type of seeds,Any previous disease of crops,Loss suffered from that disease";
		technologyusage.writeNext(technologyusageHeader.split(","));
		technologyusage.flush();
		
		String filePath4 = saveDir + "/otherInsuranceownedbyclient" + unixTime + ".csv";
		CSVWriter OtherInsuranceownedby = new CSVWriter(new FileWriter(filePath4), CSVWriter.DEFAULT_SEPARATOR,
				CSVWriter.NO_QUOTE_CHARACTER);
		String OtherInsuranceownedbyHeader="Name,Address,Country,insurance on tractors,premium paid,Insurance on barns,silos and others,Premium paid,Income insurance";
		OtherInsuranceownedby.writeNext(OtherInsuranceownedbyHeader.split(","));
		OtherInsuranceownedby.flush();
		
		String filePath6 = saveDir + "/Iotinformationusage" + unixTime + ".csv";
		CSVWriter Iotinformationusage = new CSVWriter(new FileWriter(filePath6), CSVWriter.DEFAULT_SEPARATOR,
				CSVWriter.NO_QUOTE_CHARACTER);
		String IotinformationusageHeader="Name,Address,Country,Usage of IOT Information,Use of Sensors to detect moisture,Use of sensors for weather,Use of water farming technique,Types of crops grown";
		Iotinformationusage.writeNext(IotinformationusageHeader.split(","));
		
		String filePath7 = saveDir + "/Coverageofpremium" + unixTime + ".csv";
		CSVWriter Coverageofpremium = new CSVWriter(new FileWriter(filePath7), CSVWriter.DEFAULT_SEPARATOR,
				CSVWriter.NO_QUOTE_CHARACTER);
		String CoverageofpremiumHeader="Year,Prices of crops,premium prices of previousclaims,Number of premiums in the company,Number of defaulters in premium,natural disaster,disease of crops,insured amount to be paid by the company";
		Coverageofpremium.writeNext(CoverageofpremiumHeader.split(","));
		Coverageofpremium.flush();
		
		for (int i = 0; i <row; i++) {
			String name = df.getFirstName() + " " + df.getLastName();
			String address = df.getAddress();
			String country = countryArray[df.getNumberBetween(0, 4)];
			String business = businessArray[df.getNumberBetween(0, 4)];
			int age = df.getNumberBetween(18, 80);
			int familymember = df.getNumberBetween(1, 6);
			int annualincome = df.getNumberBetween(1000, 10000);
			int expenditure = df.getNumberBetween(700, annualincome);
			String csvRow = name + "," + address + "," + country + "," + business + "," + age + "," + familymember + ","
					+ annualincome + "," + expenditure;
			writer.writeNext(csvRow.split(","));
			clientPastClaimSummary(name, address, country, clientPastClaimSummarywriter);
			experienceInFarming(name, address, country, Experienceinfarming, technologyusage);
			// technologyUsage(name,address,country,technologyusage);
			otherInsuranceownedbyclient(name, address, country, OtherInsuranceownedby);
			iotInformationUsage(name, address, country, Iotinformationusage);
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

	public static void clientPastClaimSummary(String name, String address, String country, CSVWriter writer)
			throws IOException {

		int pastCliam = df.getNumberBetween(0, 5);
		int recieveCliam = df.getNumberBetween(50000, 100000);
		String paymentRecieve = businessArray[df.getNumberBetween(0, 4)];
		String defaulter = businessArray[df.getNumberBetween(0, 4)];
		int depositTermYear = df.getNumberBetween(0, 15);
		String csvRow = name + "," + address + "," + country + "," + pastCliam + "," + recieveCliam + ","
				+ paymentRecieve + "," + defaulter + "," + depositTermYear;
		writer.writeNext(csvRow.split(","));
		writer.flush();

	}

	public static void experienceInFarming(String name, String address, String country, CSVWriter writer,
			CSVWriter technologyusage) throws IOException {

		int noYearFarming = df.getNumberBetween(1, 35);
		String possesOwnLand = businessArray[df.getNumberBetween(0, 4)];
		String multiCrop = businessArray[df.getNumberBetween(0, 4)];
		String technologyFarming = businessArray[df.getNumberBetween(0, 4)];
		int avgYeild = df.getNumberBetween(500, 10000);
		String farmingBusiness = businessArray[df.getNumberBetween(0, 4)];
		String csvRow = name + "," + address + "," + country + "," + noYearFarming + "," + possesOwnLand + ","
				+ multiCrop + "," + technologyFarming + "," + avgYeild + "," + farmingBusiness;
		technologyUsage(name, address, country, avgYeild, technologyusage);
		writer.writeNext(csvRow.split(","));
		writer.flush();

	}

	public static void technologyUsage(String name, String address, String country, int avgYeild, CSVWriter writer)
			throws IOException {

		int areaFarming = df.getNumberBetween(50, 500);
		int typeofCrop = df.getNumberBetween(1, 4);
		String pesticides = businessArray[df.getNumberBetween(0, 4)];
		// int totalamount=df.getNumberBetween(500, 10000);
		int totalamount = avgYeild;
		String seeds = businessArray[df.getNumberBetween(0, 4)];
		String disease = businessArray[df.getNumberBetween(0, 4)];
		int lossSuffered = df.getNumberBetween(0, 10000);
		if(disease=="0"){
			lossSuffered=0;
		}

		String csvRow = name + "," + address + "," + country + "," + areaFarming + "," + typeofCrop + "," + pesticides
				+ "," + totalamount + "," + seeds + "," + disease + "," + lossSuffered;
		writer.writeNext(csvRow.split(","));
		writer.flush();
	}

	public static void otherInsuranceownedbyclient(String name, String address, String country, CSVWriter writer)
			throws IOException {

		String insuranceontractors = businessArray[df.getNumberBetween(0, 4)];
		int premiumPaid = df.getNumberBetween(200, 500);
		String insuranceonbarns = businessArray[df.getNumberBetween(0, 4)];
		int premiumPaidbarns = df.getNumberBetween(200, 400);
		String incomeInsurance = businessArray[df.getNumberBetween(0, 4)];
		int premiumInsurance = df.getNumberBetween(200, 400);
		if(insuranceontractors=="0"){
			premiumPaid=0;
			premiumPaidbarns=0;
			premiumInsurance=0;
		}

		String csvRow = name + "," + address + "," + country + "," + insuranceontractors + "," + premiumPaid + ","
				+ insuranceonbarns + "," + premiumPaidbarns + "," + incomeInsurance + "," + premiumInsurance;
		writer.writeNext(csvRow.split(","));
		writer.flush();

	}

	public static void iotInformationUsage(String name, String address, String country, CSVWriter writer)
			throws IOException {
		String iotInformation = businessArray[df.getNumberBetween(0, 4)];
		String detectMoisture = businessArray[df.getNumberBetween(0, 4)];
		String weather = businessArray[df.getNumberBetween(0, 4)];
		String waterfarming = businessArray[df.getNumberBetween(0, 4)];
		int cropsgrown = df.getNumberBetween(1, 5);

		String csvRow = name + "," + address + "," + country + "," + iotInformation + "," + detectMoisture + ","
				+ weather + "," + waterfarming + "," + cropsgrown;
		writer.writeNext(csvRow.split(","));
		writer.flush();
	}

	public static void coverageOfPremium(CSVWriter writer) throws IOException {
		int years = df.getNumberBetween(2000, 2016);
		int priceCrops = df.getNumberBetween(140, 180);
		int previousclaims = df.getNumberBetween(250, 500);
		int nopremiers = df.getNumberBetween(15000, 20000);
		int noofpremiers = df.getNumberBetween(10, 50);
		String naturaldisaster = businessArray[df.getNumberBetween(0, 4)];
		String diseaseofcrops = businessArray[df.getNumberBetween(0, 4)];
		int amountpaidocompany = df.getNumberBetween(5000, 15000);

		String csvRow = years + "," + priceCrops + "," + previousclaims + "," + nopremiers + "," + noofpremiers + ","
				+ naturaldisaster + "," + diseaseofcrops + "," + amountpaidocompany;
		writer.writeNext(csvRow.split(","));
		writer.flush();
	}

	public static void main(String[] args) throws IOException {
		/*if (args.length < 2) {
			System.out.println("Please enter <Destination Location> <No no Line>");
		} else {
			String destLocation = args[0];
			int row = Integer.parseInt(args[1]);
			generateClientInformation(destLocation, row);
		}*/
		
		generateClientInformation("/home/abhinandan/Desktop/dump", 100);
	}

}
