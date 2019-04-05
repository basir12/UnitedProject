package com.week11.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.library.Base;
import com.library.ExcelManager;

public class CalculatorTest extends Base {
	private int counter =0;
	
	
	@BeforeMethod
	public void beforeTest() {
		driver.get("https://www.mortgagecalculator.net/");
		String browserTitle = driver.getTitle();
		//System.out.println("website title is:" + browserTitle + "'");
		Assert.assertEquals(browserTitle, "Mortgage Calculator 2019 - FREE & Easy Calculator Tool");
	}
	
@DataProvider(name = "MortgageTestData")
	
	private Object [][] calculatorData() {
	ExcelManager excelReader = new ExcelManager("src/test/resources/data/CalculatorTestData.xls");
	Object [][] data;
	data=excelReader.getExcelData("Calculator");
	return data;
}
	
	@Test (dataProvider = "MortgageTestData")
	public void dataDrivenTests(String amount,String Myear,String Mmonth,String intYear,String intMonth,
			String intType,String intRate,String startMonth,String strartYear,String paymentPeriod,String expectedResults) {
		try {
		counter++;
		myLibrary.enterTextField(By.id("amount"), amount);
		myLibrary.enterTextField(By.xpath("//*[@id='amortizationYears']"), Myear);
		myLibrary.enterTextField(By.cssSelector("#amortizationMonths"), Mmonth);
		myLibrary.enterTextField(By.id("interestTermYears"), intYear);
		myLibrary.enterTextField(By.cssSelector("#interestTermMonths"), intMonth);
		myLibrary.selectDropDown(By.id("interestType"), intType);
		myLibrary.enterTextField(By.xpath("//*[@id='rate']"), intRate); 
		myLibrary.selectDropDown(By.id("startMonth"),Integer.valueOf(startMonth));
		myLibrary.selectDropDown(strartYear, By.id("startYear"));
		myLibrary.selectDropDown(By.cssSelector("#paymentMode"),paymentPeriod);
		myLibrary.clickButton(By.cssSelector("#button"));
		Thread.sleep(2000);
		
		WebElement monthlyPaymentResults = driver.findElement(By.id("summaryMonthly"));
		String monthlyPaymentTxt = monthlyPaymentResults.getAttribute("value");
		System.out.println("Test Scenario : " + counter +", Monthly payment amount is: "+ monthlyPaymentTxt + " Expected:["+  expectedResults+"]");
		//myLibrary.clickButton(By.id("showCalculator"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
