package com.greatcourse.test;

import org.testng.annotations.Test;

import com.greatcourse.pages.AddToCartPage;
import com.greatcourse.pages.CheckOutProgressPage;
import com.greatcourse.pages.ChooseProductFormatePage;
import com.greatcourse.pages.HomePage;
import com.greatcourse.pages.ScienceCoursesPage;
import com.library.Base;

public class BuyACourseTests extends Base {

	HomePage myHomePage = new HomePage(); 
	ScienceCoursesPage sciencePage = new ScienceCoursesPage();
	ChooseProductFormatePage cpfPage = new ChooseProductFormatePage();
	AddToCartPage actPage = new AddToCartPage();
	CheckOutProgressPage coPage = new CheckOutProgressPage();
	
	
	
	@Test
	public void buy_Nightsky_course() {
		
		myHomePage.gototheGreatCourseWebsite();
		myHomePage.click_CategoryScience();
		sciencePage.waitUntilPageLoadComplete();
		sciencePage.selectACourse("Our Night Sky");
		cpfPage.waitUntilPageLoadComplete();
		cpfPage.select_DVD();
		//cpf.select_videoDownload();
		cpfPage.clickAddToCartBTN();
		actPage.waitUntilPageloadAddTocart();
		actPage.proceedToCheckOutBtn();
		coPage.waitUntilPageLoadComplete();
		String tempEmail="test"+myLibrary.getCurrentTime() + "@test.com";
		System.out.println("Email ["+ tempEmail + "]");
		coPage.complete_checkoutMethodSection(tempEmail);
		coPage.comlepeBillingAddressSection();
		coPage.complete_shipping_method();
		coPage.complete_Credit_info();
	}
	
}
