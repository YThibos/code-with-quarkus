package be.jcrafters.pastebin;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class DumpRestControllerTest {

    @Test
    public void dumpEndPointPOST() {
        String result = given()
                .when()
                .body("hi")
                .post("/dump")
                .then()
                .statusCode(200)
                .extract().body().asPrettyString();

        System.out.println("result = " + result);
    }

}
