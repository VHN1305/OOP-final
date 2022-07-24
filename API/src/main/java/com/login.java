package com;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import model.constant.constant;
import org.json.simple.JSONObject;
import org.junit.Test;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

public class login {
    @Test
    public void login(){
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        JSONObject request = new JSONObject();
        request.put("email","vanvanvanvanvanvanvanvan@gmail.com");
        request.put("password","123456");
        httpRequest.body(request.toJSONString());
        Response response = httpRequest.header("Content-Type","application/json").contentType(ContentType.JSON).
                body(request.toJSONString()).when().post("/login");
        ResponseBody body = response.getBody();
        System.out.println(body.asPrettyString());
    }

    public static String getAccessToken(){
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        JSONObject request = new JSONObject();
        request.put("email","vanvanvanvanvanvanvanvan@gmail.com");
        request.put("password","123456");
        httpRequest.body(request.toJSONString());
        Response response = httpRequest.header("Content-Type","application/json").contentType(ContentType.JSON).
                body(request.toJSONString()).when().post("/login");

        JsonPath jp = response.jsonPath();
        LinkedHashMap<String, Object> data = jp.get("data");
        return data.get("access_token").toString();
    }
    public static String getAccessToken(String email, String password){
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        JSONObject request = new JSONObject();
        request.put("email",email);
        request.put("password",password);
        httpRequest.body(request.toJSONString());
        Response response = httpRequest.header("Content-Type","application/json").contentType(ContentType.JSON).
                body(request.toJSONString()).when().post("/login");

        JsonPath jp = response.jsonPath();
        LinkedHashMap<String, Object> data = jp.get("data");
        return data.get("access_token").toString();
    }
    private String access_token;
    private String codeResponse;
    private String messageResponse;
    private String dataResponse;


    public String creRequest(String... request) {
        JSONObject req = new JSONObject();
        req.put("email", request[0]);
        req.put("password", request[1]);
        return req.toString();
    }

    public void callAPI(String request){
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/login");
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
    public void Login1() {
        System.out.println("\nLogin test 1: The email or password is incorrect:");
        String rq= this.creRequest("vanvanvanvanvanvanvanvan@gmail.com","123");
        this.callAPI(rq);
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1002") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
//        assert(rp.message != null && !"".equals(rp.message));
    }
    @Test
    public void Login2() {
        System.out.println("\nLogin test 2: The email is empty, the code shall be 1001:");
        String rq= this.creRequest("","123");
        this.callAPI(rq);
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1001") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
//        assert(rp.message != null && !"".equals(rp.message));
    }
    @Test
    public void Login3() {
        System.out.println("\nLogin test 3: The email and password are correct, the code shall be 1000:");
        String rq= this.creRequest("vanvanvanvanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq);
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1000") && this.messageResponse.equals("OK"))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
//        assert(rp.message != null && !"".equals(rp.message));
    }
    @Test
    public void Login4() {
        System.out.println("\nLogin test 4: The email is the wrong format");
        String rq= this.creRequest("vanvanvanvanvan@hust.edu.com","vdq118");
        this.callAPI(rq);
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1001") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
//        assert(rp.message != null && !"".equals(rp.message));
    }
    @Test
    public void Login5() {
        System.out.println("\nLogin test 5: The email is more than 255 characters");
        String rq= this.creRequest("12300000000000000000000000000000000000000000000000000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111110000000000000000000000000000000000000000000000011"
                ,"vdq118");
        this.callAPI(rq);
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1001") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
//        assert(rp.message != null && !"".equals(rp.message));
    }
    @Test
    public void Login6() {
        System.out.println("\nLogin test 6: The password is null");
        String rq= this.creRequest("vdq118@gmail.com"
                ,"");
        this.callAPI(rq);
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1001") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
//        assert(rp.message != null && !"".equals(rp.message));
    }
    @Test
    public void Login7() {
        System.out.println("\nLogin test 7: The password is more than 255 characters");
        String rq= this.creRequest("ab000011@gmail.com"
                ,"111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111111111111111111111111111111111111111111111111111111");
        this.callAPI(rq);
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1001") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
//        assert(rp.message != null && !"".equals(rp.message));
    }



    public String getDataResponse() {
        return dataResponse;
    }
    public void setDataResponse(String dataResponse) {
        this.dataResponse = dataResponse;
    }
}