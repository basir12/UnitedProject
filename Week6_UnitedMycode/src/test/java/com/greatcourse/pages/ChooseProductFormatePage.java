package com.greatcourse.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.library.Base;

public class ChooseProductFormatePage extends Base {

	public ChooseProductFormatePage waitUntilPageLoadComplete() {

		WebElement chooseFormateElem = myLibrary.fluentWait(By.id("media-format-radio"));
		Assert.assertNotNull(chooseFormateElem, "wait for choose product formate page loading - failed");

		return this;

	}

	public ChooseProductFormatePage select_DVD() {
		// using library method to click radio button
	
		//WebElement dvd = getRadioButtonFormatOptions().get(1);
		//myLibrary.handleCheckBoxRadioBTN(dvd, true);
	//dvd.click();
		WebElement dvd = getRadioButtonFormatOptions().get(1);
		dvd.click();
		return this;
	}

	public ChooseProductFormatePage select_videoDownload() {

		WebElement videoDownload = getRadioButtonFormatOptions().get(0);
		videoDownload.click();
		return this;
	}

	public ChooseProductFormatePage clickAddToCartBTN() {
		myLibrary.clickButton(By.id("add-to-cart-btn"));
	return this;
	
	}
	
	/// Helper methods///

	private List<WebElement> getRadioButtonFormatOptions() {
		List<WebElement> radioOptions = new ArrayList<>();
		WebElement radioGroup = driver.findElement(By.id("media-format-radio"));
		radioOptions = radioGroup.findElements(By.tagName("label"));

		return radioOptions;
	}

}
