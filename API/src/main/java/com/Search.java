package com;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import model.constant.constant;
import org.json.simple.JSONObject;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

public class Search {
    @Test
    public void Test_01() {
        JSONObject request = new JSONObject();

        baseURI = constant.BaseURL;

        request.put("type", "2");
        request.put("key", "10000");

        Response response = given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                get("/search");
        System.out.println(response.getBody().asPrettyString());
    }
    private String access_token;
    private String codeResponse;
    private String messageResponse;
    private String dataResponse;


    public void getAccessToken(String email, String password) {
        this.access_token = login.getAccessToken(email, password);
    }

    public String creRequest(String... request1) {
        JSONObject request = new JSONObject();
        request.put("type",request1[0]);
        request.put("key",request1[1]);
        return request.toString();
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
                .when().get("/search" );
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
    public void Search1() {
        System.out.println("\nSearch 1: Tim theo gia khoi diem ");
        String rq=this.creRequest("1","1");
        getAccessToken("van5@gmail.com","123456");
        this.callAPI(rq);
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1000") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void Search2() {
        System.out.println("\nSearch 2: Tim theo thoi gian bat dau ");
        String rq=this.creRequest("2","2022-05-31");
        getAccessToken("van5@gmail.com","123456");
        this.callAPI(rq);
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1000") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void Search3() {
        System.out.println("\nSearch 3: Tim theo thoi diem ket thuc ");
        String rq=this.creRequest("3","2022-05-31");
        getAccessToken("van5@gmail.com","123456");
        this.callAPI(rq);
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1000") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void Search4() {
        System.out.println("\nSearch 4: Tim kiem theo ten cua phien dau gia ");
        String rq=this.creRequest("4","Thor - Love and Thunder");
        getAccessToken("van5@gmail.com","123456");
        this.callAPI(rq);
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1000") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void Search5() {
        System.out.println("\nSearch 5: Type khong trong khoang [1,4] ");
        String rq=this.creRequest("5","Thor - Love and Thunder");
        getAccessToken("van5@gmail.com","123456");
        this.callAPI(rq);
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1001") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void Search6() {
        System.out.println("\nSearch 5: Type is null ");
        String rq=this.creRequest(null,"Thor - Love and Thunder");
        getAccessToken("van5@gmail.com","123456");
        this.callAPI(rq);
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1001") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
}