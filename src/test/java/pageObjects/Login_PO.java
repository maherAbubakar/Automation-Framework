package pageObjects;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Slf4j
public class Login_PO extends BasePage_PO {

    public @FindBy(id = "text") WebElement userName;
    public @FindBy(id = "password") WebElement passWord;
    public @FindBy(id = "login-button") WebElement loginButton;

    public Login_PO(){
        super();
    }

    public void loginPage(){
        navigateToURL("https://www.webdriveruniversity.com/Login-Portal/index.html");
    }
    public void enterUserName(String username){
        sendingValueToWebElement("username",userName,username);
    }

    public void enterPasswords(String password){
        sendingValueToWebElement("password",passWord, password);
    }

    public void clickOnLoginButton() throws InterruptedException {
        waitAndClickOnElement(loginButton);
    }

    public void validate_Successful_SubmissionMessage_Text() {

        waitForAlert_And_ValidateText("validation succeeded");
        }

    public void validate_UnSuccessful_SubmissionMessage_Text() {

        waitForAlert_And_ValidateText("validation failed");
    }
}


