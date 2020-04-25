package KarrosTechPJ.Karros.API;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import KarrosTechPJ.Karros.API.PayLoads.GetPostResponsePayLoad;
import KarrosTechPJ.Karros.Base.APIInit;


public class PostAPIsMethods{
	public GetPostResponsePayLoad getGetPostResponse(String id) throws IOException
	{	
		APIInit apiinit = new APIInit();
		GetPostResponsePayLoad gp=(given().spec(apiinit.requestSpecification()).urlEncodingEnabled(false).pathParam("id",URLDecoder.decode(id)).log().all().when().get("/typicode/demo/posts/{id}").as(GetPostResponsePayLoad.class));	
		return gp;
	}
	public List<GetPostResponsePayLoad> getGetPostResponse() throws IOException
	{	
		APIInit apiinit = new APIInit();
		return Arrays.asList(given().spec(apiinit.requestSpecification())
	            .when()
	            .get("/typicode/demo/posts")
	            .then()
	            .extract()
	            .response()
	            .body()
	            .as(GetPostResponsePayLoad[].class));
	}
	public int getGetResponseStatus(String id) throws IOException
	{	
		APIInit apiinit = new APIInit();
		return given().spec(apiinit.requestSpecification()).pathParam("id",id).when().get("/typicode/demo/posts/{id}").getStatusCode();
	}
	public int getGetResponseStatus() throws IOException
	{	
		APIInit apiinit = new APIInit();
		return given().spec(apiinit.requestSpecification())
	            .when()
	            .get("/typicode/demo/posts").getStatusCode();
	}
	public String getGetResponseStatusMessage(String id) throws IOException
	{	
		APIInit apiinit = new APIInit();
		return given().spec(apiinit.requestSpecification()).pathParam("id",id).when().get("/typicode/demo/posts/{id}").getStatusLine();
	}
	public String getGetResponseStatusMessage() throws IOException
	{	
		APIInit apiinit = new APIInit();
		return given().spec(apiinit.requestSpecification()).when().get("/typicode/demo/posts").getStatusLine();
	}
}
