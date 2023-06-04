package definitions;


import com.stockwatch.capstone.CapstoneApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.User;
import org.json.JSONException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = CapstoneApplication.class)
public class WatchListStepDefinitions {


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














}
