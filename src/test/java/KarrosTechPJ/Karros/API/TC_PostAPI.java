package KarrosTechPJ.Karros.API;

import org.testng.annotations.Test;

import KarrosTechPJ.Karros.Base.GlobalVariables;
import KarrosTechPJ.Karros.Base.WebInit;
import KarrosTechPJ.Karros.Web.FiltersPage;
import KarrosTechPJ.Karros.Web.LoginPage;
import KarrosTechPJ.Karros.Web.RequestPage;
import KarrosTechPJ.Karros.API.PostAPIsMethods;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

//import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class TC_PostAPI {  

	@Test
	public void TO_PostAPI() throws InterruptedException, IOException {
				
		String expectedid="1";
		String expectedtitle="Post 1";
		PostAPIsMethods pam=new PostAPIsMethods();
		
		// Verify the response of API endpoint - GET https://my-json-server.typicode.com/typicode/demo/posts/1 (ID, Title).
		System.out.println(pam.getGetPostResponse(expectedid).getId());
		Assert.assertEquals(pam.getGetPostResponse(expectedid).getId(), Integer.parseInt(expectedid));
		System.out.println(pam.getGetPostResponse(expectedid).getTitle());
		Assert.assertEquals(pam.getGetPostResponse(expectedid).getTitle(),expectedtitle);
	}
}
