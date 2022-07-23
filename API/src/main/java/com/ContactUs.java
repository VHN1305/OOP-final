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

public class ContactUs {
    @Test
    public void ContactUs(){
        String ACCESS_TOKEN = login.getAccessToken();
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        JSONObject request = new JSONObject();
        request.put("name","Huy");
        request.put("phone","08866079000");
        request.put("email","a==@gmail.com");
        request.put("content","i am the fastest man alive");
        request.put("file",null);
        request.put("report_type",3);
        httpRequest.body(request.toJSONString());
        Response response = httpRequest.given()
                .header("Content-Type","application/json")
                .header("Authorization",("bearer" + ACCESS_TOKEN))
                .contentType(ContentType.JSON)
                .when().post("/contactUs");
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

    public String creRequest(String... requestString) {
        JSONObject request = new JSONObject();
        request.put("name",requestString[0]);
        request.put("phone",requestString[1]);
        request.put("email",requestString[2]);
        request.put("content",requestString[3]);
        request.put("file",requestString[4]);
        request.put("report_type",requestString[5]);
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
                .when().post("/contactUs");
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
    public void ContactUs1() {
        System.out.println("\nContact test 1: Successfully ");
        String rq=this.creRequest("Huy","0886607900","vanvanvanvanvan@gmail.com","i am the fastest man alive",null,"3");
        getAccessToken("vanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq);
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1000") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void ContactUs2() {
        System.out.println("\nContact test 2: Report Type vuot khong phai 1 2 3 ");
        String rq=this.creRequest("Huy","0886607900","vanvanvanvanvan@gmail.com","i am the fastest man alive",null,"0");
        getAccessToken("vanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq);
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1001") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void ContactUs3() {
        System.out.println("\nContact Us test 3: Email null ");
        String rq=this.creRequest("Huy","0886607900",null,"i am the fastest man alive",null,"3");
        getAccessToken("vanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq);
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1001") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void ContactUs4() {
        System.out.println("\nContact Us test 4: Sodienthoai khong dung dinh dang ");
        String rq=this.creRequest("Huy","+840886607900","vanvanvanvanvan@gmail.com","i am the fastest man alive",null,"3");
        getAccessToken("vanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq);
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1001") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
}
