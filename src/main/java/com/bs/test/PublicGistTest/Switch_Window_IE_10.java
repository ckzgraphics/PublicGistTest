package com.bs.test.PublicGistTest;

import java.io.File;
import java.net.URL;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Switch_Window_IE_10 {

	static String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
	static String AUTOMATE_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
	static String HUB_URL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	public static void main(String[] args) {

		WebDriver webDriver = null;
		String AUT_URL = "https://the-internet.herokuapp.com/windows";
		
		URL URLObj = null;
		DesiredCapabilities caps = null;
		Actions act = null;
		WebElement ele = null;

		try {

			URLObj = new URL(HUB_URL);
			caps = new DesiredCapabilities();
			
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "7");
			caps.setCapability("browser", "IE");
			caps.setCapability("browser_version", "10.0");
			
			// IE (10) SPECEFIC CAPS
			caps.setCapability("ignoreProtectedModeSettings",1);
			caps.setCapability("IntroduceInstabilityByIgnoringProtectedModeSettings",true);
			caps.setCapability("nativeEvents",true);
			caps.setCapability("browserFocus",true);
			caps.setCapability("ignoreZoomSetting", true);
			caps.setCapability("requireWindowFocus","true");
			caps.setCapability("INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS", true);
			
			caps.setCapability("project", "Test Run");
			caps.setCapability("build", "Support Automate");
			caps.setCapability("name", "Test: Switch to window - IE 10");
			
			caps.setCapability("browserstack.ie.driver", "2.53.1");
			caps.setCapability("browserstack.ie.arch", "x32");
			caps.setCapability("browserstack.selenium_version", "2.53.1");
			caps.setCapability("browserstack.captureCrash", "true");
			
			caps.setCapability("browserstack.local", "false");
			caps.setCapability("browserstack.debug", "true");
			caps.setCapability("browserstack.video", "true");
			
			webDriver = new RemoteWebDriver(URLObj, caps);
			act = new Actions(webDriver);
			
			// OPEN URL
			webDriver.get(AUT_URL);
			Thread.sleep(5000);
			
			webDriver.manage().window().maximize();
			Thread.sleep(2000);
			
			take_screenshot(webDriver);
			
			ele = webDriver.findElement(By.xpath(".//a[contains(.,'Click Here')]"));
			act.moveToElement(ele).click().perform();
			
			Thread.sleep(3000);
			
			Set<String> windwos = webDriver.getWindowHandles();
			String oldWindow = webDriver.getWindowHandle();
			String newWindow = null;
			for (String string : windwos) {
				System.out.println("Wind :: " + string);
				newWindow = string;
			}
			webDriver.switchTo().window(newWindow);
			Thread.sleep(3000);
			take_screenshot(webDriver);
			System.out.println("TITLE :: " + webDriver.getTitle());
			
			webDriver.switchTo().window(oldWindow);
			Thread.sleep(3000);
			take_screenshot(webDriver);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(webDriver != null){
				webDriver.quit();
			}
		} // FINALLY END
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
