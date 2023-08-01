package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users")
                .then().statusCode(200);
    }

    @Test
//1. Verify the if the total record is 20
    public void verifyTotal() {
        response.body("size", equalTo(20));
    }

    @Test
//2. Verify the if the name of id = 4040694 is equal to ”Bhasvan Kapoor”

    public void verifyName() {
        response.body("find{it.id == 4040694}.name", equalTo("Bhasvan Kapoor"));
    }

    @Test
// 3. Check the single ‘Name’ in the Array list (Kama Asan)
    public void checkSingleName() {
        response.body("name", hasItem("Kama Asan"));
    }

    @Test
//4. Check the multiple ‘Names’ in the ArrayList (Ganak Patel, Kama Asan, Bhasvan Kapoor)
    public void checkMultipleNames() {
        response.body("name", hasItems("Kama Asan", "Ganak Patel", "Bhasvan Kapoor"));
    }

    @Test
//5. Verify the emai of userid = 4040691 is equal “chaturbhuj_reddy@quitzon-reichel.test”
    public void verifyEmail() {
        response.body("find{it.id == 4040691}.email", equalTo("chaturbhuj_reddy@quitzon-reichel.test"));
    }

    @Test
//6. Verify the status is “Active” of user name is “Dharani Kocchar”
    public void verifyStatus() {
        response.body("find{it.name == 'Dharani Kocchar'}.status", equalTo("active"));
    }

    @Test
//7. Verify the Gender = male of user name is “Niro Prajapat”
    public void verifyGender() {
response.body("find{it.name == 'Tushar Ahluwalia'}.gender", equalTo("male"));
    }
}
