package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    @Test
//11. Verify the if the total is equal to 51957
    public void verifyTotal() {
        response.body("total", equalTo(51957));
    }

    @Test
//12. Verify the if the stores of limit is equal to 10
    public void verifyLimit() {
        response.body("limit", equalTo(10));
    }

    @Test
//13. Check the single ‘Name’ in the Array list (Duracell - AAA Batteries (4-Pack))
    public void checkSingleName() {
        response.body("data.name", hasItem("Duracell - AAA Batteries (4-Pack)"));
    }

    @Test
//14. Check the multiple ‘Names’ in the ArrayList (Duracell - AA 1.5V CopperTop Batteries (4-Pack), Duracell - AA Batteries (8-Pack), Energizer - MAX Batteries AA (4-Pack))
    public void checkMultipleName() {
        response.body("data.name", hasItems("Duracell - AA 1.5V CopperTop Batteries (4-Pack)", "Duracell - AA Batteries (8-Pack)", "Energizer - MAX Batteries AA (4-Pack)"));
    }

    @Test
//15. Verify the productId=150115 inside categories of the third name is “Household Batteries”
    public void verifyCategories() {
        response.body("data[3].id", equalTo(150115));
    }

    //16. Verify the categories second name = “Housewares” of productID = 333179
    @Test
    public void verifySecondName() {
        response.body("data[2].categories[1].name", equalTo("Housewares"));
    }

    @Test
//17. Verify the price = 4.99 of forth product
    public void verifyPrice() {
        response.body("data[3].price", equalTo(4.99f));
    }

    @Test
//18. Verify the Product name = Duracell - D Batteries (4-Pack) of 6th product
    public void productName() {
        response.body("data[5].name", equalTo("Duracell - D Batteries (4-Pack)"));
    }

    @Test
//19. Verify the ProductId = 333179 for the 9th product
    public void verifyProductId() {
        response.body("data[8].id", equalTo(333179));
    }

    @Test
//20. Verify the productId = 346575 have 5 categories
    public void verifyCategory() {
        response.body("data[9].categories", contains("5 id"));
    }
}
