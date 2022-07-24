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

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class getListLikes {
    @Test
    public void getListLikes() {
        String ACCESS_TOKEN = login.getAccessToken();
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();

        JSONObject request = new JSONObject();
        request.put("index", 1);
        request.put("count", 100);

        httpRequest.body(request.toJSONString());

        Response response = httpRequest.
                header("Content-Type","application/json").
                header("Authorization",("bearer" + ACCESS_TOKEN)).
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                get("/likes/0");
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

    public String creRequest(String... request1) {
        JSONObject request = new JSONObject();
        request.put("index", request1[0]);
        request.put("count", request1[1]);
        return request.toString();
    }

    public void callAPI(String request, String StatucID) {
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.given()
                .header("Content-Type","application/json")
                .header("Authorization",("bearer" + this.access_token))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when().get("/likes/" + StatucID);
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
    public void GetListLike1() {
        System.out.println("\nGetListLike 1: Successfully ");
        String rq=this.creRequest("1","1");
        getAccessToken("vanvanvanvanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, "0");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1000") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void GetListLike2() {
        System.out.println("\nGetListLike 2: Index Null ");
        String rq=this.creRequest(null,"1");
        getAccessToken("vanvanvanvanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, "0");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1001") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void GetListLike3() {
        System.out.println("\nGetListLike 3: Count Null ");
        String rq=this.creRequest("1",null);
        getAccessToken("vanvanvanvanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, "0");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1001") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void GetListLike4() {
        System.out.println("\nGetListLike 3: Count and index Null ");
        String rq=this.creRequest(null,null);
        getAccessToken("vanvanvanvanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, "0");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1001") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
}