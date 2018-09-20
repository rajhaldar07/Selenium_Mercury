package com.rajhaldar;

import com.google.common.base.Verify;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import javax.json.Json;
import javax.json.JsonObject;

import com.jayway.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import sun.misc.Request;


public class RestAssuredFrameworkTest {


    @Test
    public void testGet(){
        //Response response = RestAssured.get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
        Response response = RestAssured.get("http://localhost:3000/posts/2");
        int code = response.getStatusCode();
        String responseBody = response.asString();
        System.out.println(responseBody);
        //responseBody = responseBody.substring(1, responseBody.length()-1);
        JsonPath jsonPath = new JsonPath(responseBody);
        Verify.verify(jsonPath.get("title").equals("Harry Potter"));
        Verify.verify(jsonPath.get("author").equals("J K Rowling"));

        Assert.assertEquals(code, 200);
    }

    @Test
    public void testPut(){
        JsonObject newPut = Json.createObjectBuilder()
                .add("id", 2)
                .add("title", "Harry Potter")
                .add("author", "J K Rowling")
                .build();
        RequestSpecification reqSpec = RestAssured.given();
        reqSpec.header("Content-Type", "application/json");
        reqSpec.body(newPut.toString());
        Response response = reqSpec.put("http://localhost:3000/posts/2");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testPost(){
        JsonObject newPost = Json.createObjectBuilder()
                .add("id", "2")
                .add("title", "Harry Potter")
                .add("author", "Raj Haldar")
                .build();
        RequestSpecification reqSpec = RestAssured.given();
        reqSpec.header("Content-Type", "application/json");
        reqSpec.body(newPost.toString());
        Response response = reqSpec.post("http://localhost:3000/posts");
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    @Test
    public void testDelete(){

        RequestSpecification reqSpec = RestAssured.given();
        Response response = reqSpec.delete("http://localhost:3000/posts/2");
        Assert.assertEquals(response.getStatusCode(), 200);

    }
}
