package com.greatcourse.pages;

import static org.testng.Assert.assertNotNull;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.library.Base;

public class CheckOutProgressPage extends Base{


	public CheckOutProgressPage waitUntilPageLoadComplete() {

		WebElement continueBtn = myLibrary.fluentWait(By.id("checkout-sigin"));
		Assert.assertNotNull(continueBtn, "wait for checkout progress page loading - failed");

		return this;


	}
public CheckOutProgressPage complete_checkoutMethodSection(String emailaddress ) {
	
	myLibrary.enterTextField(By.id("login-email"), emailaddress);
	//myLibrary.handleCheckBoxRadioBTN(By.id("no_have_pass"), true);
	myLibrary.clickHiddenElement(By.id("no_have_pass"));
	myLibrary.clickButton(By.id("checkout-sigin"));
	
	
	return this;
}
	
public 	CheckOutProgressPage comlepeBillingAddressSection() {
	
WebElement emailElem= myLibrary.waitForElementVisiblity(By.id("billing:firstname"));
	
	myLibrary.enterTextField(emailElem, "Lakshmi");
	myLibrary.enterTextField(By.id("billing:lastname"), "Sunkara");
	myLibrary.enterTextField(By.id("billing:street1"), "10 apple street");
	myLibrary.enterTextField(By.id("billing:city"), "Moon");
	myLibrary.selectDropDown(By.id("billing:region_id"), "Virginia");
	myLibrary.enterTextField(By.id("billing:postcode"), "12345");
	myLibrary.enterTextField(By.id("billing:customer_password"), "123456");
	myLibrary.enterTextField(By.id("billing:confirm_password"), "123456");
	WebElement btn=driver.findElement(By.id("billing-buttons-container"));
	WebElement continuebtn=btn.findElement(By.tagName("button"));
	continuebtn.click();
	
	return this;
}
	
public 	CheckOutProgressPage complete_shipping_method() {
	
	WebElement shippingSection = myLibrary.waitForElementVisiblity(By.id("checkout-shipping-method-load"));
	assertNotNull(shippingSection);
	
	myLibrary.clickButton(By.cssSelector("#shipping-method-buttons-container > button > span > span"));
	
	return this;
}

	public CheckOutProgressPage complete_Credit_info() {
	
		WebElement iframe =	myLibrary.waitForElementVisiblity(By.id("vantiv-payframe"));
		//switch to iframe
		driver.switchTo().frame(iframe);
		myLibrary.enterTextField(By.id("accountNumber"), "1234567897974564");
		myLibrary.selectDropDown(By.id("expMonth"), "April");
		myLibrary.selectDropDown(By.id("expYear"), "2020");
		
		//exit from iframe
		driver.switchTo().defaultContent();
		//myLibrary.handleCheckBoxRadioBTN(By.cssSelector("#payment_form_vantiv_cc_frame > li:nth-child(2) > label"), false);
		myLibrary.handleCheckBoxRadioBTN(By.id("payment-vantiv-cc-save-in-vault"), false);
		
		return this;
	}
	
}
