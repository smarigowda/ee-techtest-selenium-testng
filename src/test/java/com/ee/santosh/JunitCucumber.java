package com.ee.santosh;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/feature", glue="src/test/java/com/stepDefinitions")
public class JunitCucumber {
// This class can be empty
}
