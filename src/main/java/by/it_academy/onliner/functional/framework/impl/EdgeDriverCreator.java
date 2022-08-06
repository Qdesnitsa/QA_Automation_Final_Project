package by.it_academy.onliner.functional.framework.impl;

import by.it_academy.onliner.functional.framework.WebDriverCreator;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EdgeDriverCreator implements WebDriverCreator {
    private static final Logger LOG = LoggerFactory.getLogger(EdgeDriverCreator.class);
    @Override
    public WebDriver createDriver() {
        ThreadLocal<WebDriver> driver = new ThreadLocal<>();
        WebDriverManager.edgedriver().setup();
        driver.set(new EdgeDriver());
        LOG.info("EdgeDriver was setup");
        return driver.get();
    }
}