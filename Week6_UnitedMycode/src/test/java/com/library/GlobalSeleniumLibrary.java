package com.library;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.google.common.io.Files;

/***
 * This is a common global selenium libraries and can be used any selenium
 * projects
 * 
 * @author Lakshmi @ Created 12/22/18
 */

public class GlobalSeleniumLibrary {
	final static Logger logger = Logger.getLogger(GlobalSeleniumLibrary.class);
	private WebDriver driver;
	private boolean isDemo = false;
	public List<String>errorScreenshots;
	public void setDemo(boolean isDemo) {
		this.isDemo = isDemo;
	}

	public boolean getDemo() {
		return isDemo;
	}

	/***
	 * This is a Constructor
	 * 
	 * @param _driver
	 */
	public GlobalSeleniumLibrary(WebDriver _driver) {
		driver = _driver;
	}

	/***
	 * This is the method enters text string to a edit webElement in web-site
	 * 
	 * @param by
	 * @param value
	 */
	public void enterTextField(By by, String value) {
		try {
			WebElement textWebElement = driver.findElement(by);
			highlightElement(textWebElement);
			textWebElement.clear();
			textWebElement.sendKeys(value);
		} catch (Exception e) {

			logger.error("Error: ", e);
			assertTrue(false);
		}

	}

	public void enterTextField(WebElement element, String value) {
		try {
			highlightElement(element);
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}

	}

