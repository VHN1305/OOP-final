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

import java.util.LinkedHashMap;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class changePass {
    @Test
    public void changePass(){
        String ACCESS_TOKEN = login.getAccessToken();
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        JSONObject request = new JSONObject();
        request.put("old_pass","123456");
        request.put("new_pass","123456");
        request.put("re_pass","123456");
        httpRequest.body(request.toJSONString());
        Response response = httpRequest.given()
                .header("Content-Type","application/json")
                .header("Authorization",("bearer" + ACCESS_TOKEN))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when().post("/changepass");
        ResponseBody body = response.getBody();
        System.out.println(body.asPrettyString());
    }
    private String codeResponse;
    private String messageResponse;
    private String dataResponse;
    private String access_token;

    public void getAccessToken(String email, String password) {
        this.access_token = login.getAccessToken(email, password);
    }

    public String creRequest(String... request) {
        JSONObject req = new JSONObject();
        req.put("old_pass", request[0]);
        req.put("new_pass", request[1]);
        req.put("re_pass",request[2]);
        return req.toString();
    }

    public void callAPI(String request) {
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.given()
                .header("Content-Type","application/json")
                .header("Authorization",("bearer" + this.access_token))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when().post("/changepass");
        JsonPath jp = response.jsonPath();
        int code = jp.get("code");
        this.codeResponse = code + "";
        String message = jp.get("message");
        this.messageResponse = message;
        Object data = jp.get("data");
        if (data == null){
            data = "null";
        }
        else {
            this.dataResponse = jp.get("data").toString();
        }
    }

    @Test
    public void ChangePass1() {
        System.out.println("\nChange Pass 1: Successfully, code 1000");
        this.getAccessToken("vanvanvanvanvan@gmail.com","123456");
        String rq= this.creRequest("123456","123456","123456");
        this.callAPI(rq);
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1000") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void ChangePass2() {
        System.out.println("\nChange Pass 2: Re_Pass != New_Pass, code 1000");
        this.getAccessToken("vanvanvanvanvan@gmail.com","123456");
        String rq= this.creRequest("123456","123456   ","123456");
        this.callAPI(rq);
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1001") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
}
