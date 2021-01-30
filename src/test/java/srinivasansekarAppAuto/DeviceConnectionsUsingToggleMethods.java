package srinivasansekarAppAuto;

import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;

public class DeviceConnectionsUsingToggleMethods 
{
	public static void main(String[] args) throws Exception
	{
		//details of app and device(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","192.168.1.11:2020");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","10");
		dc.setCapability("appPackage","com.vodqareactnative");
		dc.setCapability("appActivity","com.vodqareactnative.MainActivity");
		
		//Start appium server programmatically
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
		//Get address of appium service
		URL u=new URL("http:127.0.0.1:4723");
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
		
		//validation
		try
		{
			ConnectionState setcon;
			ConnectionState cs=driver.getConnection();
			//Enabling and disabling FIWI
			if(cs.isWiFiEnabled())
			{
				System.out.println("Wifi is ON");
				Thread.sleep(5000);
				driver.toggleWifi();
				Thread.sleep(50000);
				if(cs.isWiFiEnabled())
				{
					System.out.println("Wifi is till ON");
				}
				else
				{
					System.out.println("Wifi is turned OFF");
				}
			}
			else
			{
				System.out.println("Wifi is OFF");
				driver.toggleWifi();
				Thread.sleep(5000);
				if(cs.isWiFiEnabled())
				{
					System.out.println("Wifi is now turned ON");
				}
				else
				{
					System.out.println("Wifi is still OFF");
				}
				
			}
			
			//Enabling and disabling data
			if(cs.isDataEnabled())
			{
				System.out.println("Data is ON");
				Thread.sleep(5000);
				driver.toggleData();
				Thread.sleep(5000);
				if(cs.isDataEnabled())
				{
					System.out.println("Data is still ON");
				}
				else
				{
					System.out.println("Data is turned OFF");
				}
			}
			else
			{
				System.out.println("Data is OF");
				driver.toggleData();
				Thread.sleep(5000);
				if(cs.isDataEnabled())
				{
					System.out.println("Data is now turned ON");
				}
				else
				{
					System.out.println("Data is still OFF");
				}
			}
			
			//Enabling and Disabling airplane mode
			if(cs.isAirplaneModeEnabled())
			{
				System.out.println("Airplane Mode is ON");
				Thread.sleep(5000);
				driver.toggleAirplaneMode();
				Thread.sleep(5000);
				if(cs.isAirplaneModeEnabled())
				{
					System.out.println("Airplane mode is ON");
					
				}
				else
				{
					System.out.println("Airplane mode if OFF");
				}
			}
			else
			{
				System.out.println("Airplane mode id OFF");
				driver.toggleAirplaneMode();
				Thread.sleep(5000);
				if(cs.isAirplaneModeEnabled())
				{
					System.out.println("Airplane mode is now Turned ON");
				}
				else
				{
					System.out.println("Airplane mode is still OFF ");
				}
			}
			
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
