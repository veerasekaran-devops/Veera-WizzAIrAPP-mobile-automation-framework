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
public class PaymentInsideSteps {
    MenuPage menuPage = new MenuPage(getmenuPage());

    @Given("I have reached the payment step")
    public void reachPaymentStep() {
        MobileElement paymentBtn = menuPage.findElementById("com.mycompany.myapp:id/proceed_to_payment");
        Assert.assertTrue(paymentBtn.isDisplayed());
        paymentBtn.click();
    }

    @When("I switch to the WebView context")
    public void switchToWebView() {
        Set<String> contexts = menuPage.getContextHandles();
        for (String context : contexts) {
            if (context.contains("WEBVIEW")) {
                menuPage.context(context);
                System.out.println("Switched to WebView: " + context);
                break;
            }
        }
    }

    @And("I complete the payment form with card {string}, expiry {string}, CVV {string}")
    public void completePaymentForm(String cardNumber, String expiry, String cvv) {
        menuPage.findElementByCssSelector("#card_number").sendKeys(cardNumber);
        menuPage.findElementByCssSelector("#expiry_date").sendKeys(expiry);
        menuPage.findElementByCssSelector("#cvv").sendKeys(cvv);
        menuPage.findElementByCssSelector("#pay_button").click();
    }

    @Then("I return to the native app")
    public void switchBackToNative() {
        menuPage.context("NATIVE_APP");
    }
    @And("I should see the booking confirmation screen")
    public void verifyBookingConfirmation() {
        MobileElement confirmationTitle = menuPage.findElementById("com.mycompany.myapp:id/confirmation_title");
        Assert.assertTrue(confirmationTitle.isDisplayed());
    }
    
}
