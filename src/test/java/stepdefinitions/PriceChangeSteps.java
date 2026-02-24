package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.pageobjects.MenuPage;

import java.util.List;

import static engine.Engine.getmenuPage;
import static io.github.the_sdet.cucumber.CucumberUtils.logToReport;

/**
 * Step Definitions for PriceChange Tests
 *
 * @author Veerasekaran Mariappan
 */
public class PriceChangeSteps {
    MenuPage menuPage = new MenuPage(getmenuPage());

    @Given("I have selected flight {string} to book")
    public void selectFlight(String flightNumber) {
        MobileElement flight = menuPage.findElementByXPath(
            "//android.widget.TextView[@resource-id='com.mycompany.myapp:id/flight_number' and @text='" + flightNumber + "']"
        );
        flight.click();
    }

    @When("I start the booking process")
    public void startBooking() {
        menuPage.findElementById("com.mycompany.myapp:id/book_button").click();
    }

    @And("a price change modal appears")
    public void detectPriceChangeModal() {
        try {
            MobileElement modal = menuPage.findElementById("com.mycompany.myapp:id/price_change_modal");
            Assert.assertTrue(modal.isDisplayed());
        } catch (Exception e) {
            System.out.println("Price change modal did not appear.");
        }
    }
    @Then("I handle the price change and continue booking")
    public void handlePriceChange() {
        try {
            MobileElement modal = menuPage.findElementById("com.mycompany.myapp:id/price_change_modal");
            MobileElement continueBtn = modal.findElementById("com.mycompany.myapp:id/continue_button");
            continueBtn.click();
        } catch (Exception e) {
            System.out.println("No modal to handle; continuing booking normally.");
        }
    }

    @And("I should reach the payment screen")
    public void verifyPaymentScreen() {
        MobileElement paymentTitle = menuPage.findElementById("com.mycompany.myapp:id/payment_title");
        Assert.assertTrue(paymentTitle.isDisplayed());
    }
    
}
