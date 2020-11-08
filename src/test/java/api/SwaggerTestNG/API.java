package api.SwaggerTestNG;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class API {
	
	
  @Test
  public void Request() {
	  
	String requestType =  System.getProperty("requesttype");
	int postid = 0;
	
	System.out.println("reqtype : "+requestType);
	if(requestType.equalsIgnoreCase("Post")) {
		String res =	RestAssured.given().header("Content-Type","application/json").body(PostPayload.reqPostPayload()).log().all()
				.when().post("http://localhost:3030/stores")
				.then().log().all().statusCode(201).extract().asString();

			JsonPath json = new JsonPath(res);
			postid =json.get("id");
			System.out.println("name : "+postid);
			System.out.println("name : "+json.get("name"));
			System.out.println("type : "+json.get("type"));
		
	}
	else if(requestType.equalsIgnoreCase("Get")) {
	String res =	RestAssured.given().header("Content-Type","application/json").log().all()
		.when().get("http://localhost:3030/stores")
		.then().log().all().statusCode(200).extract().asString();

	JsonPath json = new JsonPath(res);
	System.out.println("total : "+json.get("total"));
	System.out.println("limit : "+json.get("limit"));
	
	}
	else if(requestType.equalsIgnoreCase("Patch")) {
		
	}
	else if(requestType.equalsIgnoreCase("Delete")) {
		
		String res =	RestAssured.given().header("Content-Type","application/json")
				.pathParam("id", postid).log().all()
				.when().delete("http://localhost:3030/stores/{id}")
				.then().log().all().statusCode(200).extract().asString();
		JsonPath json = new JsonPath(res);
		System.out.println("name : "+json.get("name"));
		System.out.println("type : "+json.get("type"));
	}
	else {
		System.out.println("enter valid request type");
	}
  }

}