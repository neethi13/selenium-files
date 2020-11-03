package seletest.com.test;

import java.awt.Robot;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class Qone extends BaseClass{

	
	@BeforeSuite
	public void befSuite()
	{
		try {
			LaunchBrowser("Chrome");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	@BeforeMethod
	public void befMethod()
	{
		try {
			driver.get("https://mayexam.cpsatexam.org/");
			System.out.println("Before method");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public static void test1()
	{
		try {
			Thread.sleep(4000);
			
			driver.findElement(By.xpath("//i[@class='eicon-close']")).click();
			List <WebElement> doppa = driver.findElements(By.xpath("//ul[@id='menu-main-1']/li[2]/a[.='Doppa2020']"));
			for (int i = 0; i < doppa.size(); i++) {
				doppa.get(0);
			}
			
			Actions action = new Actions(driver);
			Thread.sleep(2000);
			action.moveToElement(doppa.get(0)).build().perform();
			Thread.sleep(4000);
			takeSnapshot(driver, "Doppa2020");
			List <WebElement> about = driver.findElements(By.xpath("//ul[@id='menu-main-1']/li[2]/a[.='Doppa2020']/following::ul[1]/li[1]/a[1]"));
			for (int i = 0; i < about.size(); i++) {
				about.get(0).click();
				Thread.sleep(2000);
			}
			Thread.sleep(3000);
			String strArray[] =null;
			List <WebElement> name = driver.findElements(By.xpath("//figure[@class='elementor-image-box-img']/following::div[@class='elementor-image-box-content']"));
			Thread.sleep(3000);
			List <WebElement> designation = driver.findElements(By.xpath("//figure[@class='elementor-image-box-img']/following::div[@class='elementor-image-box-content']/p[1]"));
			for (int i = 0; i < designation.size(); i++) {
				
				System.out.println(designation.get(i).getText());
				strArray[i] =  designation.get(i).getText();
					Thread.sleep(3000);
					
			for (int j = 0; j < designation.size(); j++) {
				
			
			if(strArray[j].contains("Test"))
			{
				System.out.println(designation.get(i).getText());
			}
			}
			}
			
			String pwin = driver.getWindowHandle();
			action.moveToElement(driver.findElement(By.xpath("//button[.='Submit']"))).build().perform();
			Thread.sleep(2000);
			takeSnapshot(driver, "Social Media");
			String social;
			String ptitle;
			for (int k = 1; k <= 4; k++) {
				WebElement href = driver.findElement(By.xpath("//div[@class='social_icon']/a["+k+"])"));
				
				social = href.getAttribute("href");
				href.click();
				
				System.out.println(href);
				
				
				Set<String> windows = driver.getWindowHandles(); 
				System.out.println(windows); 
				System.out.println("a1l window"); 
				for (String window : windows) 
				{ 
					driver.switchTo().window(window); 
				}
				ptitle=driver.getTitle();
				System.out.println("Title"+ptitle);
				Thread.sleep(2000);
				takeSnapshot(driver, "Social icon "+k+"");
				driver.close();
				driver.switchTo().window(pwin);
				Thread.sleep(3000);
				System.out.println();
				
				int l=0;
				for (int p=0;p<7;p++) {
				if(driver.getPageSource().contains("doppa2020"))
				{
				   l++;
				}

				else
				{
				    //Click abc
				}
				}
				System.out.println("Number of occurances of doppa20202 is "+l);
				
			}
			
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	

	@BeforeTest
	public void befTest()
	{
		try {
			System.out.println("Before Test");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@BeforeClass
	public void befClass()
	{
		try {
			System.out.println("Before class");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


	@AfterMethod
	public void aftMethod()
	{
		try {
			//driver.close();
			System.out.println("After method");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@AfterClass
	public void aftClass()
	{
		try {
			System.out.println("After Class");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@AfterTest
	public void aftTest()
	{
		try {
			System.out.println("After Test");
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	@AfterSuite
	public void aftSuite()
	{
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	
	
}
