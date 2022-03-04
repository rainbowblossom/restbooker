package com.restful.cucumber.steps;

import com.restful.bookinginfo.BookingSteps;
import com.restful.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;
import java.util.Map;

public class MyStepdefs {
    static String firstName = "Dave" + TestUtils.getRandomValue();
    static String lastName = "Ava" + TestUtils.getRandomValue();
    static int totalPrice = 200;
    static boolean depositPaid = true;
    static String checkIn = "2022-02-23";
    static String checkOut = "2022-03-01";
    static String additionalNeeds = "Lunch";
    static int bookingId;
    static String username = "admin";
    static String password = "password123";
    static String token;
    static ValidatableResponse response;

    @Steps
    BookingSteps bookingSteps;
    @When("^User send GET request to see all booking$")
    public void userSendGETRequestToSeeAllBooking() {
        response = bookingSteps.getAllBookingRecords().log().all();
    }
    @Then("^User must get back a valid status code$")
    public void userMustGetBackAValidStatusCode()
    {
        response.statusCode(200);
    }

    @When("^I create new booking by providing firstName, lastName, totalPrice, depositPaid, checkIn,  checkOut, additionalNeeds$")
    public void iCreateNewBookingByProvidingFirstNameLastNameTotalPriceDepositPaidCheckInCheckOutAdditionalNeeds() {
        Map<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2018-01-01");
        bookingdates.put("checkout", "2019-01-01");
        ValidatableResponse response = bookingSteps.createBooking(firstName, lastName, totalPrice, depositPaid,bookingdates , additionalNeeds);
        response.log().all().statusCode(200);

        bookingId = response.extract().path("bookingid");
        System.out.println("Booking : "+bookingId);
    }

    @Then("^I verify that booking is created with firstName, lastName, totalPrice, depositPaid, checkIn,  checkOut, additionalNeeds in database$")
    public void iVerifyThatBookingIsCreatedWithFirstNameLastNameTotalPriceDepositPaidCheckInCheckOutAdditionalNeedsInDatabase()
    {
        bookingSteps.getBookingById(bookingId).statusCode(200);
    }

    @When("^I update booking by providing firstName, lastName, totalPrice, depositPaid, checkIn,  checkOut, additionalNeeds$")
    public void iUpdateBookingByProvidingFirstNameLastNameTotalPriceDepositPaidCheckInCheckOutAdditionalNeeds() {
        Map<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2018-01-01");
        bookingdates.put("checkout", "2019-01-01");

        firstName = firstName + "_updated";
        bookingSteps.updateBooking(username,password,bookingId, firstName, lastName, totalPrice, depositPaid, bookingdates, additionalNeeds).log().all().statusCode(200);
    }
    @Then("^I verify that booking is updated with firstName, lastName, additionalNeeds in database$")
    public void iVerifyThatBookingIsUpdatedWithFirstNameLastNameAdditionalNeedsInDatabase()
    {
        bookingSteps.getBookingById(bookingId).statusCode(200);
    }
    @When("^I delete booking data$")
    public void iDeleteBookingData()
    {
        bookingSteps.deleteBooking(bookingId).statusCode(201);
    }

    @Then("^I verify that same booking data was deleted by getting data by Id$")
    public void iVerifyThatSameBookingDataWasDeletedByGettingDataById()
    {
        bookingSteps.getBookingById(bookingId).statusCode(404);
    }



}