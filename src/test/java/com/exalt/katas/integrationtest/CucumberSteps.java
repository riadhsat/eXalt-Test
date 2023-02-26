package com.exalt.katas.integrationtest;

import static org.assertj.core.api.Assertions.assertThat;

import com.exalt.katas.application.exception.ErrorResponse;
import com.exalt.katas.application.response.RestPageTransaction;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.springframework.boot.test.web.server.LocalServerPort;

public class CucumberSteps {

  private static Response response;
  private static RequestSpecification request;
  @LocalServerPort
  private int port;

  @Before
  public void setUp() {
    RestAssured.baseURI = "http://localhost:" + port;
    RestAssured.basePath = "/";
    RestAssured.port = port;
    request = RestAssured.given();
  }

  @Given("prepare request param avec {string}")
  public void preparedRequestParamAmount(String amount) {
    request.param("amount", amount);
  }

  @Given("prepare request param with page {string} and pageSize {string}")
  public void preparedRequestParamPagination(String page,String pageSize) {
    request.param("page", page).param("pageSize",pageSize);
  }

  @When("call endpoint {string} with {string}")
  public void callEndPoint(String url, String method) {
    switch (method) {
      case "POST" -> response = request.post("/compte" + url);
      case "GET" -> response = request.get("/compte" + url);
    }
  }

  @Then("successful operation with {string}")
  public void shouldHaveASuccessResponse(String message) {
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    assertThat(response.asString()).isEqualTo(message);
  }

  @Then("successful operation with page {int} and totalElement {int}")
  public void shouldHaveASuccessResponseAndTwoTransaction(int page,int total) {
    RestPageTransaction restPageTransactions = response.as(RestPageTransaction.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    assertThat(restPageTransactions).isNotNull();
    assertThat(restPageTransactions.getTotalTransactions()).isEqualTo(total);
    assertThat(restPageTransactions.getPage()).isEqualTo(page);
  }

  @Then("failed operation with {int} and error code {int} and message {string}")
  public void shouldHaveAFailedResponse(int httpStatus, int errorCode, String message) {
    ErrorResponse errorResponse = response.as(ErrorResponse.class);
    assertThat(response.getStatusCode()).isEqualTo(httpStatus);
    assertThat(errorResponse.getErrorCode()).isEqualTo(errorCode);
    assertThat(errorResponse.getErrorMessage()).isEqualTo(message);
  }
}
