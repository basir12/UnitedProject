package com.week10.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.library.Base;

public class WindowHandleTest  extends Base{
	final static Logger logger = Logger.getLogger(WindowHandleTest.class);
@Test(enabled = false)
public void testing_two_browsers() {
	driver.get("https://www.guru99.com/popup.php/");
	
	String browser1 = driver.getWindowHandle();
	logger.info("Browers:"+browser1);
	myLibrary.highlightElement(By.tagName("body"));
	WebElement clickHereLink = driver.findElement(By.partialLinkText("Click Here"));
	clickHereLink.click();
	Set<String> browsers2 = driver.getWindowHandles();
	logger.info("Browers:"+browsers2);
	
	Iterator<String>iterator = browsers2.iterator();
	while(iterator.hasNext()) {
		String window = iterator.next();
		if(!browser1.equals(window)) {
			driver.switchTo().window(window);	
		myLibrary.highlightElement(By.tagName("body"));
		}
		
	}
	
}
@Test(enabled = true)
public void testing_morethantwo_browsers() {
	driver.get("http://demo.guru99.com/popup.php/");
	
	String browser1 = driver.getWindowHandle();
	logger.info("Browers:"+browser1);
	
	WebElement clickHereLink = driver.findElement(By.partialLinkText("Click Here"));
	clickHereLink.click();
	WebElement clickHereLink2 = driver.findElement(By.partialLinkText("Click Here"));
	clickHereLink2.click();
	
	Set<String> Allbrowsers = driver.getWindowHandles();
	logger.info("Browers:"+Allbrowsers);
	
	Iterator<String> iterator = Allbrowsers.iterator();
	List<String> windowHandles = new ArrayList<>();
	
	while(iterator.hasNext()) {
		String window = iterator.next();
		String singleHandle = driver.switchTo().window(window).getWindowHandle();
		windowHandles.add(singleHandle);
		}
	
	driver.switchTo().window(windowHandles.get(2));		
	myLibrary.highlightElement(By.tagName("body"));
}
}