	/***
	 * This method starts chrome browser and maximize it
	 * 
	 * @return WebDriver
	 */
	public WebDriver startChromeBrowser() {
		try {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/browers_drivers/chromedriver.exe");
			// start a browser
			driver = new ChromeDriver();
			logger.info("Chrome browser is starting ...");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();

		} catch (Exception e) {
			// logger.error("Error: ", e);
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;

	}

	/***
	 * This is a fluent wait, waits dynamically for a webElement and polls the
	 * source html
	 * 
	 * @param by
	 * @return webElement
	 */
	public WebElement fluentWait(By by) {
		WebElement targetElem = null;
		try {
			@SuppressWarnings("deprecation")
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS)
					.pollingEvery(3, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
			targetElem = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(by);
				}

			});

		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		highlightElement(targetElem);
		return targetElem;

	}

	/***
	 * This Explicit Wait method is use to wait for the visibility of an element on
	 * the page
	 */

	public WebElement waitForElementVisiblity(By by) {

		WebElement element = null;
		element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(by));
		return element;
	}

	/***
	 * This method is use to select the drop down webElement by selecting the
	 * visible text in web-site
	 * 
	 * @param by
	 * @param visibleTextValue
	 */
	public void selectDropDown(By by, String visibleTextValue) {
		try {
			WebElement dropDownElement = driver.findElement(by);
			highlightElement(dropDownElement);
			Select dropDown = new Select(dropDownElement);
			dropDown.selectByVisibleText(visibleTextValue);

		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	/***
	 * This method is use to select the drop down webElement by selecting the index
	 * in web-site
	 * 
	 * @param by
	 * @param index
	 */

	public void selectDropDown(By by, int index) {
		try {
			WebElement dropDownElement = driver.findElement(by);
			highlightElement(dropDownElement);
			Select dropDown = new Select(dropDownElement);
			dropDown.selectByIndex(index);

		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	/***
	 * This method is use to select the drop down by selecting the webElement by
	 * value in web-site
	 * 
	 * @param attributeValue
	 * @param by
	 */
	public void selectDropDown(String attributeValue, By by) {
		try {

			WebElement dropDownElement = driver.findElement(by);
			highlightElement(dropDownElement);
			Select dropDown = new Select(dropDownElement);
			dropDown.selectByValue(attributeValue);

		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	/***
	 * This method is use for click the webElement button
	 * 
	 * @param by
	 */
	public void clickButton(By by) {
		try {
			WebElement button = driver.findElement(by);
			highlightElement(button);
			button.click();

		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void clickButton(WebElement webelement) {
		try {
			highlightElement(webelement);
			webelement.click();
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}

	}

	/***
	 * This method is clicks or un-clicks for radio button
	 * 
	 */
	public void handleCheckBoxRadioBTN(WebElement webelement, boolean isUserWantsToCheckBox) {

		boolean checkboxState = webelement.isSelected();

		if (checkboxState == true) { // by default is checked
			if (isUserWantsToCheckBox == true) {// Scenario1 do nothing

			} else { // scenario2:
				// webelement.click();
				highlightElement(webelement);
				clickHiddenElement(webelement);
			}

		} else {// by default is not checked

			if (isUserWantsToCheckBox == true) { // Secnario3
				// webelement.click();
				highlightElement(webelement);
				clickHiddenElement(webelement);
			} else { // Scenario4:

			}

		}

	}

	public void handleCheckBoxRadioBTN(By by, boolean isUserWantsToCheckBox) {

		// scenario1: by default check box is checked, user wants to check the check
		// box.
		// scenario2: by default check box is checked, user wants to uncheck the check
		// box.
		// scenario3: by default check box is not checked, user wants to check the check
		// box.
		// scenario4: by default check box is not checked, user wants to uncheck the
		// check box.
		WebElement elem = driver.findElement(by);
		highlightElement(elem);
		boolean checkboxState = elem.isSelected();

		if (checkboxState == true) { // by default is checked
			if (isUserWantsToCheckBox == true) {// Scenario1
				// do nothing
			} else { // scenario2:
				// elem.click();
				clickHiddenElement(elem);
			}

		} else {// by default is not checked Secnario3

			if (isUserWantsToCheckBox == true) {
				// elem.click();
				clickHiddenElement(elem);
			} else { // Scenario4:

			}

		}

	}

	/***
	 * This method is used to click the hidden webelement direct
	 * 
	 * @param elem
	 */
	public void clickHiddenElement(WebElement elem) {

		try {
			highlightElement(elem);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", elem);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	/***
	 * This method is use to click the hidden elementS
	 * 
	 * @param by
	 */

	public void clickHiddenElement(By by) {
		try {
			WebElement hiddenElem = driver.findElement(by);
			highlightElement(hiddenElem);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", hiddenElem);

		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	/***
	 * This is a java script to use for scroll to webelement
	 * 
	 * @param element
	 */
	public void scrollToWebElement(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
			highlightElement(element);

		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	/***
	 * This method is to scrolling the browser to vertical-pixel
	 * 
	 * @param verticalpixel
	 */
	public void scrollByOffsetVertical(String verticalpixel) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeAsyncScript("scroll(0," + verticalpixel + ")");// "scroll(0,200)"
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	/***
	 * This method is to scrolling the browser to horizontal-pixel
	 * 
	 * @param verticalpixel
	 */

	public void scrollByOffsetHorizontal(String horizontalpixel) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeAsyncScript("scroll(" + horizontalpixel + ",0)");// "scroll(200,0)"
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	/***
	 * This is the current time stamp method
	 * 
	 * @return
	 */
	public String getCurrentTime() {

		String finalTimeStamp = null;
		Date date = new Date();

		String tempTime = new Timestamp(date.getTime()).toString();
		logger.info("original time stamp is " + tempTime);
		finalTimeStamp = tempTime.replace(":", "_").replace(" ", "_").replace(".", "_").replace("-", "_");
		logger.info(finalTimeStamp);
		return finalTimeStamp;
	}

	public WebElement waitForElementVisilibity(By by) {
		WebElement element = null;

		element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(by));
		highlightElement(element);
		return element;
	}

	/***
	 * Custom method for thread.sleep
	 * 
	 * @param inseconds
	 */
	public void customWait(double inseconds) {
		// 1 second = 1000 milli sec
		// 0.3 sec = 300 milli sec
		// inseconds sec = inseconds* 1000 milli sec
		try {
			Thread.sleep((long) (inseconds * 1000));

		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	/***
	 * This method is used to highlight the element that we are working and its
	 * useful for demo perpose
	 * 
	 * @param by
	 * @return
	 */
	public WebElement highlightElement(By by) {
		WebElement element = null;
		try {
			if (isDemo == true) {
				for (int i = 0; i < 4; i++) {
					element = driver.findElement(by);
					WrapsDriver wrappedElement = (WrapsDriver) element;
					JavascriptExecutor js = (JavascriptExecutor) wrappedElement.getWrappedDriver();
					customWait(0.4);
					js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
							"color: red; border: 2px solid yellow;");
					customWait(0.4);
					js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
				}
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return element;
	}

	/***
	 * This method will highlight the webelement this is a java script
	 * 
	 * @param webElement
	 * @return
	 */
	public WebElement highlightElement(WebElement webElement) {
		WebElement element = webElement;
		try {
			if (isDemo == true) {
				for (int i = 0; i < 4; i++) {
					// element = driver.findElement(by);
					WrapsDriver wrappedElement = (WrapsDriver) element;
					JavascriptExecutor js = (JavascriptExecutor) wrappedElement.getWrappedDriver();
					customWait(0.4);
					js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
							"color: red; border: 2px solid yellow;");
					customWait(0.4);
					js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
				}
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return element;
	}

	/***
	 * This method is for captured the screenshot
	 */

	public String captureScreenShot(String screenshotFileName, String filePath) {
		String screenshotPath = null;
		String timestamp = getCurrentTime();
		try {
			if (!filePath.isEmpty()) {
				checkDirectory(filePath);
				screenshotPath = filePath + screenshotFileName + timestamp + ".png";
			} else {
				checkDirectory("target/screenshots/");
				screenshotPath = "target/screenshots/" + screenshotFileName + timestamp + ".png";
			}
			// This code is for capture screen
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// this is for store the captured file
			Files.copy(srcFile, new File(screenshotPath));

		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		logger.info("Screenshot captured: " + screenshotPath);
		return screenshotPath;
	}

	/***
	 * This method is use for make file for screenshot
	 * 
	 * @param inputPath
	 * @return
	 */
	private String checkDirectory(String inputPath) {
		File file = new File(inputPath);
		String abPath = file.getAbsolutePath();
		File file2 = new File(abPath);
		if (!file2.exists()) {
			if (file2.mkdirs()) {
				logger.info("folders created...");
			} else {
				logger.info("folders are not created...");
			}
		}
		return abPath;

	}

	public WebDriver startLocalBrowser(String browser) {
		if (browser.contains("Chrome")) {
			// Starts Chrome browser
			driver = startChromeBrowser();

		} else if (browser.contains("IE")) {
			// starts IE browser

			driver = startIEBrowser();

		} else if (browser.contains("Firefox")) {
			// starts Fire-fox browser
			logger.info("Starting Firefox browser, but not implemented yet!!! sorry.");

		} else {
			// other browsers we don't support with this version of site
			logger.info("Ops, sorry we do not support the browser:!!! ");
		}

		return driver;
	}

	private WebDriver startIEBrowser() {
		try {
			System.setProperty("webdriver.ie.driver", "src/test/resources/browers_drivers/IEDriverServer.exe");
			// start a IE browser
			// Ignoring security protected mode using below code

			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability("ignoreProectedModesetting", true);
			capabilities.setCapability("ie.ensureCleanSession", true);
			driver = new InternetExplorerDriver(capabilities);
			// This is setting zoom to 100% by code
			// WebElement body = driver.findElements(By.tagName("body"));

			logger.info("InternetExplorer browser is starting ...");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();

		} catch (Exception e) {

			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;

	}

	/***
	 * This method detects alert and returns alert object
	 * 
	 * @return Alert object
	 */

	public Alert isAlertPresent() {

		Alert alert = null;
		try {
			alert = driver.switchTo().alert();
			logger.info("Alert dected:{" + alert.getText() + "}");

		} catch (Exception e) {

			logger.info("Alert not detected:");
		}
		return alert;
	}

	/***
	 * Handling mutiple windows
	 * 
	 * @param index
	 * @return
	 */
	public WebDriver switchToWindow(int index) {
		try {
			Set<String> allbrowsers = driver.getWindowHandles();
			Iterator<String> iterator = allbrowsers.iterator();
			List<String> windowHandles = new ArrayList<>();

			while (iterator.hasNext()) {
				String window = iterator.next();
				windowHandles.add(window);
			}
			// switch ti index N
			driver.switchTo().window(windowHandles.get(index));
			// highlightElement(By.tagName("body"));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);

		}
		return driver;
	}

	public List<String> automaticallyAttachErrorImgtoEmail() {
		List<String>fileNames = new ArrayList<>();
		JavaPropertiesManager propertyReadder = new JavaPropertiesManager("src/test/resources/dynamicconfig.properties");
		String tempTimeStamp = propertyReadder.readProperty("sessionTime");
		String numberTimeStamp = tempTimeStamp.replaceAll("_", "");
		long testStartTime = Long.parseLong(numberTimeStamp);
		//first check if error- screenshot folder has file
		File file = new File ("target/screenshots");
		if(file.isDirectory()) {
			if(file.list().length>0) {
				File[]screenshotFiles = file.listFiles();
				for(int i =0;i<screenshotFiles.length;i++) {
					//checking if file is a file,not a folder
					if(screenshotFiles[i].isFile()) {
						String eachFileName = screenshotFiles[i].getName();
						logger.info("Testing file names: "+ eachFileName );
						int indexof20 = searchStringInString("20",eachFileName);
						String timeStampFromScreenshotFile = eachFileName.substring(indexof20, 
								eachFileName.length()-4);
						logger.info("Testing file timestamp "+ timeStampFromScreenshotFile);
						String fileNumberStamp = timeStampFromScreenshotFile.replace("_", "");
						long screenshotfileTime = Long.parseLong(fileNumberStamp);
						testStartTime = Long.parseLong(numberTimeStamp.substring(0,14));
						screenshotfileTime = Long.parseLong(fileNumberStamp.substring(0,14));
						if(screenshotfileTime>testStartTime) {
							fileNames.add("target/screenshots/"+ eachFileName);
						}
					}
				}
			}
		
		
		
		}
		
		errorScreenshots = fileNames;
		return fileNames;
		
	}

	/***
	 * This method is for getting index of String
	 * @param target
	 * @param message
	 * @return
	 */
	public int searchStringInString(String target, String message) {
		int targetIndex =0;
		for(int i =-1;(i = message.indexOf(target,i+1))!=-1;) {
			targetIndex= i;
			break;
		}
		
		return targetIndex;
	}
		
		
		
		
		
		
		
		
		
		
		
	

}
