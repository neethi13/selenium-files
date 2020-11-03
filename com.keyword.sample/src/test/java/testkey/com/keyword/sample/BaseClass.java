package testkey.com.keyword.sample;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseClass {

	public static WebDriver driver;
	final static Logger logger = Logger.getLogger("BaseClass.class");

	@SuppressWarnings("deprecation")
	public static void launchBrowser(String browserType) {
		try {
//			BasicConfigurator.configure();
//			Properties prop = new Properties();
//			File file = new File("src/test/resources/Configuration/Config.properties");
//			FileInputStream fis = new FileInputStream(file);
//			prop.load(fis);
			//String browserType = prop.getProperty("browserType");
			System.out.println("browser for execution: "+browserType);
			// logger is a system out this is to just write in the log file

			if(browserType.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");
				options.setExperimentalOption("useAutomationExtension", false);
				options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
				options.addArguments("--disable-extensions");
				driver=new ChromeDriver(options);
			}

			else if(browserType.equalsIgnoreCase("internetexplorer")) {
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

				System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
				driver = new InternetExplorerDriver(ieCapabilities);
				driver.manage().window().maximize();
			}

			else if(browserType.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
				FirefoxOptions options = new FirefoxOptions();
				//options.addArguments("--start-maximized");
				options.addArguments("disable-infobars");
				driver=new FirefoxDriver(options);
				driver.manage().window().maximize();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void takeSnapshot(WebDriver driver, String fileSuffix) {
		String currdatentimestamp = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("HH_MM_SS");
			Calendar cal = Calendar.getInstance();
			currdatentimestamp = dateFormat.format(cal.getTime());

			TakesScreenshot scrshot = ((TakesScreenshot)driver);
			String filepath = ".//src//test/resources//snapshots//"+fileSuffix;
			File srcFile = scrshot.getScreenshotAs(OutputType.FILE);
			String filename = filepath+"_"+currdatentimestamp+".png";
			logger.info("Screenshot destination: "+filename);
			File destFile = new File(filename);
			FileUtils.copyFile(srcFile, destFile);
			logger.info("Screenshot captured");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
