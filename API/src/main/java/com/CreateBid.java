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

public class CreateBid {
    @Test
    public void CreateBid(){
        String ACCESS_TOKEN = login.getAccessToken();
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        JSONObject request = new JSONObject();
        request.put("price","300000000000");
        request.put("bid_last_id",null);

        httpRequest.body(request.toJSONString());
        Response response = httpRequest.given()
                .header("Content-Type","application/json")
                .header("Authorization",("bearer" + ACCESS_TOKEN))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
//                .body(request)
                .when().post(("/bids/create/1749"));
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
        req.put("price",request[0]);
        req.put("bid_last_id",request[1]);
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
                .when().post("/bids/create/" + AuctionID);
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
    public void CreatBid1() {
        System.out.println("\nCreatBid test 1: Successfully ");
        String rq=this.creRequest("699000000000",null);
        getAccessToken("vanvanvanvanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, "1749");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1000") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void CreatBid2() {
        System.out.println("\nCreatBid test 2: Tra gia khong lon hon gia cao nhat hien tai ");
        String rq=this.creRequest("650000000000",null);
        getAccessToken("vanvanvanvanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, "1749");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1001") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void CreatBid3() {
        System.out.println("\nCreatBid test 3: Tra gia cho phien dau gia khong ton tai");
        String rq=this.creRequest("650000000000",null);
        getAccessToken("vanvanvanvanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, "10000");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1001") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void CreatBid4() {
        System.out.println("\nCreatBid test 4: Phien dau gia da het han");
        String rq=this.creRequest("750000000000",null);
        getAccessToken("vanvanvanvanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, "1");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1008") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
}