package by.it_academy.onliner.functional.framework.impl;

import by.it_academy.onliner.functional.framework.WebDriverCreator;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverCreator implements WebDriverCreator {

    @Override
    public WebDriver createDriver() {
        ThreadLocal<WebDriver> driver = new ThreadLocal<>();
        WebDriverManager.chromedriver().setup();
        driver.set(new ChromeDriver());
        return driver.get();
    }
}