package SeleCucu.com.app.selecucu;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(
		features={"src/test/resources/features/test.feature"},
		//tags={"@sample"},
		glue="SeleCucu.com.app.selecucu",
		format = {"pretty", "html:target/Destination"},
		plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
		monochrome=true
		)



public class testrunner {

}
