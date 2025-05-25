package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.ScreenshotUtils;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        extent = ExtentReportManager.getReportInstance();
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return ExcelUtils.getSheetData("Sheet1");
    }

    @Test(dataProvider = "loginData")
    public void loginLogoutTest(String username, String password) {
        test = extent.createTest("Login Test - " + username);

        loginPage.login(username, password);
        ScreenshotUtils.captureScreenshot(driver, username);

        if (username.equals("Admin") && password.equals("admin123")) {
            Assert.assertTrue(homePage.isLoggedIn(), "Valid login should be successful");
           // homePage.logout();
            test.pass("Valid credentials - Test Passed");
            test.addScreenCaptureFromPath("screenshots/" + username + ".png");
            homePage.logout();
        } else {
            Assert.assertFalse(homePage.isLoggedIn(), "Invalid login should fail");
            test.fail("Invalid credentials - Login Failed as expected");
            test.addScreenCaptureFromPath("screenshots/" + username + ".png");
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
}
