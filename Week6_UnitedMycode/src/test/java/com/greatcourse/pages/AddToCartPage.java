package com.greatcourse.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.library.Base;

public class AddToCartPage extends Base {
	private String cssString = "#main-content > div > div.col-main.grid-full.in-col1 > div.block.cart-item-wrapper > div > div.actions > button.button.btn-checkout";

	public AddToCartPage waitUntilPageloadAddTocart() {

		WebElement checkOueBtnElem = myLibrary.fluentWait(By.cssSelector(cssString));
		Assert.assertNotNull(checkOueBtnElem, "wait for add to cart page loading - failed");

		return this;
	}

	public AddToCartPage proceedToCheckOutBtn() {

		myLibrary.clickButton(By.cssSelector(cssString));

		return this;
	}

}
