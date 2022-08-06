package by.it_academy.onliner.util;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static by.it_academy.onliner.functional.pageobject.BasePage.driver;

public class ScreenshotUtil {
    private ScreenshotUtil() {
    }

    public static void takeScreenshotAndAttachToAllureReport() {
        try {
//            File screenshotAs = Screenshots.takeScreenShotAsFile();
//            Allure.addAttachment("Screenshot", FileUtils.openInputStream(screenshotAs));
            File scrFile = ((TakesScreenshot)driver.get()).getScreenshotAs(OutputType.FILE);
            //FileUtils.copyFile(scrFile, new File("src/test/java/by/it_academy/onliner/util/screenshot.png"));
            Allure.addAttachment("Screenshot", FileUtils.openInputStream(scrFile));
        } catch (IOException e) {
            //LOG.error("Error creating screenshot");
        }
    }
}
