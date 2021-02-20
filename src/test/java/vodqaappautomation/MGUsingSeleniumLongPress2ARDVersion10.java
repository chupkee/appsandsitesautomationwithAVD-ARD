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

public class MGUsingSeleniumLongPress2ARDVersion10 
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
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='LOG IN']"))).click();
			while(2>1)
			{
				try
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Long Press']"))).click();
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
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Long Press Me']")));
			MobileElement ele=(MobileElement)driver.findElement(By.xpath("//*[@text='Long Press Me']"));
			Point source=ele.getCenter();
			PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH,"finger");
			Sequence longpress=new Sequence(finger,1);
			Interaction i1=finger.createPointerMove(Duration.ofMillis(0), Origin.viewport(),source.x, source.y);
			longpress.addAction(i1);
			Interaction i2=finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
			longpress.addAction(i2);
			Pause i3=new Pause(finger, Duration.ofMillis(1000));
			longpress.addAction(i3);
			Interaction i4=finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg());
			longpress.addAction(i4);
			driver.perform(Arrays.asList(longpress));
			
			//Validation
			try
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='you pressed me hard :P']")));
				if(driver.findElement(By.xpath("//*[@text='you pressed me hard :P']")).isDisplayed())
				{
					System.out.println("LongPress test Passed");
					driver.findElement(By.xpath("//*[@text='OK']")).click();
				}
			}
			catch(Exception exec)
			{
				System.out.println("LongPress test Failed");
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
