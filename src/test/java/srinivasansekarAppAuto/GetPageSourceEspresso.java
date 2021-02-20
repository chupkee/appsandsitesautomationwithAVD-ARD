package srinivasansekarAppAuto;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

public class GetPageSourceEspresso 
{
	public static void main(String[] args) throws Exception
	{
		//Start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
		//Get address of appium server
		URL u=new URL("http://127.0.0.1:4723/wd/hub");
		//Desired Capabilities of app and device
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ESPRESSO);
		dc.setCapability("forceEspressRebuild","true");
		dc.setCapability("showGradleLog","true");
		dc.setCapability("autoGrantPermissions","true");
		dc.setCapability("adbExecTimeout", "40000");
		dc.setCapability("deviceName", "ZY2243MMDP");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion", "8.1.0");
		dc.setCapability("appPackage","com.vodqareactnative");
		dc.setCapability("appActivity","com.vodqareactnative.MainActivity");
		
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
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@text='LOG IN']")).click();
			Thread.sleep(5000);
			String x=driver.getPageSource();
			System.out.println(x);
			Thread.sleep(5000);
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		//close app
		driver.closeApp();
		
		//Stop appium sever programmatically
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}

}
