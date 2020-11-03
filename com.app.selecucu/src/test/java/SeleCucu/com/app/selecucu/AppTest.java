package SeleCucu.com.app.selecucu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.PageFactory;
import org.testng.xml.dom.Tag;

import com.app.objects.pageobjs;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AppTest extends BaseClass {
		
	@Given("^user opens browser$")
	public void user_opens_browser() {
		try {

			launchBrowser();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Given("^navigates to application url$")
	public void navigates_to_application_url() {
		try {
			PageFactory.initElements(driver, pageobjs.class);
			driver.get("http://demo.guru99.com/test/newtours/register.php");
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	@When("^user navigates to register screen$")
	public void user_navigates_to_register_screen(){
		try {
			PageFactory.initElements(driver, pageobjs.class);
			pageobjs.firstname.isDisplayed();
			System.out.println("First name field is displayed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Then("^user should be able to create a new user$")
	public static void user_should_be_able_to_create_a_new_user() {
		try {
			PageFactory.initElements(driver, pageobjs.class);
			Fillo fillo = new Fillo();
			Connection connection = fillo.getConnection("src/test/resources/testdata/testData.xlsx");
			String strQuery = "select * from demoaut";
			Recordset recordset = connection.executeQuery(strQuery);
			int count = recordset.getCount();
			System.out.println("Total number of rows: " +count);
//			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//			driver = new ChromeDriver();
//			driver.manage().window().maximize();
			//recordset.moveFirst();
			for(int i = 0; i < count; i++) {
			recordset.moveNext();
			Thread.sleep(500);
			String tcid = recordset.getField("TC_ID");
			String fname = recordset.getField("Firstname");
			String lname = recordset.getField("Lastname");
			String pno = recordset.getField("Phone");
			String email = recordset.getField("Email");
			String uname = recordset.getField("Username");
			String pwd = recordset.getField("Password");
			String cpwd = recordset.getField("Confirm");

			driver.get("http://demo.guru99.com/test/newtours/register.php");

			
			pageobjs.firstname.sendKeys(fname);

			safeClick(pageobjs.lastname);
			pageobjs.lastname.sendKeys(lname);
			pageobjs.phonenumber.sendKeys(pno);
			pageobjs.email.sendKeys(email);
			pageobjs.address.sendKeys("test street");
			pageobjs.city.sendKeys("Chennai");
			pageobjs.state.sendKeys("TamilNadu");
			pageobjs.country.sendKeys("INDIA");
			pageobjs.userName.sendKeys(uname);
			pageobjs.password.sendKeys(pwd);
			pageobjs.confirmPassword.sendKeys(cpwd);
			pageobjs.submitbtn.click();

			//System.out.println("Iteration: " +i);
			System.out.println("TC_ID:  "+tcid+"  Firstname: " +fname);
			Thread.sleep(2000);

			String actMsg = pageobjs.createduname.getText();
			System.out.println(actMsg);
			String add[] = actMsg.split(" ");
			int lent = add.length;
			System.out.println("Total number of words in success message: " +lent);

			String unamefin = add[lent-1].replace(".", "");
			System.out.println("Username created: " +unamefin);
			if(actMsg.contains(uname)) {
				System.out.println("User creation passed! The created Username is: "+uname);
			}

			else {
				System.out.println("User creation failed!");
			}

			String updateQuery = "Update demoaut Set CreatedUser='"+unamefin+"' where TC_ID='"+tcid+"'";
			connection.executeUpdate(updateQuery);
			}

			Thread.sleep(5000);
			driver.quit();		
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Then("^user tries with multiple \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_tries_with_multiple_and(String uname, String pword) {
	   try {
		   
		   driver.get("http://demo.guru99.com/test/newtours/login.php");
		   driver.findElement(By.name("userName")).sendKeys(uname);
		   driver.findElement(By.name("password")).sendKeys(pword);
		   driver.findElement(By.name("submit")).click();

		
	} catch (Exception e) {
		
	}
	}

}