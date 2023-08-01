package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {
    static ValidatableResponse response;

    @Test
    public void verifyUserCreatedSuccessfully() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(getRandomValue() + "Jay");
        userPojo.setEmail(getRandomValue() + "@gmail.com");
        userPojo.setGender("male");
        userPojo.setStatus("active");
        Response response = given()
                .header("Authorization", "Bearer d59b5e8b4c972a46bf739389f5ad7a438aa9243a771cd8eeb1093c7a9b8e9247")
                .header("Content-Type", "application/json")
                .when()
                .body(userPojo)
                .post("/users");
        response.prettyPrint();
        response.then().statusCode(201);
    }

    @Test
    public void verifyUserUpdateSuccessfully() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName("Krishna");
        userPojo.setEmail(getRandomValue() + "@gmail.com");
        userPojo.setGender("male");
        userPojo.setStatus("active");
        Response response = given()
                .header("Authorization", "Bearer d59b5e8b4c972a46bf739389f5ad7a438aa9243a771cd8eeb1093c7a9b8e9247")
                .header("Content-Type", "application/json")
                .when()
                .body(userPojo)
                .put("/users/4135097");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void VerifyUserDeleteSuccessfully() {
        Response response = given()
                .header("Authorization", "Bearer d59b5e8b4c972a46bf739389f5ad7a438aa9243a771cd8eeb1093c7a9b8e9247")
                .header("Connection", "keep-alive")
                .when()
                .delete("/users/4135097");
        response.prettyPrint();
        response.then().statusCode(204);
    }
}
