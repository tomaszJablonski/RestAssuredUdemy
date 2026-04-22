package org.rest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestHamcrest {

    @Test
    public void hamcrestContains() {
        given().
                baseUri("https://api.postman.com/").
                header("x-api-key","PMAK-69bd76a781b5b600017f6c53-2c2523ed204eb8972251cec679e2e2e594").
                when().
                get("/workspaces").
                then().
                log().all()
                .assertThat()
                .statusCode(200)
                .body("workspaces.name", contains("My Workspace3", "My Workspace4", "My Workspace5"),
                        "workspaces.name", containsInAnyOrder("My Workspace4", "My Workspace3", "My Workspace5"),
                        "workspaces.name", is(not(empty())),  //czy json jest pusty lub nie
                        "workspaces.name", is(not(emptyArray())), // czy lista jest pusta czy nie
                        "workspaces.name", hasSize(3), // jaki ma rozmiar dany element
                        "workspaces.name", everyItem(startsWith("My")), // czy wszystkie name zaczynają się na My
                        "workspaces[0]", hasKey("id"), // czy mapa workspaces posiada klucz id
                        "workspaces[0]", hasValue("My Workspace3"), // czy pierwsza mapa posiada wartość My Workspace3
                        "workspaces[0]", hasEntry("name", "My Workspace3"), // sprawdza parę klucz i wartość do niej
                        "workspaces[0]", is(not(equalTo(Collections.EMPTY_MAP))), // sprawdzay czy kolekcja jest lub nie jest pusta
                        "workspaces[0].name", allOf(startsWith("My"), containsString("Workspace")), //wszystko zaczyna sie na My i zawiera tekst Workspace
                        "workspaces[0].name", anyOf(startsWith("My"))); // // każdy zaczyna się na My
    }
//    https://javadoc.io/doc/org.hamcrest/hamcrest/latest/index.html
// pozostałe metody
//    Numbers:
//    greaterThanOrEqualTo()
//    lessThan()
//    lessThanOrEqualTo()
//
//    String:
//    containsString()
//    emptyString()

}
