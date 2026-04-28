package org.rest;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestHeaders {

    @Test
    public void multipleHeaders() {
        Header header = new Header("header", "value2");
        Header mockHeader = new Header("x-mock-match-request-headers", "header");

        given().

                baseUri("https://91c5ce28-3d83-40ec-a116-b68859835702.mock.pstmn.io")
                .header(header)
                .header(mockHeader)

                .when()
                .get("/get")


                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void multipleHeadersUsingHeaders() {
        Header header = new Header("header", "value2");
        Header mockHeader = new Header("x-mock-match-request-headers", "header");

        Headers headers = new Headers(header,mockHeader);

        given().

                baseUri("https://91c5ce28-3d83-40ec-a116-b68859835702.mock.pstmn.io")
                .headers(headers)

                .when()
                .get("/get")


                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void multipleHeadersUsingHashMap() {

        HashMap<String, String> headers = new HashMap<>();
        headers.put("header", "value2");
        headers.put("x-mock-match-request-headers", "header");

        given().

                baseUri("https://91c5ce28-3d83-40ec-a116-b68859835702.mock.pstmn.io")
                .headers(headers)

                .when()
                .get("/get")


                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void multivalueHeadersUsingHashMap() {

        Header header1 = new Header("multiValueHeader", "value1");
        Header header2 = new Header("multiValueHeader", "value2");

        Headers headers = new Headers(header1,header2);

        given().

                baseUri("https://91c5ce28-3d83-40ec-a116-b68859835702.mock.pstmn.io")
                .headers(headers)
//                .header("multiValueHeader", "value1", "value2")
                .log().headers()

                .when()
                .get("/get")


                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void assertResponseHeaders() {

        HashMap<String, String> headers = new HashMap<>();
        headers.put("header", "value1");
        headers.put("x-mock-match-request-headers", "header");

        given().

                baseUri("https://91c5ce28-3d83-40ec-a116-b68859835702.mock.pstmn.io")
                .headers(headers)
                .log().headers()

                .when()
                .get("/get")


                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .headers("responseHeader", "resValue1",
                        "Content-Type", "application/json; charset=utf-8");
    }

    @Test
    public void extractHeaders() {

        HashMap<String, String> headers = new HashMap<>();
        headers.put("header", "value1");
        headers.put("x-mock-match-request-headers", "header");

        Headers extractedHeaders = given().

                baseUri("https://91c5ce28-3d83-40ec-a116-b68859835702.mock.pstmn.io")
                .headers(headers)
                .log().headers()

                .when()
                .get("/get")


                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .headers();

        for (Header header: extractedHeaders) {
            System.out.println(header.getName());
            System.out.println(header.getValue());
        }

        System.out.println("-----------------------------------------------------------");

        System.out.println(extractedHeaders.get("responseHeader").getName());
        System.out.println(extractedHeaders.get("responseHeader").getValue());
        System.out.println(extractedHeaders.getValue("responseHeader"));
    }

    @Test
    public void multiValueExtractHeaders() {

        HashMap<String, String> headers = new HashMap<>();
        headers.put("header", "value1");
        headers.put("x-mock-match-request-headers", "header");

        Headers extractedHeaders = given().

                baseUri("https://91c5ce28-3d83-40ec-a116-b68859835702.mock.pstmn.io")
                .headers(headers)
                .log().headers()

                .when()
                .get("/get")


                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .headers();

        List<String> listOfHeaders = extractedHeaders.getValues("multiValueHeader");
        for (String value: listOfHeaders) {
            System.out.println(value);
        }
    }
}