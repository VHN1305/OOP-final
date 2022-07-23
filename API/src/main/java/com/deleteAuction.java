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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class deleteAuction {
    @Test
    public void deleteAuction() {
        String ACCESS_TOKEN = login.getAccessToken();
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();

        JSONObject request = new JSONObject();

        httpRequest.body(request.toJSONString());

        Response response = httpRequest.
                header("Content-Type","application/json").
                header("Authorization",("bearer" + ACCESS_TOKEN)).
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                post("/auctions/deleteAuction/1758");
        ResponseBody body = response.getBody();
        System.out.println(body.asPrettyString());
    }
    private String access_token;
    private String codeResponse;
    private String messageResponse;
    private String dataResponse;


    public void callAPI(String request, String auctionId) {
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.given()
                .header("Authorization",("bearer" + this.access_token))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when().post("/auctions/deleteAuction/" + auctionId);
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
    public void getAccessToken(String email, String password) {
        this.access_token = login.getAccessToken(email, password);
    }

    public String creRequest(String... request) {
        return null;
    }
    @Test
    public void DeleteAuction1() {
        System.out.println("Delete Auction test 1: Auction da duyet");
        getAccessToken("vanvanvanvanvan@gmail.com", "123456");
        String rq= this.creRequest();
        this.callAPI(rq,"1749");
        System.out.println("Code: " + this.codeResponse + "\nMessage: " + this.messageResponse + "\nData:" + this.dataResponse);
        if(this.codeResponse.equals("9994") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
//        assert(rp.message != null && !"".equals(rp.message));
    }
    @Test
    public void DeleteAuction2() {
        System.out.println("Delete Auction test 2: Successfully");
        getAccessToken("vanvanvanvanvan@gmail.com", "123456");
        String rq= this.creRequest();
        this.callAPI(rq,"2133");
        System.out.println("Code: " + this.codeResponse + "\nMessage: " + this.messageResponse + "\nData:" + this.dataResponse);
        if(this.codeResponse.equals("1000") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
//        assert(rp.message != null && !"".equals(rp.message));
    }
    @Test
    public void DeleteAuction3() {
        System.out.println("Delete Auction test 3: Khong co quyen xoa");
        getAccessToken("vanvanvanvanvan@gmail.com", "123456");
        String rq= this.creRequest();
        this.callAPI(rq,"1");
        System.out.println("Code: " + this.codeResponse + "\nMessage: " + this.messageResponse + "\nData:" + this.dataResponse);
        if(this.codeResponse.equals("1000") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
//        assert(rp.message != null && !"".equals(rp.message));
    }
}
