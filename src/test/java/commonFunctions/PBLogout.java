package commonFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
public class PBLogout {
	WebDriver driver;
	public PBLogout(WebDriver driver)
	{
		this.driver =driver;
	}
	@FindBy(xpath="(//img)[4]")
	WebElement ClickLogout;
	@FindBy(xpath="//input[@id='login']")
	WebElement LoginBtn;
	public boolean verify_Logout() throws Throwable
	{
		this.ClickLogout.click();
		Thread.sleep(4000);
		if (this.LoginBtn.isDisplayed())
		{
			Reporter.log("Logout Success",true);
			return true;
		}
		{
			Reporter.log("Logout Fail",true);
			return false;
		}
	}
}
