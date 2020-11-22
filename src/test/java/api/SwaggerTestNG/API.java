package api.SwaggerTestNG;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ExcelReader.ReadData;
import api.SwaggerTestNG.pojo.request.PostRequestPayloadforStores;
import api.SwaggerTestNG.pojo.request.StoresPostPayload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class API {

	 @DataProvider(name="readData")

	    public Object[][] readDataFromDataSheet() throws Exception{

	         Object[][] testObjArray = ReadData.readDataFromExcel("storesPostBodyRequest");

	         return (testObjArray);

			}
	
  @Test(dataProvider= "readData")
  public void Request(String name,String type,String address,String address2,String city,String state,String zip,String lt,String lg,String hours,
			String personname,String perid,String personworkadd) {
	  
	String requestType =  System.getProperty("requesttype");
	int postid = 0;
	
	double lat = Double.parseDouble(lt);
	double lng = Double.parseDouble(lg);
	long personid = Long.parseLong(perid);
	
	System.out.println("reqtype : "+requestType);
	if(requestType.equalsIgnoreCase("Post")) {
		PostRequestPayloadforStores pb = new PostRequestPayloadforStores();
	// StoresPostPayload postbody = pb.postReqPayloadStores("honor", "mobile", "abc", "def", "mum", "mh", "4000078", 42.50, 22.30, "3", "raj", 877, "kkjh");
	StoresPostPayload postbody = pb.postReqPayloadStores(name,type,address,address2,city,state,zip,lat,lng,hours,personname,personid,personworkadd);
		
		String res =	RestAssured.given().header("Content-Type","application/json").body(postbody).log().all()
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