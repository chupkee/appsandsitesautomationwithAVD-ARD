package srinivasansekarAppAuto;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.appmanagement.ApplicationState;

public class SampleMethods1 
{
	public static void main(String[] args) throws Exception
	{
		//Details of app and device
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "");
		dc.setCapability("platformName","");
		dc.setCapability("platfromVersion","");
		dc.setCapability("appPackage","");
		dc.setCapability("appActivity","");
		//Start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
		//get address of appium server
		URL u=new URL("http://127.0.0.1:4723/wb/hub/");
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
			//Get details of Launched app Specified in desiredCapability 
			System.out.println("Package name is "+driver.getCurrentPackage());
			System.out.println("Activity name is "+driver.currentActivity());
			Thread.sleep(5000);
			//work with other app
			if(driver.isAppInstalled(""))
			{
				System.out.println("app is available");
				driver.activateApp("");
				Thread.sleep(5000);
			}
			else
			{
				driver.installApp("");
				System.out.println("app is now available");
			}
			//Get status of other app launch 
			ApplicationState as=driver.queryAppState("");
			System.out.println(as.toString());
			Thread.sleep(5000);
			
			//Get other app launched details
			System.out.println("Package name is "+driver.getCurrentPackage());
			System.out.println("Activity name is "+driver.currentActivity());
			Thread.sleep(5000);
			
			//Close the other launched app details
			driver.terminateApp("");
			Thread.sleep(5000);
			driver.launchApp();
			driver.runAppInBackground(Duration.ofSeconds(10));
			Thread.sleep(5000);
			String o1=driver.getOrientation().name();
			System.out.println(o1);
			Thread.sleep(3000);
			if(o1.equalsIgnoreCase("PORTRAIT"))
			{
				driver.rotate(ScreenOrientation.LANDSCAPE);
			}
			Thread.sleep(5000);
			String o2=driver.getOrientation().name();
			System.out.println(o2);
			if(o2.equalsIgnoreCase("LANDSCAPE"))
			{
				driver.rotate(ScreenOrientation.PORTRAIT);
			}
			Thread.sleep(5000); 
			
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='username']")));
			driver.findElement(By.xpath("//*[@content-desc='username']")).click();
			Thread.sleep(3000);
			if(driver.isKeyboardShown())
			{
				System.out.println("keyboard is visible");
				driver.hideKeyboard();
				Thread.sleep(5000);
			}
			KeyEvent k=new KeyEvent(AndroidKey.HOME);
			driver.pressKey(k);
			Thread.sleep(5000);
			
			//Back to work with specified desired Capability
			driver.resetApp();  // driver.launchApp();
			Thread.sleep(5000);
			
			
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
