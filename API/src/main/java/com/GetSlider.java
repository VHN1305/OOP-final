package com;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.constant.constant;
import org.json.simple.JSONObject;
import org.junit.Test;

public class GetSlider {
    @Test
    public void getSlider() {
        System.out.println("Successfully");
        String ACCESS_TOKEN = login.getAccessToken();

        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        JSONObject request = new JSONObject();

        httpRequest.body(request.toJSONString());
        Response response = httpRequest.given().header("Content-Type", "application/json")
                .header("Authorization", "bearer" + ACCESS_TOKEN)
                .contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().get("/slider");
        System.out.println(response.getBody().asPrettyString());

    }
}
