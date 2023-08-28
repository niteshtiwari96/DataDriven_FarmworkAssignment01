package dd_tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dd_Core.testCore;
import dd_Util.ExcelReader;
import dd_Util.testUtil;



public class GoogleWebpage_Launching_Test  extends testCore{
	
	@BeforeTest
	public void isSkip()
	{
		if(!testUtil.isExecutable("GoogleWebpage_Launching_Test"))
		{
			throw new SkipException("Skipping testcase as runmode is set to No");
		}
	}
	
	@Test(dataProvider="getData")
	public void googleBrowerLaunching(String email,String pass) throws IOException
	{
		try{
			//click_on_SignIn_Button
			Thread.sleep(3000);
			app_logs.debug("Executing google Brower Launching Test");
			app_logs.debug("click_on_SignIn_Button");
			/*try {
				
				driver.findElement(By.xpath(object.getProperty("Sign"))).click();
		    }catch(Exception e) 
		    {   
	        JavascriptExecutor executor=( JavascriptExecutor)driver;
	        executor.executeScript("arguments[0].click();",  driver.findElement(By.xpath(object.getProperty("Sign"))));
		    }*/
			driver.findElement(By.xpath(object.getProperty("Sign"))).click();
			//Enter username
			app_logs.debug("Enter username");
			driver.findElement(By.id(object.getProperty("Email_ID"))).sendKeys(email);
			
			//click on Next button
			app_logs.debug("click on Next button");
			Thread.sleep(3000);
			driver.findElement(By.xpath(object.getProperty("ID_Next_Button"))).click();
			
			//Enter password
			app_logs.debug("Enter password");
			driver.findElement(By.xpath(object.getProperty("Password"))).sendKeys(pass);
			
			//click on Next button
			app_logs.debug("click on Next button");
			Thread.sleep(3000);
			driver.findElement(By.xpath(object.getProperty("Password_Next_Button"))).click();
			
			Thread.sleep(2000);
			//Enter on Google_Search_Box
			app_logs.debug("Enter on Google_Search_Box");
			driver.findElement(By.id(object.getProperty("Google_Search_Box"))).sendKeys("cds");
			
			Thread.sleep(3000);
			//Click On GoogleSearch Button
			app_logs.debug("Click On GoogleSearch Button");
			driver.findElement(By.xpath(object.getProperty("GoogleSearchButton"))).click();
			
			driver.navigate().back();
			
			//click on imFeelingLucky link
			Thread.sleep(2000);
			app_logs.debug("click on imFeelingLucky link");
			driver.findElement(By.xpath(object.getProperty("imFeelingLucky"))).click();
			
			driver.navigate().back();
			Thread.sleep(2000);
		
			driver.close();
			
			}catch (Throwable t)
			{
				testUtil.captureScreenshot("GoogleWebpage_Launching_Test");
				Assert.assertTrue(false, t.getMessage());
				
			}
		
	}
	
	@DataProvider
	public static Object[][] getData()
	{
		return testUtil.getData("GoogleWebpage_Launching_Test");
	}

	
}

