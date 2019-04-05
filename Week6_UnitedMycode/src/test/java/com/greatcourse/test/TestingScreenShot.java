package com.greatcourse.test;

import org.testng.annotations.Test;

import com.library.Base;

public class TestingScreenShot extends Base {

	@Test
	public void testingScreenShotCapture() {
	driver.get("https://www.google.com/");	
	
	myLibrary.captureScreenShot("photo", "");
	
	
	
	}
	
	
	
}
