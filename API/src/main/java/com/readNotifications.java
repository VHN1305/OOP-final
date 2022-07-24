package com;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import model.constant.constant;
import org.json.simple.JSONObject;
import org.junit.Test;


public class readNotifications {
    private String ACCESS_TOKEN;
    public void getAccessToken(String email, String password) {
        this.ACCESS_TOKEN = login.getAccessToken(email, password);
    }
    @Test
    public void ReadNotifications() {
        System.out.println("Successfully");
        RestAssured.baseURI = constant.BaseURL;
        getAccessToken("van5@gmail.com","123456");
        RequestSpecification httpRequest = RestAssured.given();

        JSONObject request = new JSONObject();

        httpRequest.body(request.toJSONString());

        Response response = httpRequest.
                header("Content-Type","application/json").
                header("Authorization",("bearer" + this.ACCESS_TOKEN)).
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                get("/notifications/read/2332");
        ResponseBody body = response.getBody();
        System.out.println(body.asPrettyString());
    }
}
