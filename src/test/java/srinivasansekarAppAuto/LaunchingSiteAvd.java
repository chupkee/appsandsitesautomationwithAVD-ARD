package srinivasansekarAppAuto;

import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class LaunchingSiteAvd 
{
	public static void main(String[] args) throws Exception
	{
		//Launch appium server programmatically
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 0.0.0.0 -p 4724\"");
		//get address of appium server
		URL u=new URL("http://0.0.0.0:4724/wd/hub");
		//Desired Capabilities
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"chrome");
		dc.setCapability("deviceName","emulator-5554");
		dc.setCapability("platfromName","android");
		dc.setCapability("platfromVersion","5.1.1");
		
		
		//Object creation
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
		
		Thread.sleep(5000);
		
		//close site
		driver.close();
		//Stop appium server
		Runtime.getRuntime().exec("tasktill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
		
	}
}
