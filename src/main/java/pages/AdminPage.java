package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AdminPage {
    WebDriver driver;
    WebDriverWait wait;

    By leftMenuOptions = By.cssSelector("ul.oxd-main-menu > li");
    By adminTab = By.xpath("//span[text()='Admin']");
    By usernameField = By.xpath("//label[text()='Username']/../following-sibling::div/input");
    By roleDropdown = By.xpath("(//div[@class='oxd-select-text--after'])[1]");
    By statusDropdown = By.xpath("(//div[@class='oxd-select-text--after'])[2]");
    By searchBtn = By.xpath("//button[@type='submit']");
    By resetBtn = By.xpath("(//button[@type='button'])[5]");
    By resultCount = By.cssSelector(".oxd-table-card");

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public int getLeftMenuCount() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(leftMenuOptions));
        return driver.findElements(leftMenuOptions).size();
    }

    public void clickAdminTab() {
        wait.until(ExpectedConditions.elementToBeClickable(adminTab)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
    }

    public void searchByUsername(String uname) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(uname);
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(resultCount));
    }

    public void searchByUserRole(String roleText) {
        wait.until(ExpectedConditions.elementToBeClickable(roleDropdown)).click();
        By roleOption = By.xpath("//div[@role='listbox']//span[text()='" + roleText + "']");
        wait.until(ExpectedConditions.elementToBeClickable(roleOption)).click();
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(resultCount));
    }

    public void searchByStatus(String statusText) {
        wait.until(ExpectedConditions.elementToBeClickable(statusDropdown)).click();
        By statusOption = By.xpath("//div[@role='listbox']//span[text()='" + statusText + "']");
        wait.until(ExpectedConditions.elementToBeClickable(statusOption)).click();
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(resultCount));
    }

    public int getResultCount() {
        List<WebElement> results = driver.findElements(resultCount);
        return results.size();
    }

    public void resetSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(resetBtn)).click();
    }
}
