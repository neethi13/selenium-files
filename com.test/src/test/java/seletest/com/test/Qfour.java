package seletest.com.test;

import java.awt.Robot;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.PageFactory;
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

import com.app.objects.pageobjs;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class Qfour extends BaseClass{

	
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
			driver.findElement(By.className("eicon-close")).click();
			System.out.println("Before method");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public static void test4()
	{
		try {
			PageFactory.initElements(driver, pageobjs.class);
			Thread.sleep(5000);
			List<WebElement> element = driver.findElements(By.xpath("//li[@class='menu-item.*']"));
			for (int i = 0; i < element.size(); i++) {
				System.out.println(element.size());
				Actions action = new Actions(driver);
				action.moveToElement(element.get(2)).perform();
				action.moveToElement(element.get(2)).build();
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
