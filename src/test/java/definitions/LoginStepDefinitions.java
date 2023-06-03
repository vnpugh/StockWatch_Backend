package definitions;

import com.jayway.jsonpath.DocumentContext;
import com.stockwatch.capstone.CapstoneApplication;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = CapstoneApplication.class)
public class LoginStepDefinitions {

    private static final String BASE_URL = "http://localhost:";

    private static Response response;

    @LocalServerPort
    String port;

    @Given("a registered user")
    public void aRegisteredUser() throws JSONException {
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "email100@gmail.com");
        requestBody.put("password", "password100");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port +"/api/users/register");
    }

    @When("I enter my valid username and password")
    public void iEnterMyValidUsernameAndPassword() {
        JsonPath jsonObject = new JsonPath(response.asString());
        String email = jsonObject.get("data.user.email");  // Adjust the JSON path if needed
        String password = "password100";

        Assert.assertEquals("email100@gmail.com", email);
        Assert.assertEquals("password100", password);
    }








}