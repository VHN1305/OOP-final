package com;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import model.constant.constant;
import org.json.simple.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class LikeAuction {
    @Test
    public void LikeAuction(){
        String ACCESS_TOKEN = login.getAccessToken();
        JSONObject request = new JSONObject();

        baseURI = constant.BaseURL;
        Response response = given().
                header("Content-Type","application/json").
                header("Authorization","bearer" + ACCESS_TOKEN).
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                post("/updateLike/1749");
        ResponseBody body = response.getBody();
        System.out.println(body.asPrettyString());
    }
    private String access_token;
    private String codeResponse;
    private String messageResponse;
    private String dataResponse;


    public void getAccessToken(String email, String password) {
        this.access_token = login.getAccessToken(email, password);
    }

    public String creRequest(String... requestString) {
        return null;
    }

    public void callAPI(String request, String AuctionID) {
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.given()
                .header("Content-Type","application/json")
                .header("Authorization",("bearer" + this.access_token))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
//                .body(request)
                .when().post("/updateLike/" + AuctionID);
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
    public void LikeAuction1() {
        System.out.println("\nLike Auction test 1: Successfully ");
        String rq=this.creRequest();
        getAccessToken("vanvanvanvanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq,"1749");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1000") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void LikeAuction2() {
        System.out.println("\nLike Auction test 1: Auction chua duyet");
        String rq=this.creRequest();
        getAccessToken("vanvanvanvanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq,"2166");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1000") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void LikeAuction3() {
        System.out.println("\nLike Auction test 3: Auction khong ton tai");
        String rq=this.creRequest();
        getAccessToken("vanvanvanvanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq,"10000");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1001") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
}