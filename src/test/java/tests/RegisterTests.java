package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

public class RegisterTests {

    @Test
    void successRegisterTest() {
        given()
                .log().all()
                .body("{\n" +
                        "    \"email\": \"elen.holt@reqres.in\",\n" +
                        "    \"password\": \"prima\"\n" +
                        "}")
                .contentType(JSON)
                .when()
                        .post("https://reqres.in/api/users")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("id", is(4));

    }

    @Test
    void login() {
        given()
                .body("{\n" +
                        "    \"email\": \"elen.holt@reqres.in\",\n" +
                        "    \"password\": \"prima\"}")
                .log().all()
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().all();

    }

    @Test
    void getDataUser(){
        given()
                .log().all()
                .body("{\n" +
                "    \"name\": \"morpheus123\",\n" +
                "    \"job\": \"workerss\"\n" +
                "}")
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users/559")
                        .then()
                .log().all()
                        .body("data.id", is(82));
    }

    @Test
    void createUser(){
        String id;
        given()
                .body("{\n" +
                        "    \"name\": \"morpheus123\",\n" +
                        "    \"job\": \"worker\"\n" +
                        "}")
                .log().all()
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
              //  .body("id", hasKey(id))
                .body("job", hasKey("workers"));

    }
}
