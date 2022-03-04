package com.restful.restfulinfo;


import com.restful.bookinginfo.BookingSteps;
import com.restful.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

@RunWith(SerenityRunner.class)
public class BookingCURDTestWithSteps extends TestBase {

    static String firstname = "Darshana";
    static String lastname = "Akbari";
    static int totalprice = 450;
    static boolean depositpaid = false;
    static String additionalneeds  = "Lunch";
    static int bookingId;

    static String username = "admin";
    static String password = "password123";
    static String tokenSt;

//    @BeforeClass
//    public static void inIT(){
//        RestAssured.basePath = Path.BOOKING;
//    }
    @Steps
BookingSteps bookingSteps;


    @Title("This is will create an booking")
    @Test
    public void test001() {
        Map<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2018-01-01");
        bookingdates.put("checkout", "2019-01-01");

        ValidatableResponse response = bookingSteps.createBooking(firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds);
        response.log().all().statusCode(200);

        bookingId = response.extract().path("bookingid");
        System.out.println("Booking : "+bookingId);
        bookingSteps.getBookingById(bookingId);
    }
    @Title("Update the Booking information and verify the updated information")
    @Test
    public void test003() {
        Map<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2018-01-01");
        bookingdates.put("checkout", "2019-01-01");

        firstname = firstname + "_updated";
        bookingSteps.updateBooking( username, password, bookingId, firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds).log().all().statusCode(200);
        bookingSteps.getBookingById(bookingId).statusCode(200);
    }
    @Title("Delete the Booking and verify if the Booking is deleted!")
    @Test
    public void test004() {
        bookingSteps.deleteBooking(bookingId).statusCode(201);
        bookingSteps.getBookingById(bookingId).statusCode(404);
    }
    }


