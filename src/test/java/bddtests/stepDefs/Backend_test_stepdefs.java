package bddtests.stepDefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import com.jayway.restassured.path.json.JsonPath;

public class Backend_test_stepdefs {

    private Response response;
    private RequestSpecification request;

    @When("^a GET request is sent to \"([^\"]*)\"$")
    public void aGETRequestIsSentTo(String endPoint) throws Throwable {
        request = given().request().contentType(ContentType.JSON);
        response = request.when().get(endPoint);

    }

    @Then("^the status code is (\\d+)$")
    public void theStatusCodeIs(int statusCode) throws Throwable {
        assertEquals("expected http status code", statusCode, response.statusCode());

    }

    @And("^the response contains the email address \"([^\"]*)\"$")
    public void theResponseContainsTheEmailAddress(String expectedOutcome) throws Throwable {
        String responseJson = this.response.prettyPrint();
        JsonPath json = JsonPath.from(responseJson);
        String actualOutcome = json.getString("email");
        assertEquals("incorrect value", actualOutcome, expectedOutcome);

    }

}
