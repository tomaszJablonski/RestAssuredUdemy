package org.rest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestAutomateGet {

//    @Test
    public void getPositiveAssertBody() {
        given().
                baseUri("https://api.postman.com/").
                header("x-api-key","PMAK-69bd76a781b5b600017f6c53-2c2523ed204eb8972251cec679e2e2e594").
        when().
                get("/workspaces").
        then().
                log().all()
                .assertThat()
                .statusCode(200)
                .body("workspaces*.name", hasItems("My Workspace3"),
                        "workspaces*.type", hasItems("personal"),
                        "workspaces[0].name", is(equalTo("My Workspace3")),
                        "workspaces.size()", equalTo(3),
                        "workspaces.name", hasItem("My Workspace5"));
    }

//    @Test
    public void extract_response() {
        Response response = given().
                baseUri("https://api.postman.com/").
                header("x-api-key","PMAK-69bd76a781b5b600017f6c53-2c2523ed204eb8972251cec679e2e2e594").
        when().
                get("/workspaces").
        then().
                assertThat().statusCode(200).
                extract().response();

        System.out.println(response.asString());
    }

//    @Test
    public void extract_single_value_from_response() {
        Response response =
                given().
                baseUri("https://api.postman.com/").
                header("x-api-key","PMAK-69bd76a781b5b600017f6c53-2c2523ed204eb8972251cec679e2e2e594").

                when().
                get("/workspaces").

                then().
                assertThat().statusCode(200).
                extract().response();

        //JsonPath - RestAssured class
        JsonPath jsonPath = new JsonPath(response.asString());
        System.out.println("JsonPath -> Workspace name = " + jsonPath.getString("workspaces[0].name"));

        //groovy
        System.out.println("Groovy -> Workspace name = " + response.path("workspaces[0].name"));
    }

//    @Test
    public void hamcrest_assert_on_extracted_response() {
        String name =
                given().
                        baseUri("https://api.postman.com/").
                        header("x-api-key","PMAK-69bd76a781b5b600017f6c53-2c2523ed204eb8972251cec679e2e2e594").

                        when().
                        get("/workspaces").

                        then().
                        assertThat().statusCode(200).
                        extract().response().path("workspaces[0].name");

        System.out.println(name);

        assertThat(name, equalTo("My Workspace3"));
        //or
        Assert.assertEquals(name, "My Workspace3");
    }
}
