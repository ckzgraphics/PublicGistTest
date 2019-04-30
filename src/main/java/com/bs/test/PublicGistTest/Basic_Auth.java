package com.bs.test.PublicGistTest;

import java.io.File;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Basic_Auth {

	public static void main(String[] args) {

		String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
		String AUTOMATE_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
		String HUB_URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
		
		String AUT_URL = "https://admin:admin@the-internet.herokuapp.com/basic_auth";
		
		URL URLObj = null;
		WebDriver webDriver = null;
		DesiredCapabilities caps = null;

		try {

			URLObj = new URL(HUB_URL);

			caps = new DesiredCapabilities();
		
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "10");
			caps.setCapability("browserName", "Chrome");
			caps.setCapability("browser_version", "70.0");
			
			caps.setCapability("project", "Test Run");
			caps.setCapability("build", "Debug: Basic Auth Bahaviour");
			caps.setCapability("name", "Test: Basic Auth NO network logs");
			
//			caps.setCapability("browserstack.maskBasicAuth", "true");
			
			caps.setCapability("browserstack.console", "verbose");
			caps.setCapability("browserstack.networkLogs", "true");
			caps.setCapability("browserstack.debug", "true");
			caps.setCapability("browserstack.captureCrash", "true");
			caps.setCapability("browserstack.networkLogs", "false");
			caps.setCapability("browserstack.local", "false");
			
			webDriver = new RemoteWebDriver(URLObj, caps);
			webDriver.get(AUT_URL);
			webDriver.manage().window().maximize();
			
			// SIGN IN
			Thread.sleep(7000);
			
			take_screenshot(webDriver);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(webDriver != null){
				webDriver.quit();
			}
		} 
	} // MAIN END
	
	public static void take_screenshot(WebDriver webDriver){
		try {
			TakesScreenshot scrShot =((TakesScreenshot)webDriver);
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
//			File DestFile=new File("/Users/test.png");
//			FileUtils.copyFile(SrcFile, DestFile);
		} catch (Exception e) {
			 e.printStackTrace();
		}
	} // FUNC END
} // CLASS END
