package KarrosTechPJ.Karros.API;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;

public class TC_PostAPI {  
		int status200=200;
		int status404=404;
		String status200mesg="HTTP/1.1 200 OK";
		PostAPIsMethods pam=new PostAPIsMethods();
		@BeforeMethod
		public void beforeMethod() throws Exception {
		}
	//Verify that the response payload is returned correctly and response status is 200 with invalid Id parameter
	  @Test public void TO_API_001() throws InterruptedException, IOException {
		  System.out.println("----------------TO_API_001----------------");
		  String id="1"; 
		  String expectedid="1";
		  String expectedtitle="Post 1";
		  // Verify the response payload for Id=1
		  int actualid=pam.getGetPostResponse(id).getId();
		  String actualtitle=pam.getGetPostResponse(expectedid).getTitle();
		  String actual_status_mesg =pam.getGetResponseStatusMessage(id);
		  int actual_status= pam.getGetResponseStatus(id);
		  System.out.println("ID: " + actualid);
		  Assert.assertEquals(actualid,Integer.parseInt(expectedid)); 
		  System.out.println("Title: " + actualtitle);
		  Assert.assertEquals(actualtitle,expectedtitle); 
		  //Verify the response status is 200 
		  System.out.println("Status: " + actual_status_mesg);
		  Assert.assertEquals(actual_status_mesg,status200mesg); 
		  System.out.println("Status: " + actual_status_mesg);
		  Assert.assertEquals(actual_status,status200); 
	  } 
	  
	  //Verify that the response payload is empty and response status is 404 with Id parameter in string
	  @Test public void TO_API_002() throws InterruptedException, IOException {
		  System.out.println("----------------TO_API_002----------------");
		  String id="Test"; 
		  String expectedid="0"; 
		  String expectedtitle=null;
		  // Verify the response payload for invalid Id=Test
		  int actualid=pam.getGetPostResponse(id).getId();
		  String actualtitle=pam.getGetPostResponse(expectedid).getTitle();
		  int actual_status= pam.getGetResponseStatus(id);
		  System.out.println("ID: "+ actualid);
		  Assert.assertEquals(actualid,Integer.parseInt(expectedid)); 
		  System.out.println("Title: " + actualtitle);
		  Assert.assertEquals(actualtitle,expectedtitle);
		  //Verify the response status is 404 
		  System.out.println("Status: " + actual_status);
		  Assert.assertEquals(actual_status,status404); 
	  }
	 
	//Verify that the response payload is returned all post data and response status is 200 without Id parameter
	  @Test
	  public void TO_API_003() throws InterruptedException, IOException {
		  	System.out.println("----------------TO_API_003----------------");	
			ArrayList<String> expectedid = new ArrayList<String>(Arrays. asList("1", "2", "3"));
			ArrayList<String> expectedtitle = new ArrayList<String>(Arrays. asList("Post 1", "Post 2", "Post 3"));
			
			int response_size=pam.getGetPostResponse().size();
			System.out.println("Response size: " + response_size);
			for(int i=0;i<response_size;i++) 
			{
				// Verify the response payload 
				int actual_id=pam.getGetPostResponse().get(i).getId();
				String actual_title=pam.getGetPostResponse().get(i).getTitle();
				int actual_status=pam.getGetResponseStatus(Integer.toString(actual_id));
				
				System.out.println("ID: " + actual_id );
				Assert.assertEquals(actual_id, Integer.parseInt(expectedid.get(i)));
				System.out.println("Title: " + actual_title);
				Assert.assertEquals(actual_title,expectedtitle.get(i));
				//Verify the response status is 200 
				System.out.println("Status: " + actual_status);
				Assert.assertEquals(actual_status,status200); 
			}		
	  }
	  //Verify that the response payload is empty and response status is 404 with invalid Id parameter (Id value does not exist database)
	  @Test public void TO_API_004() throws InterruptedException, IOException {
		  System.out.println("----------------TO_API_004----------------");
		  String expectedid="0"; 
		  String expectedtitle=null;
		  int response_size=pam.getGetPostResponse().size();
		  String id=Integer.toString(response_size+1);
		  // Verify the response payload for invalid Id=Test
		  int actual_id=pam.getGetPostResponse(id).getId();
		  String actual_title=pam.getGetPostResponse(id).getTitle();
		  System.out.println("ID: "+ actual_id);
		  Assert.assertEquals(actual_id,Integer.parseInt(expectedid)); 
		  System.out.println("Title: " + actual_title);
		  Assert.assertEquals(actual_title,expectedtitle);
		  //Verify the response status is 404 
		  int actual_status= pam.getGetResponseStatus(id);
		  System.out.println("Status: " + actual_status);
		  Assert.assertEquals(actual_status,status404); 
	  }
	  //Verify that the response payload is empty and response status is 404 with negative Id parameter
	  @Test public void TO_API_005() throws InterruptedException, IOException {
		  System.out.println("----------------TO_API_005----------------");
		  String id="-1"; 
		  String expectedid="0"; 
		  String expectedtitle=null;
		  // Verify the response payload for invalid Id=Test 
		  int actual_id=pam.getGetPostResponse(id).getId();
		  String actual_title=pam.getGetPostResponse(id).getTitle();
		  System.out.println("ID: "+ actual_id);
		  Assert.assertEquals(actual_id,Integer.parseInt(expectedid)); 
		  System.out.println("Title: " + actual_title);
		  Assert.assertEquals(actual_title,expectedtitle);
		  //Verify the response status is 404 
		  int actual_status=0; 
		  actual_status=pam.getGetResponseStatus(id);
		  System.out.println("Status: " + actual_status);
		  Assert.assertEquals(actual_status,status404);
	  }
		//Verify that the response payload is empty and response status is 404 with special character parameter
	    @Test
	  	public void TO_API_006() throws InterruptedException, IOException {
		  System.out.println("----------------TO_API_006----------------");	  		  
		  List<String> id = Arrays.asList(new String[] {
				  "%2B", "%2D", "%26", "%21", "%28", "%29","%7E", "%2A", "%3A","%2E","%B8","%40","%24","%5F" });
		  
		  String expectedid="0"; 
		  String expectedtitle=null;
		  System.out.println(id.size());
		  for(int i=0;i<id.size();i++)
		  {
			// Verify the response payload for invalid Id=Test 
			  int actualid=pam.getGetPostResponse(id.get(i)).getId();
			  String actualtitle=pam.getGetPostResponse(id.get(i)).getTitle();
			  System.out.println("ID: "+ actualid );
			  Assert.assertEquals(actualid,Integer.parseInt(expectedid)); 
			  System.out.println("Title: " + actualtitle);
			  Assert.assertEquals(actualtitle,expectedtitle);
			  //Verify the response status is 404 
			  int actual_status=pam.getGetResponseStatus(id.get(i)); 
			  System.out.println("Status: " + actual_status);
			  Assert.assertEquals(actual_status,status404);
		  }
	  }
}
