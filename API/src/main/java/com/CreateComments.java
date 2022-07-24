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
import java.util.LinkedHashMap;

public class CreateComments {
    @Test
    public void CreateComment() {

        String ACCESS_TOKEN = login.getAccessToken();
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();

        JSONObject request = new JSONObject();
        request.put("content","trong da thay uy tin");
        request.put("comment_last_id",null);

        httpRequest.body(request.toJSONString());
        Response response = httpRequest.given().
                header("Content-Type", "application/json").
                header("Authorization",("bearer" + ACCESS_TOKEN)).
                contentType(ContentType.JSON).
                when().
                post("/comments/create/2156");
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

    public String creRequest(String... request) {
        JSONObject req = new JSONObject();
        req.put("content", request[0]);
        req.put("comment_last_id", request[1]);
        return req.toString();
    }

    public void callAPI(String request, String AuctionId) {
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.given()
                .header("Content-Type","application/json")
                .header("Authorization",("bearer" + this.access_token))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when().post("/comments/create/" + AuctionId);
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
    public void CreateComment1() {
//        getAuctionID();
        System.out.println("\nCreateComment test 1: Successfully ");
        String rq=this.creRequest("Hoang Thanh Thang Long 1","4");
        getAccessToken("vanvanvanvanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, "1749");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1000") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
//        assert(rp.message != null && !"".equals(rp.message));
    }
    @Test
    public void CreateComment2() {
        System.out.println("\nCreateComment test 2: Phien dau gia chua duoc duyet ");
        String rq=this.creRequest("Hoang Thanh Thang Long 1",null);
        getAccessToken("vanvanvanvanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, "2134");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1008") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void CreateComment3() {
        System.out.println("\nCreateComment test 3: Content is null ");
        String rq=this.creRequest("",null);
        getAccessToken("vanvanvanvanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, "1749");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1003") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
}