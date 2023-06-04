package definitions;

import com.stockwatch.capstone.CapstoneApplication;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.User;
import net.minidev.json.JSONObject;
import org.json.JSONException;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;


@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = CapstoneApplication.class)
public class LoginStepDefinitions {


    private static final String BASE_URL = "http://localhost:";
    private static final int PORT = 8080;

    @LocalServerPort
    String port;

    private static Response response;
    private ResponseEntity<User> responseEntity;

    LocalDate localDate = LocalDate.of(2023, 6, 3);
    LocalDate currentDate = localDate.now();

    User user = new User("Jane", "email100@gmail.com", "password100");

    public String getYourJWT() throws JSONException {
        RequestSpecification request = RestAssured.given();
        org.json.JSONObject jsonObject = new org.json.JSONObject();
        jsonObject.put("email", "email100@gmail.com");
        jsonObject.put("password", "password100");
        request.header("Content-Type", "application/json");
        response = request.body(jsonObject.toString()).post(BASE_URL + port + "/api/auth/users/login");
        return response.jsonPath().getString("message");
    }


    @Given("a registered user")
    public void aRegisteredUser() throws JSONException {
//        User user = new User("Jane", "email100@gmail.com", "password100");
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "email100@gmail.com");
        requestBody.put("password", "password100");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/auth/users/register");
//        Assert.assertEquals(200, response.getStatusCode());



    }



    @When("I enter my valid email and password")
    public void iEnterMyValidEmailAndPassword() {
        JsonPath jsonObject = response.jsonPath();
        String email = "email100@gmail.com";
        String password = "password100";
        boolean isValidEmail = validateEmail(email);
        boolean isValidPassword = validatePassword(password);
        Assert.assertTrue("Email is not valid", isValidEmail);
        Assert.assertTrue("Password is not valid", isValidPassword);
        Assert.assertNotNull(jsonObject);
    }

    private boolean validateEmail(String email) {    // Validate email method
        return email.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.com");
//        return email.matches("email100@gmail.com");
    }

    private boolean validatePassword(String password) {    // Validate password method
        return password.length() >= 8;
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        Assert.assertEquals(200, response.getStatusCode());
//        int statusCode = response.getStatusCode();
//        if (statusCode == 200) {
//            System.out.println("Login successful!");
//        } else {
//            Assert.fail("Unexpected response status code: " + statusCode);
//        }
//    }

//}

//        Assert.assertEquals(200, response.getStatusCode());
//        // Implement the logic to verify the login success or failure
//        int statusCode = response.getStatusCode();
//        if (statusCode == 200) {
//            // Successful login
//            System.out.println("Login successful!");
//            // Additional assertions or actions for successful login can be performed here
//        } else if (statusCode == 401) {
//            // Login failed due to incorrect credentials
//            Assert.fail("Login failed. Incorrect credentials.");
//        } else {
//            // Unexpected response status code
//            Assert.fail("Unexpected response status code: " + statusCode);
//        }
//    }

    }

    @Given("a user has a unique email")
    public void aUserHasAUniqueEmail() {
        try {
            RestAssured.baseURI = BASE_URL;
            RequestSpecification request = RestAssured.given();
            org.json.JSONObject requestBody = new org.json.JSONObject();
            requestBody.put("email", "email100@email.com");
            requestBody.put("password", "password100");
            request.header("Content-Type", "application/json");
            response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/auth/users/login");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }



    }

    @When("they enter their email and password")
    public void theyEnterTheirEmailAndPassword() {
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("email100@gmail.com"));

    }



    }
