package com.restful.booker.crudtest;

import com.restful.booker.model.AuthPojo;
import com.restful.booker.model.BookingPojo;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PostTest {
    @Test
    public void createToken() {
        AuthPojo authPojo = new AuthPojo();
        authPojo.setUsername("admin");
        authPojo.setPassword("password123");
        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(authPojo)
                .post("https://restful-booker.herokuapp.com/auth");
        response.prettyPrint();
        response.then().log().all().statusCode(200);
        // token = "token": "f8649230fe68a63"
    }

    @Test
    public void createBooking() {
        BookingPojo.BookingDates date = new BookingPojo.BookingDates();
        date.setCheckIn("2023-09-01");
        date.setCheckOut("2023-10-01");
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname("Kalu");
        bookingPojo.setLastname("Kappu");
        bookingPojo.setTotalprice("700");
        bookingPojo.setDepositpaid(true);
        bookingPojo.setBookingdates(date);
        bookingPojo.setAdditionalneeds("Breakfast");
        Response response = given().log().all()
                .header("Authorization", "token=f8649230fe68a63")
                .header("Content-Type", "application/json")
                .when()
                .body(bookingPojo)
                .post("https://restful-booker.herokuapp.com/booking");
        response.prettyPrint();
        response.then().log().all().statusCode(200);

    }
}
