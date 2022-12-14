package by.it_academy.onliner.functional.pageobject;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BasePage {
    private static final Logger LOG = LoggerFactory.getLogger(BasePage.class);
    private static final int DRIVER_WAIT_TIME = 30;
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebElement findElement(By by) {
        return driver.get().findElement(by);
    }

    public List<WebElement> findElements(By by) {
        return driver.get().findElements(by);
    }

    public void navigate(String url) {
        driver.get().get(url);
    }

    public WebElement waitForElementVisible(By by) {
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver.get(), DRIVER_WAIT_TIME);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            LOG.info("WebDriver could not locate the element");
            return null;
        }
    }

    public List<WebElement> waitForElementsVisible(By by) {
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver.get(), DRIVER_WAIT_TIME);
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        } catch (TimeoutException e) {
            LOG.info("WebDriver could not locate the elements");
            return Collections.emptyList();
        }
    }

    public List<String> getTextsFromWebElements(List<WebElement> webElements) {
        List<String> textFromWebElements = new ArrayList<>();
        webElements.forEach(element -> textFromWebElements.add(element.getText()));
        return textFromWebElements;
    }

    public String getTextFromWebElement(WebElement webElement) {
        return webElement.getText();
    }

    public static void closeBrowser() {
        try {
            driver.get().quit();
        } catch (Exception e) {
            LOG.info("Failed attempt to close browser");
        }
    }
}
