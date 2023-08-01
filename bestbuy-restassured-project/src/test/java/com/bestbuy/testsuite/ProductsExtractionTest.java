package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {
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
//21. Extract the limit
//22. Extract the total
//23. Extract the name of 5th product
//24. Extract the names of all the products
//25. Extract the productId of all the products
//26. Print the size of the data list
//27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4- Pack)
//28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2- Pack)
//29. Get all the categories of 8th products
//30. Get categories of the store where product id = 150115
//31. Get all the descriptions of all the products
//32. Get id of all the all categories of all the products
//33. Find the product names Where type = HardGood
//34. Find the Total number of categories for the product where product name = Duracell - AA
//1.5V CopperTop Batteries (4-Pack)
//35. Find the createdAt for all products whose price < 5.49
//36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
//37. Find the manufacturer of all the products
//38. Find the imge of products whose manufacturer is = Energizer
//39. Find the createdAt for all categories products whose price > 5.99
//40. Find the uri of all the products
}
