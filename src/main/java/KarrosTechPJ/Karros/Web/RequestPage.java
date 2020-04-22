package KarrosTechPJ.Karros.Web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RequestPage {

	WebDriver driver;
	
	public RequestPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}
	
	//-----------------Filters button-----------------//
	@FindBy(xpath="//button[@class = 'btn btn-filter module_grid__btn_filter btn btn-default']")
    public WebElement btnFilters;
	
	public void clickFilters () throws InterruptedException
	{
		int _timeout=0;		
		while (!btnFilters.isEnabled() && _timeout < 5000)
		{
			_timeout = _timeout + 1000;
			Thread.sleep(1000);
		}
		System.out.println("Click on Filters.");
		btnFilters.click();
	}
	//-------------------------------------------------//	
}
