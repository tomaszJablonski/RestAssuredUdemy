package org.rest;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

public class NoBDD {

    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass() {
        requestSpecification = with().
                baseUri("https://api.postman.com/").
                header("x-api-key", "PMAK-69d388a18c35aa0001781056-88fc76b581cad50a170fea601e6e7deec6");
    }


    @Test
    public void testOne() {
        given().spec(requestSpecification).

                when().
                get("/workspaces").

                then().
                assertThat().statusCode(200);

    }

    @Test
    public void testTwo() {
        given().spec(requestSpecification).

                when().
                get("/workspaces").

                then().
                assertThat().statusCode(200);

    }
}
