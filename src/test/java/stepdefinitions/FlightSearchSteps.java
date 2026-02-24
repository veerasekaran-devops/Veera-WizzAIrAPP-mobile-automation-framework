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
public class FlightSearchSteps {
    HomePage homePage = new HomePage(gethomePage());

    @Given("the app is launched")
    public void appIsLaunched() {
        // Ensure app is running
        Assert.assertTrue(homePage.isAppInstalled("com.mycompany.myapp"));
    }

    @When("I search for flights from {string} to {string} on {string}")
    public void searchForFlights(String from, String to, String date) {
        homePage.findElementById("com.mycompany.myapp:id/from_input").sendKeys(from);
        homePage.findElementById("com.mycompany.myapp:id/to_input").sendKeys(to);
        homePage.findElementById("com.mycompany.myapp:id/date_input").sendKeys(date);
        homePage.findElementById("com.mycompany.myapp:id/search_button").click();
    }

    @And("I scroll through the flight results")
    public void scrollThroughFlightResults() {
        boolean endOfList = false;
        int previousCount = 0;

        while (!endOfList) {
            List<MobileElement> flights = homePage.findElementsById("com.mycompany.myapp:id/flight_item");
            int currentCount = flights.size();

            if (currentCount == previousCount) {
                endOfList = true; // no more new flights
            } else {
                previousCount = currentCount;
                // Scroll down
                int startX = homePage.manage().window().getSize().width / 2;
                int startY = (int) (homePage.manage().window().getSize().height * 0.8);
                int endY = (int) (homePage.manage().window().getSize().height * 0.2);
                homePage.swipe(startX, startY, startX, endY, 1000); // scroll gesture
            }
        }
    }

    @Then("I should see flight {string} in the search results")
    public void verifyFlightAppears(String flightNumber) {
        List<MobileElement> flights = homePage.findElementsById("com.mycompany.myapp:id/flight_item");

        boolean found = flights.stream()
                .anyMatch(f -> f.findElementById("com.mycompany.myapp:id/flight_number").getText().equals(flightNumber));

        Assert.assertTrue("Flight " + flightNumber + " not found in search results", found);
    }

  
    
}
