package definitions;

import com.stockwatch.capstone.CapstoneApplication;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
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
    private static final int PORT = 8080;

    @LocalServerPort
    String port;

    private static Response response;

    @Given("a registered user")
    public void aRegisteredUser() throws JSONException {
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "email100@gmail.com");
        requestBody.put("password", "password100");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/users/register");

    }




    @When("I enter my valid email and password")
    public void iEnterMyValidEmailAndPassword() {
        String email = "email100@gmail.com";
        String password = "password100";
        boolean isValidEmail = validateEmail(email);
        boolean isValidPassword = validatePassword(password);
        Assert.assertTrue("Email is not valid", isValidEmail);
        Assert.assertTrue("Password is not valid", isValidPassword);
    }

    private boolean validateEmail(String email) {    // Validate email method
        return email.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.com");
    }

    private boolean validatePassword(String password) {    // Validate password method
        return password.length() >= 8;
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            System.out.println("Login successful!");
        } else if (statusCode == 401) {
            Assert.fail("Login failed. Incorrect credentials.");
        } else {
            Assert.fail("Unexpected response status code: " + statusCode);
        }
    }
}