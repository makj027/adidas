package stepdefs;


import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.junit.Cucumber;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.junit.runner.RunWith;
import utilities.LoggerUtil;
import utilities.RestAssuredUtil;

import java.util.HashMap;

@Log4j
@RunWith(Cucumber.class)
public class Api_stepDef {

    private static Response getResponse;
    private static Response postResponse;

    @Given("^GET request on (.+), (.+) for (.+) pets$")
    public void get_request_on_for_pets(String baseuri, String endpoint, String status) throws Throwable {

        getResponse = RestAssuredUtil.getRequest(baseuri+endpoint,new HashMap<String,String>().put("status",status));
    }

    @Then("^Validate GET response$")
    public void validate_get_response() throws Throwable {

        boolean flag = false;
        if(getResponse.getStatusCode()==200){
            flag=true;

        }
        Assert.assertTrue("Status is incorrect",flag);
        LoggerUtil.logInfo("Status is correct: 200",log);
        LoggerUtil.logInfo("Available pets:"+getResponse.getBody().jsonPath().getString("$"),log);

    }

    @Given("^POST request on (.+), (.+) for (.+) pet to store$")
    public void post_request_on_for_pet_to_store(String baseuri, String endpoint, String payload) throws Throwable {

        postResponse = RestAssuredUtil.postRequest(ContentType.JSON,baseuri+endpoint,payload);
    }

    @Then("^Validate pet is added to store$")
    public void validate_pet_is_added_to_store() throws Throwable {
        boolean flag = false;
        if(getResponse.getStatusCode()==200){
            flag=true;

        }
        Assert.assertTrue("Pet not added",flag);
        LoggerUtil.logInfo("Pet is successfully added to store",log);
        LoggerUtil.logInfo("pet added to store:"+postResponse.getBody().jsonPath().getString("$"),log);

    }

    //Implementation pending//
//    @Given("^UPDATE request for pet status to sold$")
//    public void update_request_for_pet_status_to_sold() throws Throwable {
//        throw new PendingException();
//    }
//
//    @Then("^Validate pet status$")
//    public void validate_pet_status() throws Throwable {
//        throw new PendingException();
//    }

//    @Given("^DELETE pet from store$")
//    public void delete_pet_from_store() throws Throwable {
//        throw new PendingException();
//    }
//
//    @Then("^Validate delete request result$")
//    public void validate_delete_request_result() throws Throwable {
//        throw new PendingException();
//    }

}
