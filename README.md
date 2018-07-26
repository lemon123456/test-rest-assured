### Mock Server

物料清单：

- node
- npm
- json-server
- json数据文件



#### 安装node

```shell
$ brew install node
```

#### 更新npm

```shell
$ npm i -g npm
```

#### 安装json-server

```shell
$ npm i -g json-server
```

#### 准备数据文件

编写json格式的文件，保存为`data.json`，名称可以任意指定。

```json
{
  "users": [
    {
      "id": 1,
      "name": "James",
      "age": 15
    },
    {
      "id": 2,
      "name": "Julie",
      "age": 18
    },
    {
      "id": 3,
      "name": "Tom",
      "age": 17
    }
  ]
}
```

#### 启动Mock服务

```shell
$ json-server --watch data.json --port 8080
\{^_^}/ hi!

  Loading data.json
  Done

  Resources
  http://localhost:8080/users

  Home
  http://localhost:8080
```

现在可以访问以上API，支持`GET`, ` POST`,`PUT`,`DELETE`等



### JUnit测试代码工程

物料清单：

可连接互联网



#### 快速构建工程

（访问：http://start.spring.io/）

#### 使用IntelliJ构建工程

`New` - `Project ...` - `Spring Initializr` - 选择JDK 8 ，Maven工程，依赖管理中加入`Web`即可 - `Finish`

#### 引入`rest-assured`依赖

编辑`pom.xml`，加入以下依赖

```xml
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>3.1.0</version>
    <scope>test</scope>
</dependency>
```

#### 编写测试

在目录`src/test/java/com/tw/demo/`中新建测试类`RestTest.java`

```java
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

```



