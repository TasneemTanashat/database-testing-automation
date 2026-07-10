package MyDataBaseProject;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AccountRegistrationTest {

	WebDriver driver = new ChromeDriver();

	String website = "https://automationteststore.com/";

	Random rand = new Random();

	Connection con;

	Statement stmt;

	ResultSet rs;

	String username;
	String password;

	String firstname;
	String loginName;
	String lastname;

	String email;

	String address;
	String numberAsText;

	@BeforeTest
	public void mySetup() throws SQLException {

		driver.get(website);

		driver.manage().window().maximize();

		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "YOUR_USERNAME",
				"YOUR_PASSWORD");

	}

	@AfterMethod
	public void aftertest() throws IOException, InterruptedException {

		Date mydate = new Date();

		String filename = mydate.toString().replace(":", "-");

		TakesScreenshot ts = (TakesScreenshot) driver;

		File file = ts.getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(file, new File("src/screenshots/" + filename + ".jpg"));

	}

	@Test(priority = 1, enabled = false)
	public void signup() throws SQLException {

		stmt = con.createStatement();

		String query = "INSERT INTO customers (" + "customerNumber, customerName, contactLastName, contactFirstName, "
				+ "phone, addressLine1, addressLine2, city, state, postalCode, "
				+ "country, salesRepEmployeeNumber, creditLimit" + ") " + "VALUES (" + "992, "
				+ "'Blue Horizon Solutions', " + "'Hada', " + "'Lina', " + "'+962 7 9555 6789', "
				+ "'King Abdullah II Street', " + "'Building 15', " + "'Irbid', " + "NULL, " + "'21110', "
				+ "'Jordan', " + "NULL, " + "75000.00" + ");";

		stmt.executeUpdate(query);
	}

	@Test(priority = 2, enabled = false)
	public void updateData() throws SQLException {

		stmt = con.createStatement();

		String query = "update customers set contactFirstName ='Mohammad' where customerNumber = 992 ";

		stmt.executeUpdate(query);

	}

	@Test(priority = 3, enabled = false)
	public void DeleteData() throws SQLException {

		stmt = con.createStatement();

		String query = "delete from customers where customerNumber = 992  ";

		stmt.executeUpdate(query);

	}

	@Test(priority = 4, enabled = true)
	public void readData() throws SQLException {

		stmt = con.createStatement();

		String[] randomIDs = { "121", "141", "124", "125", "171" };

		int randomindex = rand.nextInt(randomIDs.length);
		String therandomID = randomIDs[randomindex];

		String query = "select * from customers where customerNumber = " + therandomID;

		rs = stmt.executeQuery(query);

		while (rs.next()) {

			firstname = rs.getString("contactFirstName");

			password = rs.getString("customerNumber") + rs.getString("contactLastName");

//			int a = rand.nextInt(233);
//			int c = rand.nextInt(321);
//			int numberrrrrrrr = a + c;
//			numberAsText = Integer.toString(numberrrrrrrr);

			lastname = rs.getString("contactLastName");

			firstname = firstname.replaceAll("[^a-zA-Z]", "");
			lastname = lastname.replaceAll("[^a-zA-Z]", "");

			email = firstname.trim() + lastname.trim() + System.currentTimeMillis() + "@gmail.com";

			address = rs.getString("addressLine1");
		}
	}

	@Test(priority = 5, enabled = true)
	public void Signup() throws InterruptedException {

		driver.navigate().to("https://automationteststore.com/index.php?rt=account/create");

		loginName = firstname.trim() + System.currentTimeMillis();
		driver.findElement(By.name("loginname")).sendKeys(loginName);

		driver.findElement(By.name("password")).sendKeys(password);

		driver.findElement(By.id("AccountFrm_email")).sendKeys(email.trim());

		driver.findElement(By.id("AccountFrm_firstname")).sendKeys(firstname.trim());

		driver.findElement(By.id("AccountFrm_lastname")).sendKeys(lastname.trim());

		driver.findElement(By.id("AccountFrm_address_1")).sendKeys(address.trim());

		driver.findElement(By.id("AccountFrm_city")).sendKeys("aajksdha");

		WebElement selectState = driver.findElement(By.id("AccountFrm_zone_id"));
		Select myselctor = new Select(selectState);

		myselctor.selectByIndex(1);

		driver.findElement(By.id("AccountFrm_postcode")).sendKeys("123Abc");

		driver.findElement(By.id("AccountFrm_confirm")).sendKeys(password);

		driver.findElement(By.id("AccountFrm_agree")).click();

		driver.findElement(By.cssSelector(".btn.btn-orange.pull-right.lock-on-click")).click();

		Thread.sleep(2000);

		String ActualValue = driver.getCurrentUrl();

		System.out.println(ActualValue);

		Assert.assertEquals(ActualValue, "https://automationteststore.com/index.php?rt=account/success");
		Assert.assertEquals(ActualValue.contains("success"), true);

		Thread.sleep(2000);

	}

	@Test(priority = 6, enabled = true, dependsOnMethods = { "Signup" })
	public void Logout() throws InterruptedException {

		driver.navigate().to("https://automationteststore.com/index.php?rt=account/logout");

		String ActualValue = driver.getCurrentUrl();
		Assert.assertEquals(ActualValue.contains("logout"), true);
		Thread.sleep(2000);

	}

	@Test(priority = 7, enabled = true, dependsOnMethods = { "Logout" })
	public void Login() throws InterruptedException {

		driver.navigate().to("https://automationteststore.com/index.php?rt=account/login");

		driver.findElement(By.id("loginFrm_loginname")).sendKeys(loginName);
		driver.findElement(By.id("loginFrm_password")).sendKeys(password);
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		Thread.sleep(2000);

		Assert.assertEquals(driver.getPageSource().contains(firstname.trim()), true);

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
