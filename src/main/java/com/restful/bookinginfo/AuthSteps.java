package com.restful.bookinginfo;


import com.restful.constants.Path;
import com.restful.model.AuthPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;


public class AuthSteps {
public static String token;
    @Step("Creating user with username: {0}, password: {1}")
    public ValidatableResponse createUser(String username, String password){
        AuthPojo authPojo=AuthPojo.getAuthPojo(username,password);

        return  SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(authPojo)
                .when()
                .basePath(Path.AUTH)
                .post()
                .then();

    }
public static String getToken(String username, String password){
    AuthPojo authPojo = AuthPojo.getAuthPojo(username, password);

    token =  SerenityRest.given().log().all()
            .contentType(ContentType.JSON)
            .body(authPojo)
            .when()
            .basePath(Path.AUTH)
            .post()
            .then()
            .extract()
            .path("token");
    return token;
}
}
