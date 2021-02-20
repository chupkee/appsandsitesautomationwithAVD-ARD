package locator;


public class MSMobileBy5AndroidViewTagAndroidEspresso
{

	public static void main(String[] args) throws Exception
	{
		
		
		/*
		driver.findElement(MobileSelector.ANDROID_VIEWTAG.toString(),"view-tag attribute value").click();
        driver.findElement(MobileBy.AndroidViewTag("view-tag attribute value")).click();		
		driver.findElementByAndroidViewTag("view-tag attribute value").click();
		*/
		
		
		//view-tag attribute value can be given by espresso driver only via getPageSource
		//Cannot be provided by appium desktop server inspector/uiAutomatorViews

	}

}
