package com.library;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
public class Base {
	final static Logger logger = Logger.getLogger(Base.class);
	public static WebDriver driver;
	public 	static GlobalSeleniumLibrary myLibrary;
	private static JavaPropertiesManager readProperty;
	private static JavaPropertiesManager readProperty2;
	private static String browser;
	private static String demoType;
	private static String isAutoSendEmail;
	
	@BeforeClass
	public void beforeAllTestStart() {
		
		
		readProperty = new JavaPropertiesManager("src/test/resources/config.properties");
		browser = readProperty.readProperty("browserType");
		demoType = readProperty.readProperty("demoMode");
		isAutoSendEmail = readProperty.readProperty("autoEmail");
		
		myLibrary = new GlobalSeleniumLibrary(driver);	
	
		readProperty2 = new JavaPropertiesManager("src/test/resources/dynamicconfig.properties");
		String tempTime = myLibrary.getCurrentTime();
		readProperty2.setProperty("sessionTime",tempTime );
		logger.info("session Time:"+tempTime);
		
		
		
		if(demoType.contains("true")) {
		myLibrary.setDemo(true);
		logger.info("Test is running in demo mode ON ...");
	}else {
		logger.info("Test is running in demo OFF...");
	}
	}
	@AfterClass
	public void afterAllTestCompleted() {
		if(driver!= null) {
			driver.quit();
		}
	//sending all the reports,screenshots and log files via email
	List<String> screenshots = new ArrayList<>();
	EmailManager emailSender = new EmailManager();
	emailSender.attachmentFiles.add("target/logs/log4j-selenium.log");
	emailSender.attachmentFiles.add("target/logs/Selenium-Report.html");
	screenshots = myLibrary.automaticallyAttachErrorImgtoEmail();
	if(screenshots.size()!=0) {
		for(String attachFile: screenshots) {
			emailSender.attachmentFiles.add(attachFile);
		}
	}
	if(isAutoSendEmail.contains("true")) {
		emailSender.sendEmail(emailSender.attachmentFiles);
	}
	}
	
	
	@BeforeMethod
	public void beforeEachTestStart() {
		driver = myLibrary.startLocalBrowser(browser);
		//driver = myLibrary.startChromeBrowser();
		
		/*System.setProperty("webdriver.chrome.driver", "src/test/resources/browers_drivers/chromedriver.exe");
		// start a browser
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		driver.manage().window().maximize();*/
	}

	@AfterMethod
	public void afterEachTestEnd(ITestResult result) {
		try {
			// If test failure this method capture screen shot
			if(ITestResult.FAILURE == result.getStatus()) {
			myLibrary.captureScreenShot(result.getName(), "target/screenshots/");	
			}
			Thread.sleep(5 * 1000);
			driver.close();// it close the driver only
			driver.quit();// kills/deletes the driver object
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("Error: ", e);
		}

	}

}
