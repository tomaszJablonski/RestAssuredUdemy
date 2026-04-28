package org.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TestNoBDD {

    @BeforeClass
    public void beforeClass() {
//        requestSpecification = with().
//                baseUri("https://api.postman.com/").
//                header("x-api-key", "PMAK-69d388a18c35aa0001781056-88fc76b581cad50a170fea601e6e7deec6")
//                .log().all();

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("https://api.postman.com/")
                .addHeader("x-api-key", "PMAK-69d388a18c35aa0001781056-88fc76b581cad50a170fea601e6e7deec6")
                .log(LogDetail.ALL);

        RestAssured.requestSpecification = requestSpecBuilder.build();
    }

    @Test
    public void queryTest(){
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(RestAssured.requestSpecification);
        System.out.println(queryableRequestSpecification.getBaseUri());
        System.out.println(queryableRequestSpecification.getHeaders());
    }

    @Test
    public void testOne() {
        Response response = get("/workspaces")
                .then()
                .log().all()
                .extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));
    }

    @Test
    public void testTwo() {
        Response response = get("/workspaces")
                .then()
                .log().all()
                .extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));
        assertThat(response.path("workspaces[0].name").toString(), equalTo("My Workspace3"));
    }
}