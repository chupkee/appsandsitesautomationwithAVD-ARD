package srinivasansekarAppAuto;


public class StartAndStopAppiumServer 
{
	public static void main(String[] args)  throws Exception
	{
		//Start Appium server programmatically
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
		Thread.sleep(10000);
		
		//Stop appium server programmatically
		Runtime.getRuntime().exec("taskkill /F /IM node.eex");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");	
	}
}
