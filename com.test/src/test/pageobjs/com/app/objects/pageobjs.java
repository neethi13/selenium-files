package com.app.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class pageobjs {

	public pageobjs()
	{
		
	}
	

	
	@FindBy(how=How.CLASS_NAME, using="eicon-close']" )
	public static WebElement closeAlert;
	
	
	@FindBy(how=How.XPATH, using="//li[@class='.*menu-item-12116']" )
	public static WebElement dataMenu;
	

	@FindBy(how=How.XPATH, using="//a[text()='Data 2']" )
	public static WebElement dataSubMenu;
	
}
