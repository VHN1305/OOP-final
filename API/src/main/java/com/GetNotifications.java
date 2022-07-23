package com;

import static io.restassured.RestAssured.baseURI;

import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import model.constant.constant;
import org.json.simple.JSONObject;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetNotifications {
    @Test
    public void testGetNotifications()
    {
        String ACCESS_TOKEN = login.getAccessToken();
        JSONObject request = new JSONObject();
        request.put("index","1");
        request.put("count","10");
        request.put("is_not_real", null);
        baseURI = constant.BaseURL;
        Response response = RestAssured.
                given().
                header("Content-Type","application/json").
                header("Authorization","bearer" + ACCESS_TOKEN).
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                get("/notifications");

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
        request.put("index",request1[0]);
        request.put("count",request1[1]);
        request.put("is_not_real", request1[2]);
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
                .when().get("/notifications" );
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
    public void GetNotification1() {
        System.out.println("\nGetNotification: Successfully ");
        String rq=this.creRequest("1","1",null);
        getAccessToken("vanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq);
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1000") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void GetNotification2() {
        System.out.println("\nGetNotification 2: Index Null ");
        String rq=this.creRequest(null,"1",null);
        getAccessToken("vanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq);
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1001") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void GetNotification3() {
        System.out.println("\nGGetNotification 3: Count Null ");
        String rq=this.creRequest("1",null,null);
        getAccessToken("vanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq);
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1001") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void GetNotification() {
        System.out.println("\nGGetNotification 4: Count and index Null ");
        String rq=this.creRequest(null,null,null);
        getAccessToken("vanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq);
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1001") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
}
