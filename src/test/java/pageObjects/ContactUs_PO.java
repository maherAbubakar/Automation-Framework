package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class ContactUs_PO extends BasePage_PO {

    public @FindBy(how = How.XPATH, using = "//input[@name='first_name']") WebElement firstName_TextField;

    public @FindBy(xpath = "//input[@name='last_name']") WebElement lastName_TextField;

    public @FindBy(xpath = "//input[@name='email']") WebElement emailAddress_TextField;

    public @FindBy(xpath = "//textarea[@name='message']") WebElement comment_TextField;

    public @FindBy(xpath = "//input[@value='SUBMIT']") WebElement submit_Button;

    public @FindBy(xpath = "//div[@id='contact_reply']/h1") WebElement successfulSubmission_Message_Text;


    public ContactUs_PO() {
        super();
    }

    public void ContactUs_Page() {
        navigateToURL("https://www.webdriveruniversity.com/Contact-Us/contactus.html");
    }

    public void setUnique_FirstName() {
        sendingValueToWebElement("firstName", firstName_TextField, "Maher");
    }

    public void setUnique_LastName() {
        sendingValueToWebElement("lastName", lastName_TextField, "Abubakar");
    }

    public void setUnique_EmailAddress() {
        sendingValueToWebElement("email", emailAddress_TextField, "maher@gmail.com");

    }

    public void setUnique_Comment() {
        sendingValueToWebElement("comment", comment_TextField, "Hello I am ABubaakar");
    }

    public void setSpecific_FirstName(String firstName) {
        sendingValueToWebElement("firstName", firstName_TextField, firstName);


    }

    public void setSpecific_LastName(String lastName) {
        sendingValueToWebElement("firstName", lastName_TextField, lastName);
    }

    public void setSpecific_EmailAddress(String emailAddress) {
        sendingValueToWebElement("firstName", emailAddress_TextField, emailAddress);
    }

    public void setSpecific_Comment(String comment) {
        sendingValueToWebElement("firstName", comment_TextField, comment);
    }

    public void clickOn_Submit_Button() throws InterruptedException {
        waitAndClickOnElement(submit_Button);
    }

    public void validate_Successful_SubmissionMessage_Text() {
        if(waitTillWebElementIsVisible("message ",successfulSubmission_Message_Text)){
            Assert.assertEquals(successfulSubmission_Message_Text.getText(), "Thank You for your Message!");
        }
    }


}
