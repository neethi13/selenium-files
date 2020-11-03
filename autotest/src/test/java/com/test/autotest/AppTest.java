package com.test.autotest;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//h2[.='Latest Videos']")).click();
			takeSnapshot(driver,"QuickLinks");
			String href;

//			List<WebElement> anchors = driver.findElements(By.xpath("//figure[@class='elementor-image-box-img']/a[1]"));
//			Iterator<WebElement> i = anchors.iterator();
//
//			while(i.hasNext()) {
//				WebElement anchor = i.next();
//				href = anchor.getAttribute("href");
//				System.out.println("href of "+i+" is "+href);
//				break;
//			}
			driver.findElement(By.xpath("//h2[.='Quick Links']")).click();
			takeSnapshot(driver,"Vision");
			String num = driver.findElement(By.xpath("//strong[.='24+']")).getText();
			System.out.println("The number of countries is "+num.substring(0, 2));
			String pwin = driver.getTitle();
			String href2;
			for(int j=1;j<=6;j++)
			{
				driver.findElement(By.xpath("//div[@class='elementor-social-icons-wrapper']/a["+j+"]")).click();
				
				Set<String> windows = driver.getWindowHandles(); 
				System.out.println(windows); 
				System.out.println("a1l window"); 
				for (String window : windows) 
				{ 
					driver.switchTo().window(window); 
				}

				String htitle = driver.getTitle().substring(0, 14);
				System.out.println("Title "+j+": "+htitle);
				Thread.sleep(5000);
				takeSnapshot(driver,htitle);
				Thread.sleep(2000);
				driver.switchTo().window(pwin);
				
			    String cwh=null;
			    while(pwin!=cwh)
			    {   
			    new Actions(driver).sendKeys(Keys.CONTROL).sendKeys(Keys.NUMPAD1).perform();
			    driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL, Keys.TAB);
			    cwh=driver.getWindowHandle();
			    driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL+"w");
			    }
			    
			}
				
			


		
} catch (Exception e) {

	e.printStackTrace();
}
}
@Test
public void test2()
{
	try {
		driver.get("https://julyexam2.cpsatexam.org/index.php/data1/");
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection("src/test/resources/TestData/testdata.xlsx");
		System.out.println("Connection Established");
		String strQuery = "select * from data";
		Recordset recordset = connection.executeQuery(strQuery);
		int count = recordset.getCount();
		System.out.println("The total count of rows is : "+count);
		

		
		String org = driver.findElement(By.xpath("Mayiki Consultants")).getText();
		String org1 = driver.findElement(By.xpath("Essence of Testing")).getText();
		String org2 = driver.findElement(By.xpath("Utopia Solutions")).getText();
		String sc = driver.findElement(By.xpath("//p[.='Mayiki Consultants']/following::p[1]")).getText();
		String sc1 = driver.findElement(By.xpath("//p[.='Essence of Testing']/following::p[1]")).getText();
		String sc2 = driver.findElement(By.xpath("//p[.='Utopia Solutions']/following::p[1]")).getText();
		Thread.sleep(3000);
		
		for(int i=1;i<=count;i++)
		{
			recordset.moveNext();
			String sno = recordset.getField("S.No");
			String orgn = recordset.getField("Organisation");
			String score = recordset.getField("Score");
			String tcid=recordset.getField("S.No.");
			System.out.println("S.No. is : "+tcid);
			Thread.sleep(2000);
			if(orgn.equalsIgnoreCase(org) && sc.equalsIgnoreCase(score))
			{
				String updateQuery = "Update href Set Status='Pass' where Status='"+tcid+"'";

				connection.executeUpdate(updateQuery);	
			}
			else
			{
				String updateQuery = "Update href Set Status='Fail' where Status='"+tcid+"'";

				connection.executeUpdate(updateQuery);	
			}
			
			if(orgn.equalsIgnoreCase(org1))
			{
				String updateQuery = "Update href Set Status='Pass' where Status='"+tcid+"'";

				connection.executeUpdate(updateQuery);	
			}
			else
			{
				String updateQuery = "Update href Set Status='Fail' where Status='"+tcid+"'";

				connection.executeUpdate(updateQuery);	
			}
			
			if(orgn.equalsIgnoreCase(org2))
			{
				String updateQuery = "Update href Set Status='Pass' where Status='"+tcid+"'";

				connection.executeUpdate(updateQuery);	
			}
			else
			{
				String updateQuery = "Update href Set Status='Fail' where Status='"+tcid+"'";

				connection.executeUpdate(updateQuery);	
			}
			
			System.out.println("Excel sheet updated");
			
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}



@BeforeSuite
public void befSuite()
{
	try {
		LaunchBrowser("Chrome");
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
		driver.get("https://julyexam2.cpsatexam.org/");
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
