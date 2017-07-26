package bddtests.stepDefs;

import static bddtests.utils.TestConstants.*;
import static bddtests.utils.ScenarioHelper.*;

import bddtests.utils.ScenarioHelper;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.path.xml.XmlPath.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Soap_test_stepdefs {

    private ScenarioHelper scenarioHelper = new ScenarioHelper();
    private String xml;

    @Given("^a test SOAP service exists$")
    public void aTestSOAPServiceExists() throws Throwable {
    }

    @Given("^the SOAP service \"([^\"]*)\" exists$")
    public void theSOAPServiceExists(String URI) throws Throwable {
        baseURI = URI;
        port = 80;
    }

    @When("^I POST a test payload containing the word \"([^\"]*)\"$")
    public void iPOSTATestPayloadContainingTheWord(String arg0) throws Throwable {
        //create a function to return xml as string
        String requestBodyAsString = scenarioHelper.convertXMLToString(SOAP_XML_PATH);

        Map<String, String> authhdrs = new HashMap<String, String>();
        authhdrs.put("SOAPAction", "Define");
        //authhdrs.put("Content-Length", Integer.toString( myEnvelope.length() ) );

        xml = given().request().headers(authhdrs)
                .contentType("application/soap+xml; charset=UTF-8;").body(requestBodyAsString)
                .when().post( "/DictService/DictService.asmx" ).andReturn().asString();
    }

    @Then("^the response contains definitions of the word \"([^\"]*)\"$")
    public void theResponseContainsDefinitionsOfTheWork(String arg0) throws Throwable {
        String prettyXML = with(xml).prettyPrint();
        System.out.println(prettyXML);
    }
}
