package KarrosTechPJ.Karros.Web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FiltersPage {
	
	WebDriver driver;
	
	public FiltersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}
	
	//-----------------Request Status combobox--------------------//
	@FindBy(id="formControlsSelect")
	public WebElement cbbRequestStatus;
		
	public void selectRequestStatus (String requeststatusstr)
	{
		System.out.println("Select Request Status to filter:" + requeststatusstr);
		Select sl = new Select(cbbRequestStatus);
		sl.selectByVisibleText(requeststatusstr);
	}
	//------------------------------------------------------------//
		
	//-----------------Email textbox--------------------//
	@FindBy(id="formHorizontalEmail")
    public WebElement txtEmail;
	
	public void setEmail (String emailstr)
	{
		System.out.println("Enter Email to filter:" + emailstr);
		txtEmail.sendKeys(emailstr);
	}
	//--------------------------------------------------//

	//-----------------Student ID textbox--------------------//
	@FindBy(id="formHorizontalStudentID")
    public WebElement txtStudentID;
	
	public void setStudentID (String studentidstr)
	{
		System.out.println("Enter Student ID to filter:" + studentidstr);
		txtStudentID.sendKeys(studentidstr);
	}
	//-------------------------------------------------------//
		
	//-----------------Student First Name textbox--------------------//
	@FindBy(id="formHorizontalStudentFN")
    public WebElement txtStudentFirstName;
	
	public void setStudentFirstName (String studentfirstnamestr)
	{
		System.out.println("Enter Student First Name to filter:" + studentfirstnamestr);
		txtStudentFirstName.sendKeys(studentfirstnamestr);
	}
	//---------------------------------------------------------------//
	
	//-----------------Student Last Name textbox--------------------//
	@FindBy(id="formHorizontalStudentLN")
	public WebElement txtStudentLastName;
			
	public void setStudentLastName (String studentlastnamestr)
	{
		System.out.println("Enter Student Last Name to filter:" + studentlastnamestr);
		txtStudentLastName.sendKeys(studentlastnamestr);
	}
	//--------------------------------------------------------------//

	//-----------------Cancel button--------------------//
	@FindBy(xpath="//button[@class = 'btn btn-default']")
	public WebElement btnCancel;
			
	public void clickCancel ()
	{
		System.out.println("Click on Cancel Filters.");
		btnCancel.click();
	}
	//--------------------------------------------------//

	//-----------------Apply Filters button------------------//
	@FindBy(xpath="//button[@class = 'btn-filter btn btn-default']")
	public WebElement btnApplyFilters;
			
	public void clickApplyFilters () throws InterruptedException
	{
		int _timeout=0;
			
		do {
			_timeout = _timeout + 1000;
			Thread.sleep(1000);
		}while (!btnApplyFilters.isDisplayed() && _timeout <= 5000);
		
		System.out.println("Click on Apply Filters.");
		btnApplyFilters.click();
	}
	//-------------------------------------------------------//
		
	public void Filter (String requeststatusstr, String emailstr, String studentidstr, String studentfirstnamestr, String studentlastnamestr) throws InterruptedException 
	{	
		selectRequestStatus(requeststatusstr);
		setEmail(emailstr);
		setStudentID(studentidstr);
		setStudentFirstName(studentfirstnamestr);
		setStudentLastName(studentlastnamestr);
		clickApplyFilters();
	}	
}
