package mobilegestures;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class MGUsingAppiumZoom 
{
	public static void main(String[] args) throws Exception
	{
		//Start appium server programmatically
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
		//get address of appium server
		URL u=new URL("http://127.0.0.1:4723/wd/hub");
		//Details of app and devices (ARD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName", "ZY2243MMDP");
		dc.setCapability("platforName", "android");
		dc.setCapability("platformVersion", "8.1.0");
		dc.setCapability("autoGrantPermission","true");
		dc.setCapability("adbExecTimeout","40000");
		dc.setCapability("showGradleLog", "true");
		dc.setCapability("appPackage","com.vodqareactnative");
		dc.setCapability("appActivity","com.vodqareactnative.MainActivity");
		
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
		
		//App Automation
		try
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='LOG IN']"))).click();
			TouchAction ta=new TouchAction(driver);
			WaitOptions wo=new WaitOptions();
			wo.withDuration(Duration.ofMillis(5000));
			while(2>1)
			{
				try
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Photo View']")));
					break;
				}
				catch(Exception exe)
				{
					//Get device dimensions
					int width=driver.manage().window().getSize().getWidth();
					int height=driver.manage().window().getSize().getHeight();
					//swipe logic
					ta.press(ElementOption.point(width/2,(int)(height*0.8))).waitAction().moveTo(ElementOption.point(width/2,(int)(height*0.2))).release().perform();
					
				}
			}
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='android.widget.ImageView']")));
			MobileElement ele=(MobileElement)driver.findElement(By.xpath("//*[@class='android.widget.ImageView']"));
			Point source=ele.getCenter();
			int w=ele.getSize().getWidth();
			int h=ele.getSize().getHeight();
			int x=source.x;
			int y=source.y;
			
			TouchAction ta1=new TouchAction(driver);
			TouchAction ta2=new TouchAction(driver);
			ta1.press(ElementOption.point(x+10,y-10)).waitAction(wo).moveTo(ElementOption.point(x+w/2,y-h/2)).release();
			ta2.press(ElementOption.point(x-10,y+10)).waitAction(wo).moveTo(ElementOption.point(x-w/w,y+h/2)).release();
			MultiTouchAction ta3=new MultiTouchAction(driver);
			ta3.add(ta1).add(ta2).perform();Thread.sleep(5000);
			Thread.sleep(5000);
			
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
