package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
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
//1. Extract the All Ids
    public void allId() {
        List<Integer> allId = response.extract().path("id");
        System.out.println("The all Id's are " + allId);
    }

    @Test
//2. Extract the all Names
    public void allName() {
        List<String> allName = response.extract().path("name");
        System.out.println("All names are " + allName);
    }

    @Test
//3. Extract the name of 5 th object
    public void nameOf5() {
        String name = response.extract().path("[4].name");
        System.out.println("The name of 5 th object " + name);
    }

    @Test
//4. Extract the names of all object whose status = inactive
    public void inActiveName() {
        List<String> name = response.extract().path("findAll{it.status = 'inactive'}.name");
        System.out.println("Name of inactive status are " + name);
    }

    @Test
//5. Extract the gender of all the object whose status = active
    public void gender() {
        List<String> Gender = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println("Gennder of active user is " + Gender);
    }

    @Test
//6. Print the names of the object whose gender = female
    public void allFemaleName() {
        List<String> FemaleName = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("All female names are " + FemaleName);
    }

    @Test
//7. Get all the emails of the object where status = inactive
    public void allEmails() {
        List<String> allEmails = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("All email of inactive user are " + allEmails);
    }

    @Test
//8. Get the ids of the object where gender = male
    public void allIds() {
        List<Integer> allIds = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("All Ids of male users are " + allIds);
    }

    @Test
//9. Get all the status
    public void allStatus() {
        List<String> Status = response.extract().path("status");
        System.out.println("Status of all users are " + Status);
    }

    @Test
//10. Get email of the object where name = Shreyashi Kaur
    public void getEmail() {
        String email = response.extract().path("find{it.name == 'Shreyashi Kaur'}.email");
        System.out.println("Email of Shreyashi Kaur is " + email);
    }

    @Test
//11. Get gender of id = 4040687
    public void genderOf() {
        String gender = response.extract().path("find{it.id = 4040687}.gender");
        System.out.println("Gender of id 4040687 is " + gender);
    }
}
