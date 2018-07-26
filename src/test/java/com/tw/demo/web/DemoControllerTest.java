package com.tw.demo.web;

import com.tw.demo.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoControllerTest {
    @LocalServerPort
    int serverPort;

    @Test
    public void hello() {
        String api = "http://localhost:" + serverPort + "/hello";

        get(api).then().body("id", equalTo("U001"));
    }
}
