package com.bs.test.PublicGistTest;

import java.net.URL;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Switch_to_web_iPhone {

	public static void main(String[] args) {

		String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
		String AUTOMATE_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
		String HUB_URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

		JavascriptExecutor js = null;
		WebDriver webDriver = null;

		ChromeOptions options = null;
		WebDriverWait wait = null;
		Actions act = null;
		String AUT_URL = "http://toolsqa.com/automation-practice-switch-windows/";
		URL URLObj = null;
		DesiredCapabilities caps = null;

		try {

			URLObj = new URL(HUB_URL);

			caps = new DesiredCapabilities();

			caps.setCapability("browserName", "iPhone");
			caps.setCapability("device", "iPhone 7");
			caps.setCapability("realMobile", "true");
			caps.setCapability("os_version", "10.3");

			caps.setCapability("build", "Support Automate");
			caps.setCapability("name", "Test Switch To Window iOS");
			caps.setCapability("browserstack.local", "false");
			caps.setCapability("browserstack.localIdentifier", "Test123");
			caps.setCapability("browserstack.debug", "true");
			caps.setCapability("browserstack.video", "true");
			caps.setCapability("browserstack.networkLogs", "true");
			caps.setCapability("nativeWebTap", "true");

			webDriver = new RemoteWebDriver(URLObj, caps);

			wait = new WebDriverWait(webDriver, 10000);
			act = new Actions(webDriver);
			js = (JavascriptExecutor)webDriver;

			webDriver.get(AUT_URL);

			System.out.println("TITLE :: " + webDriver.getTitle());

			Thread.sleep(3000);
			webDriver.findElement(By.xpath(".//button[contains(.,'New Browser Window')]")).click();
			Thread.sleep(4000);

			Set<String> wind = webDriver.getWindowHandles();
			String news = "";
			for (String string : wind) {
				System.out.println("WINDW :: " + string);
				news = string;
			}
			webDriver.switchTo().window(news);
			System.out.println("TITLE :: " + webDriver.getTitle());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(webDriver != null){
				webDriver.quit();
			}
		} // TRY END
	} // MAIN END
} // CLASS END
