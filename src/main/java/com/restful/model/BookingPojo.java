package com.restful.model;
import java.util.Map;

public class BookingPojo {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private Map<String, String> bookingdates;
    private String additionalneeds;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public Map<String, String> getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(Map<String, String> bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    public static BookingPojo getBookingPojo(String firstname, String lastname, int totalPrice,
                                             boolean depositPaid, Map<String, String> bookingDates, String additionalNeeds) {
        BookingPojo bookingPojo = new BookingPojo();

        bookingPojo.setFirstname(firstname);
        bookingPojo.setLastname(lastname);
        bookingPojo.setTotalprice(totalPrice);
        bookingPojo.setDepositpaid(depositPaid);
        bookingPojo.setBookingdates(bookingDates);
        bookingPojo.setAdditionalneeds(additionalNeeds);

        return bookingPojo;

        }
        }