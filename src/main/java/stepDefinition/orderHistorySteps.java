package stepDefinition;

import PageObjectModel.orderHistoryPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;

public class orderHistorySteps {

    WebDriver driver;
    String referenceNumber;
    orderHistoryPage order = new orderHistoryPage();

    @And("^Get the reference number from text$")
    public void getTheReferenceNumberFromText() {

       referenceNumber = order.getTheReferenceNumber();
    }

    @Given("^Click on Tugba Aydin tab$")
    public void click_on_Tugba_Aydin_tab() throws Throwable {

        order.clickOnTugbaAydinTab();
    }

    @Given("^Click on Order History and Details$")
    public void click_on_Order_History_and_Details() throws Throwable {

        order.clickOnOrderHistoryTab();

    }
    @Then("^Confirm order reference number is same after click on my order button$")
    public void confirm_order_reference_number_is_same_after_click_on_my_order_button() throws Throwable {

        order.getTheColumnReferenceNum(referenceNumber);

    }


}
