package KarrosTechPJ.Karros.RequestAccess;

import org.testng.annotations.Test;

import KarrosTechPJ.Karros.Base.GlobalVariables;
import KarrosTechPJ.Karros.Base.WebInit;
import KarrosTechPJ.Karros.Web.LoginPage;
import KarrosTechPJ.Karros.Web.Request;
import KarrosTechPJ.Karros.Web.RequestPage;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class TC_Sort_First_Name {
	
	WebDriver driver;
	
	@BeforeMethod  
	public void beforeMethod() throws Exception {
	  	driver = WebInit.setup(GlobalVariables.glb_Browser);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	  
		driver.get(GlobalVariables.glb_Url_Login);
		driver.manage().window().maximize();
	}
  
	@Test
	public void TO_Sort_First_Name() throws InterruptedException, IOException {
		
		LoginPage loginpage = new LoginPage(driver);
		RequestPage requestpage = new RequestPage(driver);
		List<Request> sortrequestlistasc = new ArrayList<Request>();
		List<Request> sortrequestlistdesc = new ArrayList<Request>();		
		
		loginpage.Login("admin@test.com", "test123");		
		
		sortrequestlistasc = requestpage.sortAllDataRequestList(requestpage.getAllDataRequestList(), "First Name", "ascending");
		sortrequestlistdesc = requestpage.sortAllDataRequestList(requestpage.getAllDataRequestList(), "First Name", "descending");		
		
		requestpage.sortByFirstName("descending");
		
		// Verify that the data displays correctly after Sort First Name Descending.		
		Assert.assertEquals(requestpage.compareAllDataRequestList(sortrequestlistdesc, requestpage.getAllDataRequestList()), true);
				
		requestpage.sortByFirstName("ascending");
		
		// Verify that the data displays correctly after Sort First Name Ascending.
		Assert.assertEquals(requestpage.compareAllDataRequestList(sortrequestlistasc, requestpage.getAllDataRequestList()), true);
	}
	
	@AfterMethod  
	public void afterMethod() {
		driver.quit();
	}

}
