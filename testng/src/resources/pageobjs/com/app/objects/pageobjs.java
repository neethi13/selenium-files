package com.app.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class pageobjs {
	public pageobjs()
	{
		
	}
	@FindBy(how=How.XPATH, using="//input[@id='userName']")
	public static WebElement userNameField;
	
	@FindBy(how=How.NAME, using="firstName")
	public static WebElement firstname;
	
	@FindBy(how=How.NAME, using="lastName")
	public static WebElement lastname;
	
	@FindBy(how=How.NAME, using="phone")
	public static WebElement phonenumber;
	
	@FindBy(how=How.ID, using="userName")
	public static WebElement email;
	
	@FindBy(how=How.NAME, using="address1")
	public static WebElement address;
	
	@FindBy(how=How.NAME, using="city")
	public static WebElement city;
	
	@FindBy(how=How.NAME, using="state")
	public static WebElement state;
	
	@FindBy(how=How.NAME, using="country")
	public static WebElement country;
	
	@FindBy(how=How.ID, using="email")
	public static WebElement userName;
	
	@FindBy(how=How.NAME, using="password")
	public static WebElement password;
	
	@FindBy(how=How.NAME, using="confirmPassword")
	public static WebElement confirmPassword;
	
	@FindBy(how=How.NAME, using="submit")
	public static WebElement submitbtn;
	
	@FindBy(how=How.XPATH, using="//a[.=' sign-in ']/following::b[1]")
	public static WebElement createduname;
		
}
