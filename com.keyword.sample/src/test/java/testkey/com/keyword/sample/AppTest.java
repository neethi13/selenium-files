package testkey.com.keyword.sample;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class AppTest  extends BaseClass
{
	public static void LaunchBrowser()
	{
		System.out.println("Launching browser");
		launchBrowser("firefox");
	}

	public static void OpenURL()
	{

		try {
			
			driver.get("http://google.co.in");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void ClickSearch()
	{
		
		try {
			driver.findElement(By.name("q")).sendKeys("Selenium");
			Robot robot=new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
					} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void ClickLink()
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//cite[.=\"www.selenium.dev\"]")).click();
	}

	public static void gettitle()
	{
		String title = driver.getTitle();
		System.out.println("The current page title is :"+title);
	}

	
	public static void CloseB()
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.quit();
	}

	public static void main(String args[])
	{
		try {
			Fillo fillo = new Fillo();
			Connection connection = fillo.getConnection("src/test/resources/testdata/keyword.xlsx");
			String strQuery = "select * from KDF";
			Recordset recordset = connection.executeQuery(strQuery);
			System.out.println("main method");
			int count = recordset.getCount();
			//recordset.moveFirst();
			System.out.println("The number of lines in excel is :"+count);
			for(int i = 0; i < count; i++) {
				recordset.moveNext();
				Thread.sleep(500);
				String kword = recordset.getField("Keyword");
				System.out.println(kword);
				System.out.println("Excel accessed");
				if(kword.equalsIgnoreCase("OpenURL"))
				{
					System.out.println("open URL");
					OpenURL();
				}
				else if(kword.equalsIgnoreCase("ClickSearch"))
				{
					ClickSearch();
				}
				else if(kword.equalsIgnoreCase("ClickLink"))
				{
					ClickLink();
				}
				else if(kword.equalsIgnoreCase("gettitle"))
				{
					gettitle();
				}
				else if(kword.equalsIgnoreCase("LaunchBrowser"))
				{
					LaunchBrowser();
				}
				else if(kword.equalsIgnoreCase("CloseB"))
				{
					CloseB();
					System.out.println("Closing the browser");
				}
				
			} 
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
