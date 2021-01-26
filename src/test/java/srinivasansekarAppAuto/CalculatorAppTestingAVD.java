package srinivasansekarAppAuto;

import java.net.URL;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class CalculatorAppTestingAVD
{
	public static void main(String[] args) throws Exception
	{
		//Enter values for mathematical expression
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter input 1");
		String i1=sc.nextLine();
		System.out.println("Enter input 2");
		String i2=sc.nextLine();
		System.out.println("Enter operations plus/minus/times/divide");
		String op=sc.nextLine();
		sc.close();
		AndroidDriver driver = null;
		if(op.equals("plus") || op.equals("minus") || op.equals("times") || op.equals("divide"))
		{
			//Start appium server programmatically
			Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 0.0.0.0 -p 4723\"");
			//Get address of appium server
			URL u=new URL("http://0.0.0.0:4723/wd/hub");
			//DesiredCapability of app and device
			DesiredCapabilities dc=new DesiredCapabilities();
			dc.setCapability(CapabilityType.BROWSER_NAME,"");
			dc.setCapability("deviceName","emulator-5554");
			dc.setCapability("platformName","android");
			dc.setCapability("platformVersion","5.1.1");
			dc.setCapability("appPackage","com.android.calculator2");
			dc.setCapability("appActivity","com.android.calculator2.Calculator");
			
			//Create driver object
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
				
			//App automation
			try
			{
				WebDriverWait wait=new WebDriverWait(driver,20);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='9']")));
				//Enter input 1
				for(int i=0;i<i1.length();i++)
				{
					char c=i1.charAt(i);
					if(c=='-')
					{
						driver.findElement(By.xpath("//*[@content-desc='minus']")).click();
					}
					else
					{
						driver.findElement(By.xpath("//*[@text='"+c+"']")).click();
					}
				}
				
				//Click math operation button
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='"+op+"']"))).click();
				
				//Enter input 2
				for(int i=0;i<i2.length();i++)
				{
					char c=i2.charAt(i);
					if(c=='-')
					{
						driver.findElement(By.xpath("//*[@content-desc='minus']")).click();
					}
					else
					{
						driver.findElement(By.xpath("//*[@text='"+c+"']")).click();
					}
				}
				
				//Click math operation button
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='equals']"))).click();
				
				int result=Integer.parseInt(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@resource-id='com.android.calculator2:id/formula']"))).getText());
				int x=Integer.parseInt(i1);
				int y=Integer.parseInt(i2);
				
				//validation
				if(op.equals("plus"))
				{
					if(result==(x+y))
					{
						System.out.println("Additon test passed");
					}
					else
					{
						System.out.println("Addition test faile");
					}
				}
				else if(op.equals("minus"))
				{
					if(result==(x-y))
					{
						System.out.println("Subtraction test passed");
					}
					else
					{
						System.out.println("Subtraction test faile");
					}
				}
				else if(op.equals("times"))
				{
					if(result==(x*y))
					{
						System.out.println("Multiplication test passed");
					}
					else
					{
						System.out.println("Multiplication test failed");
					}
				}
				else
				{
					if(result==(x/y))
					{
						System.out.println("Division test passed");
					}
					else
					{
						System.out.println("Division test failed");
					}
				}
				
			}
			catch(Exception exe)
			{
				System.out.println(exe.getMessage());
			}
				
		}
		
		else
		{
			System.out.println("programmam tappu cheptunda....?");
			System.exit(0);
		}
		
		//Close app
		driver.closeApp();
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("cmd.exe /F /IM cmd.exe");
	}
}
