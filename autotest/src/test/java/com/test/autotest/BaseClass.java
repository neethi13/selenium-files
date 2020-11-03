package com.test.autotest;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BaseClass {
	public static WebDriver driver;
	public static void LaunchBrowser(String BrowserType)
	{
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
	}
	public static void takeSnapshot(WebDriver driver,String filesuffix)
	{
		try {
			TakesScreenshot scrShot =((TakesScreenshot)driver);
			String filename = "src//test//resources//snapshots//"+filesuffix+".png";
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			System.out.println("File name is : "+filesuffix);
			File destFile = new File(filename);
			System.out.println("Screenshot captured in path: "+filename);
			FileUtils.copyFile(SrcFile, destFile);
			System.out.println("Screenshot Captured ");
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
           ImageIO.write(tmp, "png",new File(filepath)); 
            return filepath;
            
		}catch(Exception e)
		{
			System.out.println("Some exception occured." + e.getMessage());
			return "";
		}
	}

}
