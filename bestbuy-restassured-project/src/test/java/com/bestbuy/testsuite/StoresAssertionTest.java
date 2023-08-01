package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class StoresAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    @Test
    //1. Verify the if the total is equal to 1561
    public void verifyTheTotal() {
        response.body("total", equalTo(1561));
    }

    @Test
    //2. Verify the if the stores of limit is equal to 10
    public void verifyTheLimit() {
        response.body("limit", equalTo(10));
    }

    @Test
    //3. Check the single ‘Name’ in the Array list (Inver Grove Heights)
    public void checkSingleName() {
        response.body("data.name", hasItem("Inver Grove Heights"));
    }

    @Test
    //4. Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
    public void checkMultipleName() {
        response.body("data.name", hasItems("Roseville", "Burnsville", "Maplewood"));
    }

    @Test
    //5. Verify the storeId=7 inside storeServices of the third store of second services
    public void checkStoreId7() {
        response.body("data[2].services[2].storeservices.storeId", equalTo(7));
    }

    @Test
    //6. Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville
    public void checkValues() {

    }

    @Test
    //7. Verify the state = MN of forth store
    public void verifyState() {
        response.body("data[3].state", equalTo("MN"));
    }

    @Test
    //8. Verify the store name = Rochester of 9th store
    public void verifyStoreName() {
        response.body("data[8].name", equalTo("Rochester"));
    }

    @Test
    //9. Verify the storeId = 11 for the 6th store
    public void verifyStoreId() {
        response.body("data[5].id", equalTo(11));
    }

    @Test
    //10. Verify the serviceId = 4 for the 7th store of forth service
    public void verifyServiceId() {
        response.body("data[6].services[3].storeservices.serviceId", equalTo(4));
    }
}
