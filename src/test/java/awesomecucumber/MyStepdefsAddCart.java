package awesomecucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

public class MyStepdefsAddCart {
    private WebDriver webDriver;

    @Given("I'm on the Store page")
    public void iMOnTheStorePage() {
        System.setProperty("webdriver.chrome.driver","/home/sachet/Documents/chromeDriver/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://askomdch.com/store");
    }

    @When("I add a {string} in the cart")
    public void iAddAInTheCart(String arg0) throws InterruptedException {
        By addToCartBtn = By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']");
        webDriver.findElement(addToCartBtn).click();
        Thread.sleep(5000);
        By viewCartLink = By.cssSelector("a[title='View cart']");
        webDriver.findElement(viewCartLink).click();
    }

    @Then("I see {int} {string} in the cart")
    public void iSeeInTheCart(int arg0, String arg2) {
        By productNameFld = By.xpath("//*[@id=\"post-1220\"]/div/div/div/div/form/table/tbody/tr[1]/td[3]/a");
        String actualProductName = webDriver.findElement(productNameFld).getText();
        By productQntField = By.cssSelector("input[type=\"number\"]");
        String actualQty = webDriver.findElement(productQntField).getAttribute("value");
        Assert.assertEquals(arg2, actualProductName);
        Assert.assertEquals(arg0, Integer.parseInt(actualQty));
    }

    @Given("I'm a guest customer")
    public void iMAGuestCustomer() {
        System.setProperty("webdriver.chrome.driver","/home/sachet/Documents/chromeDriver/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://askomdch.com/store");
    }

    @And("I have a product in the cart")
    public void iHaveAProductInTheCart() throws InterruptedException {
        By addToCartBtn = By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']");
        webDriver.findElement(addToCartBtn).click();
        Thread.sleep(5000);
        By viewCartLink = By.cssSelector("a[title='View cart']");
        webDriver.findElement(viewCartLink).click();
    }

    @And("I'm on the checkout page")
    public void iMOnTheCheckoutPage() {
        By proceedToCheckOutButton = By.cssSelector(".checkout-button");
        webDriver.findElement(proceedToCheckOutButton).click();
    }

    @When("I provide billing details")
    public void iProvideBillingDetails(List<Map<String, String>> billingDetails) {

        By billingFirstnameFld = By.id("billing_first_name");
        By billingLastNameFld = By.id("billing_last_name");
        By billingAddressOneFld = By.id("billing_address_1");
        By billingCityFld = By.id("billing_city");
        By billingStateDropdown = By.id("billing_state");
        By billingZipFld = By.id("billing_postcode");
        By billingEmailFld = By.id("billing_email");

        webDriver.findElement(billingFirstnameFld).clear();
        webDriver.findElement(billingFirstnameFld).sendKeys(billingDetails.get(0).get("firstname"));
        webDriver.findElement(billingLastNameFld).clear();
        webDriver.findElement(billingLastNameFld).sendKeys(billingDetails.get(0).get("lastname"));
        webDriver.findElement(billingAddressOneFld).clear();
        webDriver.findElement(billingAddressOneFld).sendKeys(billingDetails.get(0).get("address_line1"));
        webDriver.findElement(billingCityFld).clear();
        webDriver.findElement(billingCityFld).sendKeys(billingDetails.get(0).get("city"));
        Select select = new Select(webDriver.findElement(billingStateDropdown));
        select.selectByVisibleText(billingDetails.get(0).get("state"));
        webDriver.findElement(billingZipFld).clear();
        webDriver.findElement(billingZipFld).sendKeys(billingDetails.get(0).get("zip"));
        webDriver.findElement(billingEmailFld).clear();
        webDriver.findElement(billingEmailFld).sendKeys(billingDetails.get(0).get("email"));
    }

    @And("I place an order")
    public void iPlaceAnOrder() {
        By placeOrderButton = By.id("place_order");
        webDriver.findElement(placeOrderButton).click();
    }

    @Then("the order should be placed successfully")
    public void theOrderShouldBePlacedSuccessfully() throws InterruptedException {
        Thread.sleep(5000);
        By noticeText = By.cssSelector(".woocommerce-notice");
        String actualNoticeMessage = webDriver.findElement(noticeText).getText();
        Assert.assertEquals("Thank you. Your order has been received.", actualNoticeMessage);
    }
}
/**
 * SHORTCOMINGS
 * initialising the driver twice, duplicating the driver initialisation
 * driver can be static and runn features sequentially, but cannot initialise single
 * Cucumber runs scenario randomly
 * if driver is static so we may loose the driver in scenarios
 * we may use thread local, but for multiple step defs we will repeat same code of thread local
 * not using synchronising strategy, no waits are there, only used static sleep
 */
