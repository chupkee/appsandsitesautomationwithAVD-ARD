package mobilegestures;

import java.net.URL;
import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class MGUsingAppiumVerticalHorizontalContactProgram
{
	public static void main(String[] args) throws Exception
	{
		//Take contact name from Scanner class
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter contact name to call as it saved in contact list");
		String contactname=sc.nextLine();
		sc.close();
		//Start appium server programmatically
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
		//Get address of appium server
		URL u=new URL("http://127.0.0.1:4723/wd/hub");
		//Detail of app and device ARD
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","ZY2243MMDP");
		dc.setCapability("platformName", "android");
		dc.setCapability("platformVersion","8.1.0");
		dc.setCapability("appPackage","com.android.contacts");
		dc.setCapability("appActivity","com.android.contacts.activities.PeopleActivity");
		
		//create driver object
		AndroidDriver driver;
		while(2>1)
		{
			try
			{;
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
			WebDriverWait wait=new WebDriverWait(driver,3);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Contacts']")));
			TouchAction ta=new TouchAction(driver);
			WaitOptions wo=new WaitOptions();
			wo.withDuration(Duration.ofMillis(1000));
			
			while(2>1)
			{
				try
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='"+contactname+"']")));
					break;
				}
				catch(Exception exe)
				{
					//Get device dimension
					int width=driver.manage().window().getSize().getWidth();
					int height=driver.manage().window().getSize().getHeight();
					//swipe logic
					ta.press(ElementOption.point(width/2,(int)(width*0.9))).waitAction(wo).moveTo(ElementOption.point(width/2,(int)(height*0.1))).release().perform();				
				}
			}
			//Get Element dimensions
			MobileElement ele=(MobileElement)driver.findElement(By.xpath("//*[@text='"+contactname+"']/parent::*/parent::*"));
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@resource-id='com.android.contacts:id/icon'])[1]"))).click();
			Point source=ele.getCenter();
			int w=ele.getSize().getWidth();
			int h=ele.getSize().getHeight();
			int x=source.x;
			int y=source.y;
			ta.press(ElementOption.point((int)(x-w/2*0.8),y)).waitAction(wo).moveTo(ElementOption.point((int)(x+w/2*0.2),y)).release().perform();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@resource-id='com.android.contacts:id/icon'])[1]"))).click();
			Thread.sleep(20000);
			
			driver.findElementByAndroidUIAutomator("new UiSelector().description(\"End call\")").click();
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		//close app
		driver.closeApp();
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F IM node.exe");
		Runtime.getRuntime().exec("taskkill /F IM cmd.exe");
	}
}
