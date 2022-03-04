package com.restful.restfulinfo;


import com.restful.bookinginfo.AuthSteps;
import com.restful.constants.Path;
import com.restful.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class AuthCRUDTestWithSteps extends TestBase {

    static String username = "admin";
    static String password = "password123";
    static String token;
@Steps
AuthSteps authSteps;

@Title("This will create an user details and Auth info")
    @Test
    public void test001(){
    ValidatableResponse response = authSteps.createUser(username, password);
    response.log().all().statusCode(200);
    token = response.extract().path("token");
    System.out.println(token);
    Path.TOKEN=token;
    System.out.println("Token Const: "+ Path.TOKEN);
}
}
