package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.pageobjects.HomePage;

import static engine.Engine.gethomePage;

/**
 * Step Definitions for FlightSearch Tests
 *
 * @author Veerasekaran Mariappan
 */
public class AppBackgroundSteps {
    HomePage homePage = new HomePage(gethomePage());

    @Given("I am on the passenger details screen")
    public void onPassengerDetailsScreen() {
        MobileElement title = homePage.findElementById("com.mycompany.myapp:id/passenger_details_title");
        Assert.assertTrue(title.isDisplayed());
    }

    @When("I enter passenger details with name {string} and age {string}")
    public void enterPassengerDetails(String name, String age) {
        homePage.findElementById("com.mycompany.myapp:id/passenger_name").sendKeys(name);
        homePage.findElementById("com.mycompany.myapp:id/passenger_age").sendKeys(age);
    }

    @And("I send the app to background for {int} seconds")
    public void sendAppToBackground(int seconds) {
        homePage.runAppInBackground(java.time.Duration.ofSeconds(seconds));
    }

    @Then("I should see the passenger details {string} and {string} preserved")
    public void verifyPassengerDetailsPreserved(String name, String age) {
        String enteredName = homePage.findElementById("com.mycompany.myapp:id/passenger_name").getText();
        String enteredAge = homePage.findElementById("com.mycompany.myapp:id/passenger_age").getText();

        Assert.assertEquals(name, enteredName);
        Assert.assertEquals(age, enteredAge);
    }
 

  
    
}
