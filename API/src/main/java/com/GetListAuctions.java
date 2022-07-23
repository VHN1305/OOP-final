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

public class GetListAuctions {
    @Test
    public void GetListAuctions(){
        String ACCESS_TOKEN = login.getAccessToken();
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        JSONObject request = new JSONObject();
        request.put("index", "1");
        request.put("count", "100");
        httpRequest.body(request.toJSONString());
        Response response = httpRequest.given()
                .header("Content-Type","application/json")
                .header("Authorization",("bearer" + ACCESS_TOKEN))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when().get("/auctions/7");
        ResponseBody body = response.getBody();
        System.out.println(body.asPrettyString());
    }
    private String codeResponse;
    private String messageResponse;
    private String dataResponse;

    public void callAPI(String request, String statusId){
        RestAssured.baseURI = constant.BaseURL;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.given()
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when().get("/auctions/" + statusId);
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
    public String creRequest(String... request){
        JSONObject req = new JSONObject();
        req.put("index",request[0]);
        req.put("count",request[1]);
        return req.toString();
    }
    @Test
    public void Test1() {
        System.out.println("\nGet_list_auctions test 1: all auctions");
        String rq = creRequest("1","1");
        this.callAPI(rq,"0");
        System.out.println("Code: " + this.codeResponse + "\nMessage: " + this.messageResponse + "\nData:" + this.dataResponse);
        if(codeResponse.equals("1000") && !messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void Test2() {
        System.out.println("\nGet_list_auctions test 2: Dang dien ra");
        String rq = creRequest("1","1");
        this.callAPI(rq,"1");
        System.out.println("Code: " + this.codeResponse + "\nMessage: " + this.messageResponse + "\nData:" + this.dataResponse);
        if(codeResponse.equals("1000") && !messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void Test3() {
        System.out.println("\nGet_list_auctions test 3: Sap dien ra");
        String rq = creRequest("1","1");
        this.callAPI(rq,"2");
        System.out.println("Code: " + this.codeResponse + "\nMessage: " + this.messageResponse + "\nData:" + this.dataResponse);
        if(codeResponse.equals("1000") && !messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void Test4() {
        System.out.println("\nGet_list_auctions test 1: Da ket thuc");
        String rq = creRequest("1","1");
        this.callAPI(rq,"3");
        System.out.println("Code: " + this.codeResponse + "\nMessage: " + this.messageResponse + "\nData:" + this.dataResponse);
        if(codeResponse.equals("1000") && !messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void Test5() {
        System.out.println("\nGet_list_auctions test 4: Dang cho duyet");
        String rq = creRequest("1","1");
        this.callAPI(rq,"4");
        System.out.println("Code: " + this.codeResponse + "\nMessage: " + this.messageResponse + "\nData:" + this.dataResponse);
        if(codeResponse.equals("1000") && !messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void Test6() {
        System.out.println("\nGet_list_auctions test 5: Bi tu choi");
        String rq = creRequest("1","1");
        this.callAPI(rq,"5");
        System.out.println("Code: " + this.codeResponse + "\nMessage: " + this.messageResponse + "\nData:" + this.dataResponse);
        if(codeResponse.equals("1000") && !messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
    @Test
    public void Test7() {
        System.out.println("\nGet_list_auctions test 6: Da ban thanh cong");
        String rq = creRequest("1","1");
        this.callAPI(rq,"6");
        System.out.println("Code: " + this.codeResponse + "\nMessage: " + this.messageResponse + "\nData:" + this.dataResponse);
        if(codeResponse.equals("1000") && !messageResponse.equals(""))
            System.out.println("Finished! Satisfied!");
        else System.out.println("Fail");
    }
}
