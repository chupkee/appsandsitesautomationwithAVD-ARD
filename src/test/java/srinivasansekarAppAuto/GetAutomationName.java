package srinivasansekarAppAuto;

import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;

public class GetAutomationName 
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
		
		//create driver object
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
			//Get connection details of FIWI, DATA, AND AIRPLANEMODE of related device
			ConnectionState cs=driver.getConnection();
			//About FiWi
			if(cs.isWiFiEnabled())
			{
				System.out.println("Fiwi is ON");
			}
			else
			{
				System.out.println("Fiwi is OFF");
			}
			
			//About Data
			if(cs.isDataEnabled())
			{
				System.out.println("Data is ON");
			}
			else
			{
				System.out.println("Data is OFF");
			}
			
			//About airplane mode
			if(cs.isAirplaneModeEnabled())
			{
				System.out.println("AirplaneMode is ON");
			}
			else
			{
				System.out.println("AirplaneMode is OFF");
			}
			
			String autoname=driver.getAutomationName();
			System.out.println(autoname);
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
