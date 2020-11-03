package seletest.com.test;

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

public class Qthree extends BaseClass{

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
			driver.get("https://mayexam.cpsatexam.org/");
			pageobjs.closeAlert.click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	@When("^user performs required action$")
	public void user_performs_required_action()  {
		try {
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@When("^user prints href$")
	public void user_prints_href()  {
		try {
			
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}


		@Then("^successful validation and close browser$")
		public void successful_validation_and_close_browser()  {
			try {
				Thread.sleep(3000);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	
}
