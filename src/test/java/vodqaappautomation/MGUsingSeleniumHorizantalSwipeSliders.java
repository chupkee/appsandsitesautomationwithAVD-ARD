package vodqaappautomation;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class MGUsingSeleniumHorizantalSwipeSliders 
{
	public static void main(String[] args) throws Exception
	{
		//Start appium sever
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		URL u=new URL("http://0.0.0.0:4723/wd/hub");
		//Define desired capabilities related to device and app
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","ZY2243MMDP");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.1.0");
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
		
		try
		{
			WebDriverWait wait=new WebDriverWait(driver,5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='LOG IN']"))).click();
			while(2>1)
			{
				try
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Slider']"))).click();
					break;
					
				}
				catch(Exception exe)
				{
					//get device diamention
					int width=driver.manage().window().getSize().getWidth();
					int height=driver.manage().window().getSize().getHeight();
					//Swipe logic 
					PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH,"finger");
					Sequence swipe=new Sequence(finger,1);
					Interaction i1=finger.createPointerMove(Duration.ofMillis(0),Origin.viewport(),width/2,(int) (height*0.8));
					swipe.addAction(i1);
					Interaction i2=finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
					swipe.addAction(i2);
					Interaction i3=finger.createPointerMove(Duration.ofMillis(1000), Origin.viewport(),width/2,(int)(height*0.3));
					swipe.addAction(i3);
					Interaction i4=finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg());
					swipe.addAction(i4);
					driver.perform(Arrays.asList(swipe));
				}
			}
			
			
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Slider']")));
			//Slider 1
			//Swipe left to right and right to left horizontally
			//Get element dimensions
			MobileElement ele1=(MobileElement) driver.findElement(By.xpath("//*[@content-desc='slider']"));
			int width1=ele1.getSize().getWidth();
			int height1=ele1.getSize().getHeight();
			int x1=ele1.getLocation().getX();
			int y1=ele1.getLocation().getY();
			//Swipe Logic
			PointerInput finger1=new PointerInput(PointerInput.Kind.TOUCH,"finger1");
			Sequence swipe1=new Sequence(finger1,1);
			Interaction i1=finger1.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),x1,y1+height1/2);
			swipe1.addAction(i1);
			Interaction i2=finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
			swipe1.addAction(i2);
			Interaction i3=finger1.createPointerMove(Duration.ofMillis(1000),PointerInput.Origin.viewport(),x1+width1/2,y1+height1/2);
			swipe1.addAction(i3);
			Thread.sleep(3000);
			Interaction i4=finger1.createPointerMove(Duration.ofMillis(1000),PointerInput.Origin.viewport(),x1,y1+height1/2);
			swipe1.addAction(i4);
			Interaction i5=finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg());
			swipe1.addAction(i5);
			driver.perform(Arrays.asList(swipe1));
			
			Thread.sleep(3000);
			System.out.println("Slider 1 left to right and right to left horizontally");
			
			//Slider 2
			//Swipe left to right and right to left horizontally
			//Get element dimensions
			MobileElement ele2=(MobileElement) driver.findElement(By.xpath("//*[@content-desc='slider1']"));
			int width2=ele2.getSize().getWidth();
			int height2=ele2.getSize().getHeight();
			int x2=ele2.getLocation().getX();
			int y2=ele2.getLocation().getY();
			//Swipe Logic
			PointerInput finger2=new PointerInput(PointerInput.Kind.TOUCH,"finger2");
			Sequence swipe2=new Sequence(finger2,1);
			Interaction i6=finger2.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),x2,y2+height2/2);
			swipe2.addAction(i6);
			Interaction i7=finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
			swipe2.addAction(i7);
			Interaction i8=finger2.createPointerMove(Duration.ofMillis(1000),PointerInput.Origin.viewport(),x2+width2/2,y2+height2/2);
			swipe2.addAction(i8);
			Thread.sleep(3000);
			Interaction i9=finger2.createPointerMove(Duration.ofMillis(1000),PointerInput.Origin.viewport(),x2,y2+height2/2);
			swipe2.addAction(i9);
			Interaction i10=finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg());
			swipe2.addAction(i10);
			driver.perform(Arrays.asList(swipe2));
			Thread.sleep(3000);
			System.out.println("Slider2 left to right and right to left horizontally");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		//Close app
		driver.closeApp();
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}
