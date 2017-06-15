package bddtests.stepDefs;

import static bddtests.utils.TestConstants.*;
import static bddtests.utils.ScenarioHelper.*;

import bddtests.utils.ScenarioHelper;
import cucumber.api.java.en.Given;
import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.path.xml.XmlPath.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Soap_test_stepdefs {

    private ScenarioHelper scenarioHelper = new ScenarioHelper();

    @Given("^do a test$")
    public void doATest() throws Throwable {

        baseURI = "http://services.aonaware.com";
        port = 80;
        String word = "hand";

        //create a function to return xml as string

        String requestBodyAsString = scenarioHelper.convertXMLToString(SOAP_XML_PATH);

        Map<String, String> authhdrs = new HashMap<String, String>();
        authhdrs.put("SOAPAction", "Define");
        //authhdrs.put("Content-Length", Integer.toString( myEnvelope.length() ) );

        String xml = given().request().headers(authhdrs)
                .contentType("application/soap+xml; charset=UTF-8;").body(requestBodyAsString)
                .when().post( "/DictService/DictService.asmx" ).andReturn().asString();

        String prettyXML = with(xml).prettyPrint();
        System.out.println(prettyXML);

    }
}
