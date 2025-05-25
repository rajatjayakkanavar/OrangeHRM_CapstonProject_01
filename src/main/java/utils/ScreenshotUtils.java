package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {
    public static void captureScreenshot(WebDriver driver, String fileName) {
        try {
            // Create screenshots directory if it doesn't exist
            File screenshotDir = new File("screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            // Add timestamp to avoid overwriting
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String finalFileName = fileName + "_" + timestamp + ".png";

            // Take screenshot
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(src.toPath(), new File(screenshotDir, finalFileName).toPath());

            System.out.println("Screenshot saved: screenshots/" + finalFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
