package commonFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
public class PBUpdation {
	WebDriver driver;
	public PBUpdation(WebDriver driver)
	{
		this.driver = driver;
	}
	@FindBy(xpath="(//img)[10]")
	WebElement ClickEdit;
	@FindBy(xpath="//input[@id='txtbnameU']")
	WebElement EnterBranch;
	@FindBy(xpath="(//input[@id='txtadd1u'])[1]")
	WebElement EnterAddress;
	@FindBy(xpath="//input[@id='txtareaU']")
	WebElement EnterArea;
	@FindBy(xpath="//input[@id='txtzipu']")
	WebElement EnterZipCode;
	@FindBy(xpath="//input[@id='btnupdate']")
	WebElement ClickUpdate;
	public boolean verify_UpdateBranch(String Branch,String Address,String Area,String ZipCode) throws Throwable
	{
		this.ClickEdit.click();
		this.EnterBranch.clear();
		this.EnterBranch.sendKeys(Branch);
		this.EnterAddress.clear();
		this.EnterAddress.sendKeys(Address);
		this.EnterArea.clear();
		this.EnterArea.sendKeys(Area);
		this.EnterZipCode.clear();
		this.EnterZipCode.sendKeys(ZipCode);
		this.ClickUpdate.click();
		Thread.sleep(3000);
		String ExpectedAlert = driver.switchTo().alert().getText();
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		String ActualAlert ="Branch updated";
		if (ExpectedAlert.toLowerCase().contains(ActualAlert.toLowerCase())) 
		{
			Reporter.log(ExpectedAlert,true);
			return true;
		}
		else
		{
			Reporter.log("Unable to Update Branch",true);
			return false;
		}

	}
}
