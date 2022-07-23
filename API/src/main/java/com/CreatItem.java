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

import java.net.StandardSocketOptions;

public class CreatItem {
    private String access_token;
    private String codeResponse;
    private String messageResponse;
    private String dataResponse;
    private String auctionID;
    private String randomSeries;

    @Test
    public void RandomSeries() {
        double randomDouble = Math.random();
        randomDouble = randomDouble * 10000000 + 1;
        int randomInt = (int) randomDouble;
        this.randomSeries = randomInt+"";
    }
    @Test
    public void getAuctionID(){
        CreateAuction auction = new CreateAuction();
        CreateAuction.RandomAuctionName randomAuctionName = new CreateAuction.RandomAuctionName();
        this.auctionID = auction.getAuctionId(1, randomAuctionName.getStringWithFixedLength(10));
//        System.out.println(this.auctionID);
    }

    @Test
    public void CreatItem(){
        String ACCESS_TOKEN = login.getAccessToken();
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();

        JSONObject request = new JSONObject();
        request.put("name","Banh Ga Tong Giang");
        request.put("starting_price",20);
        request.put("brand_id",1);
        request.put("description","ao zl");
        request.put("series","1000000");
        request.put("images",null);

        httpRequest.body(request.toJSONString());
        Response response = httpRequest.given().
                header("Content-Type", "application/json").
                header("Authorization",("bearer" + ACCESS_TOKEN)).
                contentType(ContentType.JSON).
                when().
                post("/items/create/2158");
        ResponseBody body = response.getBody();
        System.out.println(body.asPrettyString());
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
    public void callAPI(String request, String auctionId) {
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.given()
                .header("Content-Type","application/json")
                .header("Authorization",("bearer" + this.access_token))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when().post("/items/create/" + auctionId);
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
    public void CreatItem1() {
//        getAuctionID();
        System.out.println("\nCreatItem test 1: Phien dau gia da ton tai item ");
        String rq=this.creRequest("Hoang Thanh Thang Long 1","2","3","Great","11",null);
        getAccessToken("vanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, "2134");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("9995") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
//        assert(rp.message != null && !"".equals(rp.message));
    }
    @Test
    public void CreatItem2() {
        System.out.println("\nCreatItem test 2: AuctionID khong tton tai");
        String rq=this.creRequest("Hoang Thanh Thang Long 1","2","3","Great","11",null);
        getAccessToken("vanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, "10000");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("9996") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
//        assert(rp.message != null && !"".equals(rp.message));
    }
    @Test
    public void CreatItem3() {
        getAuctionID();
        RandomSeries();
        System.out.println("\nCreatItem test 3: Successfully");
        String rq=this.creRequest("Hoang Thanh Thang Long 1","1000","3","Great",this.randomSeries,null);
        getAccessToken("vanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, this.auctionID);
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1000") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
//        assert(rp.message != null && !"".equals(rp.message));
    }

    @Test
    public void CreatItem4() {
        RandomSeries();
        getAuctionID();
        System.out.println("\nCreatItem test 4: Gia khoi diem am, but code is 1000");
        String rq=this.creRequest("Hoang Thanh Thang Long 1","-2","3","Great",this.randomSeries,null);
        getAccessToken("vanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, this.auctionID);
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1000") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
//        assert(rp.message != null && !"".equals(rp.message));
    }
}
