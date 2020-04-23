package KarrosTechPJ.Karros;

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

public class NewTest {
  
	WebDriver driver;
	
	@BeforeMethod
	public void beforeMethod() throws Exception {
		driver = WebInit.setup(GlobalVariables.glb_Browser);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	  
		driver.get(GlobalVariables.glb_Url_Login);
		driver.manage().window().maximize();
	}

	@Test
	public void f() throws InterruptedException, IOException {
		
		LoginPage loginpage = new LoginPage(driver);
		RequestPage requestpage = new RequestPage(driver);
		FiltersPage filterspage = new FiltersPage(driver);
		
		loginpage.Login("admin@test.com", "test123");
		/*
		requestpage.clickFilters();
		filterspage.Filter("Inactive", "", "", "", "");
		
		Assert.assertEquals(driver.findElement(By.xpath("//a[@class = 'query__filter__item' and text() = 'Request Status: ']")).getText(), "Request Status: Inactive");
		Assert.assertEquals(driver.findElements(By.xpath("//a[@class = 'query__filter__item']")).size(),1);
		//Assert.assertEquals(driver.findElements(By.xpath("//table[@class = 'table table-striped table-bordered table-hover table-condensed table-body']/tbody/tr")).size(),3);
		*/
		
		requestpage.sortByFirstName("descending");
		requestpage.verifyAllDataRequestList(".\\src\\resources\\RA_MP_TC004_First_Name_Desc.txt");
		requestpage.sortByFirstName("ascending");
		requestpage.verifyAllDataRequestList(".\\src\\resources\\RA_MP_TC004_First_Name_Asce.txt");
		/*
		requestpage.clickFilters();
		filterspage.Filter("All", "", "", "", "");
		Assert.assertEquals(0, driver.findElements(By.xpath("//a[@class = 'query__filter__item']")).size());
		*/
	}

	@AfterMethod
	public void afterMethod() {
	}

}
