package by.it_academy.onliner.util;

import by.it_academy.onliner.functional.framework.impl.ChromeDriverCreator;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import static by.it_academy.onliner.functional.pageobject.BasePage.driver;

public class ScreenshotUtil {
    private static final Logger LOG = LoggerFactory.getLogger(ScreenshotUtil.class);
    private ScreenshotUtil() {
    }

    public static void takeScreenshotAndAttachToAllureReport() {
        try {
            File scrFile = ((TakesScreenshot)driver.get()).getScreenshotAs(OutputType.FILE);
            Allure.addAttachment("Screenshot", FileUtils.openInputStream(scrFile));
        } catch (IOException e) {
            LOG.error("Error creating screenshot");
        }
    }
}
