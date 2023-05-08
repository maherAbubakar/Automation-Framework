package runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"classpath:featureFiles"},
        glue = {"stepDefinitions"},
        monochrome = true,
        dryRun = false,      // if true, then it will not write the code in Step Definition - only run the feature lines
        tags = "",
        plugin = {"pretty",
                "html:target/CucumberReports/report.html",
                "json:target/CucumberReports/report.json",
                "junit:target/CucumberReports/report.xml"}
)
public class Runner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
