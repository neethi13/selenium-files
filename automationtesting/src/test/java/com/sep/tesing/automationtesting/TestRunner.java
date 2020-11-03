package com.sep.tesing.automationtesting;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)

@CucumberOptions
(
	features = {"src/test/resources/features/print.feature"},
	//tags={"@tag2"},
	glue="com.sep.tesing.automationtesting",
	format = {"pretty","html:target/destination"},
	monochrome=true
	
)

public class TestRunner {

}