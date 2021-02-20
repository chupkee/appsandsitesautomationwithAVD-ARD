package srinivasansekarAppAuto;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class ApiDemoAppLaunchAndVoiceRecognition 
{
	public static void main(String[] args) throws Exception
	{
		//Create an Array of names
		String[] names= {"congratulations Akka","ShwethaPraneeth"};
		
		//Start appium server programmatically
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
		//get address of appium server
		URL u=new URL("http://127.0.0.1:4723/wd/hub");
		//Details of app and device(ARD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","ZY2243MMDP");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.1.0");
		dc.setCapability("appPackage","com.example.android.apis");
		dc.setCapability("appActivity","com.example.android.apis.ApiDemos");
		
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
		//App Automation
		try
		{
			WebDriverWait wait=new WebDriverWait(driver,5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='App']"))).click();
			TouchAction ta=new TouchAction(driver);
			WaitOptions wo=new WaitOptions();
			wo.withDuration(Duration.ofMillis(1000));
			while(2>1)
			{
				try
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Voice Recognition']"))).click();
					break;
				}
				catch(Exception ex)
				{
					//get dimension of element
					int width=driver.manage().window().getSize().getHeight();
					int height=driver.manage().window().getSize().getHeight();
					
					//Swipe Logic
					/*ta.press(ElementOption.point(width/2,(int) (height*0.8))).moveTo(ElementOption.point(width/2,(int) (height*0.2))).release().perform();
					System.out.println("here come program");
					*/
					//Swipe Logic
					PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH,"finger");
					Sequence swipe=new Sequence(finger,1);
					Interaction i1=finger.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),width/2,(int) (height*0.8));
					swipe.addAction(i1);
					Interaction i2=finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
					swipe.addAction(i2);
					Interaction i3=finger.createPointerMove(Duration.ofMillis(2000),PointerInput.Origin.viewport(),width/2,(int) (height*0.3));
					swipe.addAction(i3);
					Interaction i4=finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg());
					swipe.addAction(i4);
					driver.perform(Arrays.asList(swipe));
					
					
					
					
				}
				
				
			}

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='SPEAK!']")));
			MobileElement ele=(MobileElement) driver.findElement(By.xpath("//*[@text='SPEAK!']"));
			ta.longPress(ElementOption.element(ele)).perform();
			
			
			//Validation 
			try
			{
				/*wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='Google logo']")));
				String text=driver.findElement(By.xpath("//*[@content-desc='Google logo']")).getText();
				text=text.replace("Google logo","congratulations Akka");*/
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='SPEAK!']")));
				for(int i=0;i<names.length;i++)
				{
					String text=driver.findElement(By.xpath("//*[@text='SPEAK!']")).getText();
					text=text.replace("'SPEAK!","names[i]");
				}
				
				//String text=driver.findElement(By.xpath("///*[@text='English (United States)']")).getText();
				//text=text.replace(" (United States)","congratulations Akka");
				
				
			}
			catch(Exception ex)
			{
				System.out.println();
			}
			
			
			
			
			
			
			
			
			
			
			
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='android.widget.ImageView'])[3]"))).click();
			
			
			//MobileElement ele=(MobileElement) driver.findElement(By.xpath("//*[@text='SPEAK!']"));
			//int 
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='Google logo']")));
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='congratulations Akka']")));
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		Thread.sleep(3000);
		//close app
		driver.closeApp();
		
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}

}
