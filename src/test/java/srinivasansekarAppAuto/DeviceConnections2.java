package srinivasansekarAppAuto;

import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;

public class DeviceConnections2 
{
	public static void main(String[] args) throws Exception
	{
		//Details of app and devices(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","emulator-5554");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","5.1.1");
		dc.setCapability("appPackage","com.vodqareactnative");
		dc.setCapability("appActivity","com.vodqareactnative.MainActivity");
		
		
		//Start appium server
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
		
		//validation
		try
		{
			ConnectionState setcon;
			ConnectionState cs=driver.getConnection();
			//Enabling and disabling WIFI
			if(cs.isDataEnabled())
			{
				System.out.println("Wifi is ON");
				Thread.sleep(5000);
				driver.toggleWifi();
				Thread.sleep(5000);
				if(cs.isWiFiEnabled())
				{
					System.out.println("Wifi is still ON");
				}
				else
				{
					System.out.println("Wifi is Turned OFF");
				}
			}
			else
			{
				System.out.println("Wifi is OFF");
				setcon=new ConnectionState(ConnectionState.WIFI_MASK);
				driver.setConnection(setcon);
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
			
			//Enabling and disabling DATA
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
				System.out.println("Data is OFF");
				setcon=new ConnectionState(ConnectionState.DATA_MASK);
				driver.setConnection(setcon);
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
			
			//Enabling and Disabling Airplane Mode
			if(cs.isAirplaneModeEnabled())
			{
				System.out.println("AirplaneMode is On");
				Thread.sleep(5000);
				driver.toggleAirplaneMode();
				Thread.sleep(5000);
				if(cs.isAirplaneModeEnabled())
				{
					System.out.println("AirplaneMode is still ON");
				}
				else
				{
					System.out.println("AirplaneMode is Turned OFF");
				}
			}
			else
			{
				System.out.println("AirplaneMode is OFF");
				/*setcon=new ConnectionState(ConnectionState.AIRPLANE_MODE_MASK);
				driver.setConnection(setcon);
				Thread.sleep(5000);
				if(cs.isAirplaneModeEnabled())
				{
					System.out.println("AirplaneMode is now Turned ON");
				}
				else
				{
					System.out.println("AirplaneMode is still OFF");
				}*/
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		//close app
		driver.closeApp();
		
		//stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}
