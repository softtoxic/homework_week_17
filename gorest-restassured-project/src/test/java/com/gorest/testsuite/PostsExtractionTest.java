package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .queryParam("page", "1")
                .queryParam("per_page", "25")
                .when()
                .get("/posts")
                .then().statusCode(200);
    }

    @Test
//1. Extract the title
    public void title() {
        List<String> title = response.extract().path("title");
        System.out.println("The title is " + title);
    }

    @Test
    //2. Extract the total number of record
    public void totalRecord() {
        List<Integer> record = response.extract().path("id");
        int size = record.size();
        System.out.println("Total records are " + size);
    }

    @Test
//3. Extract the body of 15th record
    public void body() {
        String body = response.extract().path("[14].body");
        System.out.println("Body of 15 th record is " + body);
    }
    @Test
//4. Extract the user_id of all the records
    public void allId(){
        List<Integer> allId = response.extract().path("user_id");
        System.out.println("Ids of all user are "+ allId);
    }
    @Test
//5. Extract the title of all the records
    public void allTitle(){
        List<String> allTitle = response.extract().path("title");
        System.out.println("The title is " + allTitle);
    }
    @Test
//6. Extract the title of all records whose user_id = 4040734
    public void titltOfId(){
        String titles = response.extract().path("find{it.user_id = 4040734}.title");
        System.out.println(titles);
    }
    @Test
//7. Extract the body of all records whose id = 56992
    public void bodyOfId(){
        String body = response.extract().path("find{it.id = 56992}.body");
        System.out.println(body);
    }
}
