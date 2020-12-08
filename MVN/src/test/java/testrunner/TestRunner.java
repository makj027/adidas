package testrunner;



import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/features"}, extraGlue = {"stepdefs"},
		plugin = { "html:target/cucumber-html-report",
				"json:target/cucumber.json", "pretty:target/cucumber-pretty.txt", "summary",
				"usage:target/cucumber-usage.json", "junit:target/cucumber-results.xml" }, strict = true, snippets = CucumberOptions.SnippetType.CAMELCASE, monochrome = true, tags = "@Adidas")

public class TestRunner {


} // TestRunner