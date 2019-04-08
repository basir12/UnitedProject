package com.week9.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.library.Base;

public class BrokenLikns_TestPractice extends Base{
	final static Logger logger = Logger.getLogger(BrokenLikns_TestPractice.class);
	public static int invalidLink;
    String currentLink;
    String temp;
	
	@Test(enabled= false)
public void testingScrollToElement() {
	try {
	driver.get("https://www.costco.com/");
	WebElement searchField = driver.findElement(By.id("footer-search-field"));
	myLibrary.scrollToWebElement(searchField);
	//searchField.sendKeys("Ashburn, VA");
	//WebElement searchBTN = driver.findElement(By.xpath("//form[@id='WarehouseSearchForm']//i[@class='co-search-thin']"));//this element is not working in IE
	
	//searchBTN.click();
	myLibrary.customWait(2);
	
	}catch(Exception e) {
		logger.info("Error: ", e);
	}
}

@Test(enabled = true)

public void testinglinksingoogle() {
	driver.get("http:www.walmart.com");
	// Get all the links
	List<WebElement> ele = driver.findElements(By.tagName("a"));
	System.out.println("size:" + ele.size());
	boolean isValid = false;
	for (int i = 0; i < ele.size(); i++) {
	    String nextHref = ele.get(i).getAttribute("href");
	    isValid = getResponseCode(nextHref);
	    if (isValid) {
	       // System.out.println("Valid Link:" + nextHref);

	    }
	    else {
	        System.out.println("INVALID Link:" + nextHref);

	    }   
	}
}

public static boolean getResponseCode(String urlString) {
	
	 boolean isValid = false;
     try {
         URL u = new URL(urlString);
         HttpURLConnection h = (HttpURLConnection) u.openConnection();
         h.setRequestMethod("GET");
         h.connect();
         //System.out.println(h.getResponseCode());
         if (h.getResponseCode() != 404) {
             isValid = true;
         }
     } catch (Exception e) {

     }
	

	return isValid;


	
	
	
	
}	
	
}

