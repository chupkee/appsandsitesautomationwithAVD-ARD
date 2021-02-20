package locator;

import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileSelector;
import io.appium.java_client.android.AndroidDriver;

public class MSMobileBy1AccessibilityIdAppSite 
{
	public static void main(String[] args) throws Exception
	{
		//Start appium server 
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
		//Get details of appium server
		URL u=new URL("http://127.0.0.1:4723/wd/hub");
		//Details of app and device
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","");
		dc.setCapability("platformName","");
		dc.setCapability("platformVersion","");
		dc.setCapability("appPackage","");
		dc.setCapability("appActivity","");
		
		//Create driver object
		AndroidDriver driver;
		while(2>1)
		{
			try
			{
				driver=new AndroidDriver(u,dc);
				break;
			}
			catch(Exception ex)
			{
				
			}
		}
		
		//Automation
		try
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("dial pad")));
			driver.findElement(MobileSelector.ACCESSIBILITY.toString(),"dial pad").click();
			//driver.findElementByAccessibilityId("dial pad").click();
			//driver.findElement(MobileBy.AccessibilityId("dial pad")).click();
			Thread.sleep(5000);
			
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		//close app
		driver.closeApp();
		
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
		
		
		
		

	}

}
