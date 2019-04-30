package com.bs.test.PublicGistTest;

import java.io.File;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Cookie_Test {

	public static void main(String[] args) {

		String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
		String AUTOMATE_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
		String HUB_URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

		WebDriver webDriver = null;
		String AUT_URL = "http://www.html-kit.com/tools/cookietester/";

		URL URLObj = null;
		DesiredCapabilities caps = null;
		Cookie cookie = null;

		try {

			URLObj = new URL(HUB_URL);
			caps = new DesiredCapabilities();
			
			caps.setCapability("browserName", "Firefox");
			caps.setCapability("browserVersion", "61.0");
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "10");

			caps.setCapability("project", "Test Run");
			caps.setCapability("build", "Support Automate");
			caps.setCapability("name", "Test: Add/Delete Cookies");

			caps.setCapability("browserstack.use_w3c", "true");
			caps.setCapability("browserstack.console", "verbose");
			caps.setCapability("browserstack.debug", "true");

			webDriver = new RemoteWebDriver(URLObj, caps);
			webDriver.manage().window().maximize();
			
			// CREATE COOKIE
			cookie = new Cookie("MyCookie_Sample", "This_is_a_test_cookie");
			// OPEN URL
			webDriver.get(AUT_URL);
			Thread.sleep(4000);

			// ADD THE COOKIE
			webDriver.manage().addCookie(cookie);
			
			// REFRESH THE WEBPAGE
			webDriver.navigate().refresh();

			// CHECK COOKIE
			Thread.sleep(15000);
			System.out.println("Cookies Value : " + webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/div[2]/dl/dd/pre")).getText());
			
			take_screenshot(webDriver);
			Thread.sleep(2000);

			// DELETE COOKIE
			webDriver.manage().deleteAllCookies();
			webDriver.findElement(By.xpath(".//input[@value='Refresh']")).click();
			Thread.sleep(15000);
			
			take_screenshot(webDriver);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(webDriver != null){
				webDriver.quit();
			}
		} // TRY CATCH END
	} // MAIN END

	public static void take_screenshot(WebDriver webDriver){
		try {
			TakesScreenshot scrShot =((TakesScreenshot)webDriver);
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			//			File DestFile=new File("/Users/test/study/java/test.png");
			//			FileUtils.copyFile(SrcFile, DestFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // FUNC END
} // CLASS END