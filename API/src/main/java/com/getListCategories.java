package com;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import model.constant.constant;
import org.json.simple.JSONObject;
import org.junit.Test;

public class getListCategories {
    @Test
    public void getlistcategories() {
        System.out.println("Succesfully");
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();

        JSONObject request = new JSONObject();
        httpRequest.body(request.toJSONString());
        Response response = httpRequest.given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                when().
                get("/categories");
        ResponseBody body = response.getBody();
        System.out.println(body.asPrettyString());
    }
}
