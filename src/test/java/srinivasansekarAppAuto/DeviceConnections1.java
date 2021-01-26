package srinivasansekarAppAuto;

import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;

public class DeviceConnections1
{
	public static void main(String[] args) throws Exception
	{
		//Details of app and device(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","emulator-5554");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","5.1.1");
		dc.setCapability("appPackage","com.vodqareactnative");
		dc.setCapability("appActivity","com.vodqareactnative.MainActivity");
		//Start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 0.0.0.0 -p 4723\"");
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
		
		//Validations
		try
		{
			//get connection details of WIFI, DATA AND AIRPHANE mode device
			ConnectionState cs=driver.getConnection();
			//about FIWI
			if(cs.isWiFiEnabled())
			{
				System.out.println("WIFI is ON");
			}
			else
			{
				System.out.println("WIFI is OFF");
			}
			
			//About Data
			if(cs.isDataEnabled())
			{
				System.out.println("DATA is ON");
			}
			else
			{
				System.out.println("DARA is OFF");
			}
			
			//About Airplane mode
			if(cs.isAirplaneModeEnabled())
			{
				System.out.println("AIRPLANE mode is ON");
			}
			else
			{
				System.out.println("AIRPLANE mode is OFF");
			}
			
			String autoname=driver.getAutomationName();
			System.out.println("Currently using driver is "+autoname);
		}
		catch(Exception exe)
		{
			System.out.println(exe.getMessage());
		}
		
		
		//close site
		driver.closeApp();
		
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}

}
