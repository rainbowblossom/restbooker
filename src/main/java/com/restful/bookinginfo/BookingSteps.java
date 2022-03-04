package com.restful.bookinginfo;

import com.restful.constants.EndPoints;
import com.restful.constants.Path;
import com.restful.model.BookingPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.Map;


public class BookingSteps {

    @Step("Creating booking with firstName : {1}, lastName: {2}, totalPrice: {3}, depositpaid: {4}, bookingdates: {5} and additionalneeds: {6}")
    public ValidatableResponse createBooking(String firstName, String lastName, int totalPrice, boolean depositPaid, Map<String, String> bookingDates, String additionNeeds ) {
        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstName, lastName, totalPrice, depositPaid,bookingDates, additionNeeds);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(bookingPojo)
                .when()
                .basePath(Path.BOOKING)
                .post()
                .then();
    }

//    @Step("Getting the booking information with firstName: {0}")
//    public HashMap<String, Object> getBookingInfoByFirstname(String firstName) {
//        String p1 = "findAll{it.firstName=='";
//        String p2 = "'}.get(0)";
//        return SerenityRest.given().log().all()
//                .when()
//                .get(EndPoints.GET_ALL_BOOKING)
//                .then()
//                .statusCode(200)
//                .extract()
//                .path(p1 + firstName + p2);
//    }

    @Step("Updating booking information with bookingId: {0},firstName : {1}, lastName: {2}, totalPrice: {3}, depositPaid: {4}, bookingDates: {5} and additionalNeeds: {6}")
    public ValidatableResponse updateBooking(String username,String password, int bookingId, String firstName, String lastName, int totalPrice, boolean depositPaid, Map<String, String> bookingDates, String additionNeeds ) {
        Path.TOKEN=AuthSteps.getToken(username, password);
        System.out.println(Path.TOKEN);

        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstName, lastName, totalPrice, depositPaid,bookingDates, additionNeeds);
        System.out.println(Path.TOKEN);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .header("Cookie", "token="+Path.TOKEN)

                .pathParam("bookingid", bookingId)
                .body(bookingPojo)
                .when()
                .basePath(Path.BOOKING)

                .put(EndPoints.UPDATE_BOOKING_BY_ID)
                .then();
    }

    @Step("Deleting Booking information with bookingId: {0}")
    public ValidatableResponse deleteBooking(int bookingId) {
        return SerenityRest
                .given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .header("Cookie", "token="+ Path.TOKEN)
                .pathParam("bookingid", bookingId)
                .when()
                .delete(Path.BOOKING+EndPoints.DELETE_BOOKING_BY_ID)
                .then();
    }

    @Step("Getting booking information with bookingId: {0}")
    public ValidatableResponse getBookingById(int bookingId) {
        return SerenityRest
                .given()
                .pathParam("bookingid", bookingId)
                .when()
                .basePath(Path.BOOKING)
                .get(EndPoints.GET_SINGLE_BOOKING_BY_ID)
                .then();
    }
    @Step("Getting all booking information")
    public ValidatableResponse getAllBookingRecords() {
        return SerenityRest
                .given()
                .when()
                .get(Path.BOOKING)
                .then();
    }
}