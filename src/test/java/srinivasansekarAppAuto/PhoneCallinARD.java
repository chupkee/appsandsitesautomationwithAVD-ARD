package srinivasansekarAppAuto;

import java.net.URL;
import java.util.Scanner;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class PhoneCallinARD 
{
	public static void main(String[] args) throws Exception
	{
		//Give a mobile number
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter 1st 10 digit mobile number to dail");
		String mbno1=sc.nextLine();
		System.out.println("Enter 2nd 10 digit mobile number to dail");
		String mbno2=sc.nextLine();
		sc.close();
		//Details of app and devices(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","");
		dc.setCapability("platformName", "android");
		dc.setCapability("platformVersion","8.1.1");
		dc.setCapability("appPackage","");
		dc.setCapability("appActivity","");
		 
		//Start appium server programmatically
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
		//get address of appim server
		URL u=new URL("http://127.0.0.1:4723/wb/hub");
		
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
		
		//close app
		driver.closeApp();
		
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}

}
