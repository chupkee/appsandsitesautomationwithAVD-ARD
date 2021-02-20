package locator;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileSelector;
import io.appium.java_client.android.AndroidDriver;

public class MSMobileBy2ImageAppSite 
{
	public static void main(String[] args) throws Exception
	{
		//Convert base64 image to string
		File f=new File("E:\\batch249\\appiumexamples\\dialpic.png");
		Path path=f.toPath();
	    //String x=Base64.getEncoder().encodeToString(Files.readAllBytes(path));
		//System.out.println(x);
		
		//Start appium server 
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
		//Get details of appium server
		URL u=new URL("http://127.0.0.1:4723/wd/hub");
		//Details of app and device
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","ZY2243MMDP");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.1.0");
		dc.setCapability("appPackage","com.motorola.dialer");
		dc.setCapability("appActivity","com.motorola.dialer.app.ExtendedDialtactsActivity");
		
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
			Thread.sleep(5000);
			/*driver.findElement(MobileBy.image(x)).isDisplayed();
			driver.findElement(MobileSelector.IMAGE.toString(),x).isDisplayed();
			if(driver.findElementByImage(x).isDisplayed())
			{
				int xco=driver.findElementByImage(x).getLocation().getX();
				int yco=driver.findElementByImage(x).getLocation().getY();
				int w=driver.findElementByImage(x).getSize().getWidth();
				int h=driver.findElementByImage(x).getSize().getHeight();
				System.out.println(xco+" "+yco);
				System.out.println(w+" "+h);
				//Automating operations on matching element using "TouchActions" class methods
			}*/
			
			Thread.sleep(5000);
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		//close app
		driver.closeApp();
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.cmd");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}
