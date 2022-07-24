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

public class EditItem {
    @Test
    public void EditItem(){
        String ACCESS_TOKEN = login.getAccessToken();
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        JSONObject request = new JSONObject();
        request.put("name","May anh 80s");
        request.put("starting_price",300);
        request.put("brand_id",1);
        request.put("description","may ngon lam dooooo");
        request.put("series","90");
        request.put("images",null);
        httpRequest.body(request.toJSONString());
        Response response = httpRequest.given()
                .header("Content-Type","application/json")
                .header("Authorization",("bearer" + ACCESS_TOKEN))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when().post("/items/edit/466");
        ResponseBody body = response.getBody();
        System.out.println(body.asPrettyString());
    }
    private String access_token;
    private String codeResponse;
    private String messageResponse;
    private String dataResponse;
    private String randomSeries;

    @Test
    public void RandomSeries() {
        double randomDouble = Math.random();
        randomDouble = randomDouble * 10000000 + 1;
        int randomInt = (int) randomDouble;
        this.randomSeries = randomInt+"";
    }


    public void getAccessToken(String email, String password) {
        this.access_token = login.getAccessToken(email, password);
    }

    public String creRequest(String... request) {
        JSONObject req = new JSONObject();
        req.put("name", request[0]);
        req.put("starting_price", request[1]);
        req.put("brand_id", request[2]);
        req.put("description", request[3]);
        req.put("series", request[4]);
        req.put("images", request[5]);
        return req.toString();
    }

    public void callAPI(String request, String itemId) {
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.given()
                .header("Content-Type","application/json")
                .header("Authorization",("bearer" + this.access_token))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when().post("/items/edit/" + itemId);
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
    CreateAuction.RandomAuctionName AuctionName = new CreateAuction.RandomAuctionName();

    @Test
    public void EditItem2() {
//        getAuctionID();
        System.out.println("\nCreatItem test 2: Phien dau gia da duoc duyet");
        String rq=this.creRequest("Hoang Thanh Thang Long 1","2","3","Great","11",null);
        getAccessToken("vanvanvanvanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, "466");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1005") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
//        assert(rp.message != null && !"".equals(rp.message));
    }
    @Test
    public void EditItem1() {
//        getAuctionID();
        RandomSeries();
        System.out.println("\nCreatItem test 1: Successfully");
        String rq=this.creRequest("Hoang Thanh Thang Long 1","2","3","Great",this.randomSeries,null);
        getAccessToken("vanvanvanvanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, "510");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("9995") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
//        assert(rp.message != null && !"".equals(rp.message));
    }
}