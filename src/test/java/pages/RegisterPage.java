package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    WebDriver driver;
    WebDriverWait wait;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillForm(String firstName, String lastName, String email, String confirmEmail, String password, String confirmPassword) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_firstname"))).sendKeys(firstName);
        driver.findElement(By.id("member_lastname")).sendKeys(lastName);
        driver.findElement(By.id("member_emailaddress")).sendKeys(email);
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys(confirmEmail);
        driver.findElement(By.id("signup_password")).sendKeys(password);
        driver.findElement(By.id("signup_confirmpassword")).sendKeys(confirmPassword);
    }

    public void clickTerms() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("signup_terms"))).click();
    }

    public void submit() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("joinButton"))).click();
    }

    public boolean isErrorShown() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("validation-summary-errors")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}