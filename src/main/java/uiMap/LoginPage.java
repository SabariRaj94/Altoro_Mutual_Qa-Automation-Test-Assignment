package uiMap;

import org.openqa.selenium.By;
import org.testng.Assert;

import WebdriverUtility.Test_Utility;

public class LoginPage extends Test_Utility {

// ui Elements
	public static final By signInLink = By.xpath("//font[text()='Sign In']");
	public static final By validateLoginPage = By.xpath("//h1[text()='Online Banking Login']");
	public static final By username = By.xpath("//input[@name='uid']");
	public static final By password = By.xpath("//input[@id='passw']");
	public static final By loginButton = By.xpath("//input[@name='btnSubmit']");
	public static final By validatehomepage = By.xpath("//div[@class]//h1");
	public static final By errorMessage = By.xpath("//span[@id='_ctl0__ctl0_Content_Main_message']");

	public static final By viewaccountsummary = By.xpath("//a[text()='View Account Summary']");
	public static final By listaccounts = By.xpath("//select[@name='listAccounts']");
	public static final By Gobutton = By.xpath("//input[@id='btnGetAccount']");
	public static final By validateViewAccountbalancepage = By.xpath("//h1");
	public static final By AvailableBalance = By.xpath("//td[@colspan='2']//table//tbody/tr[4]//td[@align]");
	public static final By TransferFunds = By.xpath("//a[text()='Transfer Funds']");
	public static final By fromAccount = By.xpath("//select[@id='fromAccount']");
	public static final By toAccount = By.xpath("//select[@id='toAccount']");
	public static final By transferAmount = By.xpath("//input[@id='transferAmount']");
	public static final By transferButton = By.xpath("//input[@id='transfer']");
	public static final By getSuccessMessage = By.xpath("//span[@style='color: Red']");
	public static final By ViewRecentTransaction = By.xpath("//a[text()='View Recent Transactions']");
	public static final By PayeeAccount = By.xpath("//table[@id='_ctl0__ctl0_Content_Main_MyTransactions']//tbody//tr[2]//td[3]");
	public static final By PayeeDeposit = By.xpath("//table[@id='_ctl0__ctl0_Content_Main_MyTransactions']//tbody//tr[2]//td[4]");
	public static final By PaidAmount = By.xpath("//table[@id='_ctl0__ctl0_Content_Main_MyTransactions']//tbody//tr[2]//td[@align]");
	public static final By SenderAccount = By.xpath("//table[@id='_ctl0__ctl0_Content_Main_MyTransactions']//tbody//tr[3]//td[3]");
	public static final By SenderWithdrawal = By.xpath("//table[@id='_ctl0__ctl0_Content_Main_MyTransactions']//tbody//tr[3]//td[4]");
	public static final By SentAmount = By.xpath("//table[@id='_ctl0__ctl0_Content_Main_MyTransactions']//tbody//tr[3]//td[@align]");
	public static final By contactUs = By.xpath("//a[text()='Contact Us']");
	public static final By onlineForm = By.xpath("//a[text()='online form']");
	public static final By subject = By.xpath("//input[@name='subject']");
	public static final By comments = By.xpath("//textarea[@name='comments']");
	public static final By submitButton = By.xpath("//input[@name='submit']");
	public static final By thanksMessage = By.xpath("//h1"); // h1[text()='Thank You']
	public static final By signOff = By.xpath("//font[text()='Sign Off']");
	public static final By ValidateSignoff = By.xpath("//a[@id='LoginLink']//font");

	public static void login() {

		try {
			Thread.sleep(2000);
			sendkeys(username, readproperty("username"));
			sendkeys(password, readproperty("password"));
			click(loginButton);
			pageValidate(validatehomepage, "Hello Admin User");
			System.out.println("success");
			
		} catch (Exception e) {
			System.out.println("Error Message: " + e.getMessage());
		}
	}

	public static void login_Negative() {
		try {
			Thread.sleep(2000);
			Geturl(readproperty("url"));
			pageTitle("Altoro Mutual");
			click(signInLink);
			pageValidate(validateLoginPage, "Online Banking Login");
			sendkeys(username, readproperty("Wusername"));
			sendkeys(password, readproperty("Wpassword"));
			click(loginButton);
			
			String msg = getText(errorMessage);
			if (msg.contains("Login Failed")) {
				System.out.println("Please Check Username & Password");
			} else {
				System.out.println("Message: "+ msg);
			}

		} catch (Exception e) {
			System.out.println("Error Message: " + e.getMessage());
		}
	}

