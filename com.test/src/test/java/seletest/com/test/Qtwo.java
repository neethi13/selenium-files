package seletest.com.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.app.objects.pageobjs;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class Qtwo extends BaseClass{


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
			
			System.out.println("Before method");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider="testdata")
	public static void test1(String id,String empname, String city, String hiredate) {
		try {
			driver.get("https://mayexam.cpsatexam.org/");
			driver.findElement(By.xpath("//i[@class='eicon-close']")).click();
			List <WebElement> data = driver.findElements(By.xpath("//ul[@id='menu-main-1']/li[2]/a[.='Data']"));
			for (int i = 0; i < data.size(); i++) {
				data.get(0);
			}
			
			Actions action = new Actions(driver);
			Thread.sleep(2000);
			action.moveToElement(data.get(0)).build().perform();
			Thread.sleep(4000);
			takeSnapshot(driver, "Data2020");
			List <WebElement> about = driver.findElements(By.xpath("//ul[@id='menu-main-1']/li[2]/a[.='Data']/following::ul[1]/li[1]/a[2]"));
			for (int i = 0; i < about.size(); i++) {
				about.get(0).click();
				Thread.sleep(2000);
			}
			Thread.sleep(3000);



			WebElement tableWhole = driver.findElement(By.xpath("//table[@data-id='53ec51c']"));

			List<WebElement> tcol = tableWhole.findElements(By.tagName("th"));
			int colcount = tcol.size();
			System.out.println("Number of columns: " +colcount);

			for(int i = 0; i < colcount; i++) {
				System.out.println("Table header index: " +i);
				String tcoldata = tcol.get(i).getText();
				System.out.println("Table header value: " +tcoldata);
			}

			List<WebElement> trow = tableWhole.findElements(By.tagName("tr"));
			int rowcount = trow.size();
			System.out.println("Number of rows: " +rowcount);

			for(int row = 1; row < rowcount; row++) {

				List<WebElement> Columns_row = trow.get(row).findElements(By.tagName("td"));

				//To calculate no of columns (cells). In that specific row.row index starts from 1 since the first row is header
				int columns_count = Columns_row.size();
				System.out.println("Number of cells In Row " + row + " are " + columns_count);

				for (int column = 0; column < columns_count; column++) {
					// To retrieve text from that specific cell.column count starts from 0 since the first column index starts with 0
					String celtext = Columns_row.get(column).getText();
					System.out.println("Cell Value of row number " + row + " and column number " + column + " Is " + celtext);
					if(celtext.contains(id) && celtext.contains(empname) && celtext.contains(id) && celtext.contains(city)&& celtext.contains(hiredate)) {
						System.out.println("User creation passed! The created Username is: "+id);
						setData("demoaut", "Result", id, "Pass");
					}

					

					
				}
				System.out.println("-------------------------------------------------- ");
			}

						

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@DataProvider(name="testdata")
	public  Object[][] TestDataFeed(){

		Fillo fillo = new Fillo();
		Connection connection;
		try {
			connection = fillo.getConnection("src/test/resources/testdata/testData.xlsx");
			String strQuery = "select * from demoaut";
			String[][] testData =null;
			int count = getRowCount("demoaut");
			testData = new String[count][8];
			System.out.println("Total number of rows: " +count);
			Recordset recordset = connection.executeQuery(strQuery);
			
			for (int i = 0; i < count; i++) {
				recordset.moveNext();
				Thread.sleep(500);
				testData[i][0] = recordset.getField("ID");
				testData[i][1] = recordset.getField("Empname");
				testData[i][2] = recordset.getField("City");
				testData[i][3] = recordset.getField("Hiredate");
				
			}		
			CaptureScreen("testsample");
			System.out.println(testData);
			
			return (testData);
		}

		catch (Exception e) {

			e.printStackTrace();
		}
		return null;

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
