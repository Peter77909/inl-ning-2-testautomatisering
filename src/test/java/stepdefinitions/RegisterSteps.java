package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class RegisterSteps {

    WebDriver driver;
    WebDriverWait wait;

    @Given("the user is on the registration page")
    public void the_user_is_on_the_registration_page() {
        String browser = System.getProperty("browser", "chrome");

        if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    private void waitForElement(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @When("the user enters first name {string}")
    public void enterFirstName(String firstName) {
        By locator = By.id("member_firstname");
        waitForElement(locator);
        driver.findElement(locator).sendKeys(firstName);
    }

    @When("the user enters last name {string}")
    public void enterLastName(String lastName) {
        if (!lastName.isEmpty()) {
            By locator = By.id("member_lastname");
            waitForElement(locator);
            driver.findElement(locator).sendKeys(lastName);
        }
    }

    @When("the user enters email {string}")
    public void enterEmail(String email) {
        By locator = By.id("member_emailaddress");
        waitForElement(locator);
        driver.findElement(locator).sendKeys(email);
    }

    @When("the user enters confirm email {string}")
    public void enterConfirmEmail(String confirmEmail) {
        By locator = By.id("member_confirmemailaddress");
        waitForElement(locator);
        driver.findElement(locator).sendKeys(confirmEmail);
    }

    @When("the user enters password {string}")
    public void enterPassword(String password) {
        By locator = By.id("signupunlicenced_password");
        waitForElement(locator);
        driver.findElement(locator).sendKeys(password);
    }

    @When("the user enters confirm password {string}")
    public void enterConfirmPassword(String confirmPassword) {
        By locator = By.id("signupunlicenced_confirmpassword");
        waitForElement(locator);
        driver.findElement(locator).sendKeys(confirmPassword);
    }

    @When("the user selects date of birth {string}")
    public void selectDob(String dob) {
        String[] parts = dob.split("/"); // DD/MM/YYYY
        driver.findElement(By.id("dp")).sendKeys(dob);

    }

    @When("the user accepts terms {string}")
    public void acceptTerms(String accept) {
        if (accept.equalsIgnoreCase("true")) {
            WebElement checkbox1 = driver.findElement(By.cssSelector("label[for='sign_up_25") );
            WebElement checkbox = driver.findElement(By.cssSelector("label[for='sign_up_26") );
            WebElement checkbox2 = driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct"));
            if (!checkbox1.isSelected()) checkbox1.click();
            if (!checkbox.isSelected()) checkbox.click();
            if (!checkbox2.isSelected()) checkbox2.click();

        }
    }

    @When("the user clicks the register button")
    public void clickRegister() {
        By locator = By.name("join");
        waitForElement(locator);
        driver.findElement(locator).click();
    }

    @Then("the user should see {string}")
    public void verifyResult(String expected) throws InterruptedException {
        Thread.sleep(5000); // Vänta på att sidan ska laddas
        String pageSource = driver.getPageSource().toLowerCase();
        boolean found = pageSource.contains(expected.toLowerCase());

        // Extra information för felsökning
        if (!found) {
            System.out.println("\nFEL – Texten hittades inte:");
            System.out.println("Förväntad: " + expected);
            System.out.println("Sidan innehåller:\n" + pageSource);
        }

        Assert.assertTrue("Expected message not found: " + expected, found);
        driver.quit();
    }
}

