package KarrosTechPJ.Karros.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {
	
	WebDriver driver;
	
	public Utils(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}
	
	public void waitLoadingComplete (int waitingtime)
	{
		WebDriverWait wait = new WebDriverWait(driver,waitingtime);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//div[contains(@class, 'overlay')]")));
	}	
}
