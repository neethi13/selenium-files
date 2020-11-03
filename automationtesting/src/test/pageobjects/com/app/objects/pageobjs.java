package com.app.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class pageobjs {
	public pageobjs()
	{
		
	}
	
	@FindBy(how=How.XPATH, using="//span[@id='bseindex']" )
	public static WebElement bse;

	@FindBy(how=How.ID, using="nseindex" )
	public static WebElement nse;
	
	@FindBy(how=How.ID, using="query" )
	public static WebElement company;
	
	@FindBy(how=How.ID, using="moneyiframe" )
	public static WebElement frame;
	
	@FindBy(how=How.XPATH, using="//input[@class='getqbtn']" )
	public static WebElement search;
	
}
