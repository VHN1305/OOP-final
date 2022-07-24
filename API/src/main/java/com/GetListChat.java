package com;

import static io.restassured.RestAssured.baseURI;

import model.constant.constant;
import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

public class GetListChat {
    private String access_token;
    public void getAccessToken(String email, String password) {
        this.access_token = login.getAccessToken(email, password);
    }

    @Test
    public void GetListChat()
    {
        System.out.println("Successfully");
        getAccessToken("van5@gmail.com","123456");
        JSONObject request = new JSONObject();
        baseURI = constant.BaseURL;
        Response response = RestAssured.
                given().
                header("Content-Type","application/json").
                header("Authorization","bearer" +  this.access_token).
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                get("/chat");

        System.out.println(response.getBody().asPrettyString());
    }
}