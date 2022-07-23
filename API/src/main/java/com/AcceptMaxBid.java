package com;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.constant.constant;
import org.json.simple.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class AcceptMaxBid {
    @Test
    public void AcceptMaxBid(){
        JSONObject request = new JSONObject();

        RestAssured.baseURI = constant.BaseURL;

        request.put("selling_info", "3");

        String accessToken = login.getAccessToken();
        Response response = given().
                header("Content-Type","application/json").
                header("Authorization","bearer"+accessToken).
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                post("/accept/1749");
        System.out.println(response.getBody().asPrettyString());
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
        req.put("selling_info",request[0]);
        return req.toString();
    }

    public void callAPI(String request, String AuctionID) {
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.given()
                .header("Content-Type","application/json")
                .header("Authorization",("bearer" + this.access_token))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when().post("/accept/" + AuctionID);
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
    public void AccepMaxBid1() {
        System.out.println("\nAccepMaxBid test 1: Phien dau gia chua ket thuc ");
        String rq=this.creRequest("Bi lua roi!!!");
        getAccessToken("vanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, "1749");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1009") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void AccepMaxBid2() {
        System.out.println("\nAccepMaxBid test 2: Khong co quyen ");
        String rq=this.creRequest("Bi lua roi!!!");
        getAccessToken("vanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, "1");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1006") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void AccepMaxBid3() {
        System.out.println("\nAccepMaxBid test 3: Successfully ");
        String rq=this.creRequest("Bi lua roi!!!");
        getAccessToken("vanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, "2156");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1009") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
}
