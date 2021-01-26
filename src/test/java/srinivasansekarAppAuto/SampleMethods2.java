package srinivasansekarAppAuto;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class SampleMethods2 
{
	public static void main(String[] args) throws Exception
	{
		//get app details and device
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","");
		dc.setCapability("platformName","");
		dc.setCapability("platformVersion","");
		dc.setCapability("appActivity","");
		dc.setCapability("appPackage","");
		//Start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
		//Get address of appium Server
		URL u=new URL("http://127.0.0.1:4723/wd/hub");
		KeyEvent k;
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
		try
		{
			//come to home, open notifications and click clear all
			Thread.sleep(5000);
			k=new KeyEvent(AndroidKey.HOME);
			driver.pressKey(k);
			Thread.sleep(5000);
			driver.openNotifications();
			Thread.sleep(5000);
			try
			{
				WebElement e=driver.findElement(By.xpath("//*[@text='CLEAR']"));
				if(e.getAttribute("enabled").equalsIgnoreCase("true"))
				{
					e.click();
				}
				else
				{
					k=new KeyEvent(AndroidKey.BACK);
					driver.pressKey(k);
				}
			}
			catch(Exception e1)
			{
				k=new KeyEvent(AndroidKey.BACK);
				driver.pressKey(k);
			}
			Thread.sleep(5000);
			//get details of appium server
			String path=driver.getRemoteAddress().getPath();
			System.out.println("Path of appium server is"+path);
			
			String protocal=driver.getRemoteAddress().getProtocol();
			System.out.println("Protocal of appium server is"+protocal);
			
			String host=driver.getRemoteAddress().getHost();
			System.out.println("Host of appium server is"+host);
			
			int port=driver.getRemoteAddress().getPort();
			System.out.println("port of appium server is"+port);
			
			
			//Get details of device
			long density=driver.getDisplayDensity();
			System.out.println("Display density of device is "+density);
			String time=driver.getDeviceTime();
			System.out.println("Device time is "+time);
			String platname=driver.getPlatformName();
			System.out.println("Platfrom name is "+platname);
			Thread.sleep(5000);
			//get device lock status
			//driver.lockDevice();
			if(!driver.isDeviceLocked()) //if not locked if block will execute
			{
				System.out.println("unlocked");
				driver.lockDevice();
				Thread.sleep(10000);
				driver.unlockDevice(); //device should not have any security panel
			}
			Thread.sleep(5000);
			driver.launchApp();
			Thread.sleep(5000);
			
		}
		catch(Exception e2)
		{
			System.out.println(e2.getMessage());
		}
		
		//close app
		driver.closeApp();
		
		//stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}
