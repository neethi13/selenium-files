package seletest.com.test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)

@CucumberOptions
(
	features = {"src/test/resources/features/four.feature"},
	//tags={"@tag2"},
	glue="seletest.com.test",
	format = {"pretty","html:target/destination"},
	monochrome=true
	
)

public class TestRunnerFour {

}