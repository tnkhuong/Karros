package KarrosTechPJ.Karros.Filters;

import org.testng.annotations.Test;

import KarrosTechPJ.Karros.Base.GlobalVariables;
import KarrosTechPJ.Karros.Base.WebInit;
import KarrosTechPJ.Karros.Web.FiltersPage;
import KarrosTechPJ.Karros.Web.LoginPage;
import KarrosTechPJ.Karros.Web.RequestPage;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class TC_Request_Status {
	
	WebDriver driver;	
	
	@BeforeMethod
	public void beforeMethod() throws Exception {
		driver = WebInit.setup(GlobalVariables.glb_Browser);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	  
		driver.get(GlobalVariables.glb_Url_Login);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TO_Request_Status() throws InterruptedException, IOException {
		
		String os = System.getProperty("os.name").toLowerCase();
		int _total_inactive_rows = 3;
		int _total_tag_filter = 1;
		String requeststatusinactivedata = ".\\src\\test\\java\\KarrosTechPJ\\Karros\\DataSources\\RequestAccess\\TC_Request_Status_Inactive.txt";
		if (os.contains("mac"))
		{
			requeststatusinactivedata = "src/test/java/KarrosTechPJ/Karros/DataSources/RequestAccess/TC_Request_Status_Inactive.txt";
		}
		
		LoginPage loginpage = new LoginPage(driver);
		RequestPage requestpage = new RequestPage(driver);
		FiltersPage filterspage = new FiltersPage(driver);
		
		loginpage.Login("admin@test.com", "test123");		
		requestpage.clickFilters();
		filterspage.Filter("Inactive", "", "", "", "");
		
		// Verify that the filter tag "Request Status: Inactive" is displayed on Request Access page.
		Assert.assertEquals(driver.findElement(By.xpath("//a[@class = 'query__filter__item' and text() = 'Request Status: ']")).getText(), "Request Status: Inactive");
		
		// Verify that there is only one filter tag "Request Status: Inactive" displayed on Request Access page.
		Assert.assertEquals(driver.findElements(By.xpath("//a[@class = 'query__filter__item']")).size(),_total_tag_filter);
		
		// Verify that the total number of returned Inactive record is 3.
		Assert.assertEquals(driver.findElements(By.xpath("//table[@class = 'table table-striped table-bordered table-hover table-condensed table-body']/tbody/tr")).size(), _total_inactive_rows);		
		
		// Verify that the data displays correctly after filter Request Status: Inactive.
		requestpage.verifyAllDataRequestList(requeststatusinactivedata);		
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
