package driverFactory;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;
import commonFunctions.PBBrancheCreation;
import commonFunctions.PBBranches;
import commonFunctions.PBLogin;
import commonFunctions.PBLogout;
import commonFunctions.PBUpdation;
import config.AppUtil;
import utilities.ExcelFileUtil;
public class AppTest extends AppUtil {
	String inputpath ="./FileInput/DataEngine1.xlsx";
	String outputpath="./FileOutput/HybridResults.xlsx";
	String TCSheet ="TestCases";
	String TSSheet ="TestSteps";
	@Test
	public void startTest()throws Throwable
	{
		boolean res = false;
		String tcres ="";
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		int TCCount =xl.rowCount(TCSheet);
		int TSCount =xl.rowCount(TSSheet);
		Reporter.log("No of rows in TCSheet::"+TCCount+"  "+"No of rows in TSSheet::"+TSCount,true);
		for(int i=1;i<=TCCount;i++)
		{
			String Executionstatus=xl.getCelldata(TCSheet, i, 2);
			if(Executionstatus.equalsIgnoreCase("Y"))
			{
				String TCid =xl.getCelldata(TCSheet, i, 0);
				for(int j=1;j<=TSCount;j++)
				{
					String TSid = xl.getCelldata(TSSheet, j, 0);
					if(TCid.equalsIgnoreCase(TSid))
					{
						String keyword = xl.getCelldata(TSSheet, j, 3);
						if(keyword.equalsIgnoreCase("AdminLogin"))
						{
							PBLogin login =PageFactory.initElements(driver, PBLogin.class);
							String Para1 =xl.getCelldata(TSSheet, j, 5);
							String Para2 = xl.getCelldata(TSSheet, j, 6);
							res = login.verify_Login(Para1, Para2);
						}
						else if(keyword.equalsIgnoreCase("BranchCreate"))
						{
							PBBranches branch =PageFactory.initElements(driver, PBBranches.class);
							PBBrancheCreation newbranch =PageFactory.initElements(driver, PBBrancheCreation.class);
							String Para1 =xl.getCelldata(TSSheet, j, 5);
							String Para2 =xl.getCelldata(TSSheet, j, 6);
							String Para3 =xl.getCelldata(TSSheet, j, 7);
							String Para4 =xl.getCelldata(TSSheet, j, 8);
							String Para5 =xl.getCelldata(TSSheet, j, 9);
							String Para6 =xl.getCelldata(TSSheet, j, 10);
							String Para7 =xl.getCelldata(TSSheet, j, 11);
							String Para8 =xl.getCelldata(TSSheet, j, 12);
							String Para9 =xl.getCelldata(TSSheet, j, 13);
							branch.branchesClick();
							res =newbranch.verify_BranchCreation(Para1, Para2, Para3, Para4, Para5, Para6, Para7, Para8, Para9);
						}
						else if(keyword.equalsIgnoreCase("BranchUpdate"))
						{
							PBBranches branch =PageFactory.initElements(driver, PBBranches.class);
							PBUpdation branchupdate =PageFactory.initElements(driver, PBUpdation.class);
							String para1 =xl.getCelldata(TSSheet, j, 5);
							String para2 =xl.getCelldata(TSSheet, j, 6);
							String para3 =xl.getCelldata(TSSheet, j, 9);
							String para4 =xl.getCelldata(TSSheet, j, 10);
							branch.branchesClick();
							res =branchupdate.verify_UpdateBranch(para1, para2, para3, para4);
						
						}
						else if(keyword.equalsIgnoreCase("AdminLogout"))
						{
							PBLogout logout =PageFactory.initElements(driver, PBLogout.class);
							res =logout.verify_Logout();
						}
						
						String tsres="";
						if(res)
						{
							tsres="Pass";
							xl.SetCelldata(TSSheet, j, 4, tsres, outputpath);
						}
						else
						{
							tsres="Fail";
							xl.SetCelldata(TSSheet, j, 4, tsres, outputpath);
						}
						tcres=tsres;
						
					}
				}
				xl.SetCelldata(TCSheet, i, 3, tcres, outputpath);
			}
			else 
			{
				xl.SetCelldata(TCSheet, i, 3, "Blocked", outputpath);
			}
			
		}
		
	}
	}