package com.bs.test.PublicGistTest;

import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Edge_Driver_SaveYourPassword {

	public static void main(String[] args) {

		// GIT ISSUE- https://github.com/SeleniumHQ/selenium/issues/5943
		
		String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
		String AUTOMATE_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
		String HUB_URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

		WebDriver webDriver = null;

		String AUT_URL = "http://demo.guru99.com/test/login.html";
		URL URLObj = null;
		DesiredCapabilities caps = null;

		try {

			URLObj = new URL(HUB_URL);

			caps = new DesiredCapabilities();
			// PROVIDE CAPS
			caps.setCapability("browser", "Edge");
			caps.setCapability("browser_version", "17.0");
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "10");
			caps.setCapability("project", "EdgeDriver_Issues");
			caps.setCapability("build", "EdgeDriver_Issues");
			caps.setCapability("name", "Test: Save your password message");

			// INIT DRIVER INSTANCE
			webDriver = new RemoteWebDriver(URLObj, caps);

			// ENTER USERNAME
			webDriver.findElement(By.xpath(".//input[@id='email']")).sendKeys("<USERNAME>");
			Thread.sleep(2000);

			// ENTER PASSWORD
			webDriver.findElement(By.xpath(".//input[@id='passwd']")).sendKeys("<PASSWORD>");
			Thread.sleep(2000);

			// CLICK LOGIN BUTTON
			webDriver.findElement(By.xpath(".//button[@id='SubmitLogin']")).click();
			Thread.sleep(10000);

			// YOU WILL SEE 'Save your password' MESSAGE ON THE BROWSER WINDOW

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(webDriver != null){
				webDriver.quit();
			} // IF END
		} // TRY CATCH FINALLY END
	} // MAIN END
} // CLASS END
