package KarrosTechPJ.Karros.Web;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import KarrosTechPJ.Karros.Utilities.GetTXTValue;

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
		
		do 
		{
			_timeout = _timeout + 1000;
			Thread.sleep(1000);
		}while (!btnFilters.isDisplayed() && _timeout < 5000);
		
		System.out.println("Click on Filters.");
		btnFilters.click();
	}
	//-------------------------------------------------//
	
	//-----------------First Name column header-----------------//
	@FindBy(xpath="//th[@title = 'First Name']")
	public WebElement colFirstName;
		
	public void sortByFirstName (String sorttypestr) throws InterruptedException
	{
		int _timeout=0;			
		do 
		{
			_timeout = _timeout + 1000;
			Thread.sleep(1000);
		}while (!colFirstName.isDisplayed() && _timeout < 5000);	
		
		if (sorttypestr.toLowerCase().contentEquals("ascending"))
		{	
			int _count = 0;			
			while ((driver.findElements(By.xpath("//th[@title = 'First Name']/span[@class = 'order']/span[@class = 'caret table-sorting-clean-caret']")).size()!=0 
					|| driver.findElements(By.xpath("//th[@title = 'First Name']/span[@class = 'order']/span[@class = 'dropup table-sorting-dropup-icon']")).size()!=0)
					&& _count<3)
			{ 
				System.out.println("Click on First Name Column Header to sort Ascending.");
				colFirstName.click();
			}
		}
		else if (sorttypestr.toLowerCase().contentEquals("descending"))
		{
			
			if (driver.findElements(By.xpath("//th[@title = 'First Name']/span[@class = 'order dropup']/span[@class = 'caret table-sorting-clean-caret']")).size()!=0 || 
					driver.findElements(By.xpath("//th[@title = 'First Name']/span[@class = 'order']/span[@class = 'dropup table-sorting-dropup-icon']")).size()!=0)
			{ 
				System.out.println("Click on First Name Column Header to sort Descending.");
				colFirstName.click();
			}
		}
	}
	//-----------------------------------------------------------//
	
	//-----------------Request Access table-----------------//
	@FindBy(xpath="//table[@class = 'table table-striped table-bordered table-hover table-condensed table-body']")
    public WebElement tblRequestAccess;
	
	public String getRequestAccessACell (int row, int column)
	{
		int ac_row=0;
		int ac_col=0;				
		
		List<WebElement> row_table = tblRequestAccess.findElements(By.tagName("tr"));
			for(WebElement tr : row_table)
			{
				ac_row=ac_row+1;
				ac_col=0;				
				if (ac_row==row)
				{
					List<WebElement> col_table = tr.findElements(By.tagName("td"));
					for (WebElement td : col_table)
					{
						ac_col=ac_col+1;
						if (ac_col==column)
						{							
							System.out.println("Return value in table at Row: " + row + " and Column: " + column + ": " + td.getText());
							return td.getText();
						}
						else if (ac_col>column)
						{
							return null;
						}						
					}
				}
				else if (ac_row>row)
				{
					return null;
				}
			}			
			return null;
	}	
	
	public void verifyAllDataRequestList (String file_path) throws InterruptedException, IOException
	{
		String _value_from_file = null;
		String _value_from_table = null;
			
		GetTXTValue gettxt = new GetTXTValue();		
		
		// Wait until data in table loaded.
		int rowCount=driver.findElements(By.xpath("//table[@class = 'table table-striped table-bordered table-hover table-condensed table-body']/tbody/tr")).size();
		int _count = 0;
		while (rowCount < 2 && _count < 5){
			Thread.sleep(1000);
			_count++;
			rowCount=driver.findElements(By.xpath("//table[@class = 'table table-striped table-bordered table-hover table-condensed table-body']/tbody/tr")).size();
		}
		
		// Verify that the data in table same as data file.
		for (int i = 0; i<=gettxt.getTotalLine(file_path); i++)
		{
			for (int j = 0; j<=9;j++)
			{				
				_value_from_file = gettxt.getAValue(file_path,i+1,j+1);
				System.out.println("Return value in .txt file at Line: " + i + " and Column: " + j + ": " + _value_from_file);
				_value_from_table = getRequestAccessACell(i+1,j+2);
			    Assert.assertEquals(_value_from_file, _value_from_table);
			}
		}
	}
	//-------------------------------------------------------//
}
