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

import javax.lang.model.element.NestingKind;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Random;

public class CreateAuction {
    @Test
    public void CreateAuction(){
        setStartDayOfAuction();
        String ACCESS_TOKEN = login.getAccessToken();
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        JSONObject request = new JSONObject();
        request.put("category_id",1);
        request.put("start_date",this.startDayOfAuction);
        request.put("end_date",this.endDayOfAuction);
        request.put("title_ni","Thor - Love and Thunder duo - tickets");
        httpRequest.body(request.toJSONString());
        Response response = httpRequest.given()
                .header("Content-Type","application/json")
                .header("Authorization",("bearer" + ACCESS_TOKEN))
                .contentType(ContentType.JSON)
                .when().post("/auctions/create");
        ResponseBody body = response.getBody();
        System.out.println(body.asPrettyString());
    }
    private String access_token;
    private String codeResponse;
    private String messageResponse;
    private String dataResponse;
    private String startDayOfAuction;
    private String endDayOfAuction;

    @Test
    public String getAuctionId(int CategoryId, String title) {
        setStartDayOfAuction();
        String token = login.getAccessToken();
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        JSONObject request = new JSONObject();
        request.put("category_id",CategoryId);
        request.put("start_date",this.startDayOfAuction);
        request.put("end_date",this.endDayOfAuction);
        request.put("title_ni",title);
        httpRequest.body(request.toJSONString());
        Response response = httpRequest.given()
                .header("Content-Type","application/json")
                .header("Authorization",("bearer" + token))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when().post("/auctions/create");
        JsonPath jp = response.jsonPath();
        LinkedHashMap<String, Object> data = jp.get("data");
        return data.get("auction_id").toString();
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

    public void callAPI(String request) {
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.given()
                .header("Content-Type","application/json")
                .header("Authorization",("bearer" + this.access_token))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when().post("/auctions/create");
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
    RandomAuctionName AuctionName = new RandomAuctionName();
    public void setStartDayOfAuction(){
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime startDay = today.plusDays(1);
        LocalDateTime endDay = today.plusDays(2);
        this.startDayOfAuction = startDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.endDayOfAuction = endDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void Test1() {
        String randomNameofAuction = AuctionName.getStringWithFixedLength(10);
        setStartDayOfAuction();
        System.out.println("\nCreate_auctions test 1: access data");
        this.getAccessToken("vanvanvanvanvan@gmail.com", "123456");
        String rq = this.creRequest("1", this.startDayOfAuction, this.endDayOfAuction, randomNameofAuction);
        this.callAPI(rq);
        System.out.println("Code: " + this.codeResponse + "\nMessage: " + this.messageResponse + "\nData:" + this.dataResponse);

        if(codeResponse.equals("1000") && !messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }


    @Test
    public void Test2() {
        setStartDayOfAuction();
        System.out.println("\nCreate_auctions test 2: title exist");
        this.getAccessToken("vanvanvanvanvan@gmail.com", "123456");
        String rq = this.creRequest("1", this.startDayOfAuction, this.endDayOfAuction, "Thor - Love and Thunder duo - tickets");
        this.callAPI(rq);
        System.out.println("Code: " + this.codeResponse + "\nMessage: " + this.messageResponse + "\nData:" + this.dataResponse);
        if(codeResponse.equals("1001") && !messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void Test3() {
        String randomNameofAuction = AuctionName.getStringWithFixedLength(10);
        setStartDayOfAuction();
        System.out.println("\nCreate_auctions test 3: category_id null");
        this.getAccessToken("vanvanvanvanvan@gmail.com", "123456");
        String rq = this.creRequest("", this.startDayOfAuction, this.endDayOfAuction, randomNameofAuction);
        this.callAPI(rq);
        System.out.println("Code: " + this.codeResponse + "\nMessage: " + this.messageResponse + "\nData:" + this.dataResponse);
        if(codeResponse.equals("1001") && !messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void Test4() {
        String randomNameofAuction = AuctionName.getStringWithFixedLength(10);
        setStartDayOfAuction();
        System.out.println("\nCreate_auctions test 4: start date null");
        this.getAccessToken("vanvanvanvanvan@gmail.com", "123456");
        String rq = this.creRequest("1", "", this.endDayOfAuction, randomNameofAuction);
        this.callAPI(rq);
        System.out.println("Code: " + this.codeResponse + "\nMessage: " + this.messageResponse + "\nData:" + this.dataResponse);
        if(codeResponse.equals("1001") && !messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void Test5() {
        String randomNameofAuction = AuctionName.getStringWithFixedLength(10);
        System.out.println("\nCreate_auctions test 5: start date befor present time");
        this.getAccessToken("vanvanvanvanvan@gmail.com", "123456");
        String rq = this.creRequest("1", "2022-1-1", "2023-3-1", randomNameofAuction);
        this.callAPI(rq);
        System.out.println("Code: " + this.codeResponse + "\nMessage: " + this.messageResponse + "\nData:" + this.dataResponse);
        if(codeResponse.equals("1001") && !messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void Test6() {
        String randomNameofAuction = AuctionName.getStringWithFixedLength(10);
        System.out.println("\nCreate_auctions test 6: end date null");
        this.getAccessToken("vanvanvanvanvan@gmail.com", "123456");
        String rq = this.creRequest("1", "2023-1-1", "", randomNameofAuction);
        this.callAPI(rq);
        System.out.println("Code: " + this.codeResponse + "\nMessage: " + this.messageResponse + "\nData:" + this.dataResponse);
        if(codeResponse.equals("1001") && !messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test

    public void Test7() {
        String randomNameofAuction = AuctionName.getStringWithFixedLength(10);
        System.out.println("\nCreate_auctions test 7: end date earlier than start date");
        this.getAccessToken("vanvanvanvanvan@gmail.com", "123456");
        String rq = this.creRequest("1", "2023-1-2", "2023-1-1", randomNameofAuction);
        this.callAPI(rq);
        System.out.println("Code: " + this.codeResponse + "\nMessage: " + this.messageResponse + "\nData:" + this.dataResponse);
        if(codeResponse.equals("1001") && !messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void Test8() {
        System.out.println("\nCreate_auctions test 8: title null");
        this.getAccessToken("vanvanvanvanvan@gmail.com", "123456");
        String rq = this.creRequest("1", "2023-1-1", "2023-3-1", "");
        this.callAPI(rq);
        System.out.println("Code: " + this.codeResponse + "\nMessage: " + this.messageResponse + "\nData:" + this.dataResponse);
        if(codeResponse.equals("1001") && !messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void Test9() {
        String randomNameofAuction = AuctionName.getStringWithFixedLength(10);
        System.out.println("\nCreate_auctions test 9: start date incorrect format");
        this.getAccessToken("vanvanvanvanvan@gmail.com", "123456");
        String rq = this.creRequest("1", "2023-13-1", "2023-3-1", randomNameofAuction);
        this.callAPI(rq);
        System.out.println("Code: " + this.codeResponse + "\nMessage: " + this.messageResponse + "\nData:" + this.dataResponse);
        if(codeResponse.equals("1001") && !messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void Test10() {
        String randomNameofAuction = AuctionName.getStringWithFixedLength(10);
        setStartDayOfAuction();
        System.out.println("\nCreate_auctions test 10: CateGory_Id is out of 1 and 5");
        this.getAccessToken("vanvanvanvanvan@gmail.com", "123456");
        String rq = this.creRequest("6", this.startDayOfAuction, this.endDayOfAuction, randomNameofAuction);
        this.callAPI(rq);
        System.out.println("Code: " + this.codeResponse + "\nMessage: " + this.messageResponse + "\nData:" + this.dataResponse);
        if(codeResponse.equals("1001") && !messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    public static class RandomAuctionName {
        public String getSaltString() {
            String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            StringBuilder salt = new StringBuilder();
            Random rnd = new Random();
            while (salt.length() < 10) { // length of the random string.
                int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                salt.append(SALTCHARS.charAt(index));
            }
            String saltStr = salt.toString();
            return saltStr;
        }

        public String getStringWithFixedLength(int n) {
            // chose a Character random from this String
            String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

            // create StringBuffer size of AlphaNumericString
            StringBuilder sb = new StringBuilder(n);

            for (int i = 0; i < n; i++) {
                // generate a random number between
                // 0 to AlphaNumericString variable length
                int index = (int) (AlphaNumericString.length() * Math.random());

                // add Character one by one in end of sb
                sb.append(AlphaNumericString.charAt(index));
            }
            return sb.toString();
        }

    }
}
