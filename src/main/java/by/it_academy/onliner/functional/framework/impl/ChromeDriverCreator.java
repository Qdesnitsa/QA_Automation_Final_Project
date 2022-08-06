package by.it_academy.onliner.functional.framework.impl;

import by.it_academy.onliner.functional.framework.WebDriverCreator;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChromeDriverCreator implements WebDriverCreator {
    private static final Logger LOG = LoggerFactory.getLogger(ChromeDriverCreator.class);

    @Override
    public WebDriver createDriver() {
        ThreadLocal<WebDriver> driver = new ThreadLocal<>();
        WebDriverManager.chromedriver().setup();
        driver.set(new ChromeDriver());
        LOG.info("ChromeDriver was setup");
        return driver.get();
    }
}