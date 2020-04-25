package KarrosTechPJ.Karros.API;

import org.testng.annotations.Test;
import java.io.IOException;
import org.testng.Assert;

public class TC_PostAPI {  

	@Test
	public void TO_PostAPI() throws InterruptedException, IOException {
				
		String expectedid="1";
		String expectedtitle="Post 1";
		PostAPIsMethods pam=new PostAPIsMethods();
		
		// Verify the response of API endpoint - GET https://my-json-server.typicode.com/typicode/demo/posts/1 (ID, Title).
		Assert.assertEquals(pam.getGetPostResponse(expectedid).getId(), Integer.parseInt(expectedid));
		Assert.assertEquals(pam.getGetPostResponse(expectedid).getTitle(),expectedtitle);
	}
}
