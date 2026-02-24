package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.pageobjects.FlightsPage;
import pages.pageobjects.MenuPage;

import static engine.Engine.getflightsPage;

/**
 * Step Definitions for LanuchApp Tests
 *
 * @author Veerasekaran Mariappan
 */
public class LanuchAppSteps {
    MenuPage menuPage = new MenuPage(getflightsPage());
    FlightsPage flightsPage = new FlightsPage(getflightsPage());
    
    @Given("the app is installed on the device")
    public void appIsInstalled() {
        // Verify app installation (optional)
        Assert.assertTrue(flightsPage.isAppInstalled("com.mycompany.myapp"));
    }

    @When("I launch the app using deep link {string}")
    public void launchAppWithDeepLink(String deepLink) {
        // Use Appium deep link method for Android/iOS
        flightsPage.executeScript("mobile: deepLink", ImmutableMap.of(
            "url", deepLink,
            "package", "com.mycompany.myapp"  // Android package
        ));
    }

    @And("I handle all system permission popups")
    public void handlePermissions() {
        // Simple generic handler for iOS/Android
        try {
            while (flightsPage.findElementsById("com.android.packageinstaller:id/permission_allow_button").size() > 0) {
                flightsPage.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
            }
            // iOS
            while (flightsPage.findElementsByAccessibilityId("Allow").size() > 0) {
                flightsPage.findElementByAccessibilityId("Allow").click();
            }
        } catch (Exception e) {
            System.out.println("No permission popups appeared.");
        }
    }
    @Then("I should see the flight details screen for flight {string}")
    public void verifyFlightDetails(String flightNumber) {
        MobileElement flightTitle = flightsPage.findElementById("com.mycompany.myapp:id/flight_number");
        Assert.assertEquals(flightNumber, flightTitle.getText());
    }
}
