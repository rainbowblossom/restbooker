package com.restful.restfulinfo;

import com.restful.model.BookingPojo;
import com.restful.testbase.TestBase;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class CreateBooking extends TestBase {
    @Test
    public void createABooking() {
        Map<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2018-01-01");
        bookingdates.put("checkout", "2019-01-01");

        BookingPojo bookingPojo = new BookingPojo();

        bookingPojo.setFirstname("Archana");
        bookingPojo.setLastname("Patel");
        bookingPojo.setTotalprice(101);
        bookingPojo.setDepositpaid(true);
        bookingPojo.setBookingdates(bookingdates);
        bookingPojo.setAdditionalneeds("Lunch");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(bookingPojo)
                .when()
                .post("/booking");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void createABookingA() {
        Map<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2018-01-01");
        bookingdates.put("checkout", "2019-01-01");

        BookingPojo bookingPojo = new BookingPojo();

        bookingPojo.setFirstname("Archana");
        bookingPojo.setLastname("Patel");
        bookingPojo.setTotalprice(101);
        bookingPojo.setDepositpaid(true);
        bookingPojo.setBookingdates(bookingdates);
        bookingPojo.setAdditionalneeds("Lunch");

        ValidatableResponse response = given()
                .header("Content-Type", "application/json")
                .body(bookingPojo)
                .when()
                .post("/booking")
                .then().statusCode(200).log().all();
        int bookingId=response.extract().path("bookingid");
        System.out.println(bookingId);
        //response.then().statusCode(200);
//        response.prettyPrint();
    }
    @Test
    public void createABookingB() {
        Map<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2018-01-01");
        bookingdates.put("checkout", "2019-01-01");

        BookingPojo bookingPojo = new BookingPojo();

        bookingPojo.setFirstname("Archana");
        bookingPojo.setLastname("Patel");
        bookingPojo.setTotalprice(101);
        bookingPojo.setDepositpaid(true);
        bookingPojo.setBookingdates(bookingdates);
        bookingPojo.setAdditionalneeds("Lunch");

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .body(bookingPojo)
                .when()
                .post("/booking")
                .then().statusCode(200).log().all();
    }
}
