package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .queryParam("page", "1")
                .queryParam("per_page", "25")
                .get("/posts")
                .then().statusCode(200);
    }
    @Test
//1. Verify the if the total record is 25
    public void verifyTotal(){
        response.body("size", equalTo(25));
    }
    @Test
//2. Verify the if the title of id = 56992 is equal to ”Defetiscor eius labore utrum deporto.”
    public void titleIs(){
        response.body("find{it.id == 56992}.title", equalTo("Defetiscor eius labore utrum deporto."));
    }@Test
//3. Check the single user_id in the Array list (5522)
            public void singleId() {
        response.body("user_id", hasItem(4040734));
    }
    @Test
//4. Check the multiple ids in the ArrayList (56992, 56978,56965)
    public void multipleIds(){
        response.body("id", hasItems(56992, 56978, 56965));
    }
    @Test
//5. Verify the body of userid = 4040702
public void body(){
       // response.body("find{it.user_id == 4040692}.body", equalTo("Tamen creptio ulterius. Pecus spes tamdiu. Calcar cruentus error. Auctus tubineus adduco. Denuncio decipio comitatus. Admitto celer degero. Fugit facilis defero. Torqueo asper tamen. Argentum deprecator theca. Ait celebrer ab. Doloremque arma dolore. Animi ut aveho. Voluptatem speciosus aut."));
    }
}
