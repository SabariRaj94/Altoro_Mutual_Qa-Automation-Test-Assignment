package UnitTest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import WebdriverUtility.Test_Utility;
import uiMap.LoginPage;

public class Test_Altoro extends LoginPage {

	@BeforeClass
	public void testInitialization() {
		driverInitizaliation();
	}

	@Test(priority = 1)
	public void altoroLogin_Negative() {
		login_Negative();
	}

	@Test(priority = 2)
	public void altoroLogin_Positive() {
		login();
	}

	@Test(priority = 3)
	public void altoro_ViewAccountBalance() {
		viewAccountBalance();
	}

	@Test(priority = 4)
	public void altoro_FundTransfer() {
		fundTransfer();
	}

	@Test(priority = 5, dependsOnMethods = "altoro_FundTransfer")
	public void altoro_ViewRecentTransaction() {
		viewRecentTransaction();
	}

	@Test(priority = 6)
	public void contactUs() {
		contactus();
	}

	@AfterClass
	public void OnTestFinish() {
		signOff();
		driver.quit();;

	}

}
