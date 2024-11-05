import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class LoginTests {
    /*
        1. Make request to "https://reqres.in/api/login"
         with body {"email": "eve.holt@reqres.in", "password": "cityslicka"}
        2. Get response {  "token": "QpwL5tke4Pnpja7X4" }
        3. Check "token" is and status code 200
         */
    @Test
    void successfulLoginTest(){
        String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";
        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void unsuccessfulLogin415Test(){
        given()
                .log().uri()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(415);
    }
    @Test
    void unsuccessfulLogin400Test(){
        String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";
        given()
                .body(authData)
                .log().uri()

                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    @Test
    void userNotFound400Test(){
        String authData = "{\"email\": \"ev7e.holt@reqres.in\", \"password\": \"cityslicka1\"}";
        given()
                .body(authData)
                .log().uri()
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("user not found"));
    }

    @Test
    void missingPassword400Test(){
        String authData = "{\"email\": \"ev7e.holt@reqres.in\"}";
        given()
                .body(authData)
                .log().uri()
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void missingLogin400Test1(){
        String authData = "{\"password\": \"cityslicka1\"}";
        given()
                .body(authData)
                .log().uri()
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }
}
