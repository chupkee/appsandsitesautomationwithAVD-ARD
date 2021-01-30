package srinivasansekarAppAuto;

import java.net.URL;

import org.openqa.selenium.html5.Location;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class LocationServices 
{
	public static void main(String[] args) throws Exception
	{
		//Details of app and device(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","emulator-5554");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","5.1.1");
		dc.setCapability("locationServicesEnabled","true");
		dc.setCapability("locationServicesAuthorized","true");
		dc.setCapability("noReset","true");
		dc.setCapability("fullReset","false");
		dc.setCapability("appPackage","com.google.android.apps.maps");
		dc.setCapability("appActivity","com.google.android.maps.MapsActivity");
		
		//Start appium server programmatically
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
		//Get address of appium server 
		URL u=new URL("http://127.0.0.1:4723/wd/hub");
		
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
		
		//Validation
		try
		{
			Thread.sleep(5000);
			//Set location using latitude, longitude and altitude
			//Set location pune
			Location l1=new Location(18.520430,73.856743,100000);
			driver.setLocation(l1);
			Thread.sleep(6000);
			//Set location delhi
			Location l2=new Location(28.704060,77.102493,100000);
			driver.setLocation(l2);
			Thread.sleep(6000);
			//set location uk
			Location l3=new Location(51.502464,-0.137000,100000);
			driver.setLocation(l3);
			Thread.sleep(6000);
			//Set location to minneapolis
			Location l4=new Location(44.977753,-93.265015,100000);
			driver.setLocation(l4);
			Thread.sleep(5000);
		}
		catch(Exception exe)
		{
			System.out.println(exe.getMessage());
		}	
		
		//close app
		driver.closeApp();
		
		//stop appium service
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}
