package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    By profileIcon = By.className("oxd-userdropdown-name");
    By logoutBtn = By.linkText("Logout");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void logout() {
        driver.findElement(profileIcon).click();
        driver.findElement(logoutBtn).click();
    }

    public boolean isLoggedIn() {
        return driver.findElements(profileIcon).size() > 0;
    }
}
