package stepdefs;

import framework.WebDriverFactory;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.remote.RemoteWebDriver;
import utilities.CommonWebActions;
import utilities.LoggerUtil;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

@Log4j
@RunWith(Cucumber.class)
public class Category_stepDef {

    private static RemoteWebDriver driver = null;
    private static Alert alert = null;
    private static String totalp;
    private static HashMap<String,String> purchaseDetailsMap=null;

    @Given("^User launches chrome browser$")
    public void user_launches_chrome_browser() throws Throwable {

        driver = WebDriverFactory.getDriver("chrome",0,10000);
    }

    @Given("^User open$")
    public void user_open(String url) throws Throwable {

        CommonWebActions.fetchURL(driver,url);
    }

    @When("^User clicks on Home button$")
    public void user_clicks_on_home_button() throws Throwable {

        CommonWebActions.clickElementWithoutWait(driver,"//*[contains(text(),'Home')]");
    }

    @When("^User clicks on category(.+)$")
    public void user_clicks_on_category(String category) throws Throwable {

        CommonWebActions.clickElementWithoutWait(driver,"//*[text()='CATEGORIES']/following-sibling::a[text()='"+category+"']");
    }

    @And("^User clicks on Laptop (.+)$")
    public void user_clicks_on_laptop(String laptopname) throws Throwable {

        CommonWebActions.clickElementWithoutWait(driver,"//*[@class='card-title']/a[text()='"+laptopname +"']");
    }

    @Then("^Header should be (.+)$")
    public void header_should_be(String laptopname) throws Throwable {

        boolean flag=false;
        CommonWebActions.fluentWaitTillElementIsPresent("//*[text()='"+laptopname+"']",driver,Duration.ofMillis(10000),Duration.ofMillis(500));
        if(CommonWebActions.isDisplayed(driver,"//*[text()='"+laptopname+"']")){
            flag=true;
        }
        Assert.assertTrue("Header doesnot match!",flag);
    }

    @When("^User clicks on AddToCart$")
    public void user_clicks_on_addtocart() throws Throwable {

        CommonWebActions.clickElementWithoutWait(driver,"//a[text()='Add to cart']");
    }

    @Then("^Product Added pop-up should appear$")
    public void product_added_popup_should_appear() throws Throwable {

        if(CommonWebActions.waitForAlertPresent(driver,30)){
            alert = CommonWebActions.switchToAlert(driver);
            String alertText = CommonWebActions.getAlertText(alert);
            Assert.assertTrue("Validation Passed->Pop-up appears with text:"+alertText,true);
        }
        else{
            Assert.assertTrue("No Pop-up appeared",false);
        }
    }

    @And("^User accepts pop-up confirmation$")
    public void user_accepts_popup_confirmation() throws Throwable {

        CommonWebActions.acceptAlert(alert);
    }

    @When("^User clicks cart button$")
    public void user_clicks_cart_button() throws Throwable {

        CommonWebActions.clickElementWithoutWait(driver,"//a[text()='Cart']");
    }

    @Then("^Products should be added in cart$")
    public void products_should_be_added_in_cart(List<String> products) throws Throwable {

        CommonWebActions.waitFor(10000);
        for(String product:products){
            Assert.assertTrue(product+", product is not available in cart",CommonWebActions.isDisplayed(driver,"//td[text()='"+product+"']"));
        }
    }

    @When("^User deletes (.+)$")
    public void user_deletes(String product) throws Throwable {

        CommonWebActions.clickElementWithoutWait(driver,"//td[text()='"+product+"']/following-sibling::td/a[text()='Delete']");
        CommonWebActions.waitFor(5000);
    }


    @When("^User fetch total amount$")
    public void user_fetch_total_amount() throws Throwable {

        CommonWebActions.waitFor(5000);
        totalp = CommonWebActions.fetchText(driver,"//*[@id='totalp']");
    }

    @When("^User clicks on place order$")
    public void user_clicks_on_place_order() throws Throwable {

        CommonWebActions.clickElementWithoutWait(driver, "//*[text()='Place Order']");
    }

    @And("^Fill form details (.+),(.+),(.+),(.+),(.+),(.+)$")
    public void fill_form_details_(String name, String country, String city, String creditcard, String month, String year) throws Throwable {

        CommonWebActions.fillTextWithoutClear(driver, Duration.ofMillis(2000),Duration.ofMillis(2000),"//input[@id='name']",name);
        CommonWebActions.fillTextWithoutClear(driver, Duration.ofMillis(2000),Duration.ofMillis(2000),"//input[@id='country']",country);
        CommonWebActions.fillTextWithoutClear(driver, Duration.ofMillis(2000),Duration.ofMillis(2000),"//input[@id='city']",city);
        CommonWebActions.fillTextWithoutClear(driver, Duration.ofMillis(2000),Duration.ofMillis(2000),"//input[@id='card']",creditcard);
        CommonWebActions.fillTextWithoutClear(driver, Duration.ofMillis(2000),Duration.ofMillis(2000),"//input[@id='month']",month);
        CommonWebActions.fillTextWithoutClear(driver, Duration.ofMillis(2000),Duration.ofMillis(2000),"//input[@id='year']",year);
    }

    @And("^User click on Purchase button$")
    public void user_click_on_purchase_button() throws Throwable {

        CommonWebActions.clickElementWithoutWait(driver, "//*[text()='Purchase']");
    }

    @Then("^Confirmation (.+) should be displayed$")
    public void confirmation_should_be_displayed(String message) throws Throwable {

        boolean flag=false;
        if(CommonWebActions.fluentWaitTillElementIsPresent("//*[text()='Thank you for your purchase!']",
                driver, Duration.ofSeconds(30),Duration.ofMillis(500))){
             flag= true;
        }
        Assert.assertTrue("Confirmation not got",flag);
    }

    @And("^Capture purchase details and log$")
    public void capture_purchase_details_and_log() throws Throwable {

        String [] details = CommonWebActions.fetchText(driver,"//*[contains(text(),'Id')]").split("\n");
        purchaseDetailsMap = new HashMap<>();
        for(String detail: details){
            String [] arr = detail.split(":");
            purchaseDetailsMap.put(arr[0].trim(),arr[1].trim());
        }
        LoggerUtil.logInfo("ID:"+purchaseDetailsMap.get("Id"),log);
        LoggerUtil.logInfo("Amount:"+purchaseDetailsMap.get("Amount"),log);

    }

    @Then("^Verify purchase details$")
    public void verify_purchase_details() throws Throwable {

        if(totalp.equalsIgnoreCase(purchaseDetailsMap.get("Amount").replaceAll("USD","").trim())){
            Assert.assertTrue(true);
            LoggerUtil.logInfo("Amount is as expected",log);
        }
        else{
            Assert.assertTrue("Amount doesnot match. Expected:"+totalp+", " +
                    "Actual:"+purchaseDetailsMap.get("Amount").replaceAll("USD","").trim(),false);
        }
    }

    @And("^Click on OK button$")
    public void click_on_ok_button() throws Throwable {

        CommonWebActions.clickElementWithoutWait(driver, "//button[text()='OK']");
    }


}