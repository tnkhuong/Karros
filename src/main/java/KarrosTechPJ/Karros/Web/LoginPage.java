package KarrosTechPJ.Karros.Web;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}
	
	//-----------------Email textbox--------------------//
	@FindBy(xpath="//input[@type = 'email']")
    public WebElement txtEmail;
	
	public void setEmail (String emailstr)
	{
		System.out.println("Enter Email:" + emailstr);
		txtEmail.sendKeys(Keys.chord(Keys.CONTROL,"a") + Keys.DELETE);
		txtEmail.sendKeys(emailstr);		
	}
	//--------------------------------------------------//	
	
	//-----------------Password textbox-----------------//
	@FindBy(xpath="//input[@type = 'password']")
	public WebElement txtPassword;
	
	public void setPassword (String passwordstr)
	{
		System.out.println("Enter Password:" + passwordstr);
		txtPassword.sendKeys(Keys.chord(Keys.CONTROL,"a") + Keys.DELETE);
		txtPassword.sendKeys(passwordstr);		
	}
	//--------------------------------------------------//	
	
	//-----------------Log In button--------------------//
	@FindBy(xpath="//a[@class = 'col-login__btn']")
	public WebElement btnLogin;
	
	public void clickLogin ()
	{
		System.out.println("Click on Login.");
		btnLogin.click();
	}
	//--------------------------------------------------//
		
	public void Login (String emailstr, String passwordstr) 
	{	
		setEmail(emailstr);
		setPassword(passwordstr);
		clickLogin();
	}
}
