package KarrosTechPJ.Karros.Utilities;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Utils {
	
	WebDriver driver;
	
	public Utils(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}		
}
