package definitions;

import com.stockwatch.capstone.CapstoneApplication;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.User;
import net.minidev.json.JSONObject;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = CapstoneApplication.class)
public class StockStepDefinitions {

    private final Logger log = LoggerFactory.getLogger(StockStepDefinitions.class);
    private static final String BASE_URL = "http://localhost:";
    private static final int PORT = 8080;

    @LocalServerPort
    String port;

    private static Response response;
    private ResponseEntity<User> responseEntity;


    @When("I enter my valid email and password")
    public void iEnterMyValidEmailAndPassword() {
    }
    @Given("a registered user")
    public void aRegisteredUser() throws JSONException {
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "email100@gmail.com");
        requestBody.put("password", "password100");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/auth/users/register");

    }





    //<-- WatchList -->

    @When("the user adds a stock to their watchlist by symbol")
    public void theUserAddsAStockToTheirWatchlistBySymbol() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("symbol", "AAPL");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString())
                .post(BASE_URL + port + "/api/watchlist/stocks/1");
    }


    @Then("the stock is added to the user's watchlist")
    public void theStockIsAddedToTheUserSWatchlist() {
        Assert.assertEquals(201, response.getStatusCode());
    }
}
