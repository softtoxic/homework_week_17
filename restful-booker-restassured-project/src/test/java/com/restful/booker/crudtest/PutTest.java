package com.restful.booker.crudtest;

import com.restful.booker.model.BookingPojo;
import com.restful.booker.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PutTest extends TestBase {

    @Test
    public void updateCurrentBooking() {
        BookingPojo.BookingDates date = new BookingPojo.BookingDates();
        date.setCheckIn("2023-09-01");
        date.setCheckOut("2023-09-07");
        BookingPojo BookingPojo = new BookingPojo();
        BookingPojo.setFirstname("Preet");
        BookingPojo.setLastname("Patel");
        BookingPojo.setTotalprice("198");
        BookingPojo.setDepositpaid(true);
        BookingPojo.setBookingdates(date);
        BookingPojo.setAdditionalneeds("Birthday");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .pathParam("id", 151)
                .body(BookingPojo)
                .when().put("{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

}
