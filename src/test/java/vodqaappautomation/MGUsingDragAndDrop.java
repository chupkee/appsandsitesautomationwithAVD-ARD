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
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class MGUsingDragAndDrop 
{
	public static void main(String[] args) throws Exception
	{
		//Start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
		//Get address of appium Server
		URL u=new URL("http://127.0.0.1:4723/wd/hub");
		//Details of app and device(AVD)
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
		
		//Automation 
		try
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='LOG IN']"))).click();
			while(2>1)
			{
				try
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Drag & Drop']"))).click();
					break;
				}
				catch(Exception exe)
				{
					//Get device dimension
					int width=driver.manage().window().getSize().getWidth();
					int height=driver.manage().window().getSize().getHeight();
					//Swipe logic
					PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH,"finger");
					Sequence swipe=new Sequence(finger,1);
					Interaction i1=finger.createPointerMove(Duration.ofMillis(0), Origin.viewport(), width/2,(int) (height*0.9));
					swipe.addAction(i1);
					Interaction i2=finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
					swipe.addAction(i2);
					Interaction i3=finger.createPointerMove(Duration.ofMillis(1000), Origin.viewport(), width/2, (int)(height*0.2));
					swipe.addAction(i3);
					Interaction i4=finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg());
					swipe.addAction(i4);
					driver.perform(Arrays.asList(swipe));
					
				}
			}
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Drag me!']")));
			MobileElement ele1=(MobileElement)driver.findElement(By.xpath("//*[@text='Drag me!']"));
			Point drag=ele1.getCenter();
			MobileElement ele2=(MobileElement) driver.findElement(By.xpath("//*[@text='Drop here.']"));
			Point drop=ele2.getCenter();
			PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH,"finger");
			Sequence draganddrop=new Sequence(finger,1);
			Interaction i1=finger.createPointerMove(Duration.ofMillis(0), Origin.viewport(), drag.x, drag.y);
			draganddrop.addAction(i1);
			Interaction i2=finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
			draganddrop.addAction(i2);
			Interaction i3=finger.createPointerMove(Duration.ofMillis(5000), Origin.viewport(), drop.x, drop.y);
			draganddrop.addAction(i3);
			Interaction i4=finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg());
			draganddrop.addAction(i4);
			driver.perform(Arrays.asList(draganddrop));
			
			//Validation
			try
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Circle dropped']")));
				if(driver.findElement(By.xpath("//*[@text='Circle dropped']")).isDisplayed())
				{
					System.out.println("DragandDrop test passed");
				}
			}
			catch(Exception exec)
			{
				System.out.println("DragandDrop test failed");
				SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
				Date dt=new Date();
				String fname=sf.format(dt);
				File src=driver.getScreenshotAs(OutputType.FILE);
				File dest=new File(fname+".png");
				FileHandler.copy(src, dest);
			}
			
			
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
