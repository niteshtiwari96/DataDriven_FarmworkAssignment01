package dd_Core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;



import dd_Util.ExcelReader;
import dd_Util.TestConfig;
import dd_Util.monitoringMail;
import dd_Util.testUtil;





public class testCore {

	public static Properties config=new Properties();
	public static Properties object=new Properties();
	public static FileInputStream fis;
	public static WebDriver driver=null;
	public static WebDriverWait wait=null;
	public static ExcelReader excel=null;
	public static Logger app_logs=Logger.getLogger("devpinoyLogger");
	
	@BeforeSuite
	public void init() throws IOException
	{
		if(driver==null)
		{
			//loading config property file
			fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\dd_properties\\Config.properties");
			config.load(fis);
			app_logs.debug("loading config property file");
			
			//loading object property file
			fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\dd_properties\\Object.properties");
			object.load(fis);
			app_logs.debug("loading object property file");
			
			//Loading Excel file
			excel=new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\java\\dd_properties\\testDataInClass.xlsx");
			app_logs.debug("Loading Excel file");
			
			//Checking browser and launching according to it.
			app_logs.debug("Launching Browser");
			
			if(config.getProperty("browser").equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "D:\\Manual Testing and Automation Selenium\\Selenium_Drivers\\chromedriver_win32\\chromedriver.exe");
				driver=new ChromeDriver();
				
			}else if(config.getProperty("browser").equals("Edge"))
			{
				System.setProperty("webdriver.msedgedriver.driver", "D:\\Manual Testing and Automation Selenium\\Selenium_Drivers\\edgedriver_win64\\msedgedriver");
				driver=new  EdgeDriver();
				
			}else if(config.getProperty("browser").equals("firefox"))
			{
				System.setProperty("webdriver.geckodriver.driver", "D:\\Manual Testing and Automation Selenium\\Selenium_Drivers\\geckodriver-v0.32.0-win-aarch64\\geckodriver.exe");
				driver=new  FirefoxDriver();
			}
			

			driver.get(config.getProperty("testsite"));
			app_logs.debug("Opening Url");
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
		
	}
	
	
	@AfterSuite
	public void QuitDriver() throws InterruptedException, AddressException, MessagingException
	{
		Thread.sleep(4000);
		if(driver!=null)
			driver.close();
		testUtil.zip(System.getProperty("user.dir")+"\\screenshot\\");
		app_logs.debug("zip report sucessfully");
		monitoringMail mail=new monitoringMail();
		mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, TestConfig.messageBody, TestConfig.attachmentPath, TestConfig.attachmentName);
		app_logs.debug("mail send sucessfully");
	}
}

