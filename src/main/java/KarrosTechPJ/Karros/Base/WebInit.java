package KarrosTechPJ.Karros.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

//import config.GlobalVariables;

public class WebInit {
	public static WebDriver driver;
	public static WebDriver setup(String browser) throws Exception{
		
		
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println(os);
		
		
       //Check if parameter passed from TestNG is 'firefox'
        if(browser.equalsIgnoreCase("firefox")){
          System.setProperty("webdriver.gecko.driver",GlobalVariables.glb_FF_DriverPath);	
        //create firefox instance
          DesiredCapabilities capabilities = DesiredCapabilities.firefox();
          capabilities.setCapability("marionette", true);
          driver= new FirefoxDriver(capabilities);
        }
        //Check if parameter passed as 'chrome'
        else if(browser.equalsIgnoreCase("chrome")){
            //set path to chromedriver.exe You may need to download it from http://code.google.com/p/selenium/wiki/ChromeDriver
            if (os.contains("mac"))
       		{
            	System.setProperty("webdriver.chrome.driver",GlobalVariables.glb_CHR_DriverPath_mac);
            }
            else
            {
            	System.setProperty("webdriver.chrome.driver",GlobalVariables.glb_CHR_DriverPath);
            }
            //create chrome instance
            driver = new ChromeDriver();
        }
 
        else if(browser.equalsIgnoreCase("ie")){
        //set path to IEdriver.exe You may need to download it from
        // 32 bits http://selenium-release.storage.googleapis.com/2.42/IEDriverServer_Win32_2.42.0.zip
        // 64 bits http://selenium-release.storage.googleapis.com/2.42/IEDriverServer_x64_2.42.0.zip
            System.setProperty("webdriver.ie.driver",GlobalVariables.glb_IE_DriverPath);
        //create IE instance
            driver= new InternetExplorerDriver();
        }
        else{
            //If no browser passed throw exception
            throw new Exception("Browser is not correct");
        }
        return driver;
    }
}
