package com.united.pages;

import org.openqa.selenium.By;

import com.library.Base;

public class HomePage extends Base {

	public HomePage goto_United_Website() {

		driver.get("https://www.united.com/");
		String pageTitle = driver.getTitle();
		System.out.println("Website title is " + pageTitle);
		//String Excepted = "United Airlines – Airline Tickets, Travel Deals and Flights";
		//assertEquals(pageTitle,Excepted );
		return this;
	}
public HomePage selectOneWay() {
	myLibrary.handleCheckBoxRadioBTN(By.id("oneway"), true);
	
	return this;
}

public HomePage selectRoundTrip() {
myLibrary.handleCheckBoxRadioBTN(By.id("roundtrip"), true);
	
return this;
	
}
public HomePage enterFromAirport(String inputOriginAirportName, int airPortIndex) {
	try {
	myLibrary.enterTextField(By.id("bookFlightOriginInput"), inputOriginAirportName);
	Thread.sleep(2000);
	
	//myLibrary.selectDropDown("bookFlightOriginInput", "Washington DCA");
	//myLibrary.selectDropDown("3", By.id("bookFlightOriginInput"));
	
	myLibrary.clickButton(By.id("downshift-0-item-" + airPortIndex));
	}catch(Exception e) {
	e.printStackTrace();
	}
	return this;
}

public HomePage enterToAirport(String inputOriginAirportName, int airPortIndex) {
	try {
	myLibrary.enterTextField(By.id("bookFlightDestinationInput"), inputOriginAirportName);	
	Thread.sleep(2000);
	myLibrary.clickButton(By.id("downshift-1-item-" + airPortIndex));
	
	}catch(Exception e) {
		e.printStackTrace();
		}
	return this;
}


}
