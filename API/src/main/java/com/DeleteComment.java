package com;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.constant.constant;
import org.json.simple.JSONObject;
import org.junit.Test;

public class DeleteComment {
    @Test
    public void DeleteComment() {
        JSONObject request = new JSONObject();

        baseURI = constant.BaseURL;

        String accessToken = login.getAccessToken();
        Response response = given().
                header("Content-Type","application/json").
                header("Authorization","bearer"+accessToken).
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                post("/comments/delete/975");
        System.out.println(response.getBody().asPrettyString());
    }

    private String access_token;
    private String codeResponse;
    private String messageResponse;
    private String dataResponse;


    public void getAccessToken(String email, String password) {
        this.access_token = login.getAccessToken(email, password);
    }

    public String creRequest(String... request) {
        return null;
    }

    public void callAPI(String request, String CommentId) {
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.given()
                .header("Content-Type","application/json")
                .header("Authorization",("bearer" + this.access_token))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
//                .body(request)
                .when().post("/comments/delete/" + CommentId);
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
    public void DeleteComment1() {
        System.out.println("\nDelete Comment 1: Successfully ");
        String rq=this.creRequest();
        getAccessToken("vanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, "1104");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1000") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void DeleteComment2() {
        System.out.println("\nDelete Comment 2: Xoa comment cua user khac trong phien dau gia cua minh ");
        String rq=this.creRequest();
        getAccessToken("vanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, "1082");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1000") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void DeleteComment3() {
        System.out.println("\nDelete Comment 3: Xoa comment cua user khac");
        String rq=this.creRequest();
        getAccessToken("vanvanvanvanvan@gmail.com","123456");
        this.callAPI(rq, "1089");
        System.out.println("Code: "+this.codeResponse+"\nMessage: "+this.messageResponse+"\nData:"+this.dataResponse);
        if(this.codeResponse.equals("1001") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
}
