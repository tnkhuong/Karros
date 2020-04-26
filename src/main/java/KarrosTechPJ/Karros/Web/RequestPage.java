package KarrosTechPJ.Karros.Web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.By;
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
							//System.out.println("Return value in table at Row: " + row + " and Column: " + column + ": " + td.getText());
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
	
	// This function uses to compare all data between 2 Access Request Lists.
	public boolean compareAllDataRequestList (List<Request> actuallist, List<Request> expectedlist) throws InterruptedException, IOException
	{				
		for (int i = 0; i < actuallist.size(); i++)
		{
			if (actuallist.size() != expectedlist.size() 
				|| !actuallist.get(i).getRequeststatus().equals(expectedlist.get(i).getRequeststatus())
				|| !actuallist.get(i).getDatesubmitted().equals(expectedlist.get(i).getDatesubmitted())
				|| !actuallist.get(i).getRequester().equals(expectedlist.get(i).getRequester())
				|| !actuallist.get(i).getStudentid().equals(expectedlist.get(i).getStudentid())
				|| !actuallist.get(i).getFirstname().equals(expectedlist.get(i).getFirstname())
				|| !actuallist.get(i).getLastname().equals(expectedlist.get(i).getLastname())
				|| !actuallist.get(i).getDob().equals(expectedlist.get(i).getDob())
				|| !actuallist.get(i).getNotes().equals(expectedlist.get(i).getNotes())
				|| !actuallist.get(i).getUser().equals(expectedlist.get(i).getUser()))
			{
				return false;
			}
		}
		return true;
	}
	
	// This function uses to get all current data Request List.
	public List<Request> getAllDataRequestList () throws InterruptedException
	{
		List<Request> requestlist = new ArrayList<Request>();
		
		int rowCount = driver.findElements(By.xpath("//table[@class = 'table table-striped table-bordered table-hover table-condensed table-body']/tbody/tr")).size();		
		int _count = 0;
		while (rowCount < 2 && _count < 5){
			Thread.sleep(1000);
			_count++;
			rowCount = driver.findElements(By.xpath("//table[@class = 'table table-striped table-bordered table-hover table-condensed table-body']/tbody/tr")).size();
		}
		
		// Get all data in table.		
		for (int i = 0; i<rowCount; i++)
		{
			requestlist.add(new Request(getRequestAccessACell(i+1,2), getRequestAccessACell(i+1,3), getRequestAccessACell(i+1,4), 
					getRequestAccessACell(i+1,5), getRequestAccessACell(i+1,6), getRequestAccessACell(i+1,7), 
					getRequestAccessACell(i+1,8), getRequestAccessACell(i+1,9), getRequestAccessACell(i+1,10)));
		}
	
		return requestlist;
	}
	
	// This function uses to sort all data List with condition
	// Sort Column: Request Access, First Name ...
	// Sort Type: ascending/descending.
	public List<Request> sortAllDataRequestList(List<Request> list, String sortcolumn, final String sorttype) {		
		
		List<Request> requestlist = list;
		
		if (sortcolumn.toLowerCase().equals("first name"))
		{
			Collections.sort(requestlist, new Comparator<Request>() {
	            public int compare(Request o1, Request o2) {
	            	if (sorttype.toLowerCase().equals("ascending"))	            		
	            	{
	            		return o1.getFirstname().compareTo(o2.getFirstname());
	            	}else if (sorttype.toLowerCase().equals("descending"))	  
	            	{
	            		return o2.getFirstname().compareTo(o1.getFirstname());
	            	}
	            	return o1.getFirstname().compareTo(o2.getFirstname());
	            }
	        });
		}		
		return requestlist;
	}
	
	// This function uses to get all current data Request List with condition base on filter of 
	// Request Access (Approved, Denied, Pending, Rejected, Inactive)
	public List<Request> getAllDataRequestListWithRequestAccessFiltered (String filter) throws InterruptedException
	{
		List<Request> requestlist = new ArrayList<Request>();
		
		int rowCount = driver.findElements(By.xpath("//table[@class = 'table table-striped table-bordered table-hover table-condensed table-body']/tbody/tr")).size();		
		int _count = 0;
		while (rowCount < 2 && _count < 5){
			Thread.sleep(1000);
			_count++;
			rowCount = driver.findElements(By.xpath("//table[@class = 'table table-striped table-bordered table-hover table-condensed table-body']/tbody/tr")).size();
		}
		
		// Get all data which has correct condition in table.
		for (int i = 0; i<rowCount; i++)
		{			
			if (filter.equals(driver.findElement(By.xpath("//table[@class = 'table table-striped table-bordered table-hover table-condensed table-body']/tbody/tr[" + (i+1) + "]//p[@class = 'td-request-inactive uppercase']")).getText()))
			{
				requestlist.add(new Request(getRequestAccessACell(i+1,2), getRequestAccessACell(i+1,3), getRequestAccessACell(i+1,4), 
				getRequestAccessACell(i+1,5), getRequestAccessACell(i+1,6), getRequestAccessACell(i+1,7), 
				getRequestAccessACell(i+1,8), getRequestAccessACell(i+1,9), getRequestAccessACell(i+1,10)));
			}
		}
	
		return requestlist;
	}
	//-------------------------------------------------------//
}
