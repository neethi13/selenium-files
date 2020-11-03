package com.app.testng.testng;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
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
	public static void LaunchBrowser(String BrowserType)
	{
		try {
			System.out.println("In Launch Browser method!!");
			if(BrowserType.equalsIgnoreCase("Chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");
				options.addArguments("--disable-extensions");
				options.setExperimentalOption("useAutomationExtension", false);
				options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
				driver=new ChromeDriver(options);

			}
			else if(BrowserType.equalsIgnoreCase("Firefox"))
			{
				System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
				FirefoxOptions options = new FirefoxOptions();
				driver.manage().window().maximize();
				options.addArguments("--disable-extensions");
				driver=new FirefoxDriver(options);
			}


			else if(BrowserType.equalsIgnoreCase("internetexplorer")) {
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

				System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
				driver = new InternetExplorerDriver(ieCapabilities);
				driver.manage().window().maximize();
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}



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



	public boolean isObjectDisplayed(WebElement element) {
		int attempts = 0;
		boolean isObjDisp = false;
		while(attempts < 5) {
			try {
				Thread.sleep(500);
				element.isDisplayed();
				isObjDisp = true;
				System.out.println(element+ " is displayed as expected in application screen");
				break;
			}

			catch (Exception e) {
				e.printStackTrace();
			}
			attempts++;	
		}
		return isObjDisp;
	}


	public static void waitObjEnabled(WebElement element) {
		for(int i=0;i<5;i++){
			try {
				Thread.sleep(500);
				boolean b = element.isEnabled();
				if(b == true) {
					System.out.println(element+ " is enabled in application screen");
				}
				break;
			}

			catch (Exception ex) {
				System.out.println(element+ " is not enabled in application screen");
			}
		}
	}

	public static void safeClick(WebElement element) {
		try {
			waitObjEnabled(element);
			setHighlight(element);
			element.click();
			//logger.info("Performed click operation on element: " +element);
		} catch (Exception ex) {
			System.out.println("Failed to click on element: " +element);
		}
	}


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



	public static void safeType(WebElement element, String text) {
		try {
			waitObjEnabled(element);
			setHighlight(element);
			element.sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


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
			System.out.println("Screenshot destination: "+filename);
			File destFile = new File(filename);
			FileUtils.copyFile(srcFile, destFile);
			System.out.println("Screenshot captured");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String CaptureScreen(String filename)
	{
		try{
			Robot robotClassObject = new Robot();
			Rectangle screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage tmp = robotClassObject.createScreenCapture(screenSize); 
			String filepath = "src//test//resources//snapshots//"+filename+".png";
			System.out.println("Screen Capture successful");
			ImageIO.write(tmp, "png",new File(filepath)); 
			return filepath;

		}catch(Exception e)
		{
			System.out.println("Some exception occured." + e.getMessage());
			return "";
		}
	}

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


	public static void safeSelectFromListBox(WebElement element, String selectValue) {
		try {
			waitObjEnabled(element);
			setHighlight(element);
			Select select = new Select(element);
			System.out.println("Select value - '" + selectValue + "' from drop down list");
			element.click();
			select.selectByVisibleText(selectValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


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
			System.out.println("Test data: " +columnName+": "+data);
		}catch (FilloException fex) {
			System.out.println("Fillo exception occurred in execute query!");
			fex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}


	public static void setData(String sheetName, String columnName, String testCaseID, String inputText) {
		try {
			Fillo fillo = new Fillo();
			Connection connection = fillo.getConnection("src/test/resources/testdata/testData.xlsx");
			String strQuery = "Update "+sheetName+" Set "+columnName+"='"+inputText+"' where TC_ID='"+testCaseID+"'";
			System.out.println("Update query: "+strQuery);
			connection.executeUpdate(strQuery);
		}catch (FilloException fex) {
			System.out.println("Fillo exception occurred in update query!");
			fex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static List<WebElement> ListWebElementReturn(String value) {
		List<WebElement> Listvalue = driver.findElements(By.xpath(value));
		return Listvalue;
	}


	public static void safeClickLink(String linkText) {
		try {
			WebElement link = driver.findElement(By.linkText(linkText));
			waitObjEnabled(link);
			safeClick(link);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getRowCount(String sheetName)
	{
		try {
			Fillo fillo = new Fillo();
			Connection connection = fillo.getConnection("src/test/resources/testdata/testData.xlsx");
			String strQuery = "select * from demoaut";
			Recordset recordset = connection.executeQuery(strQuery);
			int count = recordset.getCount();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
