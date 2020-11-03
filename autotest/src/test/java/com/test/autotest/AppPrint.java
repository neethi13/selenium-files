package com.test.autotest;


import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.app.objects.pageobjs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

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
			driver.get("https://julyexam2.cpsatexam.org/");

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
			String ss =driver.getDataSetName();
			{
			
				tit = driver.getTitle();
				return tit;
			}
			pageobjs.sea.sendKeys("am");
			Robot rob = new Robot();
			rob.keyPress(KeyEvent.VK_ENTER);
			rob.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(5000);
			takeSnapshot(driver, "amfilter");
			Thread.sleep(3000);
			ArrayList<String> tabs = new ArrayList<String>(driver.getTitle());
			


		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@When("^user prints href$")
	public void user_prints_href()  {
		try {
			driver.get(" https://julyexam2.cpsatexam.org/");
			WebElement cert = driver.findElement(By.xpath("//a[.='Certifications' ]/span[1]"));
			Actions at = new Actions(driver);
			at.moveToElement(cert);
			driver.findElement(By.xpath("//li[@id='menu-item-218']")).click();
			driver.findElement(By.xpath("//span[.='News!']")).click();
			Thread.sleep(2000);
			takeSnapshot(driver, "ticker");
			Thread.sleep(1000);
			takeSnapshot(driver, "ticker2");
			WebElement us =driver.findElement(By.xpath("//div[@id='elementor-tab-title-1001']"));
			us.click();
			String use = driver.findElement(By.xpath("//div[@id='elementor-tab-title-1001']/following::i[1]")).getText();
			System.out.println(use);
			WebElement am =driver.findElement(By.xpath("//span[.='Am I Eligible?']"));
			String colbef = am.getAttribute("color");
			System.out.println(colbef);
			am.click();
			String colaft = am.getAttribute("color");
			System.out.println(colaft);
			String eli = driver.findElement(By.xpath("//div[@id='elementor-tab-title-1001']/following::i[2]")).getText();
			System.out.println(eli);
			WebElement dur =driver.findElement(By.xpath("//span[.='Am I Eligible?']"));
			dur.click();
			String dura = driver.findElement(By.xpath("//div[@id='elementor-tab-title-1001']/following::i[2]")).getText();
			System.out.println(dura);
			
			
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
