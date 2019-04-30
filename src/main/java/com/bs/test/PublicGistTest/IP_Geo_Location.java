package com.bs.test.PublicGistTest;

import java.io.File;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IP_Geo_Location {

	public static void main(String[] args) {

		String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
		String AUTOMATE_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
		String HUB_URL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

		String AUT_URL = "https://mylocation.org/";
		
		URL URLObj = null;
		WebDriver webDriver = null;
		DesiredCapabilities caps = null;

		try {

			URLObj = new URL(HUB_URL);
      
			caps = new DesiredCapabilities();
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "10");
			caps.setCapability("browserName", "Chrome");
			caps.setCapability("browser_version", "68.0");
			
			caps.setCapability("browserstack.geoLocation", "AR"); // VISIT - https://www.browserstack.com/automate/ip-geolocation

			caps.setCapability("project", "Test Run");
			caps.setCapability("build", "Support Automate");
			caps.setCapability("name", "IP GeoLocation Capability");
			
			caps.setCapability("browserstack.console", "verbose");
			caps.setCapability("browserstack.debug", "true");
			
			webDriver = new RemoteWebDriver(URLObj, caps);
			webDriver.get(AUT_URL);
			webDriver.manage().window().maximize();
			
			// SIGN IN
			Thread.sleep(10000);
			
			System.out.println("Country :: " + webDriver.findElement(By.xpath(".//tr[contains(.,'Country')]/td[2]")).getText());
			System.out.println("Region :: " + webDriver.findElement(By.xpath(".//tr[contains(.,'Region')]/td[2]")).getText());
			System.out.println("City :: " + webDriver.findElement(By.xpath(".//tr[contains(.,'City')]/td[2]")).getText());
			System.out.println("Country :: " + webDriver.findElement(By.xpath(".//tr[contains(.,'Country')]/td[2]")).getText());
			
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