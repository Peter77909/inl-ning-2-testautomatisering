package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegisterPage;

import java.time.Duration;

public class RegisterSteps {
    WebDriver driver;
    RegisterPage registerPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");

        // Vänta tills första fältet finns innan vi går vidare
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("member_firstname")));

        registerPage = new RegisterPage(driver);
    }

    @After
    public void tearDown() {
        // Kommentera ut för att låta fönstret vara öppet
        // driver.quit();
    }

    @Given("användaren är på registreringssidan")
    public void användaren_är_på_registreringssidan() {}

    @When("användaren fyller i alla fält korrekt")
    public void användaren_fyller_i_alla_fält_korrekt() {
        registerPage.fillForm("Peter", "Muneer", "peter@mail.com", "peter@mail.com", "Test1234!", "Test1234!");
        registerPage.clickTerms();
        registerPage.submit();
    }

    @When("användaren lämnar efternamn tomt")
    public void användaren_lämnar_efternamn_tomt() {
        registerPage.fillForm("Peter", "", "peter@mail.com", "peter@mail.com", "Test1234!", "Test1234!");
        registerPage.clickTerms();
        registerPage.submit();
    }

    @When("lösenorden inte matchar")
    public void lösenorden_inte_matchar() {
        registerPage.fillForm("Peter", "Muneer", "peter@mail.com", "peter@mail.com", "Test1234!", "Fel1234!");
        registerPage.clickTerms();
        registerPage.submit();
    }

    @When("terms and conditions inte godkänns")
    public void terms_not_checked() {
        registerPage.fillForm("Peter", "Muneer", "peter@mail.com", "peter@mail.com", "Test1234!", "Test1234!");
        registerPage.submit();
    }

    @Then("ska ett konto skapas")
    public void konto_skapas() {
        try { Thread.sleep(8000); } catch (InterruptedException e) { e.printStackTrace(); }
        Assert.assertFalse("Felmeddelande visades", registerPage.isErrorShown());
    }

    @Then("ska ett felmeddelande visas")
    public void felmeddelande_visas() {
        try { Thread.sleep(8000); } catch (InterruptedException e) { e.printStackTrace(); }
        Assert.assertTrue("Felmeddelande saknas", registerPage.isErrorShown());
    }
}