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

public class EditAuction {
    @Test
    public void EditAuction(){
        String ACCESS_TOKEN = login.getAccessToken();
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        JSONObject request = new JSONObject();
        request.put("category_id",2);
        request.put("start_date",this.startDayOfAuction);
        request.put("end_date",this.endDayOfAuction);
        request.put("title_ni","Thor - Love and Thunder dou-tickets!!!!!!!! ");
        httpRequest.body(request.toJSONString());
        Response response = httpRequest.given()
                .header("Content-Type","application/json")
                .header("Authorization",("bearer" + ACCESS_TOKEN))
                .contentType(ContentType.JSON)
                .when().post("/auctions/edit/1749");
        ResponseBody body = response.getBody();
        System.out.println(body.asPrettyString());
    }
    private String access_token;
    private String codeResponse;
    private String messageResponse;
    private String dataResponse;
    private String startDayOfAuction;
    private String endDayOfAuction;

    public void callAPI(String request, String auctionId) {
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.given()
                .header("Content-Type","application/json")
                .header("Authorization",("bearer" + this.access_token))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when().post("/auctions/edit/" + auctionId);
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
        JSONObject req = new JSONObject();
        req.put("category_id", request[0]);
        req.put("start_date", request[1]);
        req.put("end_date",request[2]);
        req.put("title_ni",request[3]);
        return req.toString();
    }
    public void setStartDayOfAuction(){
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime startDay = today.plusDays(1);
        LocalDateTime endDay = today.plusDays(2);
        this.startDayOfAuction = startDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.endDayOfAuction = endDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    @Test
    public void EditAuction1() {
        setStartDayOfAuction();
        System.out.println("Edit Auction test 1: Auction da duyet");
        getAccessToken("vanvanvanvanvanvanvanvan@gmail.com", "123456");
        String rq= this.creRequest("1",startDayOfAuction,endDayOfAuction,"Thor");
        this.callAPI(rq,"1749");
        System.out.println("Code: " + this.codeResponse + "\nMessage: " + this.messageResponse + "\nData:" + this.dataResponse);
        if(this.codeResponse.equals("1005") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
//        assert(rp.message != null && !"".equals(rp.message));
    }
    @Test
    public void EditAuction2() {
        setStartDayOfAuction();
        System.out.println("Edit Auction test 2: Edit thanh cong");
        getAccessToken("vanvanvanvanvanvanvanvan@gmail.com", "123456");
        String rq= this.creRequest("1",startDayOfAuction,endDayOfAuction,"Thorrrrrrrrrrrrrrrrrrrrrrrrrrrr");
        this.callAPI(rq,"2336");
        System.out.println("Code: " + this.codeResponse + "\nMessage: " + this.messageResponse + "\nData:" + this.dataResponse);
        if(this.codeResponse.equals("1000") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
//        assert(rp.message != null && !"".equals(rp.message));
    }
    @Test
    public void EditAuction3() {
        setStartDayOfAuction();
        System.out.println("Edit Auction test 3: Edit khong thanh cong do khong phai phien dau gia User tao");
        getAccessToken("vanvanvanvanvanvanvanvan@gmail.com", "123456");
        String rq= this.creRequest("1",startDayOfAuction,endDayOfAuction,"Thorrrrrrr");
        this.callAPI(rq,"2133");
        System.out.println("Code: " + this.codeResponse + "\nMessage: " + this.messageResponse + "\nData:" + this.dataResponse);
        if(this.codeResponse.equals("1006") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
//        assert(rp.message != null && !"".equals(rp.message));
    }

}