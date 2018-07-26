package com.tw.demo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

public class RestTest {
    @Test
    public void should_response_status_200() {
        String api = "http://www.4001961200.com/";
        get(api).then().statusCode(200);
    }

    @Test
    public void should_response_status_is_200_and_body_containt_json() {
        String api = "http://localhost:8080/users/2";
        get(api).then().statusCode(200).body("name", equalTo("Julie"));
    }

    @Test
    public void should_post_response_200() {
        RestAssured.baseURI = "http://localhost:8080";
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.body("{\n" +
                "  \"name\": \"Lucy\",\n" +
                "  \"age\": 60\n" +
                "}");

        Response response = request.post("/users");

        response.then().statusCode(201).body("name", equalTo("Lucy"));
    }
}
