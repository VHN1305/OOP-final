package com;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import model.constant.constant;
import org.json.simple.JSONObject;
import org.junit.Test;

public class UploadFile {
    private String access_token;
    private String codeResponse;
    private String messageResponse;
    private String dataResponse;
    @Test
    public void UploadFile(){
        RestAssured.baseURI = constant.BaseURL;
        JSONObject request = new JSONObject();
        request.put("email","vdq@gmail.com");
        request.put("password","123456");
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post("/login");
        ResponseBody body = response.getBody();
        System.out.println(body.asPrettyString());
    }
}
