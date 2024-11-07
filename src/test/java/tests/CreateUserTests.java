package tests;

import com.github.javafaker.Faker;
import models.lombok.CreateBodyModel;
import models.lombok.CreateResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.Spec.requestSpec;
import static specs.Spec.responseSpec;

@DisplayName("Create user tests")
public class CreateUserTests extends BaseTest {

    @Test
    void successCreateUser() {
        CreateBodyModel userData = new CreateBodyModel();
        Faker faker = new Faker();
        userData.setName(faker.name().firstName());
        userData.setJob(faker.job().position());

        CreateResponseModel response = step("Make request create user", ()->
                given(requestSpec)
                .body(userData)
                .when()
                .post("/users")
                .then()
                .spec(responseSpec)
                .statusCode(201)
                .extract().as(CreateResponseModel.class));

        step("Check name in response", ()->
                assertEquals(response.getName(), userData.getName()));
        step("Check job in response", ()->
                assertEquals(response.getJob(),userData.getJob()));
        step("Check id in response is exist", ()->
                assertThat(response.getId(), notNullValue()));
    }

    @Test
    void createUserWithoutData() {
        CreateResponseModel response = step("Make request create user without name and job", ()->
                given(requestSpec)
                .when()
                .post("/users")
                .then()
                .spec(responseSpec)
                .statusCode(201)
                .extract().as(CreateResponseModel.class));

        step("Check id in response", ()->
                assertThat(response.getId(), notNullValue()));
        step("Check doesn't name in response", ()->
                assertThat(response.getName(), nullValue()));
        step("Check doesn't job in response", ()->
                assertThat(response.getJob(), nullValue()));
    }
}
