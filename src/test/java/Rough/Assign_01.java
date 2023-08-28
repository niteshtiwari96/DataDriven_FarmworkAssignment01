package Rough;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


/*
 * 1.  To verify that the Google web page is launched successfully
 */

public class Assign_01 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "D:\\Manual Testing and Automation Selenium\\Selenium_Drivers\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		
		/*System.setProperty("webdriver.geckodriver.driver", "D:\\Manual Testing and Automation Selenium\\Selenium_Drivers\\geckodriver-v0.32.0-win-aarch64\\geckodriver.exe");
        WebDriver driver=new  FirefoxDriver();*/
		
		driver.get("https://www.google.co.in/");
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//click_on_SignIn_Button
		WebElement SignIn=driver.findElement(By.linkText("Sign in"));
		SignIn.click();
		
		//Enter username
		WebElement Email=driver.findElement(By.id("identifierId"));
		Email.sendKeys("nittiw63@gmail.com");
		
		//click on Next button
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='identifierNext']/div/button/span")).click();
		
		//Enter password
		WebElement Password=driver.findElement(By.xpath("//*[@id='password']/div[1]/div/div[1]/input"));
		Password.sendKeys("blu3@T0p");
		
		//click on Next button
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='passwordNext']/div/button/span")).click();
		
		Thread.sleep(2000);
		//Enter on Google_Search_Box
		WebElement googleSearchBox=driver.findElement(By.id("APjFqb"));
		googleSearchBox.sendKeys("cds");
		
		Thread.sleep(3000);
		//Click On GoogleSearch Button
		WebElement googleSearch=driver.findElement(By.xpath("(//input[@aria-label='Google Search'])[1]"));
		googleSearch.click();
		
		driver.navigate().back();
		
		//click on imFeelingLucky link
		Thread.sleep(2000);
		WebElement imFeelingLucky=driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[4]/center/input[2]"));
		imFeelingLucky.click();
		
		driver.navigate().back();
		Thread.sleep(2000);
	
		driver.close();
	}

}
