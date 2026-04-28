package org.rest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TestNoBDD {

    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass() {
        requestSpecification = with().
                baseUri("https://api.postman.com/").
                header("x-api-key", "PMAK-69d388a18c35aa0001781056-88fc76b581cad50a170fea601e6e7deec6")
                .log().all();
    }


    @Test
    public void testOne() {
        Response response = requestSpecification.get("/workspaces")
                        .then()
                                .log().all()
                        .extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));
    }

    @Test
    public void testTwo() {
        Response response = requestSpecification.get("/workspaces")
                .then()
                .log().all()
                .extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));
        assertThat(response.path("workspaces[0].name").toString(), equalTo("My Workspace3"));
    }
}
