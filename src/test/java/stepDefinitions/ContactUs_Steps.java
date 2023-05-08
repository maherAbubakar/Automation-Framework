package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageObjects.BasePage_PO;
import pageObjects.ContactUs_PO;

public class ContactUs_Steps extends BasePage_PO {

    private final ContactUs_PO contactUs_po;
//    private final WebDriver driver = getDriver();

    public ContactUs_Steps(ContactUs_PO contactUs_po){
        this.contactUs_po=contactUs_po;
    }

    @Given("User access the webDriver university contact us page")
    public void user_access_the_webDriver_university_contact_us_page() {
       contactUs_po.ContactUs_Page();
    }
    @When("User enter a unique first name")
    public void user_enter_a_unique_first_name() {
      //  driver.findElement(By.xpath("//input[@name='first_name']5")).sendKeys("Maher");
//        sendKeys(By.xpath("//input[@name='first_name']"), "Maher");
//        WebElement firstNameInput = driver.findElement(By.xpath("//input[@name='first_name']"));
//        sendingValueToWebElement(firstNameInput, "Maher");
        contactUs_po.setUnique_FirstName();
    }
    @And("user enter a unique last name")
    public void user_enter_a_unique_last_name() {
//        driver.findElement(By.xpath("//input[@name='last_name']")).sendKeys("Abubakar");
        //sendKeys(By.xpath("//input[@name='last_name']"), "Abubakar");
//        WebElement lastNameInput = driver.findElement(By.xpath("//input[@name='last_name']"));
//        sendingValueToWebElement(lastNameInput, "Abubakar");
        contactUs_po.setUnique_LastName();
    }
    @And("User enter unique email address")
    public void user_enter_unique_email_address() {
       //driver.findElement(By.xpath("//input[@name='email']")).sendKeys("mabubakar1@email.com");
//        sendKeys(By.xpath("//input[@name='email']"), "mabubakar1@email.com");
//        WebElement emailInput = driver.findElement(By.xpath("//input[@name='email']"));
//        sendingValueToWebElement(emailInput, "maher@gmail.com");

        contactUs_po.setUnique_EmailAddress();
    }

    @And("User enter comment")
    public void user_enter_comment() {
//        driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Hello, I find your courses ver helpful");
//        sendKeys(By.xpath("//textarea[@name='message']"), "Hello, I find your courses ver helpful");
//        WebElement commentInput = driver.findElement(By.xpath("//textarea[@name='message']"));
//        sendingValueToWebElement(commentInput, "Hello, I find your courses ver helpful");
        contactUs_po.setUnique_Comment();

    }
    @When("User enter a specific first name {}")
    public void user_enter_a_specific_first_name(String fname) {
//        driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys(fname);
        contactUs_po.setSpecific_FirstName(fname);
    }
    @When("user enter a specific last name {string}")
    public void user_enter_a_specific_last_name(String lname) {
//        driver.findElement(By.xpath("//input[@name='last_name']")).sendKeys(lname);
        contactUs_po.setSpecific_LastName(lname);
    }
    @When("User enter specific email address {string}")
    public void user_enter_specific_email_address(String email) {
//        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        contactUs_po.setSpecific_EmailAddress(email);

    }
    @When("User enter specific comment {string}")
    public void user_enter_specific_comment(String comment) {
//        driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys(comment);
        contactUs_po.setSpecific_Comment(comment);
    }
    @And("User click on submit button")
    public void user_click_on_submit_button() throws InterruptedException {
        //driver.findElement(By.xpath("//input[@value='SUBMIT']")).click();
//        waitForWebElementAndClick(By.xpath("//input[@value='SUBMIT']"));
//        WebElement submitButton = driver.findElement(By.xpath("//input[@value='SUBMIT']"));
//        waitAndClickOnElement(submitButton);
        contactUs_po.clickOn_Submit_Button();
    }
    @Then("I should be presented with a successful contact us submission message")
    public void i_should_be_presented_with_a_successful_contact_us_submission_message() {
//        String message = driver.findElement(By.xpath("//h1[.='Thank You for your Message!']")).getText();
//        System.out.println(message);
        contactUs_po.validate_Successful_SubmissionMessage_Text();
    }

}
