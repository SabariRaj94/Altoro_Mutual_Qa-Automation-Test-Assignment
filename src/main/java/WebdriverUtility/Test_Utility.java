package WebdriverUtility;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Test_Utility {

	public static Logger log = Logger.getLogger(Test_Utility.class);
	public static Properties prop;
	public static String propertyfilepath = "D:\\Eclipse Workspace\\NewWorkspace\\Automation_Testing\\Application.properties";
	public static WebDriver driver;

	public static WebDriverWait wait;

	public static String readproperty(String Getprop) {
		try {
			File file = new File(propertyfilepath);
			FileInputStream fi = new FileInputStream(file);
			prop = new Properties();
			prop.load(fi);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.warn(e.toString());
		}

		return prop.getProperty(Getprop);

	}

	public static void driverInitizaliation() {

		System.setProperty("webdriver.chrome.driver", readproperty("ChromeDriverPath"));
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	}

	public static void Geturl(String url) {
		driver.get(url);
	}

	public static void sendkeys(By elements, String input) {
		wait.until(ExpectedConditions.presenceOfElementLocated(elements)).sendKeys(input);
	}

	public static void click(By elements) {
		wait.until(ExpectedConditions.elementToBeClickable(elements)).click();
	}

	public static String getText(By elements) {

		return driver.findElement(elements).getText().trim();
	}

	public static void pageTitle(String ExpectedTitle) {

		String title = driver.getTitle();
		System.out.println("Actual: " + title);
		Assert.assertEquals(title, ExpectedTitle);

	}

	public static void pageValidate(By elements, String Expected) {
		String loginpage = getText(elements);

		Assert.assertEquals(loginpage.trim(), Expected);

	}

	public static void selectDropdown(By elements, String text) {
		Select sel = new Select(driver.findElement(elements));
		sel.selectByVisibleText(text);

	}

}
