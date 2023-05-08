package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.BasePage_PO;
import pageObjects.Login_PO;

public class Login_Steps extends BasePage_PO {

    private final Login_PO login_po;

    public Login_Steps(Login_PO login_po){
        this.login_po = login_po;
    }

    @Given("user the WebDriver university login page")
    public void user_the_web_driver_university_login_page() {
        login_po.loginPage();
    }

    @When("user enters the username {string}")
    public void user_enters_the_username(String username) {
         login_po.enterUserName(username);
    }

    @And("user enters the password {}")
    public void user_enters_the_password(String password) {
        login_po.enterPasswords(password);
    }

    @And("user clicks on login button")
    public void user_clicks_on_login_button() throws InterruptedException {
        login_po.clickOnLoginButton();
    }

    @Then("user should be presented with successful login message")
    public void user_should_be_presented_with_successful_login_message() {
        login_po.validate_Successful_SubmissionMessage_Text();
    }

    @Then("user should be presented with unsuccessful login message")
    public void user_should_be_presented_with_unsuccessful_login_message() {
//        String login_message = driver.switchTo().alert().getText();
//        Assert.assertEquals(login_message,"validation failed");
        login_po.validate_UnSuccessful_SubmissionMessage_Text();

    }
}
