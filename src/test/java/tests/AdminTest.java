package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.AdminPage;
import pages.LoginPage;
import utils.ExtentReportManager;
import utils.ScreenshotUtils;

public class AdminTest {
    WebDriver driver;
    LoginPage loginPage;
    AdminPage adminPage;
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
        loginPage.login("Admin", "admin123");
        adminPage = new AdminPage(driver);
    }

    @Test(priority = 1)
    public void verifyLeftMenuCount() {
        test = extent.createTest("Verify Left Menu Count");

        int count = adminPage.getLeftMenuCount();
        test.info("Left menu count: " + count);
        ScreenshotUtils.captureScreenshot(driver, "LeftMenuCount");
        test.addScreenCaptureFromPath("screenshots/LeftMenuCount.png");

        assert count == 12 : "Menu count mismatch!";
        test.pass("Menu count is 12 as expected.");

        adminPage.clickAdminTab();
        ScreenshotUtils.captureScreenshot(driver, "ClickedAdminTab");
        test.info("Clicked Admin tab").addScreenCaptureFromPath("screenshots/ClickedAdminTab.png");
    }

    @Test(priority = 2)
    public void searchByUserName() {
        test = extent.createTest("Search by Username");

        adminPage.clickAdminTab();
        test.info("Clicked Admin tab");
        ScreenshotUtils.captureScreenshot(driver, "SearchUserTab");

        adminPage.searchByUsername("Admin");
        test.info("Searched for Username: Admin");

        ScreenshotUtils.captureScreenshot(driver, "SearchByUsername");
        test.addScreenCaptureFromPath("screenshots/SearchByUsername.png");

        int result = adminPage.getResultCount();
        test.info("Records found: " + result);
        test.pass("Username search completed.");

        adminPage.resetSearch();
    }

    @Test(priority = 3)
    public void searchByUserRole() {
        test = extent.createTest("Search by User Role");

        adminPage.clickAdminTab();
        test.info("Clicked Admin tab");

        adminPage.searchByUserRole("Admin");
        ScreenshotUtils.captureScreenshot(driver, "SearchByRole");
        test.addScreenCaptureFromPath("screenshots/SearchByRole.png");

        int result = adminPage.getResultCount();
        test.info("Records found: " + result);
        test.pass("Role search completed.");

        adminPage.resetSearch();
    }

    @Test(priority = 4)
    public void searchByUserStatus() {
        test = extent.createTest("Search by Status");

        adminPage.clickAdminTab();
        test.info("Clicked Admin tab");

        adminPage.searchByStatus("Enabled");
        ScreenshotUtils.captureScreenshot(driver, "SearchByStatus");
        test.addScreenCaptureFromPath("screenshots/SearchByStatus.png");

        int result = adminPage.getResultCount();
        test.info("Records found: " + result);
        test.pass("Status search completed.");

        adminPage.resetSearch();
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
