package locator;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;

public class ByMobileBy5LinkTextSiteWithCDPath 
{
	public static void main(String[] args) throws Exception
	{
		//Start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium --chromedriver-executable E:\\RAJASHEKAR\\chromebrowser\\chromedriver.exe\"");
		
		//Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium --chromedriver-executable E:\\\\RAJASHEKAR\\\\chromebrowser\\\\chromedriver.exe\"");
		//Get address of appium server
		URL u=new URL("http://127.0.0.1:4723/wd/hub");
		
		//Details of app and device(ARD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"chrome");
		dc.setCapability("deviceName","ZY2243MMDP");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.1.0");
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
			driver.get("http://demo.guru99.com/test/newtours/");
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("REGISTER")));
			//driver.findElement(By.linkText("REGISTER")).click();
			//driver.findElement(MobileBy.linkText("REGISTER")).click();
			driver.findElementByLinkText("REGISTER").click();
			Thread.sleep(4000);
			
			
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		//close site
		driver.close();
		
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");	

	}
}
