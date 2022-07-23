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

public class logout {
    @Test
    public void logout(){
        String ACCESS_TOKEN = login.getAccessToken();
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        JSONObject request = new JSONObject();
        httpRequest.body(request.toJSONString());
        Response response = httpRequest.given()
                .header("Content-Type","application/json")
                .header("Authorization",("bearer" + ACCESS_TOKEN))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when().post("/logout");
        ResponseBody body = response.getBody();
        System.out.println(body.asPrettyString());
    }
    private String codeResponse;
    private String messageResponse;
    private String dataResponse;
    private String access_token;

    public void getAccessToken(String email, String password) {
        this.access_token = login.getAccessToken(email, password);
    }

    public String creRequest(String... request) {
        return null;
    }

    public void callAPI(String access_token) {
        baseURI = constant.BaseURL;
        Response response =
                given()

                        .header("Authorization", "Bearer" + access_token)
                        .when()
                        .post("/logout");
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
    public void Logout1() {
        System.out.println("\nLogout test 1: Log out");
        this.getAccessToken("vanvanvanvanvan@gmail.com", "123456");
        this.callAPI(this.access_token);
        System.out.println("Code: " + this.codeResponse + "\nMessage: " + this.messageResponse + "\nData:" + this.dataResponse);
        if (this.codeResponse.equals("1000") && !this.messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
}
