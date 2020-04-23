package KarrosTechPJ.Karros.API;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import KarrosTechPJ.Karros.API.PayLoads.GetPostResponsePayLoad;
import KarrosTechPJ.Karros.Base.APIInit;


public class PostAPIsMethods{
	
	public GetPostResponsePayLoad getGetPostResponse(String id) throws IOException
	{	
		APIInit apiinit = new APIInit();
		GetPostResponsePayLoad gp=(GetPostResponsePayLoad) given().spec(apiinit.requestSpecification()).pathParam("id",id).when().get("/typicode/demo/posts/{id}").as(GetPostResponsePayLoad.class);
		return gp;
	}
}
