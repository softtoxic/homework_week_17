package com.restful.booker.crudtest;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetTest {
    static ValidatableResponse response;

    @Test
    public void getAllBookingIDs() {
        Response response = given()
                .when().get();
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void getSingleBookingID() {
        Response response = given()
                .pathParam("id", 1661)
                .when().get("{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void getPingHealthCheck() {
        Response response = given()
                .when().get("https://restful-booker.herokuapp.com/ping");
        response.then()
                .statusCode(201);
        response.prettyPrint();
    }
}
