package utils;

import com.microsoft.playwright.Page;
import config.ConfigReader;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotHelper {
    
    public static String takeScreenshot(Page page, String screenshotName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = ConfigReader.getScreenshotPath() + screenshotName + "_" + timestamp + ".png";
        
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(screenshotPath))
                .setFullPage(true));
        
        return screenshotPath;
    }

    public static byte[] takeScreenshotAsBytes(Page page) {
        return page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
    }
}
