package StepDef;

import org.json.simple.JSONObject;
import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.nl.Gegeven;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CucumberCIs {
	RequestSpecification req;
	Response res;
	JsonPath path;
	JSONObject obj;
	String expJ, expN;
	@Gegeven("user is on reqres URL")
	public void user_is_on_reqres_url() {
	    RestAssured.baseURI="https://reqres.in/";
	    req=RestAssured.given();
	    System.out.println("Given step");
	}
 
	@When("User hits the Users API")
	public void user_hits_the_users_api() {
	    res=req.get("api/users?page=2");
	    System.out.println("When step");
	}
 
	@Then("all the Users is displayed")
	public void all_the_users_is_displayed() {
	   String data=res.asPrettyString();
	   JsonPath path=res.jsonPath();
	   String id=path.getString("data[0].id");
	   Assert.assertEquals(id, "7", "id matched");
	   System.out.println("Then step");
	}

}
