package mobilegestures;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
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

public class MGUsingAppiumPinch 
{
	public static void main(String[] args) throws Exception
	{
		//Start appium server programmatically
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
		//get address of appium server
		URL u=new URL("http://127.0.0.1:4723/wd/hub");
		//Details of app and device(ARD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","fb58a03b");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","10");
		dc.setCapability("autoGrantPermission","true");
		dc.setCapability("adbExecTimeout","40000");
		dc.setCapability("appPackage","com.vodqareactnative");
		dc.setCapability("appActivity","com.vodqareactnative.MainActivity");
		
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
		
		//APP Automation 
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
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Photo View']"))).click();
					break;
				}
				catch(Exception exe)
				{
					//Get device dimensions
					int width=driver.manage().window().getSize().getWidth();
					int height=driver.manage().window().getSize().getHeight();
					//swipe logic
					ta.press(ElementOption.point(width/2,(int) (height*0.8))).waitAction(wo).moveTo(ElementOption.point(width/2,(int) (height*0.2))).release().perform();
					
				}
			}
			
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='android.widget.ImageView']")));
			MobileElement ele1=(MobileElement) driver.findElement(By.xpath("//*[@class='android.widget.ImageView']"));
			Point source=ele1.getCenter();
			int w=ele1.getSize().getWidth();
			int h=ele1.getSize().getHeight();
			int x=source.x;
			int y=source.y;
			
			TouchAction ta1=new TouchAction(driver);
			TouchAction ta2=new TouchAction(driver);
			ta1.press(ElementOption.point(x,y)).waitAction(wo).moveTo(ElementOption.point(x,(int)(y-h/2*0.45))).release();
			ta2.press(ElementOption.point(x,y)).waitAction(wo).moveTo(ElementOption.point(x,(int)(y+h/2*0.45))).release();
			
			MultiTouchAction ma=new MultiTouchAction(driver);
			ma.add(ta1).add(ta2).perform();
			Thread.sleep(10000);
			
			//Pinch 
			TouchAction ta3=new TouchAction(driver);
			TouchAction ta4=new TouchAction(driver);
			ta3.press(ElementOption.point(x,(int) (y-h/2*0.35))).waitAction(wo).moveTo(ElementOption.point(x,y)).release();
			ta4.press(ElementOption.point(x,(int) (y+h/2*0.35))).waitAction(wo).moveTo(ElementOption.point(x,y)).release();
			ma.add(ta3).add(ta4).perform();
			Thread.sleep(10000);
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		Thread.sleep(5000);
		//close app
		driver.closeApp();
		//Stop appium server 
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}

}