	public static void viewAccountBalance() {

		try {
			Thread.sleep(2000);
			click(viewaccountsummary);
			selectDropdown(listaccounts, readproperty("ListAccount"));
			click(Gobutton);
			pageValidate(validateViewAccountbalancepage, "Account History - " + readproperty("ListAccount") + "");
			
//Assert Balance
			String bal = getText(AvailableBalance);
			Assert.assertEquals(bal, readproperty("AvailableBalance"));
			System.out.println("View Account Balance Assertion Pass");
		} catch (Exception e) {
			System.out.println("Error Message: " + e.getMessage());
		}
	}

	public static void fundTransfer() {
		try {
			Thread.sleep(2000);
			click(TransferFunds);
			selectDropdown(fromAccount, readproperty("FromAccount"));
			selectDropdown(toAccount, readproperty("ToAccount"));
			sendkeys(transferAmount, readproperty("TransferAmount"));
			click(transferButton);
			
//Validate Transfer Money Success Message
			String msg = getText(getSuccessMessage);
			if (msg.contains("" + readproperty("TransferAmount")
					+ ".0 was successfully transferred from Account 800000 into Account 800001")) {
				System.out.println("Amount Transfer Message Validate Successful: " + readproperty("TransferAmount"));
			} else {
				System.out
						.println("Amount Transfer Message Validate Not Successful: " + readproperty("TransferAmount"));
			}
			Thread.sleep(3000);

		} catch (Exception e) {
			System.out.println("Error Message: " + e.getMessage());
		}
	}

	public static void viewRecentTransaction() {
		try {
			Thread.sleep(4000);
			click(ViewRecentTransaction);

//Payee Details Validation
			String payeeAC = getText(PayeeAccount);
			String toAC = readproperty("ToAccount");
			String[] toACid = toAC.split(" ");
			if (payeeAC.contains(toACid[0])) {
				System.out.println("Payee Account ID Validate Successful: " + payeeAC + " & " + toACid[0]);

				String action = getText(PayeeDeposit);
				if (action.equals("Deposit")) {
					System.out.println("Payee Action Validate Successful: " + action);

					String paidamount = getText(PaidAmount);
					if (paidamount.contains("$" + readproperty("TransferAmount") + ".00")) {
						System.out.println("Paid Amount validate successful: " + paidamount);
					} else {
						System.out.println("Paid Amount Validate Not Successfull" + paidamount);
					}

				} else {
					System.out.println("Payee Action Validate Not Successful" + action);
				}
			} else {
				System.out.println("Payee Account ID Validate Not Successful" + payeeAC);
			}

//Payer details validation
			String senderAC = getText(SenderAccount);
			String fromAC = readproperty("FromAccount");
			String[] fromACid = fromAC.split(" ");
			if (senderAC.contains(fromACid[0])) {
				System.out.println("Payer Account ID Validate Successful: " + senderAC + " & " + fromACid[0]);

				String action = getText(SenderWithdrawal);
				if (action.equals("Withdrawal")) {
					System.out.println("Payer Action Validate Successful: " + action);

					String sentamount = getText(SentAmount);
					if (sentamount.contains("-$" + readproperty("TransferAmount") + ".00")) {
						System.out.println("Sent Amount validate successful: " + sentamount);
					} else {
						System.out.println("Sent Amount Validate Not Successfull: " + sentamount);
					}

				} else {
					System.out.println("Payer Action Validate Not Successful");
				}
			} else {
				System.out.println("Payer Account ID Validate Not Successful");
			}

		} catch (Exception e) {
			System.out.println("Error Message: " + e.getMessage());
		}
	}

	public static void contactus() {
		try {
			Thread.sleep(2000);
			click(contactUs);
			click(onlineForm);
			sendkeys(subject, readproperty("Subject"));
			sendkeys(comments, readproperty("Comments"));
			click(submitButton);
			
//Validate Thanks Message
			String msg = getText(thanksMessage);
			Assert.assertEquals(msg, readproperty("Message"));
			System.out.println("Message Displayed Validation Done");
			
		} catch (Exception e) {
			System.out.println("Error Message: " + e.getMessage());
		}

	}

	public static void signOff() {
		try {
			Thread.sleep(2000);
			click(signOff);

//Validate Sign Off
			String off = getText(ValidateSignoff);
			Assert.assertEquals(off, readproperty("validateSignOff"));
			System.out.println("User Sign Off successful");

		} catch (Exception e) {
			System.out.println("Error Message: " + e.getMessage());
		}
	}

}
