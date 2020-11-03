package com.sep.tesing.automationtesting;

import java.awt.Robot;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
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


public class AppTest  extends BaseClass
{
	@Test
	public static void test1()
	{
		try {
			driver.findElement(By.xpath("//a[.='MOVIES']")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			takeSnapshot(driver,"MOVIES");
			String headlines = driver.findElement(By.xpath("//a[.='Headlines']")).getAttribute("href");
			System.out.println("The href of headlines is : "+headlines );
			String images = driver.findElement(By.xpath("//a[.='Images']")).getAttribute("href");
			System.out.println("The href of images is : "+images );
			String interviews = driver.findElement(By.xpath("//a[.='Interviews']")).getAttribute("href");
			System.out.println("The href of images is : "+interviews );
			String reviews = driver.findElement(By.xpath("//a[.='Reviews']")).getAttribute("href");
			System.out.println("The href of reviews is : "+reviews );
			String webseries = driver.findElement(By.xpath("//a[.='Web Series']")).getAttribute("href");
			System.out.println("The href of webseries is : "+webseries );
			String television = driver.findElement(By.xpath("//a[.='Television']")).getAttribute("href");
			System.out.println("The href of television is : "+television );
			String videos = driver.findElement(By.xpath("//a[.='Videos']")).getAttribute("href");
			System.out.println("The href of videos is : "+videos );
			String southcinema = driver.findElement(By.xpath("//a[.='South Cinema']")).getAttribute("href");
			System.out.println("The href of southcinema is : "+southcinema );
			WebElement holly = driver.findElement(By.xpath("//div[@class='subnavbar movies']"));
			String hollywood = holly.getAttribute("href");
			System.out.println("The href of hollywood is : "+hollywood );
			String starsspotted = driver.findElement(By.xpath("//a[.='Stars Spotted']")).getAttribute("href");
			System.out.println("The href of starsspotted is : "+starsspotted );

			String expText="Cricket headlines";
			WebElement act = driver.findElement(By.xpath("//a[.='CRICKET']"));
			String actText = act.getAttribute("title");
			Assert.assertEquals(actText, expText);
			System.out.println("Actual Text is equal to Expected Text!!!");
			Thread.sleep(10000);
			WebElement tooltip = driver.findElement(By.xpath("//a[.='CRICKET']"));
			Robot robot;
			robot = new Robot();
			Point point;
			point = tooltip.getLocation();
			int x = point.getX(); 
			int y = point.getY(); 
			System.out.println(x+","+y);
			robot.mouseMove(561,285);
			Actions builder = new Actions(driver);
			Action acti = builder.moveToElement(tooltip).build();
			acti.perform();			
			Thread.sleep(5000);	
			CaptureScreen("Cricket Headlines tool tip");
			acti = builder.release(tooltip).build();
			acti.perform();
			Thread.sleep(4000);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			Thread.sleep(3000);
			
			String bgr = holly.getCssValue("background");
			System.out.println("The Background color is : "+bgr);
			String bgrsub = bgr.substring(0, 16);
			String expbgr = "rgb(199, 49, 89)";
			Assert.assertEquals(bgrsub, expbgr);
			
				{
				System.out.println("The color is 'Old Rose'");
				}
			String bgrclr = holly.getCssValue("background-color");
			System.out.println("The Background color is : "+bgrclr);
			
			String color = holly.getCssValue("color");
			System.out.println("The color is "+color);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	@Test
	public void test2()
	{
		try {
			driver.findElement(By.xpath("//a[.='MOVIES']")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			WebElement head = driver.findElement(By.xpath("//a[.='Headlines']"));
			String actheadlines = head.getAttribute("href");
			WebElement img = driver.findElement(By.xpath("//a[.='Images']"));
			String actimages = img.getAttribute("href");
			WebElement inter = driver.findElement(By.xpath("//a[.='Interviews']"));
			String actinterviews = inter.getAttribute("href")+".html";
			WebElement rev = driver.findElement(By.xpath("//a[.='Reviews']"));
			String actreviews = rev.getAttribute("href")+".html";
			
			Fillo fillo = new Fillo();
			Connection connection = fillo.getConnection("src/test/resources/TestData/testdata.xlsx");
			String strquery = "Select * from data";
			Recordset recordset = connection.executeQuery(strquery);
			int count = recordset.getCount();
			System.out.println("Count ="+count);
			for (int i=0;i<count;i++)
			{
				recordset.moveNext();
				String Menu = recordset.getField("Menu");
				String SubMenu = recordset.getField("SubMenu");
				String Href = recordset.getField("Href");
				System.out.println("Iteration"+i);
				System.out.println("Menu : "+Menu+"\n"+"SubMenu : "+SubMenu);
				if(Menu.equalsIgnoreCase("Movies") && SubMenu.equalsIgnoreCase("Headline"))
				{
					System.out.println("Inside condition 1");
					System.out.println("Actual : "+actheadlines+"\n"+"Expected  :"+Href);
					Assert.assertEquals(actheadlines, Href);
					System.out.println("The href of headlines submenu is same as in the excel");
					head.click();
					Thread.sleep(2000);
					takeSnapshot(driver,"Question2_Headline");
					driver.navigate().back();
					Thread.sleep(10000);
				}
				else if(Menu.equalsIgnoreCase("Movies") && SubMenu.equalsIgnoreCase("Images"))
				{
					System.out.println("Inside condition 2");
					System.out.println("Actual : "+actimages+"\n"+"Expected  :"+Href);
					Assert.assertEquals(actimages, Href);
					System.out.println("The href of images submenu is same as in the excel");
					driver.findElement(By.xpath("//a[.='Images']")).click();
					Thread.sleep(2000);
					takeSnapshot(driver,"Question2_Images");
					driver.navigate().back();
					
				}
				else if(Menu.equalsIgnoreCase("Movies") && SubMenu.equalsIgnoreCase("Interviews"))
				{
					System.out.println("Inside condition 3");
					System.out.println("Actual : "+actinterviews+"\n"+"Expected  :"+Href);
					Assert.assertEquals(actinterviews, Href);
					System.out.println("The href of interviews submenu is same as in the excel");
					driver.findElement(By.xpath("//a[.='Interviews']")).click();
					Thread.sleep(2000);
					takeSnapshot(driver,"Question2_Interviews");
					driver.navigate().back();
				}	
				else if(Menu.equalsIgnoreCase("Movies") && SubMenu.equalsIgnoreCase("Reviews"))
				{
					System.out.println("Inside condition 4");
					System.out.println("Actual : "+actreviews+"\n"+"Expected  :"+Href);
					Assert.assertEquals(actreviews, Href);
					System.out.println("The href of reviews submenu is same as in the excel");
					driver.findElement(By.xpath("//a[.='Reviews']")).click();
					Thread.sleep(2000);
					takeSnapshot(driver,"Question2_Reviews");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@BeforeSuite
	public void befSuite()
	{
		try {
			LaunchBrowser("firefox");
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
	@BeforeMethod
	public void befMethod()
	{
		try {
			driver.get("https://www.rediff.com");
			System.out.println("Before method");
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
