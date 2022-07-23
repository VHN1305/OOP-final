package com;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import model.constant.constant;
import org.json.simple.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.baseURI;

public class GetListMessageOfChat {
    @Test
    public void GetListMessageOfChat(){
        String ACCESS_TOKEN = login.getAccessToken();
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        JSONObject request = new JSONObject();
        httpRequest.body(request.toJSONString());
        Response response = httpRequest.given()
                .header("Content-Type","application/json")
                .header("Authorization",("bearer"+ACCESS_TOKEN))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when().get("/chat/listMessages/16");
        ResponseBody body = response.getBody();
        System.out.println(body.asPrettyString());
    }
    private String access_token;
    public void getAccessToken(String email, String password) {
        this.access_token = login.getAccessToken(email, password);
    }

    @Test
    public void GetListChat()
    {
        System.out.println("Successfully");
        getAccessToken("vanvanvanvanvanvan@gmail.com","123456");
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
                get("/chat/listMessages/16");

        System.out.println(response.getBody().asPrettyString());
    }
}
