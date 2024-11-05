import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
public class StatusCodeTest {
    /*
    1. make request to https://selenoid.autotests.cloud/status
    2. get response
    3. check total is 20
     */

   @Test
    void checkTotal20(){
       get("https://selenoid.autotests.cloud/status")
               .then()
               .body("total", is(20));
   }
   @Test
    void checkTotalWithResponseLogs(){
       get("https://selenoid.autotests.cloud/status")
               .then()
               .log().all()
               .body("total", is(20));
   }
   @Test
    void checkTotalWithRequestLogs(){
       given()
               .log().all()
               .get("https://selenoid.autotests.cloud/status")
               .then()
               .log().all()
               .body("total", is(20));
   }
}
