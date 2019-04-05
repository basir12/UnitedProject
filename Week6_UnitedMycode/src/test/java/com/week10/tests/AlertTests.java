package com.week10.tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.library.Base;

public class AlertTests extends Base {
	final static Logger logger = Logger.getLogger(AlertTests.class);
@Test
public void tesing_popup_alert() {
	createalert();
	myLibrary.customWait(3);
	Alert results = isAlertPresent();
	results.accept();
	logger.info("resultbtext:"+results);
//Alert alert = driver.switchTo().alert();
}

//Using javascript to create a alert
private void createalert() {
	JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("alert('Hello! How are you?');");

}

public Alert isAlertPresent() {
	
	Alert alert = null;
	try {
	alert=driver.switchTo().alert();
	logger.info("Alert dected:{"+alert.getText()+ "}" );
	
} catch(Exception e) {
	
	logger.info("Alert not detected:");
}
	return alert;
}








}
