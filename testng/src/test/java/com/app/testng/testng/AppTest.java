package com.app.testng.testng;

import java.util.ArrayList;

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

/**
 * Unit test for simple App.
 */
public class AppTest extends BaseClass
{
	@Test(dataProvider="testdata")
	public static void test1(String tcid,String firstname, String lastname, String phone, String email,String uname, String pword, String confirm) {
		try {
			PageFactory.initElements(driver, pageobjs.class);
			
			driver.get("http://demo.guru99.com/test/newtours/register.php");

				
			
				safeType(pageobjs.firstname, firstname);
				safeClick(pageobjs.lastname);
				
				
				pageobjs.lastname.sendKeys(lastname);
				pageobjs.phonenumber.sendKeys(phone);
				pageobjs.email.sendKeys(email);
				pageobjs.address.sendKeys("test street");
				pageobjs.city.sendKeys("Chennai");
				pageobjs.state.sendKeys("TamilNadu");
				pageobjs.country.sendKeys("INDIA");
				pageobjs.userName.sendKeys(uname);
				pageobjs.password.sendKeys(pword);
				pageobjs.confirmPassword.sendKeys(confirm);
				pageobjs.submitbtn.click();

				
				System.out.println("TC_ID:  "+tcid+"  Firstname: " +firstname);
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

				setData("demoaut", "CreatedUser", tcid, uname);
		

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@AfterSuite
	public void aftSuite() {
		try {
			System.out.println("After suite!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void befClass() {
		try {
			System.out.println("Before Class!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void aftClass() {
		try {
			System.out.println("After Class!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void befMet() {
		try {
			System.out.println("Before Method!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void aftMet() {
		try {
			System.out.println("After Method!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeTest
	public void befTest() {
		try {
			
			System.out.println("Before before test!");
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeSuite
	public void befSuite() {
		try {
			launchBrowser();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterTest
	public void aftTest() {
		try {
			Thread.sleep(5000);
			//driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@DataProvider(name="testdata")
	public  Object[][] TestDataFeed(){
		
//		Object[] object = null;
		Fillo fillo = new Fillo();
		Connection connection;
		
		
		try {
		connection = fillo.getConnection("src/test/resources/testdata/testData.xlsx");
		String strQuery = "select * from demoaut";
		Recordset recordset = connection.executeQuery(strQuery);
		int count = recordset.getCount();
		System.out.println("Total number of rows: " +count);
		ArrayList<String> recordlist = new ArrayList<>(); 
		for (int i=0;i<count;i++)
		{
			recordset.moveNext();
			Thread.sleep(500);
			System.out.println("Iteration: " +i);
			String tcid = recordset.getField("TC_ID");
			String fname = recordset.getField("Firstname");
			String lname = recordset.getField("Lastname");
			String pno = recordset.getField("Phone");
			String email = recordset.getField("Email");
			String uname = recordset.getField("Username");
			String pwd = recordset.getField("Password");
			String cpwd = recordset.getField("Confirm");
			
			return new Object[][] 
			    	{
				{tcid,fname,lname,pno,email,uname,pwd,cpwd}
			        };
			}
           
     
		
	}
		
				
		
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
		
		
	}
	
}