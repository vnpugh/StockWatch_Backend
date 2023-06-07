package definitions;

import com.stockwatch.capstone.CapstoneApplication;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = CapstoneApplication.class)
public class StockStepDefinitions {

    private static Response response;
    private static final String BASE_URL = "http://localhost:";
    private static final int PORT = 8080;




    @LocalServerPort
    String port;
    private final Logger log = LoggerFactory.getLogger(StockStepDefinitions.class);
    String JWT;

    /**
     * Generates a JWT token to pass in header of requests.
     * @return JWT as a String
     * @throws JSONException
     */
//    public String getJWT() throws JSONException {
//        RequestSpecification request = RestAssured.given();
//        org.json.JSONObject jsonObject = new org.json.JSONObject();
//        jsonObject.put("email", "email100@gmail.com");
//        jsonObject.put("password", "password100");
//        request.header("Content-Type", "application/json");
//        response = request.body(jsonObject.toString()).post(BASE_URL + port + "/api/auth/users/login");
//        return response.jsonPath().getString("token");
//    }

    @Given("a new user")
    public void aNewUser() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        org.json.JSONObject requestBody = new org.json.JSONObject();
        requestBody.put("firstName", "Jane");
        requestBody.put("email", "email@email.com");
        requestBody.put("password", "password");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/auth/users/register");
    }













//    <------ User Can Login Test - DONE --->
    @Given("a registered user")
    public void aRegisteredUser() {
            JSONObject requestBody = new JSONObject();
            requestBody.put("firstName", "Jane");
            requestBody.put("email", "email10@gmail.com");
            requestBody.put("password", "password100");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);

            ResponseEntity<String> response = new RestTemplate().exchange(
                    BASE_URL + port + "/api/auth/users/register",
                    HttpMethod.POST,
                    request,
                    String.class
            );

            Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        }

    @When("User enters their email and password")
    public void userEntersTheirEmailAndPassword() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "email10@gmail.com");
        requestBody.put("password", "password100");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/auth/users/login");

    }
    @Then("User logs in successfully")
    public void userLogsInSuccessfully() {
        Assert.assertEquals(200, response.getStatusCode());
    }


    //    <------ User Can Search For Stocks Test --->
    @Given("a logged-in user")
    public void aLoggedInUser() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("password", "123456");
        requestBody.put("email", "test@mail.com");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(requestBody.toString(), headers);
        ResponseEntity<String> response = new RestTemplate().exchange(BASE_URL + port + "/auth/login/", HttpMethod.POST, request, String.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);




        // get JWT token from response body
//        JSONObject responseBody = new JSONObject(response.getBody());
//        authToken = responseBody.getString("message");
//        RestAssured.baseURI = BASE_URL;
//        RequestSpecification request = RestAssured.given();
//        JSONObject requestBody = new JSONObject();
//        requestBody.put("email", "email10@gmail.com");
//        requestBody.put("password", "password100");
//        request.header("Content-Type", "application/json");
//        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/auth/users/login");
    }

    @When("a user searches for stocks by company or symbol")
    public void aUserSearchesForStocksByCompanyOrSymbol() {
        String company = "";
        String symbol = "";
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.queryParam("company", company)
                .queryParam("symbol", symbol)
                .get(BASE_URL + port + "/api/stocks/companyOrSymbol?company=&symbol=");
    }

    @Then("the stocks are displayed")
    public void theStocksAreDisplayed() {
        Assert.assertEquals(200, response.getStatusCode());
    }




    //<-- User Can Add a Stock to Their WatchList -->


    @When("a user adds a stock to their watchlist by symbol")
    public void aUserAddsAStockToTheirWatchlistBySymbol() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("symbol", "AAPL");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString())
                .post(BASE_URL + port + "/api/watchlist/addStock?symbol=&watchlist_id=");
    }

    @Then("the stock is added to the user's watchlist successfully")
    public void theStockIsAddedToTheUserSWatchlistSuccessfully() {
        Assert.assertEquals(201, response.getStatusCode());
    }




    //<-- User Can Delete a Stock from Their WatchList -->

    @When("a user deletes a stock from their watchlist by symbol")
    public void aUserDeletesAStockFromTheirWatchlistBySymbol() throws JSONException {
//        RestAssured.baseURI = BASE_URL + port;
//        RequestSpecification request = RestAssured.given();
//        request.header("Authorization", "Bearer "+ getJWT());
//        response = request.delete("/api/watchlist/deleteStock?symbol=&watchlist_id=");

    }


    @Then("the stock is deleted successfully")
    public void theStockIsDeletedSuccessfully() {
        Assert.assertEquals(204, response.getStatusCode());



    }



}





