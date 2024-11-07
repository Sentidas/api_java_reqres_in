package specs;
import static heplers.CustomAllureListener.withCustomTemplates;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;


import static io.restassured.RestAssured.with;

public class Spec {

    public static RequestSpecification requestSpec = with()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .log().uri()
            .log().body()
            .log().headers();

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification missingPasswordResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .build();
}


