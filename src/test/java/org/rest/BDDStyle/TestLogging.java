package org.rest.BDDStyle;

import io.restassured.config.LogConfig;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class TestLogging {

    @Test
    public void testLogging() {
        given().
                baseUri("https://api.postman.com/").
                header("x-api-key", "PMAK-69bd76a781b5b600017f6c53-2c2523ed204eb8972251cec679e2e2e594").
                log().all().

                when().
                get("/workspaces").

                then().
                log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testLogging2() {
        given().
                baseUri("https://api.postman.com/").
                header("x-api-key", "PMAK-69bd76a781b5b600017f6c53-2c2523ed204eb8972251cec679e2e2e594").
                log().headers().

                when().
                get("/workspaces").

                then().
                log().body()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testLoggingIfError() {
        given().
                baseUri("https://api.postman.com/")
                .header("x-api-key", "PMAK-69bd76a781b5b600017f6c53-2c2523ed204eb8972251cec679e2e2e594")
                .log().headers()

                .when()
                .get("/workspaces")

                .then()
                .log().ifError()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testLoggingBlackListHeader() {
        Set<String> headers = new HashSet<>();
        headers.add("x-api-key");
        headers.add("Accept");

        given().
                baseUri("https://api.postman.com/").
                header("x-api-key", "PMAK-69bd76a781b5b600017f6c53-2c2523ed204eb8972251cec679e2e2e594").
                config(config.logConfig(LogConfig.logConfig().blacklistHeader("x-api-key"))).
                config(config.logConfig(LogConfig.logConfig().blacklistHeaders(headers))).
                log().all()

                .when().
                get("/workspaces").

                then().
                log().ifValidationFails()
                .assertThat()
                .statusCode(200);
    }
}