package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class PBLogin {
	WebDriver driver;
	public PBLogin(WebDriver driver)
	{
		this.driver=driver;
	}
	@FindBy(name = "txtuId")
	WebElement username;
	@FindBy(name = "txtPword")
	WebElement EnterPass;
	@FindBy(name = "login")
	WebElement ClickLogin;
	public boolean verify_Login(String username,String password)
	{
	    this.username.sendKeys(username);
		EnterPass.sendKeys(password);
		ClickLogin.click();
		String Expected ="adminflow";
		String Actual = driver.getCurrentUrl();
		if(Actual.toLowerCase().contains(Expected.toLowerCase()))
		{
			Reporter.log("Login Success::"+Expected+"     "+Actual,true);
			return true;
		}
		else
		{
			Reporter.log("Login Fail::"+Expected+"     "+Actual,true);
			return false;
		}
	}
	}

