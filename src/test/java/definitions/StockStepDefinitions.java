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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;


@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = CapstoneApplication.class)
public class StockStepDefinitions {

    private static Response response;
    private static final String BASE_URL = "http://localhost:";

    @LocalServerPort
    String port;

    private String jwtToken;
    private String watchlistId;
    private String symbol;



    /**
     * Generates a JWT token to pass in header of requests.
     *
     * @return JWT as a String
     * @throws JSONException
     */
    public String jwtToken() throws JSONException {
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "email100@gmail.com");
        requestBody.put("password", "password100");
        request.header("Content-Type", "application/json");
        Response response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/users/login");

        JSONObject responseJson = new JSONObject();
        return responseJson.getAsString("token");


    }





    //    <------ User Can Register Test - DONE --->
    @Given("a new user")
    public void aNewUser() throws JSONException {
    }

    @When("User enters their first name, email and password")
    public void userEntersTheirFirstNameEmailAndPassword() throws JSONException {

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        org.json.JSONObject requestBody = new org.json.JSONObject();
        requestBody.put("firstName", "Test");
        requestBody.put("email", "test@email.com");
        requestBody.put("password", "password");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/auth/users/register");

    }

    @Then("User registration is successful")
    public void userRegistrationIsSuccessful() {
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }


    //    <------ User Can Login Test - DONE --->
    @Given("a registered user")
    public void aRegisteredUser() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("firstName", "Test1");
        requestBody.put("email", "test1@email.com");
        requestBody.put("password", "password1");
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
        requestBody.put("email", "test1@email.com");
        requestBody.put("password", "password1");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/auth/users/login");

    }

    @Then("User logs in successfully")
    public void userLogsInSuccessfully() {
        Assert.assertEquals(200, response.getStatusCode());
    }




    // <--- User can create a custom watchlist Test --->

@Given("a user that is logged in")
public void aUserThatIsLoggedIn() throws JSONException{
        try {

    RequestSpecification request = RestAssured.given();
    request.header("Authorization", "Bearer " + jwtToken);
    JSONObject responseJson = new JSONObject();
    jwtToken = responseJson.toString();
    Assert.assertNotNull(jwtToken);
        } catch (
    HttpClientErrorException e) {
            e.printStackTrace();
        }
}

    @When("the user creates a new watchlist")
    public void theUserCreatesANewWatchlist()  {
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("listName", "My Watchlist");
        requestBody.put("description", "My Watchlist Description");
        requestBody.put("dateCreated", LocalDate.now().toString());
        Response response = request.body(requestBody.toString())
                .post(BASE_URL + port + "/api/watchlist/create");

    }

    @Then("a new watchlist for the user is created successfully")
    public void aNewWatchlistForTheUserIsCreatedSuccessfully() {
        Assert.assertNotNull(String.valueOf(response));
    }






    //    <------ User Can Search & Add A Stock Test --->

    @Given("a user searches for stocks by entering the company name or symbol")
    public void aUserSearchesForStocksByEnteringTheCompanyNameOrSymbol() {
        String company = "";
        String symbol = "";
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.queryParam("company", company)
                .queryParam("symbol", symbol)
                .get(BASE_URL + port + "/api/stocks/companyOrSymbol?company=&symbol=");

    }

    @When("the stocks are displayed on the page")
    public void theStocksAreDisplayedOnThePage() {
        Assert.assertNotNull(response);
    }

    @Then("user can add a stock to their watchlist successfully")
    public void userCanAddAStockToTheirWatchlistSuccessfully() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("symbol", "AAPL");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString())
                .post(BASE_URL + port + "/api/watchlist/addStock?symbol=&watchlist_id=");

    }




    //<-- User Can Delete a Stock from Their WatchList -->

    @When("a user deletes a stock from their watchlist")
    public void aUserDeletesAStockFromTheirWatchlist() throws JSONException {

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + jwtToken());
        String watchlistId = "1";
        String symbol = "AAPL";
        Response response = request.delete(BASE_URL + port + "/api/watchlist/deleteStock/{watchlistId}/{symbol}", watchlistId, symbol);
        this.response = response; }







    @Then("the stock is deleted successfully")
    public void theStockIsDeletedSuccessfully() {
        Assert.assertEquals(204, response.getStatusCode());

    }



}













