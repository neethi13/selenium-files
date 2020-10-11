package com.app.testng.testng;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class BaseClass {
	public static WebDriver driver;
	final static Logger logger = Logger.getLogger("BaseClass.class");

	@SuppressWarnings("deprecation")
	public static void launchBrowser() {
		try {
			Properties prop = new Properties();
			File file = new File("src/test/resources/Configuration/Config.properties");
			FileInputStream fis = new FileInputStream(file);
			prop.load(fis);
			String browserType = prop.getProperty("browserType");
			System.out.println("browser for execution: "+browserType);

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
				options.addArguments("--start-maximized");
				options.addArguments("disable-infobars");
				driver=new FirefoxDriver(options);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 *****************************************************************************************************************
	 * Method Name		: openNewTab
	 * Description		: this method will open multiple tab based on url and position as input
	 * Input Parameter	: web element, driver object and position
	 * Output Parameter	: 
	 * Author			: Automation
	 *****************************************************************************************************************
	 */

	public static void openNewTab(WebDriver driver, String url, int position) {
		try {
			((JavascriptExecutor) driver).executeScript("window.open()");
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			System.out.println("tabs : " + tabs.size() + " >position: " + position + " >\t" + url);
			driver.switchTo().window(tabs.get(position));
			driver.get(url);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 *****************************************************************************************************************
	 * Method Name		: setHighlight
	 * Description		: this method highlight UI element
	 * Input Parameter	: web element
	 * Output Parameter	: 
	 * Author			: Automation
	 *****************************************************************************************************************
	 */

	public static void setHighlight(WebElement element) {
		try {
			String attributevalue = "border:3px solid red;";
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			String getattrib = element.getAttribute("style");
			executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, attributevalue);
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, getattrib);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 *****************************************************************************************************************
	 * Method Name		: isObjectDisplayed
	 * Description		: this method will verify whether an UI element is displayed or not
	 * Input Parameter	: web element
	 * Output Parameter	: element display state
	 * Author			: Automation
	 *****************************************************************************************************************
	 */

	public boolean isObjectDisplayed(WebElement element) {
		int attempts = 0;
		boolean isObjDisp = false;
		while(attempts < 5) {
			try {
				Thread.sleep(500);
				element.isDisplayed();
				isObjDisp = true;
				logger.info(element+ " is displayed as expected in application screen");
				break;
			}

			catch (Exception e) {
				e.printStackTrace();
			}
			attempts++;	
		}
		return isObjDisp;
	}


	/*
	 *****************************************************************************************************************
	 * Method Name		: waitObjEnabled
	 * Description		: this method will verify wait for an UI element enabled or not
	 * Input Parameter	: web element
	 * Output Parameter	: 
	 * Author			: Automation
	 *****************************************************************************************************************
	 */

	public static void waitObjEnabled(WebElement element) {
		for(int i=0;i<5;i++){
			try {
				Thread.sleep(500);
				boolean b = element.isEnabled();
				if(b == true) {
					//logger.info(element+ " is enabled in application screen");
				}
				break;
			}

			catch (Exception ex) {
				logger.info(element+ " is not enabled in application screen");
			}
		}
	}


	/*
	 *****************************************************************************************************************
	 * Method Name		: safeClick
	 * Description		: this method will click an UI element 
	 * Input Parameter	: web element
	 * Output Parameter	: 
	 * Author			: Automation
	 *****************************************************************************************************************
	 */

	public static void safeClick(WebElement element) {
		try {
			waitObjEnabled(element);
			setHighlight(element);
			element.click();
			//logger.info("Performed click operation on element: " +element);
		} catch (Exception ex) {
			logger.info("Failed to click on element: " +element);
		}
	}

	/*
	 *****************************************************************************************************************
	 * Method Name		: safeClearType
	 * Description		: this method will clears an UI element and types text
	 * Input Parameter	: web element, input text
	 * Output Parameter	: 
	 * Author			: Automation
	 *****************************************************************************************************************
	 */

	public static void safeClearType(WebElement element, String text) {
		try {
			waitObjEnabled(element);
			setHighlight(element);
			element.clear();
			element.sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 *****************************************************************************************************************
	 * Method Name		: safeType
	 * Description		: this method will types text in an UI element
	 * Input Parameter	: web element, input text
	 * Output Parameter	: 
	 * Author			: Automation
	 *****************************************************************************************************************
	 */

	public static void safeType(WebElement element, String text) {
		try {
			waitObjEnabled(element);
			setHighlight(element);
			element.sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 *****************************************************************************************************************
	 * Method Name		: safeGetText
	 * Description		: this method will retrieves text from an UI element
	 * Input Parameter	: web element
	 * Output Parameter	: text
	 * Author			: Automation
	 *****************************************************************************************************************
	 */

	public static String safeGetText(WebElement element) {
		String sValue=null;
		try {
			waitObjEnabled(element);
			setHighlight(element);
			sValue = element.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sValue;
	}

	/*
	 *****************************************************************************************************************
	 * Method Name		: safeGetAttribute
	 * Description		: this method will return attribute type from an UI element
	 * Input Parameter	: web element, attribute
	 * Output Parameter	: text
	 * Author			: Automation
	 *****************************************************************************************************************
	 */

	public static String safeGetAttribute(WebElement element, String attribute) {
		String sValue=null;
		try {
			waitObjEnabled(element);
			setHighlight(element);
			sValue = element.getAttribute(attribute);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sValue;
	}

	/*
	 *****************************************************************************************************************
	 * Method Name		: safeDoubleClick
	 * Description		: this method will perform double click on an UI element 
	 * Input Parameter	: web element
	 * Output Parameter	: 
	 * Author			: Automation
	 *****************************************************************************************************************
	 */

	public static void safeDoubleClick(WebElement element) {
		try {
			waitObjEnabled(element);
			setHighlight(element);
			Actions useraction = new Actions(driver);
			useraction.doubleClick(element).perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 *****************************************************************************************************************
	 * Method Name		: takeSnapshot
	 * Description		: this method will capture screenshot of a whole computer screen 
	 * Input Parameter	: driver, file suffix
	 * Output Parameter	: screenshot will be placed in output folder
	 * Author			: Automation
	 *****************************************************************************************************************
	 */

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

	/*
	 *****************************************************************************************************************
	 * Method Name		: checkBrowserState
	 * Description		: this method will select a text value from a list box
	 * Input Parameter	: web element, select value
	 * Output Parameter	: 
	 * Author			: Automation
	 *****************************************************************************************************************
	 */

	public static String checkBrowserState() {
		String readyState = null;
		try {
			readyState = String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"));
			return readyState;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return readyState;
	}

	/*
	 *****************************************************************************************************************
	 * Method Name		: safeSelectFromListBox
	 * Description		: this method will select a text value from a list box
	 * Input Parameter	: web element, select value
	 * Output Parameter	: 
	 * Author			: Automation
	 *****************************************************************************************************************
	 */

	public static void safeSelectFromListBox(WebElement element, String selectValue) {
		try {
			waitObjEnabled(element);
			setHighlight(element);
			Select select = new Select(element);
			logger.info("Select value - '" + selectValue + "' from drop down list");
			element.click();
			select.selectByVisibleText(selectValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 *****************************************************************************************************************
	 * Method Name		: getData
	 * Description		: this method will select and return text value from excel cell value
	 * Input Parameter	: sheet name, column name, test case ID 
	 * Output Parameter	: cell value
	 * Author			: Automation
	 *****************************************************************************************************************
	 */

	public static String getData(String sheetName, String columnName, String testCaseID) {
		String data = null;
		try {
			Fillo fillo = new Fillo();
			Connection connection = fillo.getConnection("src/test/resources/testdata/testData.xlsx");
			String strQuery = "Select "+columnName+" from "+sheetName+" where TC_ID='"+testCaseID+"'";
			System.out.println("Query for fetching records: "+strQuery);
			Recordset recordset = connection.executeQuery(strQuery);
			recordset.moveFirst();
			data = recordset.getField(columnName);
			//logger.info("Test data: " +columnName+": "+data);
		}catch (FilloException fex) {
			logger.info("Fillo exception occurred in execute query!");
			fex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}


	/*
	 *****************************************************************************************************************
	 * Method Name		: setData
	 * Description		: this method will update text value in excel cell
	 * Input Parameter	: sheet name, column name, test case ID, input text
	 * Output Parameter	: 
	 * Author			: Automation
	 *****************************************************************************************************************
	 */

	public static void setData(String sheetName, String columnName, String testCaseID, String inputText) {
		try {
			Fillo fillo = new Fillo();
			Connection connection = fillo.getConnection("src/test/resources/testdata/testData.xlsx");
			String strQuery = "Update "+sheetName+" Set "+columnName+"='"+inputText+"' where TC_ID='"+testCaseID+"'";
			System.out.println("Update query: "+strQuery);
			connection.executeUpdate(strQuery);
		}catch (FilloException fex) {
			logger.info("Fillo exception occurred in update query!");
			fex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 *****************************************************************************************************************
	 * Method Name		: encodeText
	 * Description		: this method will encode a text value and return
	 * Input Parameter	: string to be encoded
	 * Output Parameter	: encoded string 
	 * Author			: Automation
	 *****************************************************************************************************************
	 */

	public static String encodeText(String strToEncrypt) {
		Base64.Encoder encoder = Base64.getUrlEncoder();
		String eStr = encoder.encodeToString(strToEncrypt.getBytes());
		logger.info("Encoded text: "+eStr);
		return eStr;
	}

	/*
	 *****************************************************************************************************************
	 * Method Name		: decodeText
	 * Description		: this method will decode a text value and return
	 * Input Parameter	: string to be decoded
	 * Output Parameter	: decoded string 
	 * Author			: Automation
	 *****************************************************************************************************************
	 */

	public static String decodeText(String strToDecrypt) {
		Base64.Decoder decoder = Base64.getUrlDecoder();
		String dStr = new String(decoder.decode(strToDecrypt));
		return dStr;
	}

	/*
	 *****************************************************************************************************************
	 * Method Name		: ListWebElementReturn
	 * Description		: this method will return list of web elements
	 * Input Parameter	: list string
	 * Output Parameter	: list of web elements 
	 * Author			: Automation
	 *****************************************************************************************************************
	 */

	public static List<WebElement> ListWebElementReturn(String value) {
		List<WebElement> Listvalue = driver.findElements(By.xpath(value));
		return Listvalue;
	}

	/*
	 *****************************************************************************************************************
	 * Method Name		: safeClickLink
	 * Description		: this method will perform click operation on a hyper link, linktext
	 * Input Parameter	: list string
	 * Output Parameter	: 
	 * Author			: Automation
	 *****************************************************************************************************************
	 */
	public static void safeClickLink(String linkText) {
		try {
			WebElement link = driver.findElement(By.linkText(linkText));
			waitObjEnabled(link);
			safeClick(link);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 *****************************************************************************************************************
	 * Method Name		: generateRandomNumber
	 * Description		: this method will return a five digit random number each time it is invoked
	 * Input Parameter	: 
	 * Output Parameter	: integer
	 * Author			: Automation
	 *****************************************************************************************************************
	 */
	public static int generateRandomNumber() {
		try {
			Random r = new Random( System.currentTimeMillis() );
			return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	/*
	 *****************************************************************************************************************
	 * Method Name		: generateRandomText
	 * Description		: this method will return random text with user defined length
	 * Input Parameter	: integer
	 * Output Parameter	: string
	 * Author			: Automation
	 *****************************************************************************************************************
	 */
	public static String generateRandomText(int count) {
		String randomText = null;
		try {
			randomText = RandomStringUtils.randomAlphabetic(count).toLowerCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return randomText;
	}

}
