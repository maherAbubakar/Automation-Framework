package pageObjects;

//import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.DriverFactory;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.time.Duration;
import java.util.function.Function;
@Slf4j
public class BasePage_PO extends DriverFactory{

    protected WebDriverWait wait;
    protected JavascriptExecutor jsExecutor;
    protected Actions builder;
    protected WebDriver  driver = getDriver();

    public BasePage_PO(){
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.jsExecutor = ((JavascriptExecutor) driver);
        this.builder = new Actions(driver);
        PageFactory.initElements(driver,this);
    }

    public boolean waitTillWebElementIsVisible(String webElementName, WebElement webElement) {
        try {
            this.wait.until(ExpectedConditions.visibilityOf(webElement));
            logInfo("webElementIsVisible", webElementName, webElement);
            return true;
        } catch (Exception exception) {
            logError("webElementIsNotVisible", webElementName, webElement, exception);
            return false;
        }
    }

    public boolean waitTillWebElementIsVisible(String webElementName, String webElement) {
        try {
            this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(webElement)));
            logInfo("webElementIsVisible", webElementName, webElement);
            return true;
        } catch (Exception exception) {
            logError("webElementIsNotVisible", webElementName, webElement, exception);
            return false;
        }
    }

    public boolean waitTillWebElementIsEnabled(String webElementName, String webElement) {
        try {
            this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(webElement))).isEnabled();
            logInfo("webElementIsEnabled", webElementName, webElement);
            return true;
        } catch (Exception exception) {
            logError("webElementIsNotEnabled", webElementName, webElement, exception);
            return false;
        }
    }

    public void sendingValueToWebElement(String webElementName, WebElement webElement, String textToSend) throws AssertionError {
        try {
            waitTillWebElementIsVisible(webElementName, webElement);
            if (!(webElementName.equalsIgnoreCase("contractRateField") || webElementName.equalsIgnoreCase("residualValueDefinition"))) {
                clearField(webElement);
            }
            Thread.sleep(500);
            webElement.sendKeys(textToSend);
            logInfo("webElementValueIsSet", webElementName + "'s value '" + textToSend + "'", webElement);
        } catch (Exception exception) {
            logError("webElementValueIsNotSet", webElementName + "'s value '" + textToSend + "'", webElement, exception);
        }
    }

    public void actionMoveAndClick(String webElementName, WebElement webElement) throws AssertionError {
        Actions action = new Actions(driver);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(webElement)).isEnabled();
            action.moveToElement(webElement).click().build().perform();
            logInfo("webElementIsClicked", webElementName, webElement);
        } catch (StaleElementReferenceException staleElementReferenceException) {
            Boolean isWebElementPresent = wait.until(ExpectedConditions.elementToBeClickable(webElement)).isEnabled();
            if (isWebElementPresent) {
                action.moveToElement(webElement).click().build().perform();
                logInfo("webElementIsClicked", webElementName, webElement);
            }
        } catch (Exception exception) {
            logError("webElementIsNotClicked", webElementName, webElement, exception);
        }
    }


    public void waitAndClickOnElement(String webElementName, String cssPathToClick) throws InterruptedException {
        Wait<WebDriver> tempWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        boolean clicked = false;
        int attempts = 0;
        Exception exceptionError = null;
        while (!clicked && attempts < 10) {
            try {
                tempWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssPathToClick)));
                driver.findElement(By.cssSelector(cssPathToClick)).click();
                logInfo("webElementIsClicked", webElementName, cssPathToClick);
                clicked = true;
            } catch (Exception exception) {
                exceptionError = exception;
                logInfo("webElementIsClicked", webElementName, cssPathToClick);
                log.info("Clicking attempt is > " + attempts);
                attempts++;
            }
        }
        if (attempts >= 9) {
            logError("webElementIsNotClicked", webElementName, cssPathToClick, exceptionError);
        }
    }

    public void waitAndClickOnElement(WebElement element) throws InterruptedException {
        Wait<WebDriver> tempWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        boolean clicked = false;
        int attempts = 0;
        Exception exceptionError = null;
        while (!clicked && attempts < 10) {
            try {
                tempWait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                System.out.println("Element has been clicked, Element :"+ element.toString());
                clicked = true;
            } catch (Exception exception) {
                exceptionError = exception;
                System.out.println("Unable to wait and click on WebElement, Exception: " + exception.getMessage());
                log.info("Clicking attempt is > " + attempts);
                attempts++;
            }
        }
        if (attempts >= 9) {
            System.err.println("unable to click: " + getSelectorOfElement(element));
        }
    }

    public static String getSelectorOfElement(WebElement element) {
        String str = element.toString();
        return str.substring(str.indexOf("selector:") + 9, str.length() - 1);
    }

    public void clearField(WebElement element) {

        int lenText = element.getAttribute("value").length();

        for(int i = 0; i < lenText; i++){
            element.sendKeys(Keys.BACK_SPACE);
        }
    }


    public boolean isWebElementDisplayed(String webElementName, WebElement webElement) {
        return webElement.isDisplayed();
    }

    public String beautifyWebElement(WebElement webElement) {
        if (webElement.toString().contains("By.cssSelector")) {
            return webElement.toString().substring(webElement.toString().indexOf("cssSelector"), webElement.toString().length() - 1);
        } else {
            return "cssSelector".concat(webElement.toString().substring(webElement.toString().indexOf("css selector") + 12, webElement.toString().length() - 1));
        }
    }

    public String convertWebElementIntoString(WebElement webElement) {
        return webElement.toString().substring(webElement.toString().indexOf("css selector:") + 14, webElement.toString().length() - 1);
    }

    public void logInfo(String type, String webElementName, WebElement webElement) {
        switch (type) {
            case "webElementIsVisible":
                log.info("This web-element " + webElementName + " (" + beautifyWebElement(webElement) + ") is visible");
                break;
            case "webElementValueIsSet":
                log.info("For this web-element " + webElementName + " (" + beautifyWebElement(webElement) + ") is set");
                break;
            case "webElementIsClicked":
                log.info("This web-element " + webElementName + " (" + beautifyWebElement(webElement) + ") is clicked");
                break;
        }
    }

    public void logError(String type, String webElementName, WebElement webElement, Exception exception) {
        switch (type) {
            case "webElementIsNotVisible":
                log.error("This web-element " + webElementName + " (" + beautifyWebElement(webElement) + ") is not visible");
                org.testng.Assert.fail("This web-element " + webElementName + " (" + beautifyWebElement(webElement) + ") is not visible \n" + exception.getMessage());
                break;
            case "webElementValueIsNotSet":
                log.error("For this web-element " + webElementName + " (" + beautifyWebElement(webElement) + ") is not set");
                org.testng.Assert.fail("For this web-element " + webElementName + " (" + beautifyWebElement(webElement) + ") is not set \n" + exception.getMessage());
                break;
            case "webElementIsNotClicked":
                log.error("This web-element " + webElementName + " (" + beautifyWebElement(webElement) + ") is having an issue while clicking");
                org.testng.Assert.fail("This web-element " + webElementName + " (" + beautifyWebElement(webElement) + ") is having an issue while clicking \n" + exception.getMessage());
                break;
        }
    }

    public void logInfo(String type, String webElementName, String webElement) {
        switch (type) {
            case "webElementIsVisible":
                log.info("This web-element " + webElementName + " (cssSelector: " + webElement + ") is visible");
                break;
            case "webElementValueIsSet":
                log.info("For this web-element " + webElementName + " (cssSelector: " + webElement + ") is set");
                break;
            case "webElementIsClicked":
                log.info("This web-element " + webElementName + " (cssSelector: " + webElement + ") is clicked");
                break;
        }
    }

    public void logError(String type, String webElementName, String webElement, Exception exception) {
        switch (type) {
            case "webElementIsNotVisible":
                log.error("This web-element " + webElementName + " (cssSelector: " + webElement + ") is not visible");
                org.testng.Assert.fail("This web-element " + webElementName + " (cssSelector: " + webElement + ") is not visible \n" + exception.getMessage());
                break;
            case "webElementValueIsNotSet":
                log.error("For this web-element " + webElementName + " (cssSelector: " + webElement + ") is not set");
                org.testng.Assert.fail("For this web-element " + webElementName + " (cssSelector: " + webElement + ") is not set \n" + exception.getMessage());
                break;
            case "webElementIsNotClicked":
                log.error("This web-element " + webElementName + " (cssSelector: " + webElement + ") is having an issue while clicking");
                Assert.fail("This web-element " + webElementName + " (cssSelector: " + webElement + ") is having an issue while clicking \n" + exception.getMessage());
                break;
        }
    }


    public void navigateToURL(String url){

        driver.get(url);
    }
    public void waitForAlert_And_ValidateText(String text) {
        wait.until(ExpectedConditions.alertIsPresent());
        String alert_Message_Text = driver.switchTo().alert().getText();
        Assert.assertEquals(alert_Message_Text, text);
    }

}
