package com.livi.coding.challenge.demo.bdd.stepdefs;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.livi.coding.challenge.demo.bdd.SpringIntegrationTest;
import com.livi.coding.challenge.demo.para.CreditServiceRequestModel;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreditStepDefs extends SpringIntegrationTest {

	@When("The calculateCreditAssessmentScore API is called with {int}, {string}, {int} parameters")
	public void the_calculateCreditAssessmentScore_API_is_called_with(int numberOfEmployees, String companyType,
			int numberOfYearsOperated) throws Throwable {

		String token = retrieveToken(OAUTH2_PARAM_CORRECT);

		CreditServiceRequestModel req = CreditServiceRequestModel.builder().numberOfEmployees(numberOfEmployees)
				.numberOfYearsOperated(numberOfYearsOperated).companyType(companyType).build();
		executePost(CREDIT_CALCULATOR_URL, req, token);
	}

	@Then("The credit assessment score should match {int}")
	public void the_client_assessment_score_should_match(int creditScore) throws Throwable {
		final String result = latestResponse.getBody();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(result);
		final int score = rootNode.at("/data/creditScore").asInt();

		assertThat(score, is(creditScore));
	}

	@When("The calculateCreditAssessmentScore API is called with {int}, {string}, {int} parameters and some parameters are invalid.")
	public void the_calculateCreditAssessmentScore_API_is_called_with_F400(int numberOfEmployees1, String companyType1,
			int numberOfYearsOperated1) throws Throwable {

		String token = retrieveToken(OAUTH2_PARAM_CORRECT);

		CreditServiceRequestModel req = CreditServiceRequestModel.builder().numberOfEmployees(numberOfEmployees1)
				.numberOfYearsOperated(numberOfYearsOperated1).companyType(companyType1).build();
		executePost(CREDIT_CALCULATOR_URL, req, token);
	}

	@Then("The client receives status code of {int}")
	public void the_client_receives_status_code_of(int response) throws Throwable {
		final int result = latestResponse.getTheResponse().getStatusCode().value();

		assertThat(result, is(response));
	}

	@And("The client receives {string}")
	public void the_client1_receives_server_version_body(String failMessage1) throws Throwable {
		final String result = latestResponse.getBody();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(result);
		final String message = rootNode.at("/message").asText();

		assertThat(message, is(failMessage1));
	}

	@When("The calculateCreditAssessmentScore API is called and Token without 'Read' scope")
	public void the_calculateCreditAssessmentScore_API_is_called_F403() throws Throwable {

		String token = retrieveToken(OAUTH2_PARAM_WRONG);

		CreditServiceRequestModel req = CreditServiceRequestModel.builder().numberOfEmployees(1)
				.numberOfYearsOperated(1).companyType("test").build();
		executePost(CREDIT_CALCULATOR_URL, req, token);
	}

	@Then("The client1 receives status code of {int}")
	public void the_client_receives_status_code_of_F403(int response) throws Throwable {
		final int result = latestResponse.getTheResponse().getStatusCode().value();

		assertThat(result, is(response));
	}

	@And("The client1 receives {string}")
	public void the_client1_receives_server_version_body_F403(String failMessage2) throws Throwable {
		final String result = latestResponse.getBody();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(result);
		final String message = rootNode.at("/message").asText();

		assertThat(message, is(failMessage2));
	}

	@When("The calculateCreditAssessmentScore API is called without Bearer Token")
	public void the_calculateCreditAssessmentScore_API_is_called_F401() throws Throwable {

		CreditServiceRequestModel req = CreditServiceRequestModel.builder().numberOfEmployees(1)
				.numberOfYearsOperated(1).companyType("test").build();
		executePost(CREDIT_CALCULATOR_URL, req, null);
	}

	@Then("The client2 receives status code of {int}")
	public void the_client_receives_status_code_of_F401(int response) throws Throwable {
		final int result = latestResponse.getTheResponse().getStatusCode().value();

		assertThat(result, is(response));
	}

	@And("The client2 receives {string}")
	public void the_client1_receives_server_version_body_F401(String failMessage2) throws Throwable {
		final String result = latestResponse.getBody();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(result);
		final String message = rootNode.at("/message").asText();

		assertThat(message, is(failMessage2));
	}

}
