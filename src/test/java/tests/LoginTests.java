package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import models.lombok.LoginBodyLombokModel;
import models.pojo.LoginBodyModel;
import models.pojo.LoginResponseModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTests {
    /*
        1. Make request to "https://reqres.in/api/login"
         with body {"email": "eve.holt@reqres.in", "password": "cityslicka"}
        2. Get response {  "token": "QpwL5tke4Pnpja7X4" }
        3. Check "token" is and status code 200
         */

    @Test
    void successfulLoginAllureTest(){
        LoginBodyLombokModel authData = new LoginBodyLombokModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModel response = given()
                .filter(new AllureRestAssured())
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .log().body()
                .log().headers()


                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
    }

    @Test
    void unsuccessfulLogin415Test(){
        given()
                .log().uri()
                .log().body()
                .log().headers()

                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(415);
    }
    @Test
    void unsuccessfulLogin400Test(){
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        given()
                .body(authData)
                .log().uri()
                .log().body()
                .log().headers()


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
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("evwewee.holt@reqres.in");
        authData.setPassword("cityslicka");        given()
                .body(authData)
                .log().uri()
                .log().body()
                .log().headers()

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
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        given()
                .body(authData)
                .log().uri()
                .log().body()
                .log().headers()

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
        LoginBodyModel authData = new LoginBodyModel();
        authData.setPassword("cityslicka");
        given()
                .body(authData)
                .log().uri()
                .log().body()
                .log().headers()

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
