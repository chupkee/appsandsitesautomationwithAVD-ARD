package extrapractice;

import java.net.URL;
import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class OpenSavedContackAndCall 
{
	public static void main(String[] args) throws Exception
	{
		//Take data from keyboard
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter contact name to call saved in our contact list");
		String contactname=sc.nextLine();		
		sc.close();			
		//Start appium server programmatically
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
		//get address of appium server
		URL u=new URL("http://127.0.0.1:4723/wd/hub");
		//Get details of device and app(ARD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","ZY2243MMDP");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.1.0");
		dc.setCapability("appPackage","com.android.contacts");
		dc.setCapability("appActivity","com.android.contacts.activities.PeopleActivity");
		
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
		
		//app automation
		try
		{
			WebDriverWait wait=new WebDriverWait(driver,2);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Contacts']")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@resource-id='com.android.contacts:id/floating_action_button_container']")));
			TouchAction ta=new TouchAction(driver);
			WaitOptions wo=new WaitOptions();
			wo.withDuration(Duration.ofMillis(5000));
			
			while(2>1)
			{
				try
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='"+contactname+"']")));
					break;
				}
				catch(Exception e)
				{
					//Get device Automation
					int width=driver.manage().window().getSize().getWidth();
					int height=driver.manage().window().getSize().getHeight();
					//swipe logic
					ta.press(ElementOption.point(width/2,(int)(height*0.8))).waitAction(wo).moveTo(ElementOption.point(width/2,(int)(height*0.2))).release().perform();
				}
				
			}
			
			//get element dimensions
			MobileElement ele=(MobileElement) driver.findElement(By.xpath("//*[@text='"+contactname+"']/parent::*/parent::*"));
			Point source=ele.getCenter();
			int w=ele.getSize().getWidth();
			int h=ele.getSize().getHeight();
			int x=source.x;
			int y=source.y;
			
			//Horizontal swipe logic
			ta.press(ElementOption.point((int) (x-w/2*0.8),y)).waitAction(wo).moveTo(ElementOption.point((int)(x+w/2*0.8),y)).release().perform();
			
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='android.widget.ImageView'])[2]"))).click();
			
			Thread.sleep(5000);
			driver.findElementByAndroidUIAutomator("new UiSelector().description(\"End call\")");
			
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@resource-id='com.android.dialer:id/incall_end_call']"))).click();
			
			
			
			
		}
		catch(Exception exe)
		{
			System.out.println(exe.getMessage());
		}
		
		//close app
		driver.closeApp();
		
		//stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
		System.out.println("perfect Execution");
	}

}
