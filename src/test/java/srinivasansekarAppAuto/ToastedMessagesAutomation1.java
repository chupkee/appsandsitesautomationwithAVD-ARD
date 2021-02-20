package srinivasansekarAppAuto;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class ToastedMessagesAutomation1
{ 
	public static void main(String[] args) throws Exception
	{
		//Start Appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
		//address of appium server 
		URL u=new URL("http://127.0.0.1:4723/wd/hub");
		//Details of app and device
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName","ZY2243MMDP");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.1.0");
		dc.setCapability("appPackage","com.soumyasethy.smstoast");
		dc.setCapability("appActivity","com.soumyasethy.smstoast.SMSToast");
		
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
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='about']")));
			driver.findElementByAndroidUIAutomator("new UiSelector().description(\"about\")").click();
			String toast_msg=driver.findElementByXPath("/hierarchy/android.widget.Toast").getText();
			System.out.println("toast_msg");
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		//Close app
		driver.closeApp();
		//stop appim server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}
