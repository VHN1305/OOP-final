package com;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.constant.constant;
import org.json.simple.JSONObject;
import org.junit.Test;

public class GetListComments {
    @Test
    public void GetListComments(){
            JSONObject request = new JSONObject();

            baseURI = constant.BaseURL;

            request.put("index", "1");
            request.put("count", "20");

            String accessToken = login.getAccessToken();
            Response response = given().
                    header("Content-Type","application/json").
                    header("Authorization","bearer"+accessToken).
                    contentType(ContentType.JSON).
                    accept(ContentType.JSON).
                    body(request.toJSONString()).
                    when().
                    get("/comments/1749");
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
        request.put("index", request1[0]);
        request.put("count", request1[1]);
        return request.toString();
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
                .when().get("/comments/" + AuctionID);
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
    public void GetListComment1() {
        System.out.println("\nGetListComment 1: Successfully ");
        String rq=this.creRequest("1","1");
        getAccessToken("vanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, "1749");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1000") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void GetListComment2() {
        System.out.println("\nGetListComment 2: Index Null ");
        String rq=this.creRequest(null,"1");
        getAccessToken("vanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, "1749");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1001") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void GetListComment3() {
        System.out.println("\nGetListComment 3: Count Null ");
        String rq=this.creRequest("1",null);
        getAccessToken("vanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, "1749");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1001") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void GetListComment4() {
        System.out.println("\nGetListComment 3: Count and index Null ");
        String rq=this.creRequest(null,null);
        getAccessToken("vanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, "1749");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1001") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
}
