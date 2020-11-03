package com.sep.tesing.automationtesting;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.app.objects.pageobjs;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class AppPrint extends BaseClass{

	@Given("^open browser$")
	public void open_browser()  {
		try {
			LaunchBrowser("Chrome");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Given("^navigates to url$")
	public void navigates_to_url()  {
		try {
			driver.get("https://www.rediff.com");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	@When("^user performs required action$")
	public void user_performs_required_action()  {
		try {
			PageFactory.initElements(driver, pageobjs.class);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//			WebDriverWait wait = new WebDriverWait(driver,20);
			//			wait.until(ExpectedConditions.visibilityOf(pageobjs.bse));

			driver.switchTo().frame(pageobjs.frame);
			Thread.sleep(3000);;
			String bsevalue=pageobjs.bse.getText();
			System.out.println("The index value of BSE is : "+bsevalue);
			String nsevalue=pageobjs.nse.getText();
			System.out.println("The index value of NSE is : "+nsevalue);

			String pwin = driver.getWindowHandle();
			System.out.println("Rediff win handle"+pwin);

			pageobjs.company.sendKeys("HDFC Bank Ltd.");
			pageobjs.search.click();
			Thread.sleep(5000);

			Set<String> windows = driver.getWindowHandles(); 
			System.out.println(windows); 
			System.out.println("a1l window"); 
			for (String window : windows) 
			{ 
				driver.switchTo().window(window); 
			}

			String htitle = driver.getTitle().substring(0, 14);;


			System.out.println("The HDFC bank title is : "+htitle);
			Assert.assertEquals("HDFC BANK LTD.", htitle);
			System.out.println("The HDFC bank window title asserted");


			driver.switchTo().window(pwin);
			String ptitle=driver.getTitle().substring(0, 11);
			Assert.assertEquals("Rediff.com:", ptitle);
			System.out.println("The rediff window title is : "+ptitle);
			System.out.println("The rediff window title asserted");


		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@When("^user prints href$")
	public void user_prints_href()  {
		try {
			Fillo fillo = new Fillo();
			Connection connection = fillo.getConnection("src/test/resources/TestData/testdata.xlsx");
			System.out.println("Connection Established");
			String strQuery = "select * from href";
			Recordset recordset = connection.executeQuery(strQuery);
			int count = recordset.getCount();
			System.out.println("The total count of rows is : "+count);

			Thread.sleep(3000);
			
			for(int i=1;i<=count;i++)
			{
				recordset.moveNext();
				String tcid=recordset.getField("TopStories");
				System.out.println("TC_ID is : "+tcid);
				Thread.sleep(2000);
				String href = driver.findElement(By.xpath("//div[@id='topdiv_0']/h2["+i+"]/following::a[1]")).getAttribute("href");
				System.out.println("Iteration "+i+" :"+href);
				String updateQuery = "Update href Set Href='"+href+"' where TopStories='"+tcid+"'";
				System.out.println(updateQuery);
				connection.executeUpdate(updateQuery);
				System.out.println("Excel sheet updated");
				

			}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}


		@Then("^successful validation and close browser$")
		public void successful_validation_and_close_browser()  {
			try {
				Thread.sleep(3000);
				driver.quit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
